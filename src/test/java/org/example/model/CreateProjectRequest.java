package org.example.model;

import io.cucumber.java.an.E;

public class CreateProjectRequest {

    private String name;

    public CreateProjectRequest (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
