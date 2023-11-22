package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class ProjectTests {

    @Test
    public void userCanCreateANewProject() {
        var projectId = RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v2")
                    .header("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                    .contentType(ContentType.JSON)
                    .body("{\"name\": \"Shopping List\"}")
                    .log().all()
                .when()
                    .post("/projects")
                .then()
                    .log().all()
                    .assertThat()
                        .statusCode(200)
                        .header("Content-Type", Matchers.containsString("json"))
                        .body("name", Matchers.equalTo("Shopping List"))
                    .and()
                        .extract().path("id");

        RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v2")
                    .header("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                    .pathParam("id", projectId)
                    .log().all()
                .when()
                    //konkatenacja stringow
//                    .get("/projects/" + projectId)
                    // szablony tekstu
//                    .get(String.format("/projects/%s", projectId))
                    .get("/projects/{id}")
                .then()
                    .log().all()
                    .assertThat()
                        .statusCode(200)
                        .body("id", Matchers.equalTo(projectId))
                        .body("name", Matchers.equalTo("Shopping List"));

        var getNameByProjectId = String.format("find{ it.id == \"%s\" }.name", projectId );
        var getProjectByProjectId = String.format("find{ it.id == \"%s\" }", projectId );

        RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v2")
                    .header("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                    .log().all()
                .when()
                    .get("/projects")
                .then()
                    .log().all()
                    .assertThat()
                        .statusCode(200)
                        .body(getProjectByProjectId, Matchers.notNullValue())
                        .body(getNameByProjectId, Matchers.equalTo("Shopping List"));
    }

}


