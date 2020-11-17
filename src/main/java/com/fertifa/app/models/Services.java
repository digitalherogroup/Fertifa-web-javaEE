package com.fertifa.app.models;

public class Services {
    private int id;
    private String imageLink;
    private String title;
    private String description;
    private float price;

    public Services(int id, String imageLink, String title, String description, float price) {
        this.id = id;
        this.imageLink = imageLink;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Services() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
