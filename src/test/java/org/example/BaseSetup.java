package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseSetup {

    @BeforeAll
    public static void setup() {
        RestAssured.reset();
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        RestAssured.requestSpecification = reqBuilder
                .setBaseUri("https://api.todoist.com")
                .setBasePath("/rest/v1")
                .addHeader("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                .build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
