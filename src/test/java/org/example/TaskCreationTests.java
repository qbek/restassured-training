package org.example;

import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.example.steps.TaskSteps;
import org.junit.jupiter.api.Test;

public class TaskCreationTests extends BaseSetup {

    @Steps
    ProjectSteps precondition;
    @Steps
    TaskSteps steps;

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
