package com.fertifa.app.models;

import java.sql.Timestamp;

public class Tokens {
    private int id;
    private int userid;
    private String Token;
    private String name;
    private String email;
    private Timestamp creationDate;
    private String firstName;
    private String lastName;
    private int AffiliateId;
    private String company;


    public Tokens(int userid, String name, String email, Timestamp creationDate) {
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.creationDate = creationDate;
    }

    public Tokens(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Tokens(String token,int userID, String companyEmail, String companyName) {
        this.Token = token;
        this.userid = userID;
        this.email = companyEmail;
        this.name = companyName;
    }

    public Tokens(int userid,String token, String firstName, String lastName, String email) {
        this.userid=userid;
        this.Token = token;
        this.firstName =firstName;
        this.lastName = lastName;
        this.email=email;
    }

    public Tokens(String token, String firstName, String lastName, String emailFrom) {
        this.Token = token;
        this.firstName =firstName;
        this.lastName = lastName;
        this.email=emailFrom;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getToken() {
        return Token;
    }

    public int getAffiliateId() {
        return AffiliateId;
    }

    public void setAffiliateId(int affiliateId) {
        AffiliateId = affiliateId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setToken(String token) {
        Token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
