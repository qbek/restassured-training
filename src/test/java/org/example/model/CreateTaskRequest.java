package org.example.model;

public class CreateTaskRequest {
    private String content;
    private long project_id;


    public CreateTaskRequest(String name) {
        this.content = name;
    }

    public String getContent() {
        return content;
    }

    public long getProject_id() {
        return project_id;
    }

    public void setProjectId(long id) {
        this.project_id = id;
    }

}
