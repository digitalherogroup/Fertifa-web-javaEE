package com.fertifa.app.users.myaccount.card.delete;


import com.fertifa.app.com.beautyit.com.messanger.StripeData;
import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.controllers.stripecontroller.NewStripeCardController;
import com.fertifa.app.controllers.stripecontroller.NewStripeUsersController;
import com.fertifa.app.models.Users;
import com.fertifa.app.models.stripe.NewStripeCardRequest;
import com.fertifa.app.models.stripe.NewStripeCardResponse;
import com.fertifa.app.models.stripe.NewStripeUsersResponse;
import com.google.gson.Gson;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/employee/myaccount/delete-card")
public class DeleteCard extends com.fertifa.app.baseUrl.BaseUrl {
    private Users users = new Users();
    private UsersController usersController = new UsersController();
    private String cardId = "";
    private NewStripeCardController newStripeCardController = new NewStripeCardController();
    private NewStripeUsersResponse newStripeUsersRequest = new NewStripeUsersResponse();
    private NewStripeUsersController newStripeUsersController = new NewStripeUsersController();
    private NewStripeCardResponse newStripeCardResponse;


    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            deleteCard(request, response);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }


    private void deleteCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        getUserStripId();
        getParameters(request);
        deleteCardDetails(request, response);

    }

    private void getUserStripId() throws SQLException {
        newStripeUsersRequest = newStripeUsersController.getStripUserObjectByUserId(users.getId());
    }

    private void deleteCardDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int statusCode = 0;
        NewStripeCardRequest newStripeCardRequest = NewStripeCardRequest
            .builder()
            .customerId(newStripeUsersRequest.getCustomerId())
            .cardId(cardId)
            .build();
        String name = StripeData.sendCardDeleteRequest(newStripeCardRequest,request,response);
        newStripeCardResponse = new Gson().fromJson(name, NewStripeCardResponse.class);
        if (Integer.parseInt(newStripeCardResponse.getCode()) >= 200 && Integer.parseInt(newStripeCardResponse.getCode()) <= 299) {
            if (newStripeCardController.deleteCard(cardId) > 0) {
                System.out.println("createStripeCard success");
                request.getSession().setAttribute("session_success_message", newStripeCardResponse.getMessage());
                setAttributes(request);
                gotoPage(request, response);
                System.out.println("createStripeCard success");
            }
        } else {
            request.getSession().setAttribute("session_error_message", newStripeCardResponse.getMessage());
            setAttributes(request);
            gotoPage(request, response);
        }

    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/employee/myaccount";
        response.sendRedirect(url);
    }

    private void setAttributes(HttpServletRequest request) {
        request.setAttribute("EmployeeObject", users);
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("card_id") != null) {
            cardId = request.getParameter("card_id");
        }
    }

}
