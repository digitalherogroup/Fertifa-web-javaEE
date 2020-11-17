package com.fertifa.app.adminSide;

import com.fertifa.app.baseUrl.BaseUrl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/test")
public class ServletTest extends BaseUrl {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        servletTest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
        servletTest(request,response);
    }

    private void servletTest(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("inside servletTest");
    }
}
