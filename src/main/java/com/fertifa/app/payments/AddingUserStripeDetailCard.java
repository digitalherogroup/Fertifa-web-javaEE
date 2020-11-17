package com.fertifa.app.payments;

import com.fertifa.app.controllers.SessionController;
import com.stripe.exception.StripeException;
import com.fertifa.app.helpser.ChookiManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebServlet("/AddingUserStripeDetailCard")
public class AddingUserStripeDetailCard extends HttpServlet {

    private int UserId = 0;
    private String SessionUserEmail = "";
    private SessionController sessionController = new SessionController();
    private String StripeId = "";
    private String CardNumber = "";
    private String Months = "";
    private String Year = "";
    private String CVC = "";
    private String firstName = "";
    private String lastName = "";
    private int UserRole;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserRole = 3;
            ChookiManager.UserCookie userCookie = ChookiManager.getCookiData(request, response,UserRole);
            if (userCookie == null) {
                // go to login page
                gotoLoginPage(request, response);
                return;
            }
            SessionUserEmail = userCookie.getUserEmail();
            UserId = Integer.parseInt(userCookie.getUserId());
            //UserRole = Integer.parseInt(userCookie.getUserRole());
            addingUserStripeDetailCard(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                gotoLoginPage(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserRole = 3;
            ChookiManager.UserCookie userCookie = ChookiManager.getCookiData(request, response,UserRole);
            if (userCookie == null) {
                // go to login page
                gotoLoginPage(request, response);
                return;
            }
            SessionUserEmail = userCookie.getUserEmail();
            UserId = Integer.parseInt(userCookie.getUserId());
            //UserRole = Integer.parseInt(userCookie.getUserRole());
            addingUserStripeDetailCard(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                gotoLoginPage(request, response);
            }
        }
    }

    private void addingUserStripeDetailCard(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, StripeException, SQLException {
        UnicodeingSevlet(request);
        getParametrs(request);
        AddStripeAccount();

    }


    private void AddStripeAccount() throws StripeException, SQLException {
        Card card = new Card(UserId, CardNumber, Months, Year, CVC);
        PaymentServiceImpl service = new PaymentServiceImpl();
        //service.addCustomerCard(StripeId, card, firstName, lastName);
    }

    private void getParametrs(HttpServletRequest request) {
        CardNumber = request.getParameter("card_number");
        Months = request.getParameter("expireMonths");
        Year = request.getParameter("expireYears");
        CVC = request.getParameter("cvv");
        StripeId = request.getParameter("stripeId");
        firstName = request.getParameter("first_Name");
        lastName = request.getParameter("last_name");
    }

    private void getSession(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        sessionController.getCompanyUsername(request);
        if (!sessionController.getCompanySession(request)) {
            gotoLoginPage(request, response);
        } else {
            SessionUserEmail = sessionController.getCompanyUsername(request);
            System.out.println(SessionUserEmail);
        }
    }

    private void gotoLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (UserRole == 2) {
            response.sendRedirect("employerloginpage.jsp");
        } else if (UserRole == 3) {
            response.sendRedirect("employeeloginpage.jsp");
        } else {
            response.sendRedirect("splash.jsp");
        }
    }

    private void UnicodeingSevlet(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

    }
}
