package org.example.steps.project;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.ProjectTests;

public class ProjectClient {

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

    private RequestSpecBuilder getBaseReqSpecification() {
        RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
        reqSpecBuilder.addHeader("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1");
        return reqSpecBuilder;
    }

    private Response sendRequest(Method method, RequestSpecification spec, String path) {
        return RestAssured.given().spec(spec).when().request(method, path);
        //stare podejscie
//      var req = RestAssured.given().spec(spec)
//        switch (method) {
//            case GET:
//                return  req.when().reget(path);
//            case POST:
//                return req.when().post(path);
//            default:
//                throw new RuntimeException(String.format("Http method %s not supported", method));
//        }
    }
}
