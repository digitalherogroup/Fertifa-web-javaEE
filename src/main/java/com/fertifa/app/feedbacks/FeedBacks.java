package com.fertifa.app.feedbacks;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/com/fertifa/app/feedbacks")
public class FeedBacks extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        feedBacks(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        feedBacks(request,response);
    }

    private void feedBacks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("com/fertifa/app/adminSide/FeedbackValidation.jsp").forward(request,response);
        request.getSession().setAttribute("successNotices",null);
        request.getSession().setAttribute("errorNotices",null);
    }
}
