package org.example;

import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.example.steps.TaskSteps;
import org.junit.jupiter.api.Test;

public class TaskCreateTest extends BaseConfiguration {

    @Steps
    ProjectSteps project;

    @Steps
    TaskSteps task;

    @Test
    public void userCanAddTaskToTheProject() {
        project.create(generator.getProjectName());
        task.userAddsNewTaskToTheProject(generator.getTaskName(), project.getId());
        task.userChecksTaskDetails();
        task.userChecksIfTaskWasAddedToAllTasksList();
    }

}
