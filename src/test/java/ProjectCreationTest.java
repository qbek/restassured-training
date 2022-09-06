import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;


public class ProjectCreationTest {



    @BeforeAll
    public static void setup() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.requestSpecification = builder
                .setBaseUri("https://api.todoist.com")
                .setBasePath("/rest/v1")
                .addHeader("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                .setContentType(ContentType.JSON)
                .build();
    }
    @Test
    public void userCanCreateAProject() {
        long projectId = RestAssured
                .given()
                    .body("{\"name\": \"Szkolenie RestAssured\"}")
                .when()
                    .post("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("name", equalTo("Szkolenie RestAssured"))
                    .and()
                        .extract().path("id");

        RestAssured
                .given()
                    .pathParam("id", projectId)
                .when()
                    .get("/projects/{id}")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("id", equalTo(projectId))
                        .body("name", equalTo("Szkolenie RestAssured"));

        RestAssured
                .given()
                .when()
                    .get("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body(format("find{ it.id == %d }.name", projectId), equalTo("Szkolenie RestAssured"));

    }

}
