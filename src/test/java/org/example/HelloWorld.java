package org.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class HelloWorld {

    @Test
    public void helloWorld() {
        RestAssured
                .given()
                .baseUri("http://numbersapi.com")
                .log().all()
                .when()
                    .get("/1410/math")
                .then()
                .log().all();
    }
}
