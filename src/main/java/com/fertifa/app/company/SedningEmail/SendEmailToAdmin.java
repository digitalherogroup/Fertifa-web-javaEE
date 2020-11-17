package com.fertifa.app.company.SedningEmail;

import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.FaqCatController;
import com.fertifa.app.controllers.FaqController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Faq;
import com.fertifa.app.models.FaqCat;
import com.fertifa.app.models.Users;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employer/send")
public class SendEmailToAdmin extends com.fertifa.app.baseUrl.BaseUrl {
    private UsersController usersController = new UsersController();
    FaqCatController catController = new FaqCatController();
    List<FaqCat> faqCatList = new ArrayList<>();
    FaqController faqController = new FaqController();
    List<Faq> faqList = new ArrayList<>();
    private String MessageCategory = "";
    private String MessageType = "";
    private String Subject = "";
    private String CompanyName = "";
    private Users users = new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA,getEmployerId(request,response));
            sendEmailToAdmin(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    private void sendEmailToAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getParameters(request);
        sendingEmailToAdmin(response, request, users, MessageCategory, MessageType, Subject);
    }


    private void sendingEmailToAdmin(HttpServletResponse response, HttpServletRequest request, Users users, String messageCategory, String messageType, String subject) throws SQLException, ServletException, IOException {
        com.fertifa.app.utils.SendEmailToAdmin.send(users.getComapny(), messageCategory, messageType, subject, "info@fertifa.com");
        String messgae = "Your email sent successfully";
        request.getSession().setAttribute("session_success_message", messgae);
        getAllCategories();
        getAllFaqs();
        setRequests(request);
        gotoPage(request, response);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("EmployerObject", users);
        String url = "/employer/help";
        response.sendRedirect(url);
    }

    private void getAllFaqs() throws SQLException {
        faqList = faqController.getAllFaqsForCompany();
    }

    private void getAllCategories() throws SQLException {
        faqCatList = catController.findFaqCatAllForCompany();
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("Contact", "active");
        request.setAttribute("FaqList", faqList);
        request.setAttribute("FaqCatList", faqCatList);
    }

    private void getParameters(HttpServletRequest request) {

        String[] inputsParam = request.getParameterValues("input");
        System.out.println();
        if (request.getParameter("category") != null) {
            MessageCategory = request.getParameter("category");
        }
        if (request.getParameter("subject") != null) {
            MessageType = request.getParameter("subject");
        }
        if (request.getParameter("message") != null) {
            Subject = request.getParameter("message");
        }
    }


}
