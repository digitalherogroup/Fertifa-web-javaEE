package com.fertifa.app.models;

public class ShoppingCardSalesModel {

    private float dayTotal;
    private String dateString;

    public ShoppingCardSalesModel() {
    }

    public ShoppingCardSalesModel(float dayTotal, String dateString) {
        this.dayTotal = dayTotal;
        this.dateString = dateString;
    }

    public float getDayTotal() {
        return dayTotal;
    }

    public void setDayTotal(float dayTotal) {
        this.dayTotal = dayTotal;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}
