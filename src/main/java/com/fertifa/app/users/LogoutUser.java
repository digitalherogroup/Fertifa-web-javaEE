package com.fertifa.app.users;

import com.fertifa.app.helpser.ChookiManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/employee/logout")
public class LogoutUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       logoutUser(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logoutUser(request,response);
    }

    private void logoutUser(HttpServletRequest request, HttpServletResponse response) {
        ChookiManager.removeAllEmployee(request, response, 3);

        HttpSession session = request.getSession();
        session.removeAttribute("CompanyId");
        session.removeAttribute("CompanyRole");
        session.removeAttribute("SessionUserEmail");
        session.invalidate();
        try {
            Cookie cookie = new Cookie("cookieDataEmployee", null); // Not necessary, but saves bandwidth.
            cookie.setPath("/employee");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0
            response.setDateHeader("Expires", 0);
            response.sendRedirect("/employee");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
