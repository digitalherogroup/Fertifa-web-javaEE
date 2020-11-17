package com.fertifa.app.company;

import com.fertifa.app.controllers.SessionController;
import com.fertifa.app.helpser.ChookiManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/employer/logout")
public class LogoutCompany extends HttpServlet {
    private SessionController checker = new SessionController();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logoutCompany(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logoutCompany(request,response);
    }

    private void logoutCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChookiManager.removeAllEmployer(request, response, 2);

        HttpSession session = request.getSession();
        session.removeAttribute("CompanyId");
        session.removeAttribute("CompanyRole");
        session.removeAttribute("SessionUserEmail");
        session.invalidate();
        try {
            Cookie cookie = new Cookie("cookieDataEmployer", null); // Not necessary, but saves bandwidth.
            cookie.setPath("/employer");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0
            response.setDateHeader("Expires", 0);
            response.sendRedirect("/employer/login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
