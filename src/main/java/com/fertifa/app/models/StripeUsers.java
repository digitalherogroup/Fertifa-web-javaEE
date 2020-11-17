package com.fertifa.app.models;

public class StripeUsers {
    private int id;
    private int user_id;
    private int status;
    private String cardholderfirstname;
    private String cardholderlastname;
    private String cardholderphone;
    private String cardholderemail;
    private String cardholderaddress;
    private String cardholdercity;
    private String cardholdercountry;
    private String cardholderstat;
    private String cardholderpostcode;
    private String stripe_user_id;

    public StripeUsers(int id, int user_id, int status, String cardholderfirstname, String cardholderlastname, String cardholderphone, String cardholderemail, String cardholderaddress, String cardholdercity, String cardholdercountry, String cardholderstat, String cardholderpostcode, String stripe_user_id) {
        this.id = id;
        this.user_id = user_id;
        this.status = status;
        this.cardholderfirstname = cardholderfirstname;
        this.cardholderlastname = cardholderlastname;
        this.cardholderphone = cardholderphone;
        this.cardholderemail = cardholderemail;
        this.cardholderaddress = cardholderaddress;
        this.cardholdercity = cardholdercity;
        this.cardholdercountry = cardholdercountry;
        this.cardholderstat = cardholderstat;
        this.cardholderpostcode = cardholderpostcode;
        this.stripe_user_id = stripe_user_id;
    }

    public StripeUsers() {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCardholderfirstname() {
        return cardholderfirstname;
    }

    public void setCardholderfirstname(String cardholderfirstname) {
        this.cardholderfirstname = cardholderfirstname;
    }

    public String getCardholderlastname() {
        return cardholderlastname;
    }

    public void setCardholderlastname(String cardholderlastname) {
        this.cardholderlastname = cardholderlastname;
    }

    public String getCardholderphone() {
        return cardholderphone;
    }

    public void setCardholderphone(String cardholderphone) {
        this.cardholderphone = cardholderphone;
    }

    public String getCardholderemail() {
        return cardholderemail;
    }

    public void setCardholderemail(String cardholderemail) {
        this.cardholderemail = cardholderemail;
    }

    public String getCardholderaddress() {
        return cardholderaddress;
    }

    public void setCardholderaddress(String cardholderaddress) {
        this.cardholderaddress = cardholderaddress;
    }

    public String getCardholdercity() {
        return cardholdercity;
    }

    public void setCardholdercity(String cardholdercity) {
        this.cardholdercity = cardholdercity;
    }

    public String getCardholdercountry() {
        return cardholdercountry;
    }

    public void setCardholdercountry(String cardholdercountry) {
        this.cardholdercountry = cardholdercountry;
    }

    public String getCardholderstat() {
        return cardholderstat;
    }

    public void setCardholderstat(String cardholderstat) {
        this.cardholderstat = cardholderstat;
    }

    public String getCardholderpostcode() {
        return cardholderpostcode;
    }

    public void setCardholderpostcode(String cardholderpostcode) {
        this.cardholderpostcode = cardholderpostcode;
    }

    public String getStripe_user_id() {
        return stripe_user_id;
    }

    public void setStripe_user_id(String stripe_user_id) {
        this.stripe_user_id = stripe_user_id;
    }
}
