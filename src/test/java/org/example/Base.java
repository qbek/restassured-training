package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@RunWith(SerenityRunner.class)
public class Base {

    @Before
    public void setup() throws IOException {
        String filename = String.format("/%s_env.properties", System.getProperty("env"));
        InputStream envFile = this.getClass().getResourceAsStream(filename);
        Properties env = new Properties();
        env.load(envFile);
        RestAssured.baseURI = env.getProperty("baseUrl");
        RestAssured.basePath = env.getProperty("basePath");

        RestAssured.requestSpecification =
                RestAssured.given()
                        .header("Authorization", "Bearer " + env.getProperty("token"))
                        .contentType(ContentType.JSON);

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}
