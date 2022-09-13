package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class ProjectCreationTest {

    @Test
    public void userCanCreateAProject() {
        long projectId = RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v1")
                    .header("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                    .contentType(ContentType.JSON)
                    .body("{ \"name\" : \"Szkolenie RestAsurred\"}")
                    .log().all()
                .when()
                    .post("/projects")
                .then()
                    .log().all()
                    .assertThat()
                        .statusCode(200)
                        .body("name", Matchers.equalTo("Szkolenie RestAsurred"))
                    .and()
                        .extract().path("id");

        RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v1")
                    .header("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                    .pathParam("id", projectId)
                    .log().all()
                .when()
                    .get("/projects/{id}")
                .then()
                    .log().all()
                    .assertThat()
                        .statusCode(200)
                        .body("id", Matchers.equalTo(projectId))
                        .body("name", Matchers.equalTo("Szkolenie RestAsurred"));

        RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v1")
                    .header("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                    .log().all()
                .when()
                    .get("/projects")
                .then()
                .log().all()
                    .assertThat()
                        .statusCode(200)
                        .body(String.format("find{ it.id == %d}.name", projectId), Matchers.equalTo("Szkolenie RestAsurred"));

    }
}
