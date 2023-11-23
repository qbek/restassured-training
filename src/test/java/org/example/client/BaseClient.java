package org.example.client;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.testdata.Project;

import java.io.IOException;
import java.util.Properties;

public class BaseClient {

    private final static Properties testCfg;

    static {
        var env = System.getProperty("env", "prod");
        var filename = String.format("/%s.env.properties", env);
        var propertiesFile = BaseClient.class.getResourceAsStream(filename);
        testCfg = new Properties();
        try {
            testCfg.load(propertiesFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected RequestSpecBuilder getBaseReqSpecification() {
        var reqSpecBuilder = new RequestSpecBuilder();
        reqSpecBuilder
                .addHeader("Authorization", "Bearer " + testCfg.getProperty("todoistToken"))
                .setBaseUri(testCfg.getProperty("baseUri"))
                .setBasePath(testCfg.getProperty("basePath"));
        return reqSpecBuilder;
    }

    protected Response sendRequest(Method method, RequestSpecification spec, String path) {
        return RestAssured.given().spec(spec).when().request(method, path);
    }
}
