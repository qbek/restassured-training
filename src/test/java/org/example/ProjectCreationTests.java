package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;

public class ProjectCreationTests {

    @BeforeAll
    public static void setup() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        RestAssured.requestSpecification = builder
                .setBasePath("/rest/v2")
                .setBaseUri("https://api.todoist.com")
                .addHeader("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
//                .log(LogDetail.ALL)
                .build();

//        RestAssured.responseSpecification = new ResponseSpecBuilder()
//                .log(LogDetail.ALL).build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void userCanCreateANewProject() {
        var projectId = RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body("{\"name\": \"Szkolenie Rest API\"}")
                .when()
                    .post("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("name", Matchers.equalTo("Szkolenie Rest API 222"))
                        .header("Content-Type", Matchers.equalTo("application/json"))
                    .and()
                        .extract().path("id");

        RestAssured
                .given()
                    .pathParam("id", projectId)
                .when()
                    .get("/projects/{id}")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("name", Matchers.equalTo("Szkolenie Rest API"))
                        .body("id", Matchers.equalTo(projectId));


        RestAssured
                .given()
                .when()
                    .get("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body(
                                format("find{ it.id == \"%s\" }.name", projectId),
                                Matchers.equalTo("Szkolenie Rest API")
                        );

    }

}
