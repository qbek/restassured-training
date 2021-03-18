package org.example.steps;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;

public class TaskSteps {
    public void checkIfIsListed(String taskName, long taskId, long projectId) {
        String taskQuery = String.format("find { it.id == %d }", taskId);
        String taskContentQuery = taskQuery + ".content";
        String taskProjectIdQuery = taskQuery + ".project_id";
        RestAssured
                .when()
                    .get("/tasks")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body(taskContentQuery, Matchers.equalTo(taskName))
                        .body(taskProjectIdQuery, Matchers.equalTo(projectId));
    }

    public void checkDetails(String taskName, long taskId, long projectId) {
        RestAssured
                .given()
                    .pathParam("id", taskId)
                .when()
                    .get("/tasks/{id}")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("content", Matchers.equalTo(taskName))
                        .body("project_id", Matchers.equalTo(projectId));
    }

    public long createInProject(String taskName, long projectId) {
        String payload = String.format("{ \"content\":\"%s\", \"project_id\":%d}", taskName, projectId);

        return RestAssured
                .given()
                    .body(payload)
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
}
