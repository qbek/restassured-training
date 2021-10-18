package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class ProjectCreationTest {

    @Test
    public void user_can_create_a_project() {
        long projectId = RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v1")
                    .header("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
//                    .header("Content-type", "application/json" )
                    .contentType(ContentType.JSON)
                    .body("{\"name\": \"Szkolenie Rest API\"}")
                .when()
                    .post("/projects")
                .then()
                    .log().all()
                    .assertThat()
                        .statusCode(200)
                        .body("name", equalTo("Szkolenie Rest API"))
                        .header("Content-Type", equalTo("application/json"))
                    .and()
                        .extract().path("id");

        RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v1")
                    .header("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                    .contentType(ContentType.JSON)
                    .pathParam("id", projectId)
                    .log().all()
                .when()
                    .get("/projects/{id}")
                .then()
                    .log().all()
                    .assertThat()
                        .statusCode(200)
                        .body("name", equalTo("Szkolenie Rest API"));

        RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v1")
                    .header("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                    .contentType(ContentType.JSON)
                .when()
                    .get("/projects")
                .then()
                    .log().all()
                    .assertThat()
                        .body(
                                String.format("find{ it.id == %d }.name", projectId),
                                equalTo("Szkolenie Rest API")
                        );

    }
}
