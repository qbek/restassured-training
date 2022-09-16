package org.example.steps;


import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.example.model.TaskRequest;
import org.hamcrest.Matchers;


public class TaskSteps {

    private long id;
    private String name;

    @Step("User checks if task is on all tasks list")
    public void checkIsOnAllTasksList() {
        SerenityRest
                .given()
                .when()
                .get("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .body(String.format("find{ it.id == %d }.content", this.id), Matchers.equalTo(this.name));
    }

    @Step("User checks task details")
    public void checkDetails() {
        SerenityRest
                .given()
                .pathParam("id", this.id)
                .when()
                .get("/tasks/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("content", Matchers.equalTo(this.name));
    }

    @Step("User add task '{0}' to project with id: {1}")
    public void addToTheProject(String taskName, long projectId) {
        TaskRequest payload = new TaskRequest(taskName);
        payload.setProject_id(projectId);

        this.name = taskName;
        this.id = SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .body(payload)
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
