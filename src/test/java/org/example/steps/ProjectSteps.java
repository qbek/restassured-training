package org.example.steps;

import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.example.data.DataGenerator;
import org.example.model.CreateProjectRequest;
import org.example.model.Example;
import org.example.model.ProjectDetailsResponse;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import java.util.List;

public class ProjectSteps {

//    @Steps
//    ExampleSteps example;

    private String projectName;
    private long projectId;

    private DataGenerator data = new DataGenerator();

    public long getProjectId() {
        return projectId;
    }

    @Step
    public void userCreatesANewProject() {
        projectName = data.getDataGenerator().getProjectName();
        CreateProjectRequest payload = new CreateProjectRequest(projectName);
        ProjectDetailsResponse createdProject = SerenityRest
                .given()
                    .body(payload)
                .when()
                    .post("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                    .and()
                        .extract().body().as(ProjectDetailsResponse.class);
        checkProjectName(createdProject);
        projectId = createdProject.getId();
    }

    @Step("Check if created project has name: $projectName")
    public void checkProjectName(ProjectDetailsResponse project) {
        MatcherAssert.assertThat("Project has correct name", project.getName(), Matchers.equalTo(projectName));
    }

    @Step("User checks project details")
    public void userChecksProjectDetails() {
        SerenityRest
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

    @Step("User checks if project with id: {0} and name: {1} is listed with all projects")
    public void userChecksAllProjectsList() {
        String getNameByProjectId = String.format("find{it.id == %d}.name", projectId);

        //user checks id project is listed with all projects
        List<ProjectDetailsResponse> projects = SerenityRest
                .given()
                .when()
                    .get("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body(getNameByProjectId, Matchers.equalTo(projectName))
                    .and()
                        .extract().body().jsonPath().getList(".", ProjectDetailsResponse.class);
    }
}
