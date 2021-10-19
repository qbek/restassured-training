package org.example.steps;

import io.restassured.RestAssured;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

public class ProjectSteps {

    public long userCreatesAProject(String projectName) {
        return RestAssured
                .given()
                .body(
                        format("{\"name\": \"%s\"}", projectName)
                )
                .when()
                .post("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(projectName))
                .header("Content-Type", equalTo("application/json"))
                .and()
                .extract().path("id");
    }

    public void userChecksProjectDetails(long projectId, String projectName) {
        RestAssured
                .given()
                .pathParam("id", projectId)
                .when()
                .get("/projects/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(projectName));
    }

    public void userChecksIfProjectIsListedWithAllProjects(long projectId, String projectName) {
        RestAssured
                .when()
                .get("/projects")
                .then()
                .assertThat()
                .body(
                        format("find{ it.id == %d }.name", projectId),
                        equalTo(projectName)
                );
    }
}
