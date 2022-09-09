package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.Test;
import org.example.steps.ProjectSteps;
import org.example.steps.TaskSteps;

public class TaskCreationTest extends BaseTestClass {

    @Steps
    ProjectSteps project;

    @Steps
    TaskSteps task;



    @Test
    public void userCanAddTaskToTheProject() throws JsonProcessingException {
        String projectName = "Projekt z zadaniem2";
        String taskName = "to jest moje zadanie";
        project.create(projectName);
        task.addToProject(taskName, project.getId());
        task.checkDetails();
        task.checkIfAddedToTasksList();
    }
}
