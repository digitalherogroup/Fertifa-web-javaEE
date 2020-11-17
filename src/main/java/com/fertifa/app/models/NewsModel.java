package com.fertifa.app.models;


import com.fertifa.app.converters.DateConverter;

import java.sql.Timestamp;
import java.util.Objects;

public class NewsModel {
    private int id;
    private String title;
    private String shortDescription;
    private String description;
    private String thumbnailUrl;
    private Timestamp creation_Date;
    private String status;
    private String categoryOne;
    private String categoryTwo;

    public NewsModel() {
    }

    public NewsModel(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public NewsModel(String title, String shortDescription, String description) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
    }

    public NewsModel(String title, String shortDescription, String description, String thumbnailUrl) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoryOne() {
        return categoryOne;
    }

    public void setCategoryOne(String categoryOne) {
        this.categoryOne = categoryOne;
    }

    public String getCategoryTwo() {
        return categoryTwo;
    }

    public void setCategoryTwo(String categoryTwo) {
        this.categoryTwo = categoryTwo;
    }

    public Timestamp getCreation_Date() {
        return creation_Date;
    }

    public void setCreation_Date(Timestamp creation_Date) {
        this.creation_Date = creation_Date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsModel newsModel = (NewsModel) o;
        return id == newsModel.id &&
                Objects.equals(title, newsModel.title) &&
                Objects.equals(shortDescription, newsModel.shortDescription) &&
                Objects.equals(description, newsModel.description) &&
                Objects.equals(thumbnailUrl, newsModel.thumbnailUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, shortDescription, description, thumbnailUrl);
    }


    public String getGetCreationDate() {
        return DateConverter.convertDateWithRegex(creation_Date);
    }
}
