package org.example;

import io.restassured.RestAssured;
import org.junit.Test;

public class HelloWorldTest {


    @Test
    public void helloWorld() {
        RestAssured
                .given()
                    .baseUri("http://numbersapi.com")
                    .log().all()
                .when().get("1401/year")
                .then()
                    .log().all();
    }
}
