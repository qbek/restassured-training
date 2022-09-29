package org.example;

import org.example.steps.ProjectSteps;
import org.junit.jupiter.api.Test;

public class CreateProjectTest extends BaseConfiguration {

    ProjectSteps step = new ProjectSteps();

    @Test
    public void userCanCreateANewProject() {
        var projectName = "Szkolenie RestAPI";
        step.userCreatesANewProject(projectName);
        step.userCheckProjectDetails();
        step.userChecksIfProjectWasAddedToAllProjectList();
    }

}
