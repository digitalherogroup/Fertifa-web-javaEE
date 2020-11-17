package com.fertifa.app.adminSide.news;

import java.util.List;

public class NewsRequest {
    private final int id;
    private final String title;
    private final String Description;
    private final int categoryActive;
    private final String imageLink;
    private final List<String> categoryId;
    private final String Content;


    public NewsRequest(NewsRequestBuilder newsRequestBuilder) {
        this.id = newsRequestBuilder.id;
        this.title = newsRequestBuilder.title;
        this.Description = newsRequestBuilder.Description;
        this.categoryActive = newsRequestBuilder.categoryActive;
        this.imageLink = newsRequestBuilder.imageLink;
        this.categoryId = newsRequestBuilder.categoryId;
        this.Content = newsRequestBuilder.Content;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getDescription() {
        return Description;
    }

    public int getCategoryActive() {
        return categoryActive;
    }

    public List<String> getCategoryId() {
        return categoryId;
    }

    public String getContent() {
        return Content;
    }

    public static class NewsRequestBuilder {
        private int id;
        private String title;
        private String Description;
        private int categoryActive;
        private String imageLink;
        private List<String> categoryId;
        private String Content;

        public NewsRequestBuilder imageLink(String imageLink) {
            this.imageLink = imageLink;
            return this;
        }

        public NewsRequestBuilder id(int id) {
            this.id = id;
            return this;
        }

        public NewsRequestBuilder title(String title) {
            this.title = title;
            return this;
        }

        public NewsRequestBuilder Description(String Description) {
            this.Description = Description;
            return this;
        }

        public NewsRequestBuilder categoryActive(int categoryActive) {
            this.categoryActive = categoryActive;
            return this;
        }

        public NewsRequestBuilder categoryId(List<String> categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public NewsRequestBuilder Content(String Content) {
            this.Content = Content;
            return this;
        }

        public NewsRequest build() {
            return new NewsRequest(this);
        }
    }
}

