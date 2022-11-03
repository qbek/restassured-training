package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;

public class ProjectCreationTests {

    @Test
    public void userCanCreateANewProject() {
        var projectId = RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v2")
                    .header("Authorization", "Bearer  d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                    .contentType(ContentType.JSON)
                    .body("{\"name\": \"Szkolenie Rest API\"}")
                    .log().all()
                .when()
                    .post("/projects")
                .then()
                    .log().all()
                    .assertThat()
                        .statusCode(200)
                        .body("name", Matchers.equalTo("Szkolenie Rest API"))
                        .header("Content-Type", Matchers.equalTo("application/json"))
                    .and()
                        .extract().path("id");

            RestAssured
                    .given()
                        .baseUri("https://api.todoist.com")
                        .basePath("/rest/v2")
                        .header("Authorization", "Bearer  d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                        .pathParam("id", projectId)
                        .log().all()
                    .when()
                        .get("/projects/{id}")
                    .then()
                        .log().all()
                        .assertThat()
                            .statusCode(200)
                            .body("name", Matchers.equalTo("Szkolenie Rest API"))
                            .body("id", Matchers.equalTo(projectId));


        RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v2")
                    .header("Authorization", "Bearer  d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                    .log().all()
                .when()
                    .get("/projects")
                .then()
                    .log().all()
                    .assertThat()
                        .statusCode(200)
                        .body(
                                format("find{ it.id == \"%s\" }.name", projectId),
                                Matchers.equalTo("Szkolenie Rest API")
                        );

    }

}
