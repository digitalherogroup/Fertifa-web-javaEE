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

@WebServlet("/employee/myaccount/addnewcard/update")
public class UpdateCard extends com.fertifa.app.baseUrl.BaseUrl {
    private Users users = new Users();
    private UsersController usersController = new UsersController();
    private NewStripeUsersResponse newStripeUsersRequest = new NewStripeUsersResponse();
    private NewStripeUsersController newStripeUsersController = new NewStripeUsersController();
    private NewStripeCardController newStripeCardController = new NewStripeCardController();
    private String cardId;


    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            updateCard(request, response);
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

    private void getUserStripId() throws SQLException {
        newStripeUsersRequest = newStripeUsersController.getStripUserObjectByUserId(users.getId());
    }

    private void updateCard(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getParameters(request);
        getUserStripId();

        if(newStripeCardController.updateStatus(newStripeUsersRequest.getCustomerId()) > 0 ) {
            NewStripeCardResponse newStripeCardResponse = NewStripeCardResponse.builder()
                    .defaultCard(1)
                    .cardId(cardId)
                    .build();

            if (newStripeCardController.updateNewStripeCardStatus(newStripeCardResponse) > 0) {
                setAttributes(request);
                gotoPage(request, response);
            }
        }
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("card_id") != null) {
            cardId = request.getParameter("card_id");
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
