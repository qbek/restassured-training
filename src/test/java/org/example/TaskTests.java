package org.example;

import org.example.steps.ProjectSteps;
import org.example.steps.TaskSteps;
import org.junit.jupiter.api.Test;

public class TaskTests extends BaseSetup {

    ProjectSteps preconditions = new ProjectSteps(testData);
    TaskSteps steps = new TaskSteps(testData);

    @Test
    public void userCanAddTaskToTheProject() {
        preconditions.userCreatesANewProject();
        preconditions.userChecksProjectDetails();

        steps.userAddsTaskToTheProject();
        steps.userCheckTaskDetails();
        steps.userCheckAllTasksList();
    }
}
