package com.fertifa.app.models;

import com.fertifa.app.converters.DateConverter;

public class ShoppingCartFinal {
    private int id;
    private int user_id;
    private int order_id;
    private String serviceName;
    private float total_price;
    private String order_dates;
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
    private String stripe_order_id;
    private int stripe_order_amount;
    private String stripe_order_datecreated;
    private String stripe_order_serialize;
    private int stripe_order_card_numbers;
    private String stripe_user_id;
    private int company_id;
    private int service_id;
    private int count;
    private String SimpleDate;
    private int TotalPrice;
    private float day_total;
    private float price;

    public ShoppingCartFinal(String firstName, String lastName, float total, String serviceName, int orderid,String orderDate) {
        this.serviceName = serviceName;
        this.cardholderfirstname = firstName;
        this.cardholderlastname = lastName;
        this.total_price =total;
        this.order_id = orderid;
        this.order_dates = orderDate;
    }

    public ShoppingCartFinal(String firstName, String lastName, float total, String serviceName, int orderid,String orderDate,String date) {
        this.serviceName = serviceName;
        this.cardholderfirstname = firstName;
        this.cardholderlastname = lastName;
        this.total_price =total;
        this.order_id = orderid;
        this.order_dates = orderDate;
        this.SimpleDate = date;
    }

    public ShoppingCartFinal() {
    }

    public float getDay_total() {
        return day_total;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDay_total(float day_total) {
        this.day_total = day_total;
    }

    public ShoppingCartFinal(String timestamp, String servieName, int count) {
        this.order_dates = timestamp;
        this.serviceName = servieName;
        this.count = count;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getSimpleDate() {
        return SimpleDate;
    }

    public void setSimpleDate(String simpleDate) {
        SimpleDate = simpleDate;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public String getOrder_dates() {
        return order_dates;
    }

    public void setOrder_dates(String order_dates) {
        this.order_dates = order_dates;
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

    public String getStripe_order_id() {
        return stripe_order_id;
    }

    public void setStripe_order_id(String stripe_order_id) {
        this.stripe_order_id = stripe_order_id;
    }

    public int getStripe_order_amount() {
        return stripe_order_amount;
    }

    public void setStripe_order_amount(int stripe_order_amount) {
        this.stripe_order_amount = stripe_order_amount;
    }

    public String getStripe_order_datecreated() {
        return stripe_order_datecreated;
    }

    public void setStripe_order_datecreated(String stripe_order_datecreated) {
        this.stripe_order_datecreated = stripe_order_datecreated;
    }

    public String getStripe_order_serialize() {
        return stripe_order_serialize;
    }

    public void setStripe_order_serialize(String stripe_order_serialize) {
        this.stripe_order_serialize = stripe_order_serialize;
    }

    public int getStripe_order_card_numbers() {
        return stripe_order_card_numbers;
    }

    public void setStripe_order_card_numbers(int stripe_order_card_numbers) {
        this.stripe_order_card_numbers = stripe_order_card_numbers;
    }

    public String getStripe_user_id() {
        return stripe_user_id;
    }

    public void setStripe_user_id(String stripe_user_id) {
        this.stripe_user_id = stripe_user_id;
    }

    public String getGetOrderDates() {
        return DateConverter.convertDateWithRegex(order_dates);
    }

    public String getGetstripeOrderDatecreated(){
        return DateConverter.convertDateWithRegex(stripe_order_datecreated);
    }
}
