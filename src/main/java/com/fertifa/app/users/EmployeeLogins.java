package com.fertifa.app.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EmployeeLogins")
public class EmployeeLogins extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employeeLogins(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employeeLogins(request,response);
    }

    private void employeeLogins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("com.fertifa.app.Logout");
    }
}
