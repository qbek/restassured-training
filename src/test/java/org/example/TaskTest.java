package org.example;

import net.thucydides.core.annotations.Steps;
import org.example.model.Project;
import org.example.model.Task;
import org.example.steps.TaskSteps;
import org.junit.Test;

public class TaskTest extends Base {

    @Steps
    private TaskSteps task;

    @Test
    public void userCanAddTaskToTheProject() {
        Project projectData = new Project();
        projectData.setName("Projekt do zadania 3.1");

        Task taskData = new Task();
        taskData.setContent("Zadanie");

        project.create(projectData);
        task.createInProject(taskData, project.getProjectId());
        task.checkDetails();
        task.checkIfIsListed();
    }
}
