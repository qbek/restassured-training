package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.example.steps.ProjectSteps;
import org.example.steps.TaskSteps;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;

public class TaskCreationTests extends BaseSetup {

    ProjectSteps precondition = new ProjectSteps();
    TaskSteps steps = new TaskSteps();

    @Test
    public void userCanAddTaskToTheProject() {
        var projectName = "Projekt z zadaniem";
        var projectId = precondition.userCreatesANewProject(projectName);

        var taskName = "to jest moje zadanie";
        var taskId = steps.addTaskToTheProject(taskName, projectId);

        steps.checkIfTaskIsCreated(taskId, taskName);
        steps.checkIfTaskIsOnAllTasksList(taskId, taskName);
    }


}
