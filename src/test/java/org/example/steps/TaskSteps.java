package org.example.steps;

import org.example.steps.task.TaskClient;
import org.example.steps.task.TaskVerificator;
import org.example.testdata.Project;
import org.example.testdata.Task;
import org.example.testdata.TestDataManager;

public class TaskSteps {

    private TaskClient client = new TaskClient();
    private TaskVerificator verify = new TaskVerificator();

    private TestDataManager testData;

    public TaskSteps (TestDataManager testData) {
        this.testData = testData;
    }

    public void userAddsTaskToTheProject() {
        var task = testData.createTaskData();
        var project = (Project) testData.getTestData("project");
        var response = client.sendCreateTask(task.getName(), project.getId());
        verify.verifyTaskDetails(response, task.getName(), project.getId());
        task.setId(response.then().extract().path("id"));
    }

    public void userCheckTaskDetails() {
        var task = (Task) testData.getTestData("task");
        var project = (Project) testData.getTestData("project");
        var response = client.sendGetTaskDetails(task.getId());
        verify.verifyTaskDetails(response, task.getName(), project.getId());
    }

    public void userCheckAllTasksList() {
        var task = (Task) testData.getTestData("task");
        var response = client.sendGetAllTasks();
        verify.verifyTaskOnTheAllTasksList(response, task.getId());
    }
}
