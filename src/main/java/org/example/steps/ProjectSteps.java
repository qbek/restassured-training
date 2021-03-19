package org.example.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.example.steps.rest.ProjectRestSteps;

public class ProjectSteps {

    @Steps
    ProjectRestSteps rest;

    @Step("Adam deletes project with id: {0}")
    public void delete(long projectId) {
        rest.deleteProject(projectId);

    }

    @Step("Adam checks if project '{1}' is listed with all projects")
    public void checkIfIsListed(long projectId, String name) {
        rest.sendGetAllProjectsRequest();
        rest.verifyGetAllProjectsResponse(projectId, name);

    }


    @Step("Adam checks details of '{1}' project")
    public void checkDetails(long projectId, String name) {
        rest.sendGetProjectDetailsRequest(projectId);
        rest.verifyProjectDetailsResponse(projectId, name);
    }



    @Step("Adam create '{0}' project")
    public long create(String name) {
        rest.sendCreateProjectRequest(name);
        rest.verifyCreateProjectResponse(name);
        return rest.getProjectId();
    }


}
