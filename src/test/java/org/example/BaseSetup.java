package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@ExtendWith(SerenityJUnit5Extension.class)
public abstract class BaseSetup {

    @BeforeAll
    public static void setup() throws IOException {
        RestAssured.reset();
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();

        String filename = String.format("%s.properties", System.getProperty("env"));
        InputStream file = reqBuilder.getClass().getResourceAsStream("/" + filename);
        Properties cfg = new Properties();
        cfg.load(file);

        RestAssured.requestSpecification = reqBuilder
                .setBaseUri(cfg.getProperty("baseUri"))
                .setBasePath(cfg.getProperty("basePath"))
                .addHeader("Authorization", "Bearer " + cfg.getProperty("token"))
                .build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
