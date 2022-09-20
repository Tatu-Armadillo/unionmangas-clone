package br.com.clone.unionmangas.integrations.swagger;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.clone.unionmangas.config.TestConfigs;
import br.com.clone.unionmangas.integrations.containers.AbstractIntegrationTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
    void shouldDisplaySwaggerUiPage() {
        var content = given()
                .basePath("/unionmangas/swagger-ui/index.html")
                .port(TestConfigs.SERVER_PORT)
                .when().get().then()
                .statusCode(200)
                .extract().body().asString();
        assertTrue(content.contains("Swagger UI"));
    }

}
