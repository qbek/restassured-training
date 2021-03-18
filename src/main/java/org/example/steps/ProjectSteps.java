package org.example.steps;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;

public class ProjectSteps {
    public void delete(long projectId) {
        RestAssured
                .given()
                .pathParam("id", projectId)
                .when().delete("/projects/{id}")
                .then()
                .assertThat()
                .statusCode(204);
    }

    public void checkIfIsListed(long projectId, String name) {
        String projectNameQuery = String.format("find { it.id == %d }.name", projectId);
        RestAssured
                .when()
                    .get("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body(projectNameQuery, Matchers.equalTo(name));
    }

    public void checkDetails(long projectId, String name) {
        RestAssured
                .given()
                    .pathParam("id", projectId)
                .when()
                    .get("/projects/{id}")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("name", Matchers.equalTo(name))
                        .body("id", Matchers.equalTo(projectId));
    }

    public long create(String name) {

        String payload = String.format("{ \"name\":  \"%s\" }", name);

        return RestAssured
                .given()
                    .body(payload)
                .when()
                    .post("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("name", Matchers.equalTo(name))
                        .header("Content-Type", Matchers.containsString("json"))
                    .and()
                        .extract().path("id");
    }
}
