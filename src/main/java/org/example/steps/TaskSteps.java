package org.example.steps;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.example.model.Task;
import org.hamcrest.Matchers;

public class TaskSteps {

    private long taskId;
    private long projectId;
    private String name;

    @Step("Adam checks if task '#name' is on all tasks list")
    public void checkIfIsListed() {
        String taskQuery = String.format("find { it.id == %d }", taskId);
        String taskContentQuery = taskQuery + ".content";
        String taskProjectIdQuery = taskQuery + ".project_id";
        SerenityRest
                .given()
                .when()
                    .get("/tasks")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body(taskContentQuery, Matchers.equalTo(name))
                        .body(taskProjectIdQuery, Matchers.equalTo(projectId));
    }

    @Step("Adam checks '#name' task details")
    public void checkDetails() {
        SerenityRest
                .given()
                    .pathParam("id", taskId)
                .when()
                    .get("/tasks/{id}")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("content", Matchers.equalTo(name))
                        .body("project_id", Matchers.equalTo(projectId));
    }

    @Step("Adam creates '{0}' in project with id: {1}")
    public void createInProject(Task task, long projectId) {
        this.name = task.getContent();
        this.projectId = projectId;
        task.setProject_id(projectId);
        taskId = SerenityRest
                .given()
                    .body(task)
                .when()
                    .post("/tasks")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("content", Matchers.equalTo(task.getContent()))
                        .body("project_id", Matchers.equalTo(projectId))
                    .and()
                        .extract().path("id");
    }
}
