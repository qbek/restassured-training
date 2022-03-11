package org.example;

import com.github.javafaker.Faker;
import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.example.steps.TasksSteps;
import org.junit.Test;
import sun.security.x509.GeneralName;

public class TaskCreationTest extends BaseSetup {
    @Steps
    private TasksSteps steps;

    @Steps(shared = true)
    private ProjectSteps preconditions;

    @Test
    public void user_can_add_task_to_the_project() {
        preconditions.userCreatesANewProject();
        steps.userAddsTaskToTheProject();
        steps.userChecksTaskDetails();
        steps.userChecksAllTasksList();
    }
    
}
