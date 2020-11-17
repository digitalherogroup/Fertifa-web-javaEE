package com.fertifa.app.utils;

import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.notification.Notifications;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/check-recovery")
public class CheckRecovery extends HttpServlet {
    private String RecoveryEmail = "";
    private UsersController usersController = new UsersController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            checkRecovery(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            checkRecovery(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkRecovery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        getParameters(request);
        CheckIfEmailExsists(RecoveryEmail,request,response);
    }

    private void CheckIfEmailExsists(String recoveryEmail, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if(usersController.CheckEmail(recoveryEmail)){
            gotoEmailRecoveryPage(request, response);
        }else{
            String message = "We dont have your email in our database, please contact administration";
            Notifications.ErrorNotify(request,message);
            gotoErrorPage(request,response);
        }
    }

    private void gotoErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("forgot.jsp").forward(request,response);
    }

    private void gotoEmailRecoveryPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("RecoverPassword.jsp").forward(request,response);
    }

    private void getParameters(HttpServletRequest request) {
        if(request.getParameter("Email")!= null){
            RecoveryEmail = request.getParameter("Email");
        }
    }
}
