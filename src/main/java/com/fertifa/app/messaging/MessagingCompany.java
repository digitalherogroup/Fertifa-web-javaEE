package com.fertifa.app.messaging;

import com.fertifa.app.controllers.SessionController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/MessagingCompany")
public class MessagingCompany extends HttpServlet {
    private String SessionUserEmail = "";
    private SessionController sessionController = new SessionController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        messaging(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        messaging(request, response);
    }

    private void messaging(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UnicodeingSevlet(request);
        //getSession(request, response);
        gotoPage(request, response);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/messaging/Messaging.jsp").forward(request, response);
    }

    private void getSession(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        sessionController.getCompanyUsername(request);
        if (!sessionController.getCompanySession(request)) {
            gotoLoginPage(request, response);
        } else {
            SessionUserEmail = sessionController.getCompanyUsername(request);
            System.out.println(SessionUserEmail);
        }
    }

    /**
     * goto login page when user session ends
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void gotoLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionUserEmail = null;
        sessionController.DistroySession(request);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    /**
     * Unicode utf8 decleration
     *
     * @param request
     * @throws UnsupportedEncodingException
     */
    private void UnicodeingSevlet(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

    }
}
