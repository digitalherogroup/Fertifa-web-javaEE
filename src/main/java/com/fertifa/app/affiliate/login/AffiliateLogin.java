package com.fertifa.app.affiliate.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/com/fertifa/app/affiliate/login")
public class AffiliateLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        affiliateLogin(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        affiliateLogin(request,response);
    }

    private void affiliateLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/login-form.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }
}
