package org.example.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.example.model.CreateProjectRequest;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

public class ProjectSteps {

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    @Step("User checks if project is added to 'Project list'")
    public void checkIfAddedToProjectList() {
        SerenityRest
                .given()
                .when()
                    .get("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body(format("find{ it.id == %d }.name", id), equalTo(name));
    }


    @Step("User checks if project was correctly created")
    public void checkDetails() {
        SerenityRest
                .given()
                    .pathParam("id", id)
                .when()
                    .get("/projects/{id}")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("id", equalTo(id))
                        .body("name", equalTo(name));
    }

    @Step("User creates a new project with name: {0} ")
    public void create(String projectName) throws JsonProcessingException {
        name = projectName;
        CreateProjectRequest payload = new CreateProjectRequest(name);

        id = SerenityRest
                .given()
                    .body(payload)
                .when()
                    .post("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("name", equalTo(name))
                    .and()
                        .extract().path("id");
    }
}
