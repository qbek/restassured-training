package org.example;

import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.junit.Test;

public class ProjectCreationTest extends BaseSetup {

    @Steps
    private ProjectSteps steps;

    @Test
    public void user_can_create_a_new_project() {
        String projectName = "Szkolenie";
        long projectId = steps.userCreatesANewProject(projectName);
        steps.userChecksProjectDetails(projectId, projectName);
        steps.userChecksAllProjectsList(projectId, projectName);
    }
}

