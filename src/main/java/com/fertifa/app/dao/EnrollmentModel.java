package com.fertifa.app.dao;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentModel {

    private int total;
    private String simpleDate;
    private String date;
    private List<EnrollmentModel> enrollmentModels = new ArrayList<>();

    public EnrollmentModel(int total, String simpleDate) {
        this.total = total;
        this.simpleDate = simpleDate;
    }

    public List<EnrollmentModel> getEnrollmentModels() {
        return enrollmentModels;
    }

    public void setEnrollmentModels(List<EnrollmentModel> enrollmentModels) {
        this.enrollmentModels = enrollmentModels;
    }

    public EnrollmentModel() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSimpleDate() {
        return simpleDate;
    }

    public void setSimpleDate(String simpleDate) {
        this.simpleDate = simpleDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
