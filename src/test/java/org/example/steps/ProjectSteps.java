package org.example.steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;

public class ProjectSteps {

    private long id;
    private String name;

    public long getCreatedProjectId() {
        return id;
    }

    public void checkIfProjectIsOnAllProjectsList() {
        RestAssured
                .given()
                .when()
                .get("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body(String.format("find{ it.id == %d}.name", this.id), Matchers.equalTo(this.name));
    }

    public void checkProjectDetails() {
        RestAssured
                .given()
                .pathParam("id", this.id)
                .when()
                .get("/projects/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", Matchers.equalTo(this.id))
                .body("name", Matchers.equalTo(this.name));
    }

    public void createNewProject(String projectName) {
        this.name = projectName;
        this.id = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(String.format("{ \"name\" : \"%s\"}", this.name))
                .when()
                .post("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo(this.name))
                .and()
                .extract().path("id");
    }
}
