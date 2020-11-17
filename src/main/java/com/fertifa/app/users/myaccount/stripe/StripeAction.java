package com.fertifa.app.users.myaccount.stripe;


import com.fertifa.app.com.beautyit.com.messanger.StripeData;
import com.fertifa.app.controllers.stripecontroller.NewStripeCardController;
import com.fertifa.app.controllers.stripecontroller.NewStripeSubscriptionController;
import com.fertifa.app.controllers.stripecontroller.NewStripeUsersController;
import com.fertifa.app.models.Users;
import com.fertifa.app.models.stripe.NewStripeCardRequest;
import com.fertifa.app.models.stripe.NewStripeCardResponse;
import com.fertifa.app.models.stripe.NewStripeSubscriptionRequest;
import com.fertifa.app.models.stripe.NewStripeSubscriptionResponse;
import com.fertifa.app.models.stripe.NewStripeUsersRequest;
import com.fertifa.app.models.stripe.NewStripeUsersResponse;
import com.google.gson.Gson;
import com.stripe.exception.StripeException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static com.fertifa.app.models.stripe.stripecontances.StripeConstance.SUBSCRIPTION_DESCRIPTION;
import static com.fertifa.app.models.stripe.stripecontances.StripeConstance.SUBSCRIPTION_TITLE;
import static com.fertifa.app.models.stripe.stripecontances.StripeConstance.TRAIL_PERIOD;


public class StripeAction {

    private static NewStripeCardController newStripeCardController = new NewStripeCardController();
    private static NewStripeSubscriptionController newStripeSubscriptionController = new NewStripeSubscriptionController();
    private static NewStripeUsersController newStripeUsersController = new NewStripeUsersController();
    private static NewStripeUsersResponse newStripeUsersResponse = new NewStripeUsersResponse();
    private static NewStripeCardResponse newStripeCardResponse = new NewStripeCardResponse();
    private static NewStripeSubscriptionResponse newStripeSubscriptionResponse = new NewStripeSubscriptionResponse();


    public static String createSubscription(HttpServletRequest request) throws SQLException {
        String message = "";
        NewStripeSubscriptionRequest newStripeSubscriptionRequest = NewStripeSubscriptionRequest
                .builder()
                .cardId(newStripeCardResponse.getData().getCard().getId())
                .customerId(newStripeUsersResponse.getData().getId())
                .title(SUBSCRIPTION_TITLE)
                .description(SUBSCRIPTION_DESCRIPTION)
                .price(Integer.parseInt(request.getParameter("price")) * 100)
                .trial_period_days(TRAIL_PERIOD)
                .build();
        try {
            String name = StripeData.sendSubscriptionRequest(newStripeSubscriptionRequest);
            newStripeSubscriptionResponse = new Gson().fromJson(name, NewStripeSubscriptionResponse.class);
            newStripeSubscriptionResponse.setCustomerId(newStripeUsersResponse.getCustomerId());
            newStripeSubscriptionResponse.setPrice(newStripeSubscriptionRequest.getPrice());
            if (Integer.parseInt(newStripeSubscriptionResponse.getCode()) >= 200 && Integer.parseInt(newStripeSubscriptionResponse.getCode()) <= 299) {
                {
                    if (newStripeSubscriptionController.saveNewStripeSubscription(newStripeSubscriptionResponse) > 0) {
                        System.out.println("createStripeCard success");
                        message = "Subscription created successfully";
                    } else {
                        message = "Something went wrong.";
                    }
                }
            }
        } catch (Exception e) {
            message = "Something went wrong.";
        }
        return message;
    }

    public static String createCard(HttpServletRequest request) throws SQLException, StripeException {
        String message = "";

            NewStripeCardRequest newStripeCardRequest = NewStripeCardRequest
                    .builder()
                    .cvc(request.getParameter("cvv"))
                    .month(request.getParameter("expireMonths"))
                    .year(request.getParameter("expireYears"))
                    .firstName(request.getParameter("first_name"))
                    .lastName(request.getParameter("last_name"))
                    .number(request.getParameter("card_number"))
                    .object("card")
                    .customerId(newStripeUsersResponse.getData().getId())
                    .build();
        try {
            //String name = StripeData.sendCardRequest(newStripeCardRequest);
            String name = StripeData.newCard(newStripeCardRequest);
            newStripeCardResponse = new Gson().fromJson(name, NewStripeCardResponse.class);
            if (Integer.parseInt(newStripeCardResponse.getCode()) >= 200 && Integer.parseInt(newStripeCardResponse.getCode()) <= 299) {
                newStripeCardResponse.setName(request.getParameter("first_name") + request.getParameter("last_name"));
                newStripeCardResponse.setDefaultCard(1);
                newStripeCardResponse.setCustomerId(newStripeUsersResponse.getData().getId());
                if (newStripeCardController.saveNewStripeCard(newStripeCardResponse) > 0) {
                    System.out.println("createStripeCard success");
                    message = "Card created successfully";
                } else {
                    message = "Something went wrong.";
                }
            }
        } catch (Exception e) {
            message = "Something went wrong.";
        }
        return message;
    }


    public static String createCustomer(HttpServletRequest request, Users users) throws SQLException {
        String message = "";
        NewStripeUsersRequest newStripeUsersRequest = NewStripeUsersRequest
                .builder()
                .address1(request.getParameter("address"))
                .address2(request.getParameter("address2"))
                .firstName(request.getParameter("first_name"))
                .lastName(request.getParameter("last_name"))
                .phoneNumber(request.getParameter("phone"))
                .email(request.getParameter("email"))
                .city(request.getParameter("city"))
                .postalCode(request.getParameter("post_code"))
                .country(request.getParameter("country"))
                .build();
        try {
            String name = StripeData.newCustomer(newStripeUsersRequest);
            //String name = StripeData.sendNewCustomerRequest(newStripeUsersRequest);
            newStripeUsersResponse = new Gson().fromJson(name, NewStripeUsersResponse.class);
            newStripeUsersResponse.setUserId(users.getId());
            newStripeUsersResponse.setCity(request.getParameter("city"));
            newStripeUsersResponse.setCountry(request.getParameter("country"));
            newStripeUsersResponse.setAddress(request.getParameter("address"));
            newStripeUsersResponse.setZipCode(request.getParameter("post_code"));
            if (Integer.parseInt(newStripeUsersResponse.getCode()) >= 200 && Integer.parseInt(newStripeUsersResponse.getCode()) <= 299) {
                if (newStripeUsersController.saveNewStripeUser(newStripeUsersResponse) > 0) {
                    System.out.println("createStripeCard success");
                    message = "Customer created successfully";
                }
            } else {
                message = "Something went wrong.";
            }
        } catch (Exception e) {
            message = "Something went wrong.";
        }
        return message;
    }
}
