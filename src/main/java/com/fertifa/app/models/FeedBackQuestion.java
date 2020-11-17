package com.fertifa.app.models;

import java.sql.Timestamp;

public class FeedBackQuestion {

    private int id;
    private String feedBackQuestion;
    private float feedbackrating;
    private int feedbackstatus;
    private Timestamp feedbackdate;
    private String feedbacksubject;
    private String feddbacktext;
    private int company_id;


    public FeedBackQuestion(int id, String feedBackQuestion, float feedbackrating, int feedbackstatus, Timestamp feedbackdate, String feedbacksubject, String feddbacktext, int company_id) {
        this.id = id;
        this.feedBackQuestion = feedBackQuestion;
        this.feedbackrating = feedbackrating;
        this.feedbackstatus = feedbackstatus;
        this.feedbackdate = feedbackdate;
        this.feedbacksubject = feedbacksubject;
        this.feddbacktext = feddbacktext;
        this.company_id = company_id;
    }

    public FeedBackQuestion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeedBackQuestion() {
        return feedBackQuestion;
    }

    public void setFeedBackQuestion(String feedBackQuestion) {
        this.feedBackQuestion = feedBackQuestion;
    }

    public float getFeedbackrating() {
        return feedbackrating;
    }

    public void setFeedbackrating(float feedbackrating) {
        this.feedbackrating = feedbackrating;
    }

    public int getFeedbackstatus() {
        return feedbackstatus;
    }

    public void setFeedbackstatus(int feedbackstatus) {
        this.feedbackstatus = feedbackstatus;
    }

    public Timestamp getFeedbackdate() {
        return feedbackdate;
    }

    public void setFeedbackdate(Timestamp feedbackdate) {
        this.feedbackdate = feedbackdate;
    }

    public String getFeedbacksubject() {
        return feedbacksubject;
    }

    public void setFeedbacksubject(String feedbacksubject) {
        this.feedbacksubject = feedbacksubject;
    }

    public String getFeddbacktext() {
        return feddbacktext;
    }

    public void setFeddbacktext(String feddbacktext) {
        this.feddbacktext = feddbacktext;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }


}
