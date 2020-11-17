package com.fertifa.app.payments;

import com.fertifa.app.controllers.SessionController;
import com.fertifa.app.controllers.ShoppingCartController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.StripeUsers;
import com.fertifa.app.models.Users;
import com.fertifa.app.helpser.ChookiManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddNewStripeCard")
public class AddNewStripeCard extends HttpServlet {
    private int UserId = 0;
    private UsersController usersController = new UsersController();
    private String SessionUserEmail = "";
    private SessionController sessionController = new SessionController();
    private int Id = 0;
    int orderId = 0;
    List<Users> usersList = new ArrayList<>();
    private int Total = 0;
    private ShoppingCartController shoppingCartController = new ShoppingCartController();
    List<com.fertifa.app.models.ShoppingCart> shoppingCartList = new ArrayList<>();
    private String stripeId = "";
    List<StripeUsers> stripeUserList = new ArrayList<>();
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
            addNewStripeCard(request, response);
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
            addNewStripeCard(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                gotoLoginPage(request, response);
            }
        }

    }

    private void addNewStripeCard(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        UnicodeingSevlet(request);
        getRequests(request);
        setRequests(request);
        gotoAddCardPage(request, response);
    }

    private void getRequests(HttpServletRequest request) {
        stripeId = request.getParameter("StripeId");
    }

    private void gotoAddCardPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("AddCardDetails.jsp").forward(request,response);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("UserId", UserId);
        request.setAttribute("StripeId", stripeId);
    }

    private void gotoLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    private void UnicodeingSevlet(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

    }
}
