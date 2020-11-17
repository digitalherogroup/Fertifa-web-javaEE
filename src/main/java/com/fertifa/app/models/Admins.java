package com.fertifa.app.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Admins {

    private int id;
    private String email;
    private String password;
    private String address;
    private String phonenumber;
    private int branchid;
    private Date creationDate;
    private Date lastLoginDate;
    private Date updateDate;
    private String firstName;
    private String lastName;
    private int status;
    private List<Admins> adminsList = new ArrayList<>();
    private String role;

    public Admins(String email, String password, String address, String phonenumber, int branchid, Date creationDate, Date lastLoginDate, String firstName, String lastName, int status) {
        this.email = email;
        this.password = password;
        this.address = address;
        this.phonenumber = phonenumber;
        this.branchid = branchid;
        this.creationDate = creationDate;
        this.lastLoginDate = lastLoginDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    public Admins(String firstName, String lastName, String email, int status, String address, String phonenumber, String password, Timestamp today, int branchid) {
        this.email = email;
        this.address = address;
        this.phonenumber = phonenumber;
        this.branchid = branchid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.creationDate = today;
        this.status = status;
    }

    public Admins(String firstName, String lastName, int status, String address, String phonenumber, String password, int branchid) {
        this.address = address;
        this.phonenumber = phonenumber;
        this.branchid = branchid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.status = status;
    }

    public Admins() {
    }

    public Admins(int userId, int userRole, String userEmail) {
        this.id=userId;
        if(userRole == 1) {
            this.role = "ADMIN";
        }
        this.email=userEmail;
    }

    public Admins(String email, String password) {
        this.email=email;
        this.password=password;
    }

    public Admins(int id, String firstName, String lastName, String email, String phonenumber, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phonenumber = phonenumber;
        this.role=role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Admins(List<Admins> allAdminListById) {
        this.adminsList = allAdminListById;
    }

    public Admins(int id) {
        this.id=id;
    }

    public Admins(int id, String firstname, String lastname, String email, int status, String phonenumber, Timestamp creation_date, String role,int branch_id) {
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.status = status;
        this.phonenumber = phonenumber;
        this.creationDate = creation_date;
        this.role=role;
        this.branchid=branch_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Admins> getAdminsList() {
        return adminsList;
    }

    public void setAdminsList(List<Admins> adminsList) {
        this.adminsList = adminsList;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getBranchid() {
        return branchid;
    }

    public void setBranchid(int branchid) {
        this.branchid = branchid;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admins admins = (Admins) o;
        return id == admins.id &&
                branchid == admins.branchid &&
                status == admins.status &&
                Objects.equals(email, admins.email) &&
                Objects.equals(password, admins.password) &&
                Objects.equals(address, admins.address) &&
                Objects.equals(phonenumber, admins.phonenumber) &&
                Objects.equals(creationDate, admins.creationDate) &&
                Objects.equals(lastLoginDate, admins.lastLoginDate) &&
                Objects.equals(firstName, admins.firstName) &&
                Objects.equals(lastName, admins.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, address, phonenumber, branchid, creationDate, lastLoginDate, firstName, lastName, status);
    }
}