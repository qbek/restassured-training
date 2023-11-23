package org.example.steps.project;

import io.restassured.response.Response;
import org.hamcrest.Matchers;

public class ProjectVerificator {

    public void verifyProjectDetails(Response response, String expectedProjectName) {
        response.then()
                .assertThat()
                    .statusCode(200)
                    .header("Content-Type", Matchers.containsString("json"))
                    .body("name", Matchers.equalTo(expectedProjectName));

    }

    public void verifyProjectDetails(Response response, String expectedProjectName, String expectedProjectId) {
        verifyProjectDetails(response, expectedProjectName);
        response.then()
                .assertThat()
                    .body("id", Matchers.equalTo(expectedProjectId));
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
