package org.example.steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;

public class TaskSteps {

    private long id;
    private String name;

    public void checkIsOnAllTasksList() {
        RestAssured
                .given()
                .when()
                .get("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .body(String.format("find{ it.id == %d }.content", this.id), Matchers.equalTo(this.name));
    }

    public void checkDetails() {
        RestAssured
                .given()
                .pathParam("id", this.id)
                .when()
                .get("/tasks/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("content", Matchers.equalTo(this.name));
    }

    public void addToTheProject(String taskName, long projectId) {
        this.name = taskName;
        this.id = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(
                        String.format("{ \"content\": \"%s\", \"project_id\": %d}", this.name, projectId)
                )
                .when()
                .post("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .body("content", Matchers.equalTo(this.name))
                .body("project_id", Matchers.equalTo(projectId))
                .and()
                .extract().path("id");
    }
}
