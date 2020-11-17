package com.fertifa.app.company;

import com.fertifa.app.helpser.ChookiManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employer/login")
public class SignIn extends HttpServlet {

       protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        signIn(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        signIn(request,response);
    }

    private void signIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(ChookiManager.isUserLogged(request,response,2)){
            String url = "/employer/employer-dashboard";
            response.sendRedirect(url);
        }else {
            response.sendRedirect("login/");
        }
    }
}
