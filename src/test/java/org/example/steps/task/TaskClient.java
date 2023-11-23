package org.example.steps.task;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TaskClient {

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

    private RequestSpecBuilder getBaseReqSpecification() {
        RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
        reqSpecBuilder.addHeader("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1");
        return reqSpecBuilder;
    }

    private Response sendRequest(Method method, RequestSpecification spec, String path) {
        return RestAssured.given().spec(spec).when().request(method, path);
    }
}
