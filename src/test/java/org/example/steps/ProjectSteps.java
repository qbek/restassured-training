package org.example.steps;

import io.restassured.response.Response;
import org.example.steps.project.ProjectClient;
import org.example.steps.project.ProjectVerificator;
import org.hamcrest.Matchers;

public class ProjectSteps {

    private ProjectClient client = new ProjectClient();
    private ProjectVerificator verify = new ProjectVerificator();
    private String projectId;
    private String projectName;

    public void userCreatesANewProject() {
        projectName = "Szkolenie RestAssured + JAVA";
        Response resp = client.sendCreateReq(projectName);
        verify.verifyProjectDetails(resp, projectName);
        projectId = resp.then().extract().path("id");
    }

    public void userChecksProjectDetails() {
        Response resp = client.sendGetProjectDetails(projectId);
        verify.verifyProjectDetails(resp, projectName, projectId);
    }

    public void userChecksAllProjectsList() {
        Response resp = client.sendGetAllProjects();
        verify.verifyAllProjectsList(resp, projectName, projectId);
    }

    public String getId() {
        return projectId;
    }
}
