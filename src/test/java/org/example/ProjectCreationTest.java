package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.junit.Before;
import org.junit.Test;


public class ProjectCreationTest extends Base {

    @Steps
    private ProjectSteps steps;

    @Test
    public void user_can_create_a_project() {
        steps.userCreatesAProject();
        steps.userChecksProjectDetails();
        steps.userChecksIfProjectIsListedWithAllProjects();
    }
}