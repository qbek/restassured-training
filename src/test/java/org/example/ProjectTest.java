package org.example;

import org.example.model.Project;
import org.junit.Test;


public class ProjectTest extends Base {

    @Test
    public void userCanCreateAProject() {
        String projectName = "Praktykowanie RestAssured";

        Project projectData = new Project();
        projectData.setName("Reprezentacja requestu");

        project.create(projectData);
        project.checkDetails();
        project.checkIfIsListed();
    }

}
