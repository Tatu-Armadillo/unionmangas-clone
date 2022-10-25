package br.com.clone.unionmangas.integrations.controller.withxml;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.*;

import br.com.clone.unionmangas.config.TestConfigs;
import br.com.clone.unionmangas.dto.security.*;
import br.com.clone.unionmangas.integrations.containers.AbstractIntegrationTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.*;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
class AuthControllerXmlTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static TokenDto tokenDto;
    private static AccountCredentialsDto user;

    @BeforeAll
    public static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        user = new AccountCredentialsDto("teste", "teste123");
    }

    @Test
    @Order(0)
    void createUserTeste() {
        CreateCredentialsDto credentials = new CreateCredentialsDto("teste", "teste123", false);

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FRONT)
                .setBasePath("/unionmangas/auth/create")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        final var user = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_XML)
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
        assertNotNull(tokenDto.getAccessToken());
        assertNotNull(tokenDto.getRefreshToken());
    }

    @Test
    @Order(2)
    void refreshToken() {

        final var newToken = given()
                .basePath("/unionmangas/auth/refresh")
                .port(TestConfigs.SERVER_PORT)
                .contentType(TestConfigs.CONTENT_TYPE_XML)
                .pathParam("username", user.getUserName())
                .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + tokenDto.getRefreshToken())
                .body(user)
                .when().put("{username}").then()
                .statusCode(200)
                .extract().body().as(TokenDto.class);

        assertNotNull(newToken);
        assertNotNull(newToken.getAccessToken());
        assertNotNull(newToken.getRefreshToken());
    }

}
