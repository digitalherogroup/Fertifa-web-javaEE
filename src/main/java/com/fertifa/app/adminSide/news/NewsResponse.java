package com.fertifa.app.adminSide.news;

import java.sql.Timestamp;

public class NewsResponse {
    private final int id;
    private final String title;
    private final String shortDescription;
    private final String Description;
    private final String thumbnailURL;
    private final int status;
    private final String categoryOne;
    private final String categoryTwo;
    private final Timestamp creation_date;

    public NewsResponse(NewsResponseBuild newsResponseBuild) {
        this.id = newsResponseBuild.id;
        this.title = newsResponseBuild.title;
        this.shortDescription = newsResponseBuild.shortDescription;
        this.Description = newsResponseBuild.Description;
        this.thumbnailURL= newsResponseBuild.thumbnailURL;
        this.status = newsResponseBuild.status;
        this.categoryOne = newsResponseBuild.categoryOne;
        this.categoryTwo =  newsResponseBuild.categoryTwo;
        this.creation_date = newsResponseBuild.creation_date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return Description;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public int getStatus() {
        return status;
    }

    public String getCategoryOne() {
        return categoryOne;
    }

    public String getCategoryTwo() {
        return categoryTwo;
    }

    public Timestamp getCreation_date() {
        return creation_date;
    }

    public static class NewsResponseBuild {
        private int id;
        private String title;
        private String shortDescription;
        private String Description;
        private String thumbnailURL;
        private int status;
        private String categoryOne;
        private String categoryTwo;
        private Timestamp creation_date;


        public NewsResponseBuild id(int id) {
            this.id = id;
            return this;
        }

        public NewsResponseBuild status(int status) {
            this.status = status;
            return this;
        }

        public NewsResponseBuild thumbnailURL(String thumbnailURL) {
            this.thumbnailURL = thumbnailURL;
            return this;
        }

        public NewsResponseBuild creation_date(Timestamp creation_date) {
            this.creation_date = creation_date;
            return this;
        }

        public NewsResponseBuild categoryTwo(String categoryTwo) {
            this.categoryTwo = categoryTwo;
            return this;
        }


        public NewsResponseBuild categoryOne(String categoryOne) {
            this.categoryOne = categoryOne;
            return this;
        }


        public NewsResponseBuild Description(String Description) {
            this.Description = Description;
            return this;
        }

        public NewsResponseBuild shortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public NewsResponseBuild title(String title) {
            this.title = title;
            return this;
        }

        public NewsResponse build(){
            return new NewsResponse(this);
        }

    }


}
