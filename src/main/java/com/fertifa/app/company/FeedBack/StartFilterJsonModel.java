package com.fertifa.app.company.FeedBack;

import java.util.List;

public class StartFilterJsonModel {
    private List<StartFilterModel> feedbackData;
    private String status;


    public StartFilterJsonModel() {
    }

    public StartFilterJsonModel(List<StartFilterModel> feedbackData, String status) {
        this.feedbackData = feedbackData;
        this.status = status;
    }

    public List<StartFilterModel> getFeedbackData() {
        return feedbackData;
    }

    public void setFeedbackData(List<StartFilterModel> feedbackData) {
        this.feedbackData = feedbackData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
