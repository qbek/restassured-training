package org.example.steps;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.example.data.DataGenerator;
import org.example.model.CreateTaskRequest;
import org.example.model.TaskDetailsResponse;
import org.hamcrest.Matchers;

public class TasksSteps {

    private String taskName;
    private long taskId;

    private DataGenerator data = new DataGenerator();

    @Steps(shared = true)
    ProjectSteps preconditions;


    @Step
    public void userAddsTaskToTheProject() {
        taskName = data.getDataGenerator().getTaskName();
        CreateTaskRequest payload = new CreateTaskRequest(taskName, preconditions.getProjectId());
        TaskDetailsResponse createdTask =  SerenityRest
                .given()
                .body(payload)
                .when()
                .post("/tasks")
                .then()
                .assertThat()
                    .statusCode(200)
                    .body("content", Matchers.equalTo(taskName))
                    .body("project_id", Matchers.equalTo(preconditions.getProjectId()))
                .and()
                    .extract().body().as(TaskDetailsResponse.class);

        taskId =  createdTask.getId();
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
                .body("content", Matchers.equalTo(taskName));
    }

    @Step
    public void userChecksAllTasksList() {
        String getNameByTaskId = String.format("find{it.id == %d}.content", taskId);
        SerenityRest
                .given()
                .when()
                .get("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .body(getNameByTaskId, Matchers.equalTo(taskName));
    }
}
