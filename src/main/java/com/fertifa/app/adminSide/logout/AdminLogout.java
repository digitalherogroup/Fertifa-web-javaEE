package com.fertifa.app.adminSide.logout;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/logout"})

public class AdminLogout extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        adminLogout(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        adminLogout(request, response);
    }

    private void adminLogout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        sessionControlling(request, response);
    }

    private void sessionControlling(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AdminCookiManager.removeAll(request, response);

        HttpSession  session = request.getSession();
        if(session != null){
            session.invalidate();
        }
        try {
            Cookie cookie = new Cookie("cookieDataAdmin", null); // Not necessary, but saves bandwidth.
            cookie.setPath("/admin");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(0); // Don't set to -1 or it will become a session cookie!
            response.addCookie(cookie);
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0
            response.setDateHeader("Expires", 0);
            response.sendRedirect("login/");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}