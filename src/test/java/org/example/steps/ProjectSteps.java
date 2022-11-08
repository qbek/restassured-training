package org.example.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.example.model.NewProjectPayload;
import org.example.model.ProjectDetailsPayload;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import static java.lang.String.format;

public class ProjectSteps {

    @Step("User checks all projects list")
    public void userChecksAllProjectsList(String projectId, String projectName) {
        var response = sendGetAllProjectsRequest();
        verifyGetAllProjectsResponse(response, projectId, projectName);
    }

    @Step("Verify project list. Project exitst on the list: id '{1}', name '{2}'")
    public void verifyGetAllProjectsResponse(Response response, String projectId, String projectName) {
        response.then()
                .assertThat()
                .statusCode(200)
                .body(
                        format("find{ it.id == \"%s\" }.name", projectId),
                        Matchers.equalTo(projectName)
                );
    }

    @Step
    public Response sendGetAllProjectsRequest() {
        return SerenityRest
                .given()
                .when()
                .get("/projects");
    }

    @Step("User checks project details")
    public void userChecksProjectDetails(String projectId, String projectName) {
        var response = sendGetProjectDetailsRequest(projectId);
        verifyProjectDetailsResponse(response, projectId, projectName);
    }

    @Step("Verify project details: expected id '{1}', expected name '{2}'")
    public void verifyProjectDetailsResponse(Response response, String projectId, String projectName) {
        response.then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo(projectName))
                .body("id", Matchers.equalTo(projectId));
    }

    @Step
    public Response sendGetProjectDetailsRequest(String projectId) {
        return SerenityRest
                .given()
                .pathParam("id", projectId)
                .when()
                .get("/projects/{id}");
    }

    @Step("User creates a new project")
    public String userCreatesANewProject(String projectName) {
        var response = sendCreateNewProjectRequest(projectName);
        var projectId = verifyProjectCreateResponse(response, projectName);
        return projectId;
    }

    @Step("Verification of create project response. Expect name: {1}")
    public String verifyProjectCreateResponse(Response response, String projectName) {
        var projectDetails = response.then()
                .assertThat()
                    .statusCode(200)
                    .header("Content-Type", Matchers.equalTo("application/json"))
                .and()
                .extract().body().as(ProjectDetailsPayload.class);

        MatcherAssert.assertThat("Project should have correct name",
                projectDetails.getName(),
                Matchers.equalTo(projectName));

        return projectDetails.getId();
    }

    @Step
    public Response sendCreateNewProjectRequest(String projectName) {
        var payload = new NewProjectPayload(projectName);
        return SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/projects");
    }
}
