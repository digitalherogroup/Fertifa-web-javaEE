package com.fertifa.app.utils;

import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/forgot-password/change-password")
public class ChangePassword extends HttpServlet {
    private String CompanyEmail = "";
    private int CompanyId = 0;
    private String Password = "";
    private UsersController usersController = new UsersController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            changePassword(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            changePassword(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        getparamerts(request);
        getCompanyIdByEmail();
        StartUpdatePasswordById(Password, CompanyId, request, response);
    }

    private void StartUpdatePasswordById(String password, int companyId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if(checkUserStatus() != 1){
            request.setAttribute("message","please contact the admin for further verifications");
            String url ="/forgot-password";
            response.sendRedirect(url);
            return;
        }else {

            if (usersController.updatePasswordById(CreateCompanyObject(password), companyId) > 0) {
                gotoPageLoginWithSuccess(request, response);
            } else {
                gotoPageErrorPage(request, response);
            }
        }
    }

    private int checkUserStatus() throws SQLException {
        return usersController.getStatusByEmail(CompanyEmail);
    }

    private void gotoPageErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/RecoverPassword.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void gotoPageLoginWithSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url  = "/";
        response.sendRedirect(url);
        //request.getRequestDispatcher(url).forward(request, response);
    }

    private Users CreateCompanyObject(String password) {
        return new Users(password);
    }

    private void getCompanyIdByEmail() {
        CompanyId = usersController.getCompanyIdByEmail(CompanyEmail);
    }

    private void getparamerts(HttpServletRequest request) {
        if (request.getParameter("email") != null) {
            CompanyEmail = request.getParameter("email");
        }
        if (request.getParameter("password") != null) {
            Password = request.getParameter("password");
        }
    }
}
