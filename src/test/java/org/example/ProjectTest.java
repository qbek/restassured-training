package org.example;

import org.example.model.ProjecTestDataFactory;
import org.example.model.ProjectRequest;
import org.junit.Test;


public class ProjectTest extends Base {

    @Test
    public void userCanCreateAProject() {
        ProjectRequest projectRequestData = ProjecTestDataFactory.createTestProjectData();
        project.create(projectRequestData);
        project.checkDetails();
        project.checkIfIsListed();
    }

}
