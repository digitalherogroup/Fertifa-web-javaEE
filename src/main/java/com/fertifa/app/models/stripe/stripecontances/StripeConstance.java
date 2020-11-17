package com.fertifa.app.models.stripe.stripecontances;

public class StripeConstance {
    public static final String SUBSCRIPTION_TITLE = "Monthly Subscription for Employee ";
    public static final String SUBSCRIPTION_DESCRIPTION = "Fertifa subscription plan ";
    public static final Integer TRAIL_PERIOD = 3;

    //Paths
    public static final String BASE_URL_STRIPE_RESTFUL_REQUESTS = "http://second.fertifabenefits.com/stripe/";
    public static final String BASE_PATH_STRIPE_RESTFUL_REQUESTS = "stripe/v1/";
    public static final String CREATE_NEW_CARD = BASE_URL_STRIPE_RESTFUL_REQUESTS +
            BASE_PATH_STRIPE_RESTFUL_REQUESTS +
            "card/create";

    public static final String CREATE_NEW_CUSTOMER = BASE_URL_STRIPE_RESTFUL_REQUESTS +
            BASE_PATH_STRIPE_RESTFUL_REQUESTS +
            "customer/create";

    public static final String CREATE_NEW_SUBSCRIPTION = BASE_URL_STRIPE_RESTFUL_REQUESTS +
            BASE_PATH_STRIPE_RESTFUL_REQUESTS +
            "sub/create";

    public static final String DELETE_CARD = BASE_URL_STRIPE_RESTFUL_REQUESTS +
            BASE_PATH_STRIPE_RESTFUL_REQUESTS +
            "card/delete";

    public static final String DELETE_CARD2 = "http://localhost:2222/stripe/v1/card/delete";

    public static final String PAY_CARD = BASE_URL_STRIPE_RESTFUL_REQUESTS +
            BASE_PATH_STRIPE_RESTFUL_REQUESTS +
            "charge/create";
    public static final String RETRIEVE_CUSTOMER = BASE_URL_STRIPE_RESTFUL_REQUESTS +
            BASE_PATH_STRIPE_RESTFUL_REQUESTS +
            "customer/retrieve";
}
