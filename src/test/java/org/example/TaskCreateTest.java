package org.example;

import org.example.steps.ProjectSteps;
import org.example.steps.TaskSteps;
import org.junit.jupiter.api.Test;

public class TaskCreateTest extends BaseConfiguration {

    ProjectSteps project = new ProjectSteps();
    TaskSteps step = new TaskSteps();

    @Test
    public void userCanAddTaskToTheProject() {
        var projectName = "Projekt na zadnie";
        var taskName = "To jest moje zadanie";
        project.create(projectName);
        step.userAddsNewTaskToTheProject(taskName, project.getId());
        step.userChecksTaskDetails();
        step.userChecksIfTaskWasAddedToAllTasksList();
    }

}
