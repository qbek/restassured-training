package org.example;

import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.example.steps.TasksSteps;
import org.junit.Test;

public class TaskCreationTest extends BaseSetup {
    @Steps
    private TasksSteps steps;

    @Steps
    private ProjectSteps preconditions;

    @Test
    public void user_can_add_task_to_the_project() {
        String taskName = "Opanować asercje";
        String projectName = "Projekt na zadania";

        long projectId = preconditions.userCreatesANewProject(projectName);
        long taskId = steps.userAddsTaskToTheProject(taskName, projectId);
        steps.userChecksTaskDetails(taskId, taskName);
        steps.userChecksAllTasksList(taskId, taskName);
    }



}
