package steps;

import io.restassured.RestAssured;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

public class TaskSteps {

    private long id;
    private String name;

    public void checkIfAddedToTasksList() {
        RestAssured
                .given()
                .when()
                .get("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .body(format("find{ it.id == %d }.content", id), equalTo(name));
    }

    public void checkDetails() {
        RestAssured
                .given()
                .pathParam("id", id)
                .when()
                .get("/tasks/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("content", equalTo(name));
    }

    public void addToProject(String taskName, long projectId) {
        name =  taskName;
        id = RestAssured
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
