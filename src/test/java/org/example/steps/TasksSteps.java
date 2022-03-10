package org.example.steps;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.example.model.CreateTaskRequest;
import org.example.model.TaskDetailsResponse;
import org.hamcrest.Matchers;

public class TasksSteps {

    @Step
    public long userAddsTaskToTheProject(String name, long projectId) {

        CreateTaskRequest payload = new CreateTaskRequest(name, projectId);


        TaskDetailsResponse createdTask =  SerenityRest
                .given()
                .body(payload)
                .when()
                .post("/tasks")
                .then()
                .assertThat()
                    .statusCode(200)
                    .body("content", Matchers.equalTo(name))
                    .body("project_id", Matchers.equalTo(projectId))
                .and()
                    .extract().body().as(TaskDetailsResponse.class);

        return createdTask.getId();
    }

    @Step
    public void userChecksTaskDetails(long id, String name) {
        SerenityRest
                .given()
                .pathParam("id", id)
                .when()
                .get("/tasks/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("content", Matchers.equalTo(name));
    }

    @Step
    public void userChecksAllTasksList(long id, String name) {
        String getNameByTaskId = String.format("find{it.id == %d}.content", id);
        SerenityRest
                .given()
                .when()
                .get("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .body(getNameByTaskId, Matchers.equalTo(name));
    }
}
