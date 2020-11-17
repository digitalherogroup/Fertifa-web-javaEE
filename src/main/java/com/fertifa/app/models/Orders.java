package com.fertifa.app.models;

import java.sql.Timestamp;

public class Orders {
    private int id;
    private int user_id;
    private int date_id;
    private int service_id;
    private String appointment;
    private String clinic;
    private String address;
    private float coast;
    private int status;
    private int timeId;
    private Timestamp creation_date;
    private String SessionToken;

    public Orders(int id, int user_id, int date_id, int service_id, String appointment, String clinic, String address, float coast) {
        this.id = id;
        this.user_id = user_id;
        this.date_id = date_id;
        this.service_id = service_id;
        this.appointment = appointment;
        this.clinic = clinic;
        this.address = address;
        this.coast = coast;
    }

    public Orders() {
    }



    public Orders(int userId, int serviceId,float serviceCoast, int orderstatuspending) {
        this.user_id = userId;
        this.service_id = serviceId;
        this.coast = serviceCoast;
        this.status = orderstatuspending;
    }

    public Orders(int userId, int serviceId, float serviceCoast, int orderstatuspending, String sesionToken, Timestamp timestamp) {
        this.user_id = userId;
        this.service_id = serviceId;
        this.coast = serviceCoast;
        this.status = orderstatuspending;
        this.creation_date = timestamp;
        this.SessionToken = sesionToken;
    }

    public Orders(int userId, int serviceId, float serviceCoast, int dateId, int timesOrder, int orderstatuspending, String sesionToken, Timestamp timestamp) {
        this.user_id = userId;
        this.service_id = serviceId;
        this.coast = serviceCoast;
        this.date_id = dateId;
        this.timeId = timesOrder;
        this.status = orderstatuspending;
        this.creation_date = timestamp;
        this.SessionToken = sesionToken;
    }

    public Timestamp getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Timestamp creation_date) {
        this.creation_date = creation_date;
    }

    public String getSessionToken() {
        return SessionToken;
    }

    public void setSessionToken(String sessionToken) {
        SessionToken = sessionToken;
    }

    public Orders(int date_id) {
        this.date_id=date_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    public int getId() {
        return id;
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

    public int getDate_id() {
        return date_id;
    }

    public void setDate_id(int date_id) {
        this.date_id = date_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getCoast() {
        return coast;
    }

    public void setCoast(float coast) {
        this.coast = coast;
    }
}
