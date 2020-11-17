package com.fertifa.app.models;

import com.fertifa.app.converters.DateConverter;

import java.sql.Timestamp;

public class DirectChater {
    private String session_token;
    private int type;
    private int status;
    private int from_id;
    private int to_id;
    private int branch_id;
    private Timestamp creation_date;
    private String session_name;
    private String email;
    private String address;
    private String domain;
    private String Package;
    private String firstname;
    private String lastname;
    private String CompanyName;
    private int gender;
    private int age;
    private int companyid;
    private String imagelink;
    private int importantForAdmin;
    private int importantForUser;



    public DirectChater(String sessionToken, int type, int status, int from_id, int to_id, Timestamp creation_date,
                        String session_name, int branch_id, String email, String address,
                        String domain, String aPackage, String firstname, String lastname, int companyid, String companyName,
                        int gender, int age, String imagelink, int ImportantForAdmin, int importantForUser) {
        this.session_token = sessionToken;
        this.type = type;
        this.status = status;
        this.from_id = from_id;
        this.to_id = to_id;
        this.branch_id = branch_id;
        this.creation_date = creation_date;
        this.session_name = session_name;
        this.email = email;
        this.address = address;
        this.domain = domain;
        Package = aPackage;
        this.firstname = firstname;
        this.lastname = lastname;
        CompanyName = companyName;
        this.gender = gender;
        this.age = age;
        this.companyid = companyid;
        this.imagelink = imagelink;
        this.importantForAdmin=ImportantForAdmin;
        this.importantForUser=importantForUser;
    }

    public DirectChater(String sessionToken, int type, int status, int from_id, int to_id, Timestamp creation_date,String session_name, String email) {

        this.session_token = sessionToken;
        this.type = type;
        this.status = status;
        this.from_id = from_id;
        this.to_id = to_id;
        this.session_name = session_name;
        this.email = email;

    }

    public int getImportantForAdmin() {
        return importantForAdmin;
    }

    public void setImportantForAdmin(int importantForAdmin) {
        this.importantForAdmin = importantForAdmin;
    }

    public int getImportantForUser() {
        return importantForUser;
    }

    public void setImportantForUser(int importantForUser) {
        this.importantForUser = importantForUser;
    }

    public String getSession_token() {
        return session_token;
    }

    public void setSession_token(String session_token) {
        this.session_token = session_token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }

    public int getTo_id() {
        return to_id;
    }

    public void setTo_id(int to_id) {
        this.to_id = to_id;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public Timestamp getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Timestamp creation_date) {
        this.creation_date = creation_date;
    }

    public String getSession_name() {
        return session_name;
    }

    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPackage() {
        return Package;
    }

    public void setPackage(String aPackage) {
        Package = aPackage;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getChatCreationDate() {
        return DateConverter.convertDateWithRegex(creation_date);
    }

}
