package com.fertifa.app.payments;

public class User {
    private String StripeCustomerId;
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private String city;
    private String phoneNumber;
    private String country = "GB";
    private String postal_code = "";
    private String state = "";
    private int fertifaUser_id;

    public User(String stripeCustomerId, String firstName, String lastName, String email) {
        StripeCustomerId = stripeCustomerId;
        this.first_name = firstName;
        this.last_name = lastName;
        this.email = email;
    }

    public User(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public User(String firstName, String lastName, String email, String address, String city, String state, String postCode,String phoneNumber,String country) {
               this.first_name = firstName;
        this.last_name = lastName;
        this.email = email;
        this.address = address;
        this.city=city;
        this.state=state;
        this.postal_code = postCode;
        this.phoneNumber=phoneNumber;
        this.country="GB";
    }
    public User(String stripeCustomerId,String firstName, String lastName, String email, String address, String city, String state, String postCode,String phoneNumber,String country) {
        StripeCustomerId = stripeCustomerId;
        this.first_name = firstName;
        this.last_name = lastName;
        this.email = email;
        this.address = address;
        this.city=city;
        this.state=state;
        this.postal_code = postCode;
        this.phoneNumber=phoneNumber;
        this.country="GB";
    }

    public User(String firstName, String lastName, String email, String address, String city, String state, String postCode, String phoneNumber, String country,
                int userId, String stripeId) {
        this.first_name = firstName;
        this.last_name = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postal_code = postCode;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.fertifaUser_id = userId;
        this.StripeCustomerId = stripeId;

    }


    public User(String firstName, String lastName, String email, String address, String phoneNumber) {
        this.first_name = firstName;
        this.last_name = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber=phoneNumber;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStripeCustomerId() {
        return StripeCustomerId;
    }

    public int getFertifaUser_id() {
        return fertifaUser_id;
    }

    public void setFertifaUser_id(int fertifaUser_id) {
        this.fertifaUser_id = fertifaUser_id;
    }

    public void setStripeCustomerId(String stripeCustomerId) {
        StripeCustomerId = stripeCustomerId;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
