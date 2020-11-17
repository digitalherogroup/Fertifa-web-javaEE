package com.fertifa.app.adminSide.logout;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/logout_request_admin")
public class logout_request_admin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logout_request_admin(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logout_request_admin(request,response);
    }

    private void Logout_request_admin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AdminCookiManager.removeAll(request, response);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        if(session != null){
            session.invalidate();
        }
        Cookie cookie = new Cookie("cookieDataAdmin", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        Map<String, Object> jsonMap = new LinkedHashMap<>();
        jsonMap.put("status", "success");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.println(mapper.writeValueAsString(jsonMap));
        out.flush();
        out.close();
       //response.sendRedirect("/com.fertifa.app.Admin/SignIn.jsp");
    }
}
