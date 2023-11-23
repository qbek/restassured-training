package org.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseSetup {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://api.todoist.com";
        RestAssured.basePath = "/rest/v2";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
