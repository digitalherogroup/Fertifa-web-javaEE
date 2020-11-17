package com.fertifa.app;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin", "/Admin"},name = "adminstart")
public class Admin extends HttpServlet implements Servlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        admin(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        admin(request, response);
    }

    private void admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(AdminCookiManager.isUserLogged(request,response)){
            String url = request.getServletPath()+"/admin-dashboard";
            response.sendRedirect(url);
        }else {
            String url = request.getServletPath() + "/login/";
            response.sendRedirect(url);
        }
    }

}