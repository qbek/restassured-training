package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.steps.ProjectSteps;
import org.example.steps.TaskSteps;
import org.junit.Before;
import org.junit.Test;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

public class TaskCreationTest extends Base {

    ProjectSteps precoditions = new ProjectSteps();
    TaskSteps steps = new TaskSteps();
    
    @Test
    public void user_can_add_task_to_the_project() {
        String projectName = "Projekt dla drugiego testu";
        String taskName = "Opanować zasadę DRY";
        long projectId = precoditions.userCreatesAProject(projectName);

        long taskId = steps.userAddsTaskToTheProject(taskName, projectId);
        steps.userChecksTaskDetails(taskId, taskName, projectId);
        steps.userChecksIfTaskIsListedWithAllTasks(taskId, taskName, projectId);
    }

}
