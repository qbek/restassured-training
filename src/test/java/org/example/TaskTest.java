package org.example;

import net.thucydides.core.annotations.Steps;
import org.example.steps.TaskSteps;
import org.junit.Test;

public class TaskTest extends Base {

    @Steps
    private TaskSteps task;

    @Test
    public void userCanAddTaskToTheProject() {
        String projectName = "Projekt do zadania 3.1";
        String taskName = "Wykonać zadanie 3.1";

        project.create(projectName);
        task.createInProject(taskName, project.getProjectId());
        task.checkDetails();
        task.checkIfIsListed();
    }
}
