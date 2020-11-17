package com.fertifa.app.models;

public class TimesOrder {

    private int id;
    private String time;
    private int date_id;
    private int order_id;

    public TimesOrder(int id, String time, int date_id, int order_id) {
        this.id = id;
        this.time = time;
        this.date_id = date_id;
        this.order_id = order_id;
    }


    public TimesOrder(String time,int date_id,int order_id) {
        this.time = time;
        this.date_id = date_id;
        this.order_id = order_id;
    }

    public TimesOrder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDate_id() {
        return date_id;
    }

    public void setDate_id(int date_id) {
        this.date_id = date_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
