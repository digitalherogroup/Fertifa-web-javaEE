package com.fertifa.app.payments;


import com.fertifa.app.controllers.OrderController;
import com.fertifa.app.controllers.ShoppingCartController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.ShoppingCart;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.*;
import com.stripe.param.InvoiceCreateParams;
import com.stripe.param.InvoiceItemCreateParams;

import java.sql.SQLException;
import java.util.*;

public class PaymentServiceImpl implements PaymentService {
    private static final String TEST_STRIPE_SECRET_KEY = "sk_test_YX7bTlyJ7TceAndm1rm28C3U00ffWEMxw0";
    private String id = "";

    public PaymentServiceImpl() {
        Stripe.apiKey = TEST_STRIPE_SECRET_KEY;
    }

    @Override
    public String createCustomer(User user, int user_id) throws StripeException, SQLException {
        ActionStripes actionStripes = new ActionStripes();
        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("description", "Account for user id: " + user_id);
        customerParams.put("email", user.getEmail());
        customerParams.put("phone", user.getPhoneNumber());
        id = null;
        try {
            // Create customer
            Customer stripeCustomer = Customer.create(customerParams);
            //Our we site
            id = stripeCustomer.getId();
            actionStripes.createCustomerInData(user, user_id, id);
            System.out.println(stripeCustomer);
            // Use Stripe's library to make requests...
        } catch (CardException e) {
            // Since it's a decline, CardException will be caught
            System.out.println("Status is: " + e.getCode());
            System.out.println("Message is: " + e.getMessage());
        } catch (Exception e) {
            // Too many requests made to the API too quickly
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void chargeCreditCard(Order order) throws SQLException {

    }

    public StripeModel addCustomerCard(String stripeCustomerid, Card card, String firstName, String lastName, int stripeAmount) throws StripeException, SQLException {

        Customer customer = Customer.retrieve(stripeCustomerid);
        com.stripe.model.Card cardSource = null;
        StripeModel stripeModel = new StripeModel();
        stripeModel.setOrderAmount(stripeAmount);
        stripeModel.setSuccess(true);
        Token token = null;
        try {
            // create token
            Map<String, Object> cardParam = new LinkedHashMap<>();
            cardParam.put("name", firstName + " " + lastName);
            cardParam.put("number", card.getNumber());
            cardParam.put("exp_month", card.getMonths());
            cardParam.put("exp_year", card.getYears());
            cardParam.put("cvc", card.getCvc());
            Map<String, Object> sourceCard = new LinkedHashMap<>();
            sourceCard.put("card", cardParam);
            token = Token.create(sourceCard);

            // create Card Payment source
            Map<String, Object> cardCreateParam = new HashMap<>();
            String tokenString = token.getId();
            cardCreateParam.put("source", tokenString);
            cardSource = (com.stripe.model.Card) customer.getSources().create(cardCreateParam);

            // create Charge
            Map<String, Object> chargeParam = new HashMap<>();
            chargeParam.put("amount", stripeModel.getOrderAmount());
            chargeParam.put("currency", "GBP");
            chargeParam.put("source", cardSource.getId());
            chargeParam.put("customer", customer.getId());
            Charge charge = Charge.create(chargeParam);
            stripeModel.setCharge(charge);
            stripeModel.setCustomer(customer);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(customer));
        } catch (CardException e) {
            // Since it's a decline, CardException will be caught
            stripeModel.setSuccess(false);
            stripeModel.setMessage("Since it's a decline, CardException will be caught");
        } catch (RateLimitException e) {
            // Too many requests made to the API too quickly
            stripeModel.setSuccess(false);
            stripeModel.setMessage("Too many requests made to the API too quickly");
        } catch (InvalidRequestException e) {
            // Invalid parameters were supplied to Stripe's API
            stripeModel.setSuccess(false);
            stripeModel.setMessage("Invalid parameters were supplied to Stripe's API");
        } catch (AuthenticationException e) {
            // Authentication with Stripe's API failed
            // (maybe you changed API keys recently)
            stripeModel.setSuccess(false);
            stripeModel.setMessage("Authentication with Stripe's API failed");
        } catch (StripeException e) {
            // Display a very generic error to the user, and maybe send
            stripeModel.setSuccess(false);
            stripeModel.setMessage("Display a very generic error to the user, and maybe send");
        } catch (Exception e) {
            // Something else happened, completely unrelated to Stripe
            stripeModel.setSuccess(false);
            stripeModel.setMessage("Something else happened, completely unrelated to Stripe");
        }
        stripeModel.setToken(token);
        return stripeModel;
    }

    private void addMetaData(User user, String stripeCustomerid) throws StripeException {
      /*  Address address = new Address(user.getCity(), user.getCountry(), user.getPostal_code(), user.getState());
        Customer customer = Customer.retrieve(stripeCustomerid);
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("address.country", address.country);
        metadata.put("address.state", address.state);
        metadata.put("address.city", address.city);
        metadata.put("address.postalCode", address.postalCode);
        metadata.put("name", "b bb");
        metadata.put("phone", "+37498229898");
        Map<String, Object> params = new HashMap<>();
        params.put("metadata", metadata);

        Customer stripeCustomer =
        stripeCustomer = customer.update(params);*/
    }


    public String chargeCreditCard(Order order, StripeModel stripeModel, String userEmail) throws SQLException, StripeException {
        String orderDone = "";
        ShoppingCart shoppingCart = getShoppingDetails(order);
        ActionStripes actionStripes = new ActionStripes();
        User user = order.getUser();
        UsersController usersController = new UsersController();
        int usersCompanyId = usersController.getUserCompanyIdByEmail(userEmail);
        int userId = usersController.getUserIdByEmail(userEmail);
        OrderController orderController = new OrderController();

        int serviceId = orderController.getServiceIdByUserid(userId);

        try {
            //actionStripes.chargeCreditCardInData(order, user, shoppingCart, usersCompanyId, serviceId);
            System.out.println(stripeModel.getToken().getId());
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(stripeModel.getToken().getId()));

            orderDone = stripeModel.getToken().getId();

            // create invoice item
            InvoiceItemCreateParams params1 =
                    InvoiceItemCreateParams.builder()
                            .setAmount((long) stripeModel.getOrderAmount())
                            .setCurrency("GBP")
                            .setCustomer(stripeModel.getCustomer().getId())
                            .setDescription("One-time setup fee")
                            .build();

            InvoiceItem invoiceItem = InvoiceItem.create(params1);

            System.out.println(invoiceItem.getInvoice());
            System.out.println(invoiceItem.getInvoiceObject());

            InvoiceCreateParams params2 =
                    InvoiceCreateParams.builder()
                            .setCustomer(stripeModel.getCustomer().getId())
                            .setCollectionMethod(InvoiceCreateParams.CollectionMethod.SEND_INVOICE)
                            .setDaysUntilDue(30L)
                            .build();

            Invoice invoice = Invoice.create(params2);
            invoice.sendInvoice();
            stripeModel.setInvoice(invoice);
        } catch (CardException e) {
            // Transaction was declined
            System.out.println("Status is: " + e.getCode());
            System.out.println("Message is: " + e.getMessage());
        }

        return orderDone;
    }

    private ShoppingCart getShoppingDetails(Order order) throws SQLException {
        User user = order.getUser();
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        //ShoppingCartController shoppingCartController = new ShoppingCartController();
        return shoppingCartController.getObjectByUserId(user.getFertifaUser_id());

    }


}
