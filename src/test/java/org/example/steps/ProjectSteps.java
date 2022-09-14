package org.example.steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;

public class ProjectSteps {

    public void checkIfProjectIsOnAllProjectsList(long projectId, String projectName) {
        RestAssured
                .given()
                .when()
                .get("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body(String.format("find{ it.id == %d}.name", projectId), Matchers.equalTo(projectName));
    }

    public void checkProjectDetails(long projectId, String projectName) {
        RestAssured
                .given()
                .pathParam("id", projectId)
                .when()
                .get("/projects/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", Matchers.equalTo(projectId))
                .body("name", Matchers.equalTo(projectName));
    }

    public long createNewProject(String projectName) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(String.format("{ \"name\" : \"%s\"}", projectName))
                .when()
                .post("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo(projectName))
                .and()
                .extract().path("id");
    }
}
