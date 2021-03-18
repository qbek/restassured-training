package org.example;

import static org.junit.Assert.assertTrue;

import io.restassured.RestAssured;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    @Test
    public void helloWorld() {
        RestAssured.given()
                .baseUri("http://google.com")
                .log().all()
                .when().get()
                .then().log().all();


    }
}
