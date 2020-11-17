package com.fertifa.app.models;

public class TestingOrders {

    private int id;
    private String image;
    private String title;
    private String description;
    private float price;

    public TestingOrders(int id, String image, String title, String description, float price) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public TestingOrders(String image, String title, String description, float price) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public TestingOrders() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
