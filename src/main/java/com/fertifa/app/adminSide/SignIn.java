package com.fertifa.app.adminSide;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/login", "/admin/","/admin/login/"})
public class SignIn extends com.fertifa.app.baseUrl.BaseUrl {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        signIn(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        signIn(request, response);
    }

    private void signIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(AdminCookiManager.isUserLogged(request,response)){
            String url = "/admin/admin-dashboard";
            request.getRequestDispatcher(url).forward(request, response);
        }else {
            String url = request.getServletPath() + "/SignIn.jsp";
            response.sendRedirect(url);
        }
    }
}
