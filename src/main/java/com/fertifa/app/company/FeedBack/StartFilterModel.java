package com.fertifa.app.company.FeedBack;

public class StartFilterModel {
    private String feedbackText = "";
    private String starts = "";
    private float rating = 0;
    private String date = "";
    private String description = "";

    public StartFilterModel() {
    }

    public StartFilterModel(String feedbackText, String starts, float rating, String date, String description) {
        this.feedbackText = feedbackText;
        this.starts = starts;
        this.rating = rating;
        this.date = date;
        this.description = description;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        if (feedbackText != null)
            this.feedbackText = feedbackText;
    }

    public String getStarts() {
        return starts;
    }

    public void setStarts(String starts) {
        if (starts != null)
            this.starts = starts;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if (date != null)
            this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null)
            this.description = description;
    }
}
