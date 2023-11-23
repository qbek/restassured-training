package org.example.steps.project;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.ProjectTests;
import org.example.client.BaseClient;

public class ProjectClient extends BaseClient {

    public Response sendCreateReq(String projectName) {
        var payload = String.format("{\"name\": \"%s\"}", projectName);
        RequestSpecification req = getBaseReqSpecification()
                .setContentType(ContentType.JSON)
                .setBody(payload)
                .build();
        return sendRequest(Method.POST, req, "/projects");
    }

    public Response sendGetProjectDetails(String projectId) {
        RequestSpecification req = getBaseReqSpecification()
                .addPathParams("id", projectId)
                .build();
        return sendRequest(Method.GET, req, "/projects/{id}");
    }

    public Response sendGetAllProjects() {
        RequestSpecification req = getBaseReqSpecification().build();
        return sendRequest(Method.GET, req, "/projects");
    }
}
