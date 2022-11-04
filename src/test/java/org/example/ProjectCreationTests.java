package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.specification.RequestSpecification;
import org.example.steps.ProjectSteps;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;

public class ProjectCreationTests extends BaseSetup {

    ProjectSteps steps = new ProjectSteps();

    @Test
    public void userCanCreateANewProject() {
        var projectName = "Lepsze szkolenie RestAssured";
        var projectId = steps.userCreatesANewProject(projectName);
        steps.userChecksProjectDetails(projectId, projectName);
        steps.userChecksAllProjectsList(projectId, projectName);
    }
}
