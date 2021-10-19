package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.steps.ProjectSteps;
import org.junit.Before;
import org.junit.Test;


public class ProjectCreationTest extends Base {


    private ProjectSteps steps = new ProjectSteps();

    @Test
    public void user_can_create_a_project() {
        String projectName = "Szkolenie Rest API dzień 2";
        long projectId = steps.userCreatesAProject(projectName);
        steps.userChecksProjectDetails(projectId, projectName);
        steps.userChecksIfProjectIsListedWithAllProjects(projectId, projectName);
    }
}
