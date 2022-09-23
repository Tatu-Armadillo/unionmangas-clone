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
import br.com.clone.unionmangas.integrations.containers.AbstractIntegrationTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
class AuthorControllerJsonTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;

    private static AuthorGetDto authorResponse;
    private static AuthorParamDto authorParam;

    @BeforeAll
    public static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        authorResponse = new AuthorGetDto();
        authorParam = new AuthorParamDto();
    }

    @Test
    @Order(1)
    void testCreate() {
        mockAuthor();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FRONT)
                .setBasePath("/unionmangas/author")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
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
    @Order(2)
    void testCreateWithWrongOrigin() {
        mockAuthor();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FALHA)
                .setBasePath("/unionmangas/author")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(authorParam)
                .when().post().then()
                .statusCode(403)
                .extract().body().asString();
        content = this.clearContent(content);

        assertNotNull(content);
        assertEquals("Invalid CORS request", content);

    }

    @Test
    @Order(3)
    void testFindById() {
        mockAuthor();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FRONT)
                .setBasePath("/unionmangas/author")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
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
    @Order(4)
    void testFindByIdWithWrongOrigin() {
        mockAuthor();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FALHA)
                .setBasePath("/unionmangas/author")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
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
        content = content.replace("{\"message\":\"Operação realizada com sucesso.\",\"sucess\":true,\"data\":", "");
        content = content.replace("}]}}", "}");
        return content;
    }

}
