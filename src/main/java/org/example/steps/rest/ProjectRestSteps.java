package org.example.steps.rest;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.example.model.Project;
import org.hamcrest.Matchers;

public class ProjectRestSteps {

    @Step
    public void verifyGetAllProjectsResponse(long projectId, String name) {
        String projectNameQuery = String.format("find { it.id == %d }.name", projectId);
        SerenityRest
                .then()
                .assertThat()
                .statusCode(200)
                .body(projectNameQuery, Matchers.equalTo(name));
    }

    @Step
    public void sendGetAllProjectsRequest() {
        SerenityRest
                .given()
                .when()
                .get("/projects");
    }

    @Step
    public void verifyProjectDetailsResponse(long projectId, String name) {
        SerenityRest.then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo(name))
                .body("id", Matchers.equalTo(projectId));
    }

    @Step
    public void sendGetProjectDetailsRequest(long projectId) {
        SerenityRest
                .given()
                .pathParam("id", projectId)
                .when()
                .get("/projects/{id}");
    }

    @Step
    public void verifyCreateProjectResponse(String name) {
        verifyStatusCode(200);
        verifyProjectName(name);
    }

    @Step
    public void verifyProjectName(String name) {
        SerenityRest
                .then()
                .assertThat()
                .body("name", Matchers.equalTo(name));
    }

    @Step
    public void verifyStatusCode(int expectedStatus) {
        SerenityRest
                .then()
                .assertThat()
                .statusCode(expectedStatus);
    }

    @Step
    public void sendCreateProjectRequest(Project project) {
        SerenityRest
                .given()
                .body(project)
                .when()
                .post("/projects");
    }

    public long getProjectId() {
        return SerenityRest
                .then().extract().path("id");
    }

    @Step
    public void deleteProject(long projectId) {
        SerenityRest
                .given()
                .pathParam("id", projectId)
                .when().delete("/projects/{id}")
                .then()
                .assertThat()
                .statusCode(204);
    }
}
