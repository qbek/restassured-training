package org.example;

import com.github.javafaker.Faker;
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

    Faker generator = new Faker();

    @Test
    public void userCanAddTaskToTheProject() {
        String projectName = generator.zelda().game();
        String taskName = generator.backToTheFuture().quote();

        project.create(projectName);
        task.addToTheProject(taskName, project.getId());
        task.checkDetails();
        task.checkIsOnAllTasksList();
    }

}
