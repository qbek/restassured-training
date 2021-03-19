package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Base {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://api.todoist.com";
        RestAssured.basePath = "/rest/v1";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.requestSpecification = RestAssured.given()
            .header("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
            .contentType(ContentType.JSON);
    }

    @After
    public void cleanup() {
        project.delete(projectId);
    }

    protected long projectId;

    @Steps
    protected ProjectSteps project;

}
