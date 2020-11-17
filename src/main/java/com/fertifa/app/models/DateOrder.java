package com.fertifa.app.models;

public class DateOrder {
    private int id;
    private int order_id;
    private String date;

    public DateOrder(int id, int order_id, String date) {
        this.id = id;
        this.order_id = order_id;
        this.date = date;
    }

    public DateOrder() {
    }

    public DateOrder(String date,int orderId) {
        this.date = date;
        this.order_id = orderId;
    }

    public DateOrder(String timeNow) {
        this.date=timeNow;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
