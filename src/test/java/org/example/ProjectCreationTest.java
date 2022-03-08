package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class ProjectCreationTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://api.todoist.com";
        RestAssured.basePath = "/rest/v1";

        RestAssured.requestSpecification =
                RestAssured
                        .given()
                        .headers("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1")
                        .contentType(ContentType.JSON);

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }


    @Test
    public void user_can_create_a_new_project() {
        String projectName = "Szkolenie";
        long projectId = userCreatesANewProject(projectName);
        userChecksProjectDetails(projectId, projectName);
        userChecksAllProjectsList(projectId, projectName);

    }

    public long userCreatesANewProject(String name) {
        long projectId = RestAssured
                .given()
                    .body(String.format("{\"name\": \"%s\"}", name))
                .when()
                    .post("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("name", Matchers.equalTo(name))
                    .and()
                        .extract().path("id");
        return projectId;
    }

    public void userChecksProjectDetails(long id, String name) {
        RestAssured
                .given()
                    .pathParam("id", id)
                .when()
                    .get("/projects/{id}")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("name", Matchers.equalTo(name))
                        .body("id", Matchers.equalTo(id));
    }

    public void userChecksAllProjectsList(long id, String name) {
        String getNameByProjectId = String.format("find{it.id == %d}.name", id);

        //user checks id project is listed with all projects
        RestAssured
                .given()
                .when()
                    .get("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body(getNameByProjectId, Matchers.equalTo(name));
    }

}
