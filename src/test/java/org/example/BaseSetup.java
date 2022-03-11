package org.example;

import com.github.rjeschke.txtmark.Run;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.example.data.DataGenerator;
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

    protected DataGenerator data = new DataGenerator();

    @Before
    public void setup() {
        String env = System.getProperty("env");

        String filename = "/" + env + ".properties";
        Properties cfg;
        try {
            InputStream propertiesFile = getClass().getResourceAsStream(filename);
            cfg = new Properties();
            cfg.load(propertiesFile);
        } catch (Exception e) {
            throw new RuntimeException("Invalid environment file in cmd line");
        }


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
