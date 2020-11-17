package com.fertifa.app.models;

public class FullDetailAppintment {

    private int id;
    private int order_id;
    private String date;
    private String serviceName;
    private float servicePrice;
    private String firstName;
    private String lastName;
    private String companyname;
    private int status;
    private String dateCreated;

    private float getServicePrice;

    private String companyName;


    public FullDetailAppintment(int id, String date, String serviceName, float servicePrice) {
        this.id = id;
        this.date = date;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
    }

    public FullDetailAppintment(String dates, String serviceName, float servicePrice) {
        this.date = dates;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
    }

    public FullDetailAppintment(String dates, String serviceName, float servicePrice, int orderid, String firstName, String lastName, String companyName, int status) {
        this.date = dates;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.order_id = orderid;
        this.firstName=firstName;
        this.lastName=lastName;
        this.companyname =companyName;
        this.status=status;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public float getGetServicePrice() {
        return getServicePrice;
    }

    public void setGetServicePrice(float getServicePrice) {
        this.getServicePrice = getServicePrice;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public FullDetailAppintment(String date, String serviceName, float getServicePrice, int id, String DateString) {
        this.date = date;
        this.serviceName = serviceName;
        this.servicePrice = getServicePrice;
        this.order_id = id;
        this.dateCreated = DateString;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
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

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public float getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(float servicePrice) {
        this.servicePrice = servicePrice;
    }


}
