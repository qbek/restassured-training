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
        var projectName = generator.funnyName().name();
        precondition.userCreatesANewProject(projectName);

        var taskName = generator.chuckNorris().fact();
        var taskId = steps.addTaskToTheProject(taskName, precondition.getProjectId());

        steps.checkIfTaskIsCreated(taskId, taskName);
        steps.checkIfTaskIsOnAllTasksList(taskId, taskName);
    }
}
