package org.example.model;

public class CreateProjectRequest {

    private String name;

    public String getName() {
        return name;
    }

    public CreateProjectRequest(String projectName) {
        this.name = projectName;
    }
}
