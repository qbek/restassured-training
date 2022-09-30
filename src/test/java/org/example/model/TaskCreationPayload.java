package org.example.model;

public class TaskCreationPayload {
    private String content;
    private Long project_id;

    public TaskCreationPayload(String name) {
        this.content = name;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public String getContent() {
        return content;
    }

    public Long getProject_id() {
        return project_id;
    }
}
