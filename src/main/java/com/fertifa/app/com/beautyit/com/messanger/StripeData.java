package com.fertifa.app.com.beautyit.com.messanger;


import com.fertifa.app.models.stripe.NewStripeCardRequest;
import com.fertifa.app.models.stripe.NewStripeChargeShoppingCardRequest;
import com.fertifa.app.models.stripe.NewStripeSubscriptionRequest;
import com.fertifa.app.models.stripe.NewStripeUsersRequest;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.fertifa.app.models.stripe.stripecontances.StripeConstance.CREATE_NEW_CUSTOMER;
import static com.fertifa.app.models.stripe.stripecontances.StripeConstance.CREATE_NEW_SUBSCRIPTION;
import static com.fertifa.app.models.stripe.stripecontances.StripeConstance.DELETE_CARD;
import static com.fertifa.app.models.stripe.stripecontances.StripeConstance.RETRIEVE_CUSTOMER;


public class StripeData {

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public static String sendCardRequest(NewStripeCardRequest jsonBody) {
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target(CREATE_NEW_CARD);
//        String responseMedia = target.request(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .post(Entity.json(jsonBody), String.class);
//        System.out.println("responseMedia " +responseMedia);
//        return responseMedia;
//    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public static String sendNewCustomerRequest(NewStripeUsersRequest jsonBody) {
//        System.out.println("inside NewStripeUsersResponse");
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target(CREATE_NEW_CUSTOMER);
//        String responseMedia = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(Entity.json(jsonBody), String.class);
//        System.out.println("responseMedia " + responseMedia);
//        System.out.println();
//        return responseMedia;
//    }


    public static String newCustomer(NewStripeUsersRequest jsonBody){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(CREATE_NEW_CUSTOMER,
                jsonBody,
                String.class);
        return response;
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public static String sendSubscriptionRequest(NewStripeSubscriptionRequest jsonBody) {
//        System.out.println("inside NewStripeSubscriptionResponse");
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target(CREATE_NEW_SUBSCRIPTION);
//        String responseMedia = target.request(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .post(Entity.json(jsonBody), String.class);
//        System.out.println("responseMedia " +  responseMedia);
//        System.out.println();
//        return responseMedia;
//    }
    public static String sendSubscriptionRequest(NewStripeSubscriptionRequest jsonBody) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(CREATE_NEW_SUBSCRIPTION,
                jsonBody,
                String.class);
        return response;
    }

//    @DELETE
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public static String sendCardDeleteRequest(NewStripeCardRequest jsonBody, HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("inside NewStripeSubscriptionResponse");
//        Client client = ClientBuilder.newClient(new ClientConfig().register( NewStripeCardRequest.class));
//        WebTarget target = client.target(DELETE_CARD + "/" + jsonBody.getCardId() + "/" +jsonBody.getCustomerId());
//        RestTemplate restTemplate = new RestTemplate();
//        String responses = restTemplate.postForObject("http://second.fertifabenefits.com/stripe/stripe/v1/sub/create",
//                jsonBody,
//                String.class);
//        System.out.println("responseMedia " +  responseMedia);
//        System.out.println();
//        return responses;
//    }

    public static String sendCardDeleteRequest(NewStripeCardRequest jsonBody,HttpServletRequest request,HttpServletResponse response) {
        RestTemplate restTemplate = new RestTemplate();
        String url = DELETE_CARD + "/" + jsonBody.getCardId() + "/" +jsonBody.getCustomerId();
        String responses = restTemplate.postForObject(url,
                jsonBody,
                String.class);
        return responses;
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public static String chargeSHoppingCard(NewStripeChargeShoppingCardRequest jsonBody) {
//        System.out.println("chargeShoppingCard");
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target(PAY_CARD);
//        String responseMedia = target.request(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .post(Entity.json(jsonBody), String.class);
//        System.out.println("responseMedia " +  responseMedia);
//        System.out.println();
//        return responseMedia;
//    }

    public static String chargeSHoppingCard(NewStripeChargeShoppingCardRequest jsonBody) {
        RestTemplate restTemplate = new RestTemplate();
        String responses = restTemplate.postForObject("http://second.fertifabenefits.com/stripe/stripe/v1/charge/create",
                jsonBody,
                String.class);
        return responses;
    }
//    @GET
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public static String sendCustomerRetrieveRequest(String jsonBody) {
//        System.out.println("sendCustomerRetrieveRequest");
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target(RETRIEVE_CUSTOMER);
//        String responseMedia = target.request(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .post(Entity.json(jsonBody), String.class);
//        System.out.println("responseMedia " +  responseMedia);
//        System.out.println();
//        return responseMedia;
//    }

    public static String sendCustomerRetrieveRequest(String jsonBody) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(RETRIEVE_CUSTOMER,
                jsonBody,
                String.class);
        return response;

    }

    public static String newCard(NewStripeCardRequest jsonBody) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject("http://second.fertifabenefits.com/stripe/stripe/v1/card/create",
                jsonBody,
                String.class);
        return response;
    }
}
