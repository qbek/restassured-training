package org.example;

import org.example.steps.ProjectSteps;
import org.example.steps.TaskSteps;
import org.junit.jupiter.api.Test;

public class TaskTests extends BaseSetup {

    ProjectSteps preconditions = new ProjectSteps();
    TaskSteps steps = new TaskSteps();

    @Test
    public void userCanAddTaskToTheProject() {
        preconditions.userCreatesANewProject();
        preconditions.userChecksProjectDetails();

        steps.userAddsTaskToTheProject(preconditions.getProject());
        steps.userCheckTaskDetails();
        steps.userCheckAllTasksList();
    }
}
