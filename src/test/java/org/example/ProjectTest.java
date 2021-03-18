package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.steps.ProjectSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProjectTest extends Base {

    @Test
    public void userCanCreateAProject() {
        String projectName = "Praktykowanie RestAssured delete check";

        projectId = project.create(projectName);
        project.checkDetails(projectId, projectName);
        project.checkIfIsListed(projectId, projectName);
    }

}
