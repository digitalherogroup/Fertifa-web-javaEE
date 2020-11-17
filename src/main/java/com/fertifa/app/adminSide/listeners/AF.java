package com.fertifa.app.adminSide.listeners;


import java.util.ArrayList;
import java.util.List;

public class AF {

    private int id;
    private String email;
    private String session;
    private List<AF> objectList = new ArrayList<>();

    public AF(int id, String email, String session) {
        this.id = id;
        this.email = email;
        this.session = session;
    }

    public AF() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getAffiliateOnline(int id) {
        objectList = OnlineAffiliateList.getListUsers();
        return !objectList.isEmpty() && objectList.contains(this.id);

    }
}
