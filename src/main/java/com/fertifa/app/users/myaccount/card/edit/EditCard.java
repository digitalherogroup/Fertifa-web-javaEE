package com.fertifa.app.users.myaccount.card.edit;


import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.controllers.stripecontroller.NewStripeCardController;
import com.fertifa.app.controllers.stripecontroller.NewStripeUsersController;
import com.fertifa.app.models.Users;
import com.fertifa.app.models.stripe.NewStripeCardResponse;
import com.fertifa.app.models.stripe.NewStripeUsersResponse;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/employee/myaccount/edit")
public class EditCard extends com.fertifa.app.baseUrl.BaseUrl {
    private Users users = new Users();
    private UsersController usersController = new UsersController();
    private NewStripeUsersResponse newStripeUsersRequest = new NewStripeUsersResponse();
    private NewStripeUsersController newStripeUsersController = new NewStripeUsersController();
    private NewStripeCardController newStripeCardController = new NewStripeCardController();
    private NewStripeCardResponse cards = new NewStripeCardResponse();
    private String cardId;


    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            editCard(request, response);
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


    private void editCard(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        getParameters(request);
        getUserStripId();
        getCardByCardId();
        setRequests(request);
        gotoPage(request,response);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/edit-card.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("EmployeeObject",users);
        request.setAttribute("Card",cards);
        request.setAttribute("StripeUser",newStripeUsersRequest);
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("card_id") != null) {
            cardId =request.getParameter("card_id");
        }
    }

    private void getCardByCardId() throws SQLException {
        cards = newStripeCardController.findCardById(cardId);
    }

    private void getUserStripId() throws SQLException {
        newStripeUsersRequest = newStripeUsersController.getStripUserObjectByUserId(users.getId());
    }
}
