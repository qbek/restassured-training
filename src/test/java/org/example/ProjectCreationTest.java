package org.example;

import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class ProjectCreationTest extends BaseSetup {

    @Steps
    ProjectSteps project;

    @Test
    public void userCanCreateAProject() {
        String projectName = "Szkolenie RestAssured po refaktorze";
        project.create(projectName);
        project.checkDetails();
        project.checkIsOnAllProjectsList();
    }
}
