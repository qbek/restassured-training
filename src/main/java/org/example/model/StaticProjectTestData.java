package org.example.model;

public class StaticProjectTestData implements ProjectTestDataGenerator {
    @Override
    public ProjectRequest getProject() {
        ProjectRequest project = new ProjectRequest();
        project.setName("To jest statyczna nazwa projektu");
        return project;
    }

    @Override
    public String getType() {
        return "static";
    }
}
