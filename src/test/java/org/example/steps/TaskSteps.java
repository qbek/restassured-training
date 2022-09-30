package org.example.steps;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;

public class TaskSteps {

    private long id;
    private String name;
    private long projectId;

    @Step
    public void userChecksIfTaskWasAddedToAllTasksList() {
        SerenityRest
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

    @Step
    public void userChecksTaskDetails() {
        SerenityRest
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

    @Step
    public void userAddsNewTaskToTheProject(String taskName, long projectId) {
        this.name = taskName;
        this.projectId = projectId;

        this.id = SerenityRest
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
