package com.fertifa.app.models;

import java.sql.Timestamp;

public class FeedQuestion {
    private int id;
    private int user_id;
    private float feedbackrating;
    private int feedbackStatus;
    private Timestamp creationDate;
    private String feddbackSubject;
    private String feedbackText;
    private String newString;
    private String simpleDate;
    private int companyid;


    public FeedQuestion(int id, int user_id, float feedbackrating, int feedbackStatus, Timestamp creationDate, String feddbackSubject, String feedbackText) {
        this.id = id;
        this.user_id = user_id;
        this.feedbackrating = feedbackrating;
        this.feedbackStatus = feedbackStatus;
        this.creationDate = creationDate;
        this.feddbackSubject = feddbackSubject;
        this.feedbackText = feedbackText;
    }

    public FeedQuestion() {

    }

    public FeedQuestion(int userId, int feedBackStatus, Timestamp timestamp, String feedBackName) {
        this.user_id = userId;
        this.feedbackStatus = feedBackStatus;
        this.feddbackSubject = feedBackName;
        this.creationDate = timestamp;
    }

    public FeedQuestion(int rating) {
        this.feedbackrating = rating;
    }

    public FeedQuestion(String feddbackSubject,  String feedbackText, String parse, int user_id, int feedbackStatus, int id, float feedbackrating, String newString) {
        this.user_id = user_id;
        this.feedbackrating = feedbackrating;
        this.feedbackStatus = feedbackStatus;
        this.simpleDate = parse;
        this.feddbackSubject = feddbackSubject;
        this.id = id;
        this.feedbackText = feedbackText;
        this.newString=newString;
    }

    public FeedQuestion(int userId, int feedbackstatuspending, Timestamp timestamp, int ratingValue, String subject, String detail,int companyId) {
        this.user_id = userId;
        this.feedbackStatus = feedbackstatuspending;
        this.creationDate = timestamp;
        this.feedbackrating = ratingValue;
        this.feddbackSubject = subject;
        this.feedbackText = detail;
        this.companyid = companyId;
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    public String getSimpleDate() {
        return simpleDate;
    }

    public void setSimpleDate(String simpleDate) {
        this.simpleDate = simpleDate;
    }

    public int getId() {
        return id;
    }

    public String getNewString() {
        return newString;
    }

    public void setNewString(String newString) {
        this.newString = newString;
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

    public float getFeedbackrating() {
        return feedbackrating;
    }

    public void setFeedbackrating(float feedbackrating) {
        this.feedbackrating = feedbackrating;
    }

    public int getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(int feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getFeddbackSubject() {
        return feddbackSubject;
    }

    public void setFeddbackSubject(String feddbackSubject) {
        this.feddbackSubject = feddbackSubject;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

}
