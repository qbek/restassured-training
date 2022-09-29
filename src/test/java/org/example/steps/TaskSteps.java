package org.example.steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;

public class TaskSteps {

    private long id;
    private String name;
    private long projectId;


    public void userChecksIfTaskWasAddedToAllTasksList() {
        RestAssured
                .given()
                .when()
                .get("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .body(
                        String.format("find{ it.id == %d }.content", id),
                        Matchers.equalTo(name)
                );
    }

    public void userChecksTaskDetails() {
        RestAssured
                .given()
                .pathParam("id", id)
                .when()
                .get("/tasks/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("project_id", Matchers.equalTo(projectId))
                .body("content", Matchers.equalTo(name))
                .body("id", Matchers.equalTo(id));

    }

    public void userAddsNewTaskToTheProject(String taskName, long projectId) {
        this.name = taskName;
        this.projectId = projectId;

        this.id = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(
                        String.format("{\"content\": \"%s\", \"project_id\": %d}", name, this.projectId)
                )
                .when()
                .post("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .body("project_id", Matchers.equalTo(this.projectId))
                .body("content", Matchers.equalTo(name))
                .and().extract().path("id");
    }

}
