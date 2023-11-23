package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.example.steps.ProjectSteps;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class ProjectTests {

    public static RequestSpecification baseReqSpec;
    ProjectSteps steps = new ProjectSteps();

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://api.todoist.com";
        RestAssured.basePath = "/rest/v2";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void userCanCreateANewProject() {
        steps.userCreatesANewProject();
        steps.userChecksProjectDetails();
        steps.userChecksAllProjectsList();
    }
}


