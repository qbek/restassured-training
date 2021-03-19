package org.example.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.example.model.ProjectRequest;
import org.example.model.ProjectResponse;
import org.example.steps.rest.ProjectRestSteps;

public class ProjectSteps {

    @Steps
    ProjectRestSteps rest;

    private String name;

    private ProjectResponse responseProject;

    public long getProjectId() {
        return responseProject.getId();
    }

    @Step("Adam deletes project '#name'")
    public void delete() {
        rest.deleteProject(responseProject.getId());

    }

    @Step("Adam checks if project '#name' is listed with all projects")
    public void checkIfIsListed() {
        rest.sendGetAllProjectsRequest();
        rest.verifyGetAllProjectsResponse(responseProject);

    }


    @Step("Adam checks details of '#name' project")
    public void checkDetails() {
        rest.sendGetProjectDetailsRequest(responseProject.getId());
        rest.verifyProjectDetailsResponse(responseProject);
    }


    @Step("Adam create '{0}' project")
    public void create(ProjectRequest projectRequest) {
        this.name = projectRequest.getName();
        rest.sendCreateProjectRequest(projectRequest);
        rest.verifyCreateProjectResponse(name);
        responseProject = rest.getProjectId();
    }

}
