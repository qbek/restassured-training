package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

public class ProjectTest {


    @Test
    public void userCanCreateAProject() {
            long projectId = RestAssured
                    .given()
                        .baseUri("https://api.todoist.com")
                        .basePath("/rest/v1")

                        .header("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                        .contentType(ContentType.JSON)

                        .body( "{ \"name\":  \"Szkolenie RestAssured\" }" )
                        .log().all()
                    .when()
                        .post("/projects")
                    .then()
                        .log().all()
                        .assertThat()
                            .statusCode(200)
                            .body("name", Matchers.equalTo("Szkolenie RestAssured"))
                            .header("Content-Type", Matchers.containsString("json"))
                        .and()
                            .extract().path("id");

           RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v1")
                    .header("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                    .pathParam("id", projectId)
                    .log().all()
                   .when()
                        .get("/projects/{id}")

                   .then()
                        .log().all()
                        .assertThat()
                            .statusCode(200)
                            .body("name", Matchers.equalTo("Szkolenie RestAssured"))
                            .body("id", Matchers.equalTo(projectId));


           RestAssured
               .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v1")
                    .header("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                    .pathParam("id", projectId)
                   .when().delete("/projects/{id}")
                   .then()
                    .assertThat()
                    .statusCode(204);


    }
}
