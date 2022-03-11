package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.example.data.RandomDataGenerator;
import org.example.data.StaticDataGenerator;
import org.example.steps.ProjectSteps;
import org.example.steps.TasksSteps;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@RunWith(SerenityRunner.class)
public class BaseSetup {

    protected RandomDataGenerator data = new RandomDataGenerator();
    protected StaticDataGenerator staticData = new StaticDataGenerator();

    @Before
    public void setup() throws IOException {
        String env = System.getProperty("env");

        String filename = "/" + env + ".properties";
        InputStream propertiesFile = getClass().getResourceAsStream(filename);
        Properties cfg = new Properties();
        cfg.load(propertiesFile);


        RestAssured.baseURI = cfg.getProperty("baseUrl");
        RestAssured.basePath = cfg.getProperty("basePath");

        String authValue = String.format("Bearer %s", cfg.getProperty("token"));
        RestAssured.requestSpecification =
                RestAssured
                        .given()
                        .headers("Authorization", authValue)
                        .contentType(ContentType.JSON);

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
