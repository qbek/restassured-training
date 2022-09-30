package org.example;

import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.junit.jupiter.api.Test;

public class CreateProjectTest extends BaseConfiguration {

    @Steps(actor = "Stefan")
    ProjectSteps project;

    @Test
    public void userCanCreateANewProject() {
        project.create(generator.getProjectName());
        project.checkDetails();
        project.checkIfAddedToProjectsList();
    }

}
