package com.fertifa.app.payments;

public class Order {
    private int StripeCustomerId;
    private User user;
    private Integer ChargeAmountDollars;


    public Order(int stripeCustomerId, User user, Integer chargeAmountDollars) {
        StripeCustomerId = stripeCustomerId;
        this.user = user;
        ChargeAmountDollars = chargeAmountDollars;
    }

    public Order(User user, Integer chargeAmountDollars) {
        this.user = user;
        ChargeAmountDollars = chargeAmountDollars;
    }

    public Order() {
    }


    public int getStripeCustomerId() {
        return StripeCustomerId;
    }

    public void setStripeCustomerId(int stripeCustomerId) {
        StripeCustomerId = stripeCustomerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getChargeAmountDollars() {
        return ChargeAmountDollars;
    }

    public void setChargeAmountDollars(Integer chargeAmountDollars) {
        ChargeAmountDollars = chargeAmountDollars;
    }
}
