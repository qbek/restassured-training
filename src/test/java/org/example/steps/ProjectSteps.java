package org.example.steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;

import static java.lang.String.format;

public class ProjectSteps {

    public void userChecksAllProjectsList(String projectId, String projectName) {
        RestAssured
                .given()
                .when()
                .get("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body(
                        format("find{ it.id == \"%s\" }.name", projectId),
                        Matchers.equalTo(projectName)
                );
    }

    public void userChecksProjectDetails(String projectId, String projectName) {
        RestAssured
                .given()
                .pathParam("id", projectId)
                .when()
                .get("/projects/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo(projectName))
                .body("id", Matchers.equalTo(projectId));
    }

    public String userCreatesANewProject(String projectName) {
        String projectId = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(String.format("{\"name\": \"%s\"}", projectName))
                //                    .body( "{\"name\": \"" + projectName + "\"}")`
                .when()
                .post("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo(projectName))
                .header("Content-Type", Matchers.equalTo("application/json"))
                .and()
                .extract().path("id");
        return projectId;
    }
}
