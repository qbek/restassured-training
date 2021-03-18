package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.steps.ProjectSteps;
import org.example.steps.TaskSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TaskTest extends Base {


    private TaskSteps task = new TaskSteps();

    @Test
    public void userCanAddTaskToTheProject() {
        String projectName = "Projekt do zadania 3.1";
        String taskName = "Wykonać zadanie 3.1";

        projectId = project.create(projectName);
        long taskId = task.createInProject(taskName, projectId);
        task.checkDetails(taskName, taskId, projectId);
        task.checkIfIsListed(taskName, taskId, projectId);
    }
}
