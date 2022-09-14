package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class ProjectCreationTest {

    @BeforeAll
    public static void setup() {
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();

        RestAssured.requestSpecification = reqBuilder
                .setBaseUri("https://api.todoist.com")
                .setBasePath("/rest/v1")
                .addHeader("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                .build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }


    @Test
    public void userCanCreateAProject() {
        String projectName = "Szkolenie RestAssured po refaktorze";

        long projectId = createNewProject(projectName);
        checkProjectDetails(projectId, projectName);
        checkIfProjectIsOnAllProjectsList(projectId, projectName);
    }

    @Test
    public void userCanAddTaskToTheProject() {
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
                .body(String.format("find{ it.id == %d }.content", taskId), Matchers.equalTo(taskName));
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
                .body("content", Matchers.equalTo(taskName));
    }

    private long addTaskToTheProject(String taskName, long projectId) {
        return RestAssured
                .given()
                    .contentType(ContentType.JSON)
                .body(
                        String.format("{ \"content\": \"%s\", \"project_id\": %d}", taskName, projectId)
                )
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


    private void checkIfProjectIsOnAllProjectsList(long projectId, String projectName) {
        RestAssured
                .given()
                .when()
                    .get("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body(String.format("find{ it.id == %d}.name", projectId), Matchers.equalTo(projectName));
    }

    private void checkProjectDetails(long projectId, String projectName) {
        RestAssured
                .given()
                    .pathParam("id", projectId)
                .when()
                    .get("/projects/{id}")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("id", Matchers.equalTo(projectId))
                        .body("name", Matchers.equalTo(projectName));
    }

    private long createNewProject(String projectName) {
        return RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(String.format("{ \"name\" : \"%s\"}", projectName))
                .when()
                    .post("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("name", Matchers.equalTo(projectName))
                    .and()
                        .extract().path("id");
    }
}
