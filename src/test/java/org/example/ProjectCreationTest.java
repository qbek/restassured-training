package org.example;

import org.example.steps.ProjectSteps;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class ProjectCreationTest extends BaseSetup {

    ProjectSteps project = new ProjectSteps();

    @Test
    public void userCanCreateAProject() {
        String projectName = "Szkolenie RestAssured po refaktorze";
        project.create(projectName);
        project.checkDetails();
        project.checkIsOnAllProjectsList();
    }
}
