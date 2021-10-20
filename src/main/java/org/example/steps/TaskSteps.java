package org.example.steps;

import io.cucumber.java.en_lol.AN;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Shared;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.example.data.DataGenerator;
import org.example.model.TaskRequestPayload;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

public class TaskSteps {

    private TaskRequestPayload task;
    private long taskId;

    @Steps(shared = true)
    ProjectSteps project;

    @Step
    public void userAddsTaskToTheProject() {
        task = DataGenerator.getTaskData();
        task.setProject_id(project.getId());
        taskId = SerenityRest
                .given()
                .body(task)
                .when()
                .post("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .body("content", equalTo(task.getContent()))
                .body("project_id", equalTo(project.getId()))
                .and()
                .extract().path("id");
    }

    @Step
    public void userChecksTaskDetails() {
        SerenityRest
                .given()
                    .pathParam("id", taskId)
                .when()
                    .get("/tasks/{id}")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("content", equalTo(task.getContent()))
                        .body("project_id", equalTo(project.getId()));
    }

    @Step
    public void userChecksIfTaskIsListedWithAllTasks() {
        SerenityRest
                .given()
                .when()
                    .get("/tasks")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body(
                                format("find{ it.id == %d }.content", taskId),
                                equalTo(task.getContent())
                        )
                        .body(
                                format("find{ it.id == %d }.project_id", taskId),
                                equalTo(project.getId())
                        );
    }
}
