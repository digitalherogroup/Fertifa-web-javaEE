package com.fertifa.app.models;

public class HealthHistory {
    private int id;
    private int user_id;
    private String content;
    private String createdDate;

    public HealthHistory(int id, int user_id, String content) {
        this.id = id;
        this.user_id = user_id;
        this.content = content;
    }

    public HealthHistory(int user_id, String content) {
        this.user_id = user_id;
        this.content = content;
    }

    public HealthHistory() {
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
