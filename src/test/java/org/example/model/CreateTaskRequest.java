package org.example.model;

public class CreateTaskRequest {

    private String content;
    private long project_id;

    public CreateTaskRequest(String content, long project_id) {
        this.content = content;
        this.project_id = project_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getProject_id() {
        return project_id;
    }

    public void setProject_id(long project_id) {
        this.project_id = project_id;
    }
}
