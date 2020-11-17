package com.fertifa.app.users.Cart;

import com.fertifa.app.com.beautyit.com.messanger.StripeData;
import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.OrderController;
import com.fertifa.app.controllers.OrdersModeController;
import com.fertifa.app.controllers.ShoppingCartController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.controllers.stripecontroller.NewStripeCardController;
import com.fertifa.app.controllers.stripecontroller.NewStripeUsersController;
import com.fertifa.app.models.ShoppingCart;
import com.fertifa.app.models.Users;
import com.fertifa.app.models.stripe.NewStripeCardRequest;
import com.fertifa.app.models.stripe.NewStripeCardResponse;
import com.fertifa.app.models.stripe.NewStripeChargeShoppingCardRequest;
import com.fertifa.app.models.stripe.NewStripeChargeShoppingCardResponse;
import com.fertifa.app.models.stripe.NewStripeUsersResponse;
import com.fertifa.app.payments.User;
import com.fertifa.app.utils.SendingEmailNewUser;
import com.google.gson.Gson;
import com.stripe.exception.StripeException;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employee/payment-default")
public class EmployeePaymentDefault extends com.fertifa.app.baseUrl.BaseUrl {
    private UsersController usersController = new UsersController();
    private String Total = "";
    private String paymentCardId = "";
    private Users users = new Users();
    private NewStripeUsersController newStripeUsersController = new NewStripeUsersController();
    private NewStripeUsersResponse newStripeUsersRequest = new NewStripeUsersResponse();
    private NewStripeCardResponse stripeDefaultUserCard = new NewStripeCardResponse();
    private NewStripeCardController newStripeCardController = new NewStripeCardController();
    private String description = "";
    private NewStripeChargeShoppingCardRequest newStripeChargeShoppingCardRequest = new NewStripeChargeShoppingCardRequest();
    private NewStripeChargeShoppingCardResponse newStripeChargeShoppingCardResponse = new NewStripeChargeShoppingCardResponse();
    private List<com.fertifa.app.models.ShoppingCart> shoppingCarts = new ArrayList<>();
    private NewStripeCardRequest newStripeCardRequest = new NewStripeCardRequest();
    private NewStripeCardResponse newStripeCardResponse = new NewStripeCardResponse();
    private SendingEmailNewUser sendEmailController = new SendingEmailNewUser();
    private String priceId = "";
    private String productId = "";

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            employeePaymentDefault(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    private void employeePaymentDefault(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, StripeException {
        getParameters(request, response);
        getUserStripId();
        getFinalPaymentCard();
        chargeCustomer(request, response);
    }

    private void chargeCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, StripeException {

        String des = "payment for " + description + " product id:" + productId + " price id " + priceId;
        NewStripeChargeShoppingCardRequest charge = NewStripeChargeShoppingCardRequest
                .builder()
                .customerId(newStripeUsersRequest.getCustomerId())
                .cardId(paymentCardId)
                .price(Integer.parseInt(Total))
                .description(des)
                .build();
        int statusCode = 0;
        String name = StripeData.chargeSHoppingCard(charge);
        newStripeChargeShoppingCardResponse = new Gson().fromJson(name, NewStripeChargeShoppingCardResponse.class);
        if (Integer.parseInt(newStripeChargeShoppingCardResponse.getCode()) >= 200 && Integer.parseInt(newStripeChargeShoppingCardResponse.getCode()) <= 299) {
            String paymentId = newStripeChargeShoppingCardResponse.getData().getId();
            String receiptUrl = newStripeChargeShoppingCardResponse.getData().getReceiptUrl();
            ShoppingCartController shoppingCartController = new ShoppingCartController();
            ShoppingCart shoppingCartModel = new ShoppingCart();
            shoppingCartModel = shoppingCartController.getObjectByUserId(users.getId());
            shoppingCartModel.setPaymentId(paymentId);
            shoppingCartModel.setInvoiceUrl(receiptUrl);
            int result = shoppingCartController.updateShoppingCardWithPayment(shoppingCartModel,users.getId());

            changeDetails(request, response);

            System.out.println("Charged success");
            request.getSession().setAttribute("message", newStripeChargeShoppingCardResponse.getData().getOutcome().getSellerMessage() + "Thanks for your purchase – please check your e-mails for a copy of your receipt. If you have any questions, please get in touch at info@fertifa.com");
        } else {
            request.getSession().setAttribute("message", "Something else happened, completely unrelated to Stripe");
            setAttributes(request);
            gotoToNextPage(request, response);
        }
    }

    private void changeLocalOrderDetails(String invoiceId,String paymentId,String priceId, String productId) throws SQLException {
        OrdersModeController ordersModeController = new OrdersModeController();
        int result = ordersModeController.updateLocalOrder(invoiceId, paymentId, priceId, productId);
        System.out.println(result);
    }

    private void changeDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, StripeException {
        String description = "";
        User user = new User(users.getFirstName(),
                users.getLastName(),
                users.getEmail(),
                users.getAddress(),
                users.getPhoneNumber());
        String orderId = "";
        OrderController orderController = new OrderController();
        int serviceId = orderController.getServiceIdByUserid(users.getId());
        int usersCompanyId = usersController.getUserCompanyIdByEmail(users.getEmail());
        int finalShoppingCardId = 0;
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        shoppingCarts = shoppingCartController.getAllById(users.getId());
        for (int i = 0; i < shoppingCarts.size(); i++) {
            description = shoppingCarts.get(i).getServiceName();
            finalShoppingCardId = 0;
            com.fertifa.app.models.ShoppingCart shoppingCart = new ShoppingCart(
                    shoppingCarts.get(i).getOrder_id(),
                    shoppingCarts.get(i).getUser_id(),
                    shoppingCarts.get(i).getServiceName(),
                    shoppingCarts.get(i).getPrice(),
                    shoppingCarts.get(i).getPriceId(),
                    shoppingCarts.get(i).getProductId(),
                    shoppingCarts.get(i).getPaymentId(),
                    shoppingCarts.get(i).getInvoiceUrl(),
                    shoppingCarts.get(i).getOrder_id());
            changeLocalOrderDetails(shoppingCarts.get(i).getInvoiceUrl(),shoppingCarts.get(i).getPaymentId(),shoppingCarts.get(i).getPriceId(),shoppingCarts.get(i).getProductId());
            if (shoppingCartController.AddToFinal(usersCompanyId, serviceId, users.getId(), user, orderId, shoppingCart, Integer.parseInt(Total), newStripeUsersRequest.getCustomerId()) > 0) {
                int orderIdFroShop = shoppingCarts.get(i).getOrder_id();
                shoppingCartController.UpdateStatusById(orderIdFroShop, users.getId());
                sendInvoice(newStripeChargeShoppingCardResponse, request, response);
            } else {
                request.getSession().setAttribute("session_error_message", "Please check you card details for errors");
                setAttributes(request);
                gotoToNextPage(request, response);
            }
        }
        request.getSession().setAttribute("message", "Thanks for your purchase – please check your e-mails for a copy of your receipt. If you have any questions, please get in touch at info@fertifa.com");
        setAttributes(request);
        gotoToNextPage(request, response);
    }


    private void sendInvoice(NewStripeChargeShoppingCardResponse stripeModel, HttpServletRequest request, HttpServletResponse response) throws StripeException, ServletException, IOException {
        sendEmailController.sendInvoice(stripeModel.getData().getReceiptUrl(), users.getEmail());
    }

    private void setAttributes(HttpServletRequest request) {
        request.setAttribute("ShoppingCart", "active");
        request.setAttribute("Total", Total);
        request.setAttribute("EmployeeObject", users);
        request.setAttribute("employeeId", users.getId());
        request.setAttribute("Card", stripeDefaultUserCard);
    }

    private void getParameters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Total = request.getParameter("total");
        if (request.getParameter("payment-card") == null || request.getParameter("payment-card").trim().isEmpty()) {
            request.getSession().setAttribute("session_error_message", "Please check you card details for errors");
            setAttributes(request);
            gotoToNextPage(request, response);
        }
        paymentCardId = request.getParameter("payment-card");
        description = request.getParameter("description");
        priceId = request.getParameter("priceId");
        productId = request.getParameter("productId");
        System.out.println();
    }

    private void getUserStripId() throws SQLException {
        newStripeUsersRequest = newStripeUsersController.getStripUserObjectByUserId(users.getId());
    }

    private void getFinalPaymentCard() throws SQLException {
        stripeDefaultUserCard = newStripeCardController.findCardById(paymentCardId);
    }

    private void gotoToNextPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("back", null);
        String url = "/employee/stripe-detail/PaymentSuccess.jsp";
        request.getSession().setAttribute("userId",users.getId());
        response.sendRedirect(url);
        return;
    }
}
