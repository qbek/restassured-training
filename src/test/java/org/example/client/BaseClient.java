package org.example.client;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClient {
    protected RequestSpecBuilder getBaseReqSpecification() {
        RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
        reqSpecBuilder.addHeader("Authorization", "Bearer d469ce54eca3a7ca5b6b5e7d4c8d51ced8d4c7b1");
        return reqSpecBuilder;
    }

    protected Response sendRequest(Method method, RequestSpecification spec, String path) {
        return RestAssured.given().spec(spec).when().request(method, path);
    }
}
