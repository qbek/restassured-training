package org.example.steps;

import io.restassured.RestAssured;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

public class TaskSteps {

    public long userAddsTaskToTheProject(String taskName, long projectId) {
        return RestAssured
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

    public void userChecksTaskDetails(long taskId, String taskName, long projectId) {
        RestAssured
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

    public void userChecksIfTaskIsListedWithAllTasks(long taskId, String taskName, long projectId) {
        RestAssured
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
