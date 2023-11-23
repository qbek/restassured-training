package org.example;

import io.restassured.RestAssured;
import org.example.testdata.TestDataManager;
import org.junit.jupiter.api.BeforeAll;

public class BaseSetup {

    protected TestDataManager testData = new TestDataManager();

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://api.todoist.com";
        RestAssured.basePath = "/rest/v2";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
