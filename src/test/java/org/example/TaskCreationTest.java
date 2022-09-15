package org.example;

import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.example.steps.TaskSteps;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TaskCreationTest extends BaseSetup {

    @Steps
    ProjectSteps project;

    @Steps
    TaskSteps task;

    @Test
    public void userCanAddTaskToTheProject() {
        String projectName = "Projekt z zadaniem";
        String taskName = "to jest moje zadanie";

        project.create(projectName);
        task.addToTheProject(taskName, project.getId());
        task.checkDetails();
        task.checkIsOnAllTasksList();
    }

}
