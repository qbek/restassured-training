package org.example;

import org.example.model.ProjectRequest;
import org.junit.Test;


public class ProjectTest extends Base {

    @Test
    public void userCanCreateAProject() {
        ProjectRequest projectRequestData = new ProjectRequest();
        projectRequestData.setName(faker.chuckNorris().fact());
        project.create(projectRequestData);
        project.checkDetails();
        project.checkIfIsListed();
    }

}
