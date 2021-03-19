package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

@RunWith(SerenityRunner.class)
public class Base {

    private static Properties env;

    static {
        try {
            String filename = String.format("%s.properties", System.getProperty("env", "prod"));
            env = PropertiesLoaderUtils.loadAllProperties(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setup() {
        RestAssured.baseURI = env.getProperty("baseUri");
        RestAssured.basePath = env.getProperty("basePath");
//        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.requestSpecification = RestAssured.given()
            .header("Authorization", env.getProperty("token"))
            .contentType(ContentType.JSON);
    }

    @After
    public void cleanup() {
        project.delete();
    }

    protected long projectId;

    @Steps
    protected ProjectSteps project;

}
