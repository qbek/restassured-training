package org.example;

import org.junit.Test;


public class ProjectTest extends Base {

    @Test
    public void userCanCreateAProject() {
        String projectName = "Praktykowanie RestAssured";
        projectId = project.create(projectName);
        project.checkDetails(projectId, projectName);
        project.checkIfIsListed(projectId, projectName);
    }

}
