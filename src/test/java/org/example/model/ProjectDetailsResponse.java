package org.example.model;

public class ProjectDetailsResponse {
    private long id;
    private int order;
    private int color;
    private String name;
    private int comment_count;
    private boolean shared;
    private boolean favorite;
    private int sync_id;
    private String url;
    private Boolean inbox_project;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getSync_id() {
        return sync_id;
    }

    public void setSync_id(int sync_id) {
        this.sync_id = sync_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getInbox_project() {
        return inbox_project;
    }

    public void setInbox_project(Boolean inbox_project) {
        this.inbox_project = inbox_project;
    }
}
