package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
public class BaseSetup {

    @BeforeAll
    public static void setup() {
        var builder = new RequestSpecBuilder();
        var reqSpec = builder
                .setBaseUri("https://api.todoist.com")
                .setBasePath("/rest/v2")
                .addHeader("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
//                .log(LogDetail.ALL)
                .build();
        RestAssured.requestSpecification = reqSpec;
//        RestAssured.responseSpecification = new ResponseSpecBuilder()
//                .log(LogDetail.ALL).build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
