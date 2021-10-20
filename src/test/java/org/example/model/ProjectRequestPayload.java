package org.example.model;

public class ProjectRequestPayload {

    private String name;

    public ProjectRequestPayload(String projectName) {
        this.name = projectName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
