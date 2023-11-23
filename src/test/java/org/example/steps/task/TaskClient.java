package org.example.steps.task;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.client.BaseClient;

public class TaskClient extends BaseClient {

    public Response sendCreateTask(String taskName, String projectId) {
        var payload = String.format("{ \"content\": \"%s\", \"project_id\": \"%s\"}", taskName, projectId);
        var req = getBaseReqSpecification()
                .setContentType(ContentType.JSON)
                .setBody(payload)
                .build();
        return sendRequest(Method.POST, req, "/tasks");
    }

    public Response sendGetTaskDetails(String taskId) {
        var req = getBaseReqSpecification()
                .addPathParams("id", taskId)
                .build();
        return sendRequest(Method.GET, req, "/tasks/{id}");
    }

    public Response sendGetAllTasks() {
        var req = getBaseReqSpecification().build();
        return sendRequest(Method.GET, req, "/tasks");
    }


}
