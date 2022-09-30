package org.example.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.example.model.ProjectCreationPayload;
import org.example.model.ProjectDetailsResponse;
import org.hamcrest.Matchers;

public class ProjectSteps {

    private String actor;

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    @Step("#actor checks if project was added to projects list")
    public void checkIfAddedToProjectsList() {
        SerenityRest
                .given()
                .when()
                .get("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body(
                        String.format("find{ it.id == %d }.name", id),
                        Matchers.equalTo(name)
                );
    }

    @Step("#actor check project details")
    public void checkDetails() {
        ProjectDetailsResponse details = SerenityRest
                .given()
                .pathParam("id", id)
                .when()
                .get("/projects/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", Matchers.equalTo(id))
                .body("name", Matchers.equalTo(name))
                .and().extract().body().as(ProjectDetailsResponse.class);
        System.out.println(details.getId());
    }

    @Step("#actor creates a new project")
    public void create(String projectName) {
        this.name = projectName;
        Response createProjectResponse = sendPostRequest();
        verifyPostRequest(createProjectResponse);
        this.id = createProjectResponse.then().extract().path("id");
    }

    @Step("send post request with: #name")
    public Response sendPostRequest() {
        var payload = new ProjectCreationPayload(this.name);

        return SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/projects");
    }

    @Step("verify create project response: #name, #id")
    public void verifyPostRequest(Response response) {
        response.then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo(name));
    }
}
