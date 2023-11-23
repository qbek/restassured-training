package org.example;

import org.example.steps.ProjectSteps;
import org.junit.jupiter.api.Test;

public class ProjectTests extends BaseSetup {

    ProjectSteps steps = new ProjectSteps(testData);

    @Test
    public void userCanCreateANewProject() {
        steps.userCreatesANewProject();
        steps.userChecksProjectDetails();
        steps.userChecksAllProjectsList();
    }
}


