package com.fertifa.app.users.myaccount.stripe;


import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.EmployerDiscountModelController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.helpser.ChookiManager;
import com.fertifa.app.models.EmployerDiscountsModel;
import com.fertifa.app.models.Users;
import com.stripe.exception.StripeException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/employee/subscription/add")
public class EmployeeSubscription extends HttpServlet {
    private Users users = new Users();
    private UsersController usersController = new UsersController();
    private EmployerDiscountsModel employerDiscountsModel = new EmployerDiscountsModel();
    private EmployerDiscountModelController employerDiscountModelController = new EmployerDiscountModelController();
    private String customerStatus = "";
    private String cardStatus = "";
    private String subscriptionStatus = "";
    private Users company;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            employeeSubscription(request, response);
        } catch (SQLException | StripeException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            employeeSubscription(request, response);
        } catch (SQLException | StripeException e) {
            e.printStackTrace();
        }
    }

    private void employeeSubscription(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, StripeException {

        checkParameters(request, response);
        getDiscountRates();
        createStripe(request, response);
        changeUsersStatus(request, response);
    }

    private void changeUsersStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        ChookiManager.saveDataToCookie(String.valueOf(users.getId()), users.getEmail(), String.valueOf(users.getBranchId()), 3, 1, response);
        usersController.ChangeCompanyStatusToActive(String.valueOf(users.getId()));
        handleSession(request, response, users);

    }

    private void handleSession(HttpServletRequest request, HttpServletResponse response, Users users) throws IOException, ServletException {
        gotoDashboard(request, response);
    }

    private void gotoDashboard(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String url = "/employee/employee-dashboard";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void createStripe(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, StripeException {
        customerStatus = StripeAction.createCustomer(request, users);
        if (customerStatus.contains("wrong")) {
            gotoSamePage(request, response, customerStatus);
        }
        cardStatus = StripeAction.createCard(request);
        if (cardStatus.contains("wrong")) {
            gotoSamePage(request, response, cardStatus);
        }
//        subscriptionStatus = StripeAction.createSubscription(request);
//        if (subscriptionStatus.contains("wrong")) {
//            gotoSamePage(request, response, subscriptionStatus);
//        }
    }

    private String filterMessage(String cardStatus) {
        int index = cardStatus.indexOf(";");
        return cardStatus.substring(0,index);
    }

    private void getDiscountRates() throws SQLException {
        employerDiscountsModel = employerDiscountModelController.findById(users.getCompanyId());
    }

    private void checkParameters(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (request.getParameter("ID") != null) {
            String userid = request.getParameter("ID");
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, userid);
        }
    }

    private void gotoSamePage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException, SQLException {
        getUserFullDetails();
        getOffer();
        setRequests(request);
        //String finalErrorMessage = filterMessage(message);
        request.getSession().setAttribute("message", message);
        String url = "/employee/subscription/payment-dashboard.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    private void getUserFullDetails() {
        company = usersController.userObjectCompanyID(users.getCompanyId());
    }

    private void getOffer() throws SQLException {
        employerDiscountsModel = employerDiscountModelController.findById(users.getCompanyId());
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("EmployeeObject", users);
        request.setAttribute("EmployerObject", company);
        //request.setAttribute("EmployerDiscounts", employerDiscountsModel);
    }
}
