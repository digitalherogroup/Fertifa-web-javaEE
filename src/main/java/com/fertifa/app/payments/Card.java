package com.fertifa.app.payments;

public class Card {
    private int id;
    private int user_id;
    private String number;
    private String months;
    private String years;
    private String cvc;

    public Card(int id, int user_id, String number, String months, String years, String cvc) {
        this.id = id;
        this.user_id = user_id;
        this.number = number;
        this.months = months;
        this.years = years;
        this.cvc = cvc;
    }

    public Card(int user_id, String number, String months, String years, String cvc) {
                this.user_id = user_id;
        this.number = number;
        this.months = months;
        this.years = years;
        this.cvc = cvc;
    }



    public Card() {
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
}
