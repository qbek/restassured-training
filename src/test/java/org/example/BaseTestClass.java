package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@ExtendWith(SerenityJUnit5Extension.class)
public abstract class BaseTestClass {

    @BeforeAll
    public static void setup() throws IOException {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        String env = System.getProperty("env");
        String cfgFilename = String.format("/%s.properties", env);

        InputStream file = builder.getClass().getResourceAsStream(cfgFilename);
        Properties cfg = new Properties();
        cfg.load(file);

        RestAssured.requestSpecification = builder
                .setBaseUri(cfg.getProperty("baseUrl"))
                .setBasePath(cfg.getProperty("basePath"))
                .addHeader("Authorization", String.format("Bearer %s", cfg.getProperty("token")))
                .setContentType(ContentType.JSON)
                .build();
    }
}
