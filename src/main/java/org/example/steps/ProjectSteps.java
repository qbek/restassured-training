package org.example.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.example.model.Project;
import org.example.steps.rest.ProjectRestSteps;

public class ProjectSteps {

    @Steps
    ProjectRestSteps rest;

    private String name;
    private long projectId;

    public long getProjectId() {
        return projectId;
    }

    @Step("Adam deletes project '#name'")
    public void delete() {
        rest.deleteProject(projectId);

    }

    @Step("Adam checks if project '#name' is listed with all projects")
    public void checkIfIsListed() {
        rest.sendGetAllProjectsRequest();
        rest.verifyGetAllProjectsResponse(projectId, name);

    }


    @Step("Adam checks details of '#name' project")
    public void checkDetails() {
        rest.sendGetProjectDetailsRequest(projectId);
        rest.verifyProjectDetailsResponse(projectId, name);
    }


    @Step("Adam create '{0}' project")
    public void create(Project project) {
        this.name = project.getName();
        rest.sendCreateProjectRequest(project);
        rest.verifyCreateProjectResponse(name);
        projectId = rest.getProjectId();
    }

}
