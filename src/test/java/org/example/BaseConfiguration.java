package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseConfiguration {

    @BeforeAll
    public static void setup() {
        var builder = new RequestSpecBuilder();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.requestSpecification = builder
                .setBasePath("rest/v1")
                .setBaseUri("https://api.todoist.com")
                .addHeader("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                .build();
    }
}
