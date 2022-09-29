package org.example.steps;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;

public class ProjectSteps {

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    @Step
    public void userChecksIfProjectWasAddedToAllProjectList() {
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

    @Step
    public void userCheckProjectDetails() {
        SerenityRest
                .given()
                .pathParam("id", id)
                .when()
                .get("/projects/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", Matchers.equalTo(id))
                .body("name", Matchers.equalTo(name));
    }

    @Step
    public void userCreatesANewProject(String projectName) {
        this.name = projectName;
        this.id =  SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .body(String.format("{\"name\": \"%s\"}", name))
                .when()
                .post("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo(name))
                .and().extract().path("id");
    }
}
