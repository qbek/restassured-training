package org.example.steps;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;

import static java.lang.String.format;

public class ProjectSteps {

    @Step
    public void userChecksAllProjectsList(String projectId, String projectName) {
        SerenityRest
                .given()
                .when()
                .get("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body(
                        format("find{ it.id == \"%s\" }.name", projectId),
                        Matchers.equalTo(projectName)
                );
    }

    @Step("User checks project with id: {0} if has name: {1}")
    public void userChecksProjectDetails(String projectId, String projectName) {
        SerenityRest
                .given()
                .pathParam("id", projectId)
                .when()
                .get("/projects/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo(projectName))
                .body("id", Matchers.equalTo(projectId));
    }

    @Step
    public String userCreatesANewProject(String projectName) {
        String projectId = SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .body(String.format("{\"name\": \"%s\"}", projectName))
                //                    .body( "{\"name\": \"" + projectName + "\"}")`
                .when()
                .post("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo(projectName))
                .header("Content-Type", Matchers.equalTo("application/json"))
                .and()
                .extract().path("id");
        return projectId;
    }
}
