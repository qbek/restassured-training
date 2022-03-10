package org.example.steps;

import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.example.model.CreateProjectRequest;
import org.example.model.Example;
import org.example.model.ProjectDetailsResponse;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

public class ProjectSteps {

//    @Steps
//    ExampleSteps example;

    @Step
    public long userCreatesANewProject(String name) {

        CreateProjectRequest payload = new CreateProjectRequest(name);

//        example.sampleStep();
        ProjectDetailsResponse createdProject = SerenityRest
                .given()
                .body(payload)
                .when()
                .post("/projects")
                .then()
                .assertThat()
                    .statusCode(200)
//                    .body("name", Matchers.equalTo("duap"))
                .and()
                .extract().body().as(ProjectDetailsResponse.class);

        checkProjectName(createdProject, name);

        return createdProject.getId();
    }

    @Step("Check if created project has name: {1}")
    public void checkProjectName(ProjectDetailsResponse project, String expectedName) {
        MatcherAssert.assertThat("Project has correct name", project.getName(), Matchers.equalTo(expectedName));
    }

    @Step("User checks project details")
    public void userChecksProjectDetails(long id, String name) {
        SerenityRest
                .given()
                .pathParam("id", id)
                .when()
                .get("/projects/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo(name))
                .body("id", Matchers.equalTo(id));
    }

    @Step("User checks if project with id: {0} and name: {1} is listed with all projects")
    public void userChecksAllProjectsList(long id, String name) {
        String getNameByProjectId = String.format("find{it.id == %d}.name", id);

        //user checks id project is listed with all projects
        SerenityRest
                .given()
                .when()
                .get("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body(getNameByProjectId, Matchers.equalTo(name));
    }
}
