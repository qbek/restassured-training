package org.example;

import io.restassured.RestAssured;
import org.junit.Test;

public class HelloWorld {


    @Test
    public void helloWorld() {
        String test = System.getProperty("cos");
        System.out.println(test);


//        RestAssured
//                .given()
//                    .baseUri("http://numbersapi.com")
//                    .log().all()
//                .when().get("1401/year")
//                .then()
//                    .log().all();
    }
}
