package com.fertifa.app.users.Cart;

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


@WebServlet("/employee/payment-other")
public class EmployeePaymentOther extends com.fertifa.app.baseUrl.BaseUrl {

    private UsersController usersController = new UsersController();
    private String Total = "";
    private String paymentCardId = "";
    private Users users = new Users();
    private NewStripeUsersController newStripeUsersController = new NewStripeUsersController();
    private NewStripeUsersResponse newStripeUsersRequest = new NewStripeUsersResponse();
    private NewStripeCardResponse stripeDefaultUserCard = new NewStripeCardResponse();
    private NewStripeCardController newStripeCardController = new NewStripeCardController();

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            employeePaymentOther(request, response);
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

    private void employeePaymentOther(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        getParameters(request);
        getUserStripId();
        getFinalPaymentCard();
        setRequests(request);
        gotoToNextPage(request, response);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("ShoppingCart", "active");
        request.setAttribute("Total", Total);
        request.setAttribute("EmployeeObject", users);
        request.setAttribute("Card", stripeDefaultUserCard);
    }

    private void getParameters(HttpServletRequest request) {
        Total = request.getParameter("total");
        paymentCardId = request.getParameter("payment-card");
    }

    private void getUserStripId() throws SQLException {
        newStripeUsersRequest = newStripeUsersController.getStripUserObjectByUserId(users.getId());
    }

    private void getFinalPaymentCard() throws SQLException {
        stripeDefaultUserCard = newStripeCardController.getTheDefaultCard(newStripeUsersRequest.getCustomerId());
    }

    private void gotoToNextPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("userId",users.getId());
        String url = request.getServletPath() + "/Payment-default.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }
}
