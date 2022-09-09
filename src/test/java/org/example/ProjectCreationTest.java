package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.junit.jupiter.api.Test;


public class ProjectCreationTest extends BaseTestClass {

    @Steps ProjectSteps project;

    @Test
    public void userCanCreateAProject() throws JsonProcessingException {
        String projectName = dataGenerator.getProjectName();
        project.create(projectName);
        project.checkDetails();
        project.checkIfAddedToProjectList();
    }
}