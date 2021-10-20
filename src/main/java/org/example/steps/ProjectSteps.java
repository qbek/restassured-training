package org.example.steps;

import com.github.javafaker.Faker;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.example.data.DataGenerator;
import org.example.data.DataGeneratorInterface;
import org.example.data.RandomDataGenerator;
import org.example.data.StaticDataGenerator;
import org.example.model.ProjectRequestPayload;
import org.example.model.ProjectResponsePayload;

import static java.lang.String.format;
import static org.example.data.DataGenerator.getProjectData;
import static org.hamcrest.Matchers.equalTo;

public class ProjectSteps {

    private ProjectRequestPayload project;
    private ProjectResponsePayload response;

    @Step
    public void userCreatesAProject() {
        project = getProjectData();
        response =  SerenityRest
                .given()
                .body(project)
                .when()
                .post("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(project.getName()))
                .header("Content-Type", equalTo("application/json"))
                .and()
                .extract().body().as(ProjectResponsePayload.class);
    }

    @Step
    public void userChecksProjectDetails() {
        SerenityRest
                .given()
                .pathParam("id", response.getId())
                .when()
                .get("/projects/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(project.getName()));
    }

    @Step
    public void userChecksIfProjectIsListedWithAllProjects() {
        SerenityRest
                .given()
                .when()
                    .get("/projects")
                .then()
                .assertThat()
                .body(
                        format("find{ it.id == %d }.name", response.getId()),
                        equalTo(project.getName())
                );
    }

    public long getId() {
        return response.getId();
    }
}
