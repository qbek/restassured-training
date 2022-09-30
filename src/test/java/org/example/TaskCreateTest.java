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
        var projectName = "Projekt na zadnie";
        var taskName = "To jest moje zadanie";
        project.create(projectName);
        task.userAddsNewTaskToTheProject(taskName, project.getId());
        task.userChecksTaskDetails();
        task.userChecksIfTaskWasAddedToAllTasksList();
    }

}
