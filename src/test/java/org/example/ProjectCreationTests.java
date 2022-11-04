package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;

public class ProjectCreationTests {

    @BeforeAll
    public static void setup() {
        var builder = new RequestSpecBuilder();
        var reqSpec = builder
                .setBaseUri("https://api.todoist.com")
                .setBasePath("/rest/v2")
                .addHeader("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
//                .log(LogDetail.ALL)
                .build();
        RestAssured.requestSpecification = reqSpec;
//        RestAssured.responseSpecification = new ResponseSpecBuilder()
//                .log(LogDetail.ALL).build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void userCanCreateANewProject() {
        var projectName = "Lepsze szkolenie RestAssured";
        var projectId = userCreatesANewProject(projectName);
        userChecksProjectDetails(projectId, projectName);
        userChecksAllProjectsList(projectId, projectName);
    }

    @Test
    public void userCanAddTaskToTheProject() {
        var projectName = "Projekt z zadaniem";
        var taskName = "to jest moje zadanie";
        var projectId = userCreatesANewProject(projectName);
        var taskId = addTaskToTheProject(taskName, projectId);
        checkIfTaskIsCreated(taskId, taskName);
        checkIfTaskIsOnAllTasksList(taskId, taskName);
    }

    private void checkIfTaskIsOnAllTasksList(String taskId, String taskName) {
        RestAssured
                .given()
                    .when()
                .get("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .body(format("find{ it.id == %d }.content", taskId), Matchers.equalTo(taskName));
    }

    private void checkIfTaskIsCreated(String taskId, String taskName) {
        RestAssured
                .given()
                    .pathParam("id", taskId)
                .when()
                    .get("/tasks/{id}")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .body("content", Matchers.equalTo(taskName))
                    .body("id", Matchers.equalTo(taskId));
    }

    private String addTaskToTheProject(String taskName, String projectId) {
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
                        .body("content", Matchers.equalTo(taskName))
                        .body("project_id", Matchers.equalTo(projectId))
                    .and()
                    .extract().path("id");
    }



    private void userChecksAllProjectsList(String projectId, String projectName) {
        RestAssured
                .given()
                .when()
                    .get("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body(
                                format("find{ it.id == \"%s\" }.name", projectId),
                                Matchers.equalTo(projectName)
                        );
    }

    private void userChecksProjectDetails(String projectId, String projectName) {
        RestAssured
                .given()
                    .pathParam("id", projectId)
                .when()
                    .get("/projects/{id}")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("name", Matchers.equalTo(projectName))
                        .body("id", Matchers.equalTo(projectId));
    }

    private String userCreatesANewProject(String projectName) {
        String projectId = RestAssured
                    .given()
                        .contentType(ContentType.JSON)
                        .body(String.format("{\"name\": \"%s\"}", projectName))
        //                    .body( "{\"name\": \"" + projectName + "\"}")`
                    .when()
                        .post("/projects")
                    .then()
                        .assertThat()
                        .statusCode(200)
                        .body("name", Matchers.equalTo(projectName))
                        .header("Content-Type", Matchers.equalTo("application/json"))
                    .and()
                        .extract().path("id");
        return projectId;
    }

}
