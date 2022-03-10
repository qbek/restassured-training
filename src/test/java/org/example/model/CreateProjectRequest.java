package org.example.model;

import io.cucumber.java.an.E;

public class CreateProjectRequest {

    private String name;
    private Example example;

    public CreateProjectRequest (String name) {
        this.name = name;
        this.example = new Example();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Example getExample() {
        return example;
    }

    public void setExample(Example example) {
        this.example = example;
    }
}
