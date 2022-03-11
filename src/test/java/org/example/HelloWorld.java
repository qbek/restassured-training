package org.example;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Shared;
import net.thucydides.core.annotations.Steps;
import org.example.di.EmployesData;
import org.example.di.PersonalData;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
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

    @Steps(shared = true)
    PersonalData employee;

    @Steps
    EmployesData em;

    @Test
    public void depInjection() {

        employee.setName("Jakub");
        System.out.println(em.getNameOfBestEmployee());

        employee.setName("Krystyna");
        System.out.println(em.getNameOfBestEmployee());
    }
}
