package org.example.steps;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

public class TaskSteps {

    private long id;
    private String name;

    @Step("User checks if task is added to all tasks list")
    public void checkIfAddedToTasksList() {
        SerenityRest
                .given()
                .when()
                .get("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .body(format("find{ it.id == %d }.content", id), equalTo(name));
    }

    @Step("User checks if task was created")
    public void checkDetails() {
        SerenityRest
                .given()
                .pathParam("id", id)
                .when()
                .get("/tasks/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("content", equalTo(name));
    }

    @Step("User adds task '{0}' to the project")
    public void addToProject(String taskName, long projectId) {
        name =  taskName;
        id = SerenityRest
                .given()
                .body(
                        format("{ \"content\": \"%s\", \"project_id\": %d}", name, projectId)
                )
                .when()
                .post("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .body("content", equalTo(name))
                .body("project_id", equalTo(projectId))
                .and()
                .extract().path("id");
    }

}
