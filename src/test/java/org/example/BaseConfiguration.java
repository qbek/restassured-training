package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.example.data.TestDataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.IOException;
import java.util.Properties;

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(SerenityJUnit5Extension.class)
public abstract class BaseConfiguration {

    TestDataProvider generator = new TestDataProvider();

    @BeforeAll
    public static void setup() throws IOException {
        var builder = new RequestSpecBuilder();

        var filename = System.getProperty("env") + ".env";
        var configurationFile = builder.getClass().getResourceAsStream("/" + filename);
        var cfg = new Properties();
        cfg.load(configurationFile);

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.requestSpecification = builder
                .setBasePath(cfg.getProperty("basePath"))
                .setBaseUri(cfg.getProperty("baseUri"))
                .addHeader("Authorization", "Bearer " + cfg.getProperty("token"))
                .build();
    }
}
