package br.com.clone.unionmangas.integrations.controller.withjson;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.clone.unionmangas.config.TestConfigs;
import br.com.clone.unionmangas.dto.author.AuthorGetDto;
import br.com.clone.unionmangas.dto.author.AuthorParamDto;
import br.com.clone.unionmangas.dto.security.AccountCredentialsDto;
import br.com.clone.unionmangas.dto.security.CreateCredentialsDto;
import br.com.clone.unionmangas.dto.security.TokenDto;
import br.com.clone.unionmangas.integrations.containers.AbstractIntegrationTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

// TODO CRIAR FORMA DE INSERIR PERMISSION NO MOMENTO DE EXECUCAO DOS TESTES
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
class AuthorControllerJsonTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static TokenDto tokenDto;
    private static AccountCredentialsDto user;

    private static AuthorGetDto authorResponse;
    private static AuthorParamDto authorParam;

    @BeforeAll
    public static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        user = new AccountCredentialsDto("teste", "teste123");
        
        authorResponse = new AuthorGetDto();
        authorParam = new AuthorParamDto();
    }

    @Test
    @Order(0)
    void createUserTeste() {
        CreateCredentialsDto credentials = new CreateCredentialsDto("teste@gmail.com", "teste", "teste123");

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FRONT)
                .setBasePath("/unionmangas/auth/create")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        final var user = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(credentials)
                .when().post().then()
                .statusCode(200);

        assertNotNull(user);
    }

    @Test
    @Order(1)
    void authorization() {
        tokenDto = given()
                .basePath("/unionmangas/auth/signin")
                .port(TestConfigs.SERVER_PORT)
                .contentType(TestConfigs.CONTENT_TYPE_XML)
                .body(user)
                .when().post().then()
                .statusCode(200)
                .extract().body().as(TokenDto.class);

        assertNotNull(tokenDto);
        // assertNotNull(tokenDto.getAccessToken());
        // assertNotNull(tokenDto.getRefreshToken());
    }

    @Test
    @Order(2)
    void refreshToken() {

        final var newToken = given()
                .basePath("/unionmangas/auth/refresh")
                .port(TestConfigs.SERVER_PORT)
                .contentType(TestConfigs.CONTENT_TYPE_XML)
                .pathParam("username", user.getUserName())
                // .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + tokenDto.getRefreshToken())
                .body(user)
                .when().put("{username}").then()
                .statusCode(200)
                .extract().body().as(TokenDto.class);

        assertNotNull(newToken);
        // assertNotNull(newToken.getAccessToken());
        // assertNotNull(newToken.getRefreshToken());
    }

    @Test
    @Order(3)
    void testCreate() {
        mockAuthor();

        var content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FRONT)
                .body(authorParam)
                .when().post().then()
                .statusCode(200)
                .extract().body().asString();
        content = this.clearContent(content);

        try {
            final var persistedAuthor = objectMapper.readValue(content, AuthorGetDto.class);

            assertNotNull(persistedAuthor);
            assertNotNull(persistedAuthor.getAge());
            assertNotNull(persistedAuthor.getName());
            assertNotNull(persistedAuthor.getPseudonym());

            assertEquals("Papa Le guas", persistedAuthor.getName());
            assertEquals("Wile E. Coyote", persistedAuthor.getPseudonym());
            assertEquals(27, persistedAuthor.getAge());

            assertTrue(persistedAuthor.getAge() > 0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    @Order(4)
    void testCreateWithWrongOrigin() {
        mockAuthor();

        var content = given().spec(specification)
                .basePath("/unionmangas/author")
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FALHA)
                .body(authorParam)
                .when().post().then()
                .statusCode(403)
                .extract().body().asString();
        content = this.clearContent(content);

        assertNotNull(content);
        assertEquals("Invalid CORS request", content);

    }

    @Test
    @Order(5)
    void testFindById() {
        mockAuthor();

        var content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FRONT)
                .pathParam("idAuthor", authorResponse.getIdAuthor())
                .when().get("{idAuthor}").then()
                .statusCode(200)
                .extract().body().asString();
        content = this.clearContent(content);

        try {
            final var persistedAuthor = objectMapper.readValue(content, AuthorGetDto.class);

            assertNotNull(persistedAuthor);
            assertNotNull(persistedAuthor.getAge());
            assertNotNull(persistedAuthor.getName());
            assertNotNull(persistedAuthor.getPseudonym());

            assertEquals("Papa Le guas", persistedAuthor.getName());
            assertEquals("Wile E. Coyote", persistedAuthor.getPseudonym());
            assertEquals(27, persistedAuthor.getAge());

            assertTrue(persistedAuthor.getAge() > 0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    @Order(6)
    void testFindByIdWithWrongOrigin() {
        mockAuthor();

        var content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FALHA)
                .pathParam("idAuthor", authorResponse.getIdAuthor())
                .when().get("{idAuthor}").then()
                .statusCode(403)
                .extract().body().asString();
        content = this.clearContent(content);

        assertNotNull(content);
        assertEquals("Invalid CORS request", content);

    }

    private void mockAuthor() {
        authorResponse.setIdAuthor(1L);
        authorResponse.setAge(27);
        authorResponse.setName("Papa Le guas");
        authorResponse.setPseudonym("Wile E. Coyote");

        authorParam.setName("Papa Le guas");
        authorParam.setPseudonym("Wile E. Coyote");
        authorParam.setBirthdate(LocalDate.of(1995, 07, 07));
    }

    // TEST FAILED DUE TO DETAILED CONTROLLER RESPONSE
    // CORRECTION MADE BY REMOVING THE DETAILED MESSAGE
    private String clearContent(String content) {
        content = content.replace("{\"message\":\"Operation performed successfully\",\"success\":true,\"data\":", "");
        content = content.replace("}]}}", "}");
        return content;
    }

}
