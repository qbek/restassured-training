package org.example;

import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.example.steps.TaskSteps;
import org.junit.jupiter.api.Test;

public class TaskCreationTest extends BaseSetup {

    @Steps
    ProjectSteps project;

    @Steps
    TaskSteps task;

    @Test
    public void userCanAddTaskToTheProject() {
        String projectName = testData.getProjectName();
        String taskName = testData.getTaskName();

        project.create(projectName);
        task.addToTheProject(taskName, project.getId());
        task.checkDetails();
        task.checkIsOnAllTasksList();
    }

}
