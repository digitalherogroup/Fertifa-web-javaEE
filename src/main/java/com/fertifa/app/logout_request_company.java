package com.fertifa.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fertifa.app.helpser.ChookiManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/logout_request_company")
public class logout_request_company extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logout_request_company(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logout_request_company(request,response);
    }

    private void Logout_request_company(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ChookiManager.removeAll(request, response, 2);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        if(session!=null) {
            session.invalidate();
        }
        Map<String, Object> jsonMap = new LinkedHashMap<>();
        jsonMap.put("status", "success");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.println(mapper.writeValueAsString(jsonMap));
        out.flush();
        out.close();
    }
}
