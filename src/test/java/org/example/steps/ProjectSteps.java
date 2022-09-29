package org.example.steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;

public class ProjectSteps {

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void userChecksIfProjectWasAddedToAllProjectList() {
        RestAssured
                .given()
                .when()
                .get("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body(
                        String.format("find{ it.id == %d }.name", id),
                        Matchers.equalTo(name)
                );
    }

    public void userCheckProjectDetails() {
        RestAssured
                .given()
                .pathParam("id", id)
                .when()
                .get("/projects/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", Matchers.equalTo(id))
                .body("name", Matchers.equalTo(name));
    }

    public void userCreatesANewProject(String projectName) {
        name = projectName;
        id =  RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(String.format("{\"name\": \"%s\"}", name))
                .when()
                .post("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo(name))
                .and().extract().path("id");
    }
}
