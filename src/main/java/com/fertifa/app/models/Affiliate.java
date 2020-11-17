package com.fertifa.app.models;


import com.fertifa.app.adminSide.listeners.AF;
import com.fertifa.app.converters.DateConverter;
import com.fertifa.app.utils.TimeController;

import java.sql.Timestamp;

public class Affiliate extends AF {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int status;
    private String role;
    private String creationDate;
    private String registeredDate;
    private String gender;
    private String secretKey;
    private String company;
    private String packageId;

    public Affiliate(String firstName, String lastName, String email, String password, int status, String creationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.creationDate = creationDate;
    }

    public Affiliate(String email) {
        this.email = email;
    }

    public Affiliate() {
    }


    public String getPackageId() {
        return packageId;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Affiliate(String emails, String firrstnames, String lastnames) {
        this.email = emails;
        this.firstName = firrstnames;
        this.lastName = lastnames;
    }


    public Affiliate(String emails, String firsts, String lasts, String ids) {
        this.email = emails;
        this.firstName = firsts;
        this.lastName = lasts;
        this.id = Integer.parseInt(ids);
    }

    public Affiliate(int id, String firstname, String lastname, String email, int status, String role, String creation_date,String secret_key) {
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.status = status;
        this.role = role;
        this.creationDate = creation_date;
        this.secretKey=secret_key;
    }


    public Affiliate(String[] ids, String[] genders, String password, String ids1) {
        this.id = Integer.parseInt(ids[0]);
        this.gender = genders[0];
        this.password = password;
        this.secretKey = ids1;
    }

    public Affiliate(String[] ids, String[] firstNames, String[] lastNames, String[] emails, String[] genders, int affiliateActive, String affiliateRoleName, String password, String secretKey) {
        this.id = Integer.parseInt(ids[0]);
        this.firstName = firstNames[0];
        this.lastName = lastNames[0];
        this.email = emails[0];
        this.gender = genders[0];
        this.status = affiliateActive;
        this.role = affiliateRoleName;
        this.password = password;
        this.secretKey = secretKey;
    }

    public Affiliate(String email, String packageId) {
        this.email = email;
        this.packageId=packageId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getGetCreationDate() {
        return DateConverter.convertDateWithRegex(creationDate);
    }

    public int getGetCountCreationDate() {
        Timestamp today = TimeController.getTodayDate();
        Timestamp createdDate = DateConverter.convertStringToTimestamp(creationDate);
        return (int) ((today.getTime() - createdDate.getTime()) / 86400000);
    }

    @Override
    public boolean getAffiliateOnline(int id) {
        return super.getAffiliateOnline(getId());
    }
}
