package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.example.steps.ProjectSteps;
import org.example.steps.TaskSteps;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TaskCreationTest {

    ProjectSteps project = new ProjectSteps();
    TaskSteps task = new TaskSteps();

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
    public void userCanAddTaskToTheProject() {
        String projectName = "Projekt z zadaniem";
        String taskName = "to jest moje zadanie";

        project.create(projectName);
        task.addToTheProject(taskName, project.getId());
        task.checkDetails();
        task.checkIsOnAllTasksList();
    }

}
