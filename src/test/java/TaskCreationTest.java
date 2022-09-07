import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

public class TaskCreationTest {

    @BeforeAll
    public static void setup() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.requestSpecification = builder
                .setBaseUri("https://api.todoist.com")
                .setBasePath("/rest/v1")
                .addHeader("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void userCanAddTaksToTheProject() {
        String projectName = "Projekt z zadaniem";
        String taskName = "to jest moje zadanie";
        long projectId = createNewProject(projectName);
        long taskId = addTaskToTheProject(taskName, projectId);
        checkIfTaskIsCreated(taskId, taskName);
        checkIfTaskIsOnAllTasksList(taskId, taskName);
    }

    private void checkIfTaskIsOnAllTasksList(long taskId, String taskName) {
        RestAssured
                .given()
                .when()
                .get("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .body(format("find{ it.id == %d }.content", taskId), equalTo(taskName));
    }

    private void checkIfTaskIsCreated(long taskId, String taskName) {
        RestAssured
                .given()
                .pathParam("id", taskId)
                .when()
                .get("/tasks/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("content", equalTo(taskName));
    }

    private long addTaskToTheProject(String taskName, long projectId) {
        return RestAssured
                .given()
                .body(
                        format("{ \"content\": \"%s\", \"project_id\": %d}", taskName, projectId)
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

    private long createNewProject(String projectName) {
        long projectId = RestAssured
                .given()
                .body(format("{\"name\": \"%s\"}" , projectName))
                .when()
                .post("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(projectName))
                .and()
                .extract().path("id");
        return projectId;
    }
}
