package org.example;

import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.junit.jupiter.api.Test;

public class ProjectCreationTests extends BaseSetup {

    @Steps
    ProjectSteps steps;

    @Test
    public void userCanCreateANewProject() {
        var projectName = generator.lebowski().character();

        steps.userCreatesANewProject(projectName);
        steps.userChecksProjectDetails();
        steps.userChecksAllProjectsList();
    }
}
