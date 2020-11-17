package com.fertifa.app.payments;

import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.Token;

public class StripeModel extends StripeResponse {

    private String orderId;
    private int orderAmount;
    private Token token;
    private Charge charge;
    private Customer customer;
    private Invoice invoice;

    public StripeModel() {
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public int getOrderAmount() {
        return orderAmount * 100;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public Charge getCharge() {
        return charge;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public StripeModel(Token token) {
        this.token = token;
    }

    public StripeModel(boolean success, String message, Token token) {
        super(success, message);
        this.token = token;
    }


    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
