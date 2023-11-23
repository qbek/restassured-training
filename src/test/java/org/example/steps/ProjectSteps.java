package org.example.steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.ProjectTests;
import org.hamcrest.Matchers;

public class ProjectSteps {

    private String projectId;

    public void userCreatesANewProject(String projectName) {
        //user creates a new project
        var payload = String.format("{\"name\": \"%s\"}", projectName);
        projectId = RestAssured
                .given()
                .spec(ProjectTests.baseReqSpec)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/projects")
                .then()
//                    .log().all()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", Matchers.containsString("json"))
                .body("name", Matchers.equalTo(projectName))
                .and()
                .extract().path("id");
    }

    public void userChecksProjectDetails(String projectName) {
        //user checks project details
        RestAssured
                .given()
                .spec(ProjectTests.baseReqSpec)
                .pathParam("id", projectId)
                .when()
                //konkatenacja stringow
//                    .get("/projects/" + projectId)
                // szablony tekstu
//                    .get(String.format("/projects/%s", projectId))
                .get("/projects/{id}")
                .then()
//                    .log().all()
                .assertThat()
                .statusCode(200)
                .body("id", Matchers.equalTo(projectId))
                .body("name", Matchers.equalTo(projectName));
    }

    public void userChecksAllProjectsList(String projectName) {

        //user checks all project list
        var getNameByProjectId = String.format("find{ it.id == \"%s\" }.name", projectId );
        var getProjectByProjectId = String.format("find{ it.id == \"%s\" }", projectId );

        RestAssured
                .given()
                .spec(ProjectTests.baseReqSpec)
                .when()
                .get("/projects")
                .then()
//                    .log().all()
                .assertThat()
                .statusCode(200)
                .body(getProjectByProjectId, Matchers.notNullValue())
                .body(getNameByProjectId, Matchers.equalTo(projectName));

    }
}
