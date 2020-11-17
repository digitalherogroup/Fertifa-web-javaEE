package com.fertifa.app.utils;

import com.fertifa.app.controllers.UsersController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/forgot-password/send")
public class RecoveringPassword extends HttpServlet {
    private String EmailForPasswordRecovery = "";
    private UsersController usersController = new UsersController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            recoveringPassword(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            recoveringPassword(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void recoveringPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        getParameters(request);
        sendingEmail(request, response);
    }

    private void sendingEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (usersController.getStatusByEmail(EmailForPasswordRecovery) != 1) {
            request.setAttribute("message", "please contact the admin for further verifications");
            String url = "/forgot-password";
            request.getRequestDispatcher(url).forward(request, response);
            return;
        } else {
            SendingEmailNewCompany.sendRecoveryEmail(EmailForPasswordRecovery, request);

            String message = "Please check your email for password verification";
            request.setAttribute("message", message);
            gotoPage(request, response);
        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/resultpage.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("email") != null) {
            EmailForPasswordRecovery = request.getParameter("email");
        }
    }
}
