package org.example;

import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.junit.jupiter.api.Test;

public class CreateProjectTest extends BaseConfiguration {

    @Steps
    ProjectSteps step;

    @Test
    public void userCanCreateANewProject() {
        var projectName = "Szkolenie RestAPI";
        step.userCreatesANewProject(projectName);
        step.userCheckProjectDetails();
        step.userChecksIfProjectWasAddedToAllProjectList();
    }

}
