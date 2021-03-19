package org.example.steps.rest;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.example.model.ProjectRequest;
import org.example.model.ProjectResponse;
import org.hamcrest.Matchers;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class ProjectRestSteps {

    @Step
    public void verifyGetAllProjectsResponse(ProjectResponse expected) {
        List<ProjectResponse> actual = SerenityRest
                .then()
                .assertThat()
                    .statusCode(200)
                .and().extract().body().jsonPath().getList(".", ProjectResponse.class);

        assertThat("Project is on the list", actual, Matchers.hasItem(Matchers.samePropertyValuesAs(expected)));
        long count = actual.stream().filter(project -> project.getId() == expected.getId()).count();
        assertThat("There is only on project", count, Matchers.equalTo((long) 1));
    }

    @Step
    public void sendGetAllProjectsRequest() {
        SerenityRest
                .given()
                .when()
                .get("/projects");
    }

    @Step
    public void verifyProjectDetailsResponse(ProjectResponse expected) {
        ProjectResponse actual = SerenityRest.then()
                .assertThat()
                .statusCode(200)
                .and().extract().body().as(ProjectResponse.class);

        assertThat("Projects are equal", actual, Matchers.samePropertyValuesAs(expected));
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
    public void sendCreateProjectRequest(ProjectRequest projectRequest) {
        SerenityRest
                .given()
                .body(projectRequest)
                .when()
                .post("/projects");
    }

    public ProjectResponse getProjectId() {
        return SerenityRest
                .then().extract().body().as(ProjectResponse.class);
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
