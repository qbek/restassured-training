package org.example.steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;

import static java.lang.String.format;

public class TaskSteps {

    public String addTaskToTheProject(String taskName, String projectId) {
        return RestAssured
                .given()
                .body(
                        format("{ \"content\": \"%s\", \"project_id\": \"%s\"}", taskName, projectId)
                )
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

    public void checkIfTaskIsOnAllTasksList(String taskId, String taskName) {
        RestAssured
                .given()
                .when()
                .get("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .body(format("find{ it.id == \"%s\" }.content", taskId), Matchers.equalTo(taskName));
    }

    public void checkIfTaskIsCreated(String taskId, String taskName) {
        RestAssured
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
