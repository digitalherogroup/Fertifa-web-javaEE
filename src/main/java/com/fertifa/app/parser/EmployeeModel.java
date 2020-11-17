package com.fertifa.app.parser;

import java.sql.Timestamp;

public class EmployeeModel {
    private int id;
    private String firsName;
    private String lastName;
    private String email;
    private int status;
    private int branchId = 3;
    private Timestamp created_date;
    private long company_id;

    public EmployeeModel() {
    }

    public EmployeeModel(String email,String firsName, String lastName ) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.email = email;
    }

    public EmployeeModel(String firsName, String lastName, String email, int status, int branchId, Timestamp created_date, long company_id) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.branchId = branchId;
        this.created_date = created_date;
        this.company_id = company_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public Timestamp getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }

    public long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(long company_id) {
        this.company_id = company_id;
    }

    public boolean isValid() {
        return !firsName.isEmpty() &&
                !lastName.isEmpty() &&
                !email.isEmpty();
    }

    @Override
    public String toString() {
        return "EmployeeModel{" +
                "firsName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", branchId=" + branchId +
                ", created_date=" + created_date +
                ", company_id=" + company_id +
                '}' + "\n";
    }
}