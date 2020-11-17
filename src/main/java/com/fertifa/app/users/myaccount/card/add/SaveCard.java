package com.fertifa.app.users.myaccount.card.add;


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

@WebServlet("/employee/myaccount/addnewcard/add")
public class SaveCard extends com.fertifa.app.baseUrl.BaseUrl {
    private Users users = new Users();
    private UsersController usersController = new UsersController();
    private NewStripeUsersResponse newStripeUsersRequest = new NewStripeUsersResponse();
    private NewStripeUsersController newStripeUsersController = new NewStripeUsersController();
    private NewStripeCardController newStripeCardController = new NewStripeCardController();
    private NewStripeCardResponse newStripeCardResponse;

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            saveCard(request, response);
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


    private void saveCard(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        getUserStripId();
        getRequests(request, response);

    }

    private void getUserStripId() throws SQLException {
        newStripeUsersRequest = newStripeUsersController.getStripUserObjectByUserId(users.getId());
    }

    private void getRequests(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        if (request.getParameterNames() != null) {
            NewStripeCardRequest newStripeCardRequest = NewStripeCardRequest
                    .builder()
                    .customerId(newStripeUsersRequest.getCustomerId())
                    .number(request.getParameter("card_number"))
                    .cvc(request.getParameter("cvv"))
                    .month(request.getParameter("expireMonths"))
                    .year(request.getParameter("expireYears"))
                    .object("card")
                    .firstName(request.getParameter("card_holder_name"))
                    .lastName("")
                    .build();
            try {
                //String name = StripeData.sendCardRequest(newStripeCardRequest);
                String name = StripeData.newCard(newStripeCardRequest);
                newStripeCardResponse = new Gson().fromJson(name, NewStripeCardResponse.class);
                if (Integer.parseInt(newStripeCardResponse.getCode()) >= 200 || Integer.parseInt(newStripeCardResponse.getCode()) <= 299) {
                    newStripeCardResponse.setDefaultCard(0);
                    newStripeCardResponse.setName(request.getParameter("card_holder_name"));
                    newStripeCardResponse.setCustomerId(newStripeCardRequest.getCustomerId());
                    newStripeCardController.saveNewStripeCard(newStripeCardResponse);
                    System.out.println("createStripeCard success");
                    request.getSession().setAttribute("session_success_message", "Your card updated successfully");
                    setAttributes(request);
                    gotoPage(request, response);
                    System.out.println("createStripeCard success");
                } else {
                    request.getSession().setAttribute("session_error_message", "please check you card details for errors");
                    setAttributes(request);
                    gotoPage(request, response);
                }
            }catch (Exception e){
                request.getSession().setAttribute("session_error_message", "please check you card details for errors");
                setAttributes(request);
                gotoPage(request, response);
            }
        }

    }

    private void setAttributes(HttpServletRequest request) {
        request.setAttribute("EmployeeObject", users);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String url = "/employee/myaccount";
        response.sendRedirect(url);
    }
}
