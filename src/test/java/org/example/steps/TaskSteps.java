package org.example.steps;

import io.cucumber.java.en_lol.AN;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

public class TaskSteps {

    @Step
    public long userAddsTaskToTheProject(String taskName, long projectId) {
        return SerenityRest
                .given()
                .body(
                        format("{\"content\": \"%s\", \"project_id\":%d}", taskName, projectId)
                )
                .when()
                .post("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .body("content", equalTo(taskName))
                .body("project_id", equalTo(projectId))
                .and()
                .extract().path("id");
    }

    @Step
    public void userChecksTaskDetails(long taskId, String taskName, long projectId) {
        SerenityRest
                .given()
                    .pathParam("id", taskId)
                .when()
                    .get("/tasks/{id}")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("content", equalTo(taskName))
                        .body("project_id", equalTo(projectId));
    }

    @Step
    public void userChecksIfTaskIsListedWithAllTasks(long taskId, String taskName, long projectId) {
        SerenityRest
                .given()
                .when()
                    .get("/tasks")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body(
                                format("find{ it.id == %d }.content", taskId),
                                equalTo(taskName)
                        )
                        .body(
                                format("find{ it.id == %d }.project_id", taskId),
                                equalTo(projectId)
                        );
    }
}
