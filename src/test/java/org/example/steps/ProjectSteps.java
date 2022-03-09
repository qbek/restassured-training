package org.example.steps;

import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;

public class ProjectSteps {

//    @Steps
//    ExampleSteps example;

    @Step
    public long userCreatesANewProject(String name) {

//        example.sampleStep();
        long projectId = SerenityRest
                .given()
                .body(String.format("{\"name\": \"%s\"}", name))
                .when()
                .post("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo(name))
                .and()
                .extract().path("id");
        return projectId;
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
