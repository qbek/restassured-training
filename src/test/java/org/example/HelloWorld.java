package org.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class HelloWorld {

    @Test
    public void hello() {
        RestAssured
                .given()
                    .baseUri("http://numbersapi.com")
                    .log().all()
                .when()
                    .get("/1410/year")
                .then()
                    .log().all();
    }
}
