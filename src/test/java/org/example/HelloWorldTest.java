package org.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class HelloWorldTest {

    @Test
    public void helloWorld() {
        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("http://numbersapi.com/1230/year")
                .then()
                    .log().all();
    }
}
