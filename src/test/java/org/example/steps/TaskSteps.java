package org.example.steps;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.example.model.NewTaskPayload;
import org.hamcrest.Matchers;

import static java.lang.String.format;

public class TaskSteps {

    @Step
    public String addTaskToTheProject(String taskName, String projectId) {
        var payload = new NewTaskPayload(taskName);
        payload.setProject_id(projectId);

        return SerenityRest
                .given()
                .body(payload)
                .contentType(ContentType.JSON)
                .when()
                .post("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .body("content", Matchers.equalTo(taskName))
                .body("project_id", Matchers.equalTo(projectId))
                .and()
                .extract().path("id");
    }

    @Step
    public void checkIfTaskIsOnAllTasksList(String taskId, String taskName) {
        SerenityRest
                .given()
                .when()
                .get("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .body(format("find{ it.id == \"%s\" }.content", taskId), Matchers.equalTo(taskName));
    }

    @Step
    public void checkIfTaskIsCreated(String taskId, String taskName) {
        SerenityRest
                .given()
                .pathParam("id", taskId)
                .when()
                .get("/tasks/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("content", Matchers.equalTo(taskName))
                .body("id", Matchers.equalTo(taskId));
    }
}
