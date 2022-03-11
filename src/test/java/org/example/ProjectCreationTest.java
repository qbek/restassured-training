package org.example;

import com.github.javafaker.Faker;
import io.cucumber.java.tr.Fakat;
import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.junit.Test;

public class ProjectCreationTest extends BaseSetup {

    @Steps
    private ProjectSteps steps;

    @Test
    public void user_can_create_a_new_project() {
        steps.userCreatesANewProject();
        steps.userChecksProjectDetails();
        steps.userChecksAllProjectsList();
    }
}

