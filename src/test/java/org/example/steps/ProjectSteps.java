package org.example.steps;

import io.restassured.response.Response;
import org.example.steps.project.ProjectClient;
import org.example.steps.project.ProjectVerificator;
import org.example.testdata.Project;
import org.example.testdata.TestDataManager;

public class ProjectSteps {

    private ProjectClient client = new ProjectClient();
    private ProjectVerificator verify = new ProjectVerificator();
    private TestDataManager testData;

    public ProjectSteps(TestDataManager testData) {
        this.testData = testData;
    }

    public void userCreatesANewProject() {
        var project = new Project("Szkolenie RestAssured + JAVA");
        testData.setTestData("project", project);
        Response resp = client.sendCreateReq(project.getName());
        verify.verifyProjectDetails(resp, project.getName());
        project.setId(resp.then().extract().path("id"));
    }

    public void userChecksProjectDetails() {
        var project = (Project) testData.getTestData("project");
        Response resp = client.sendGetProjectDetails(project.getId());
        verify.verifyProjectDetails(resp, project.getName(), project.getId());
    }

    public void userChecksAllProjectsList() {
        var project = (Project) testData.getTestData("project");
        Response resp = client.sendGetAllProjects();
        verify.verifyAllProjectsList(resp, project.getName(), project.getId());
    }

}
