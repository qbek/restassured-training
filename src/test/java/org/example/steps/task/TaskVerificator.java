package org.example.steps.task;

import io.restassured.response.Response;
import org.hamcrest.Matchers;

public class TaskVerificator {
    public void verifyTaskDetails(Response response, String expectedTaskName, String expectedProjectId) {
        response.then()
            .assertThat()
                .statusCode(200)
                .body("content", Matchers.equalTo(expectedTaskName))
                .body("project_id", Matchers.equalTo(expectedProjectId));
    }

    public void verifyTaskOnTheAllTasksList(Response response, String expectedTaskId) {
        var getTaskByTaskId = String.format("find{ it.id == \"%s\" }", expectedTaskId);
        response.then()
                .assertThat()
                .statusCode(200)
                .body(getTaskByTaskId, Matchers.notNullValue());
    }
}
