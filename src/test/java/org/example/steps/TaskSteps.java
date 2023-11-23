package org.example.steps;

import org.example.steps.task.TaskClient;
import org.example.steps.task.TaskVerificator;

public class TaskSteps {

    private TaskClient client = new TaskClient();
    private TaskVerificator verify = new TaskVerificator();

    private ProjectSteps project;

    private String taskName;
    private String taskId;

    public void userAddsTaskToTheProject(ProjectSteps project) {
        taskName = "To jest moje zadanie";
        this.project = project;
        var response = client.sendCreateTask(taskName, project.getId());
        verify.verifyTaskDetails(response, taskName, project.getId());
        taskId = response.then().extract().path("id");
    }

    public void userCheckTaskDetails() {
        var response = client.sendGetTaskDetails(taskId);
        verify.verifyTaskDetails(response, taskName, project.getId());
    }

    public void userCheckAllTasksList() {
        var response = client.sendGetAllTasks();
        verify.verifyTaskOnTheAllTasksList(response, taskId);
    }
}
