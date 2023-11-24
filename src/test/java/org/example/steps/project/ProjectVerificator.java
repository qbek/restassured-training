package org.example.steps.project;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;

public class ProjectVerificator {

    public void verifyProjectDetails(Response response, String expectedProjectName) {
        response.then().assertThat().statusCode(200);
        var payload = response.jsonPath();

        assertThat("Project has correct name", payload.getString("name"), Matchers.equalTo(expectedProjectName));
        assertThat("Project has defined id", payload.getString("id"), Matchers.notNullValue());
    }

    public void verifyProjectDetails(Response response, String expectedProjectName, String expectedProjectId) {
        response.then().assertThat().statusCode(200);
        var payload = response.jsonPath();

        var softAssertion = new SoftAssertions();
        softAssertion.assertThat(payload.getString("name")).as("Project has correct name").isEqualTo(expectedProjectName);
        softAssertion.assertThat(payload.getString("id")).as("Project has correct id").isEqualTo(expectedProjectId);
        softAssertion.assertAll();
    }

    public void verifyAllProjectsList(Response resp, String expectedProjectName, String expectedProjectId) {
        var getNameByProjectId = String.format("find{ it.id == \"%s\" }.name", expectedProjectId );
        var getProjectByProjectId = String.format("find{ it.id == \"%s\" }", expectedProjectId );
        resp.then()
                .assertThat()
                .statusCode(200)
                .body(getProjectByProjectId, Matchers.notNullValue())
                .body(getNameByProjectId, Matchers.equalTo(expectedProjectName));
    }
}
