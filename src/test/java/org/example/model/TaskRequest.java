package org.example.model;

public class TaskRequest {

    private String content;
    private long project_id;

    public TaskRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public long getProject_id() {
        return project_id;
    }

    public void setProject_id(long project_id) {
        this.project_id = project_id;
    }
}
