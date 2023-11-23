package org.example.steps;

import org.example.steps.task.TaskClient;
import org.example.steps.task.TaskVerificator;
import org.example.testdata.Project;
import org.example.testdata.Task;

public class TaskSteps {

    private TaskClient client = new TaskClient();
    private TaskVerificator verify = new TaskVerificator();

    private Project project;
    private Task task;

    public void userAddsTaskToTheProject(Project project) {
        task = new Task("TO jest kolejne zadanie");
        this.project = project;
        var response = client.sendCreateTask(task.getName(), project.getId());
        verify.verifyTaskDetails(response, task.getName(), project.getId());
        task.setId(response.then().extract().path("id"));
    }

    public void userCheckTaskDetails() {
        var response = client.sendGetTaskDetails(task.getId());
        verify.verifyTaskDetails(response, task.getName(), project.getId());
    }

    public void userCheckAllTasksList() {
        var response = client.sendGetAllTasks();
        verify.verifyTaskOnTheAllTasksList(response, task.getId());
    }
}
