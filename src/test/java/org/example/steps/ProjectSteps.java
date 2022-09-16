package org.example.steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.example.model.ProjectDetailsResponse;
import org.example.model.ProjectRequest;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import java.util.List;

public class ProjectSteps {

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    @Step("User checks if project is on all projects list")
    public void checkIsOnAllProjectsList() {
        List<ProjectDetailsResponse> allProjects = SerenityRest
                .given()
                .when()
                .get("/projects")
                .then()
                .assertThat()
                    .statusCode(200)
                    .body(String.format("find{ it.id == %d}.name", this.id), Matchers.equalTo(this.name))
                .and().extract().body().jsonPath().getList(".");
    }

    @Step("User check project details")
    public void checkDetails() {
        checkDetailsWithDetailedDesc();
    }

    @Step("Id should be: #id, name should be: #name")
    public void checkDetailsWithDetailedDesc() {
        SerenityRest
                .given()
                .pathParam("id", this.id)
                .when()
                .get("/projects/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", Matchers.equalTo(this.id))
                .body("name", Matchers.equalTo(this.name));
    }

    @Step("User creates new project with name '{0}'")
    public void create(String projectName) {
        ProjectRequest payload = new ProjectRequest(projectName);

        this.name = projectName;
        ProjectDetailsResponse createdProject = SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo(this.name))
                .and()
                .extract().body().as(ProjectDetailsResponse.class);
        this.id = createdProject.getId();
    }
}
