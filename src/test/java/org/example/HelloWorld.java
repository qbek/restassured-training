package org.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class HelloWorld {

    @Test
    public void helloWorld() {
        RestAssured
                .given()
                    .log().all()
                .when().get("http://google.com")
                .then()
                .log().all();
    }

}
