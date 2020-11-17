package com.fertifa.app.users.help;

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

@WebServlet("/employee/sendquestion")
public class SendEmailToAdminUser extends com.fertifa.app.baseUrl.BaseUrl {

    private UsersController usersController = new UsersController();
    FaqCatController catController = new FaqCatController();
    List<FaqCat> faqCatList = new ArrayList<>();
    FaqController faqController = new FaqController();
    List<Faq> faqList = new ArrayList<>();

    private String MessageCategory = "";
    private String MessageType = "";
    private String Subject = "";
    private Users users = new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            sendEmailToAdminUser(request, response);
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

    private void sendEmailToAdminUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        getAllCategories();
        getAllFaqs();
        getParameters(request);
        sendingEmailToAdmin(response, request, users.getFirstName() + " " + users.getLastName(), MessageCategory, MessageType, Subject);


    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("category") != null) {
            MessageCategory = request.getParameter("category");
        }
        if (request.getParameter("type") != null) {
            MessageType = request.getParameter("type");
        }
        if (request.getParameter("message") != null) {
            Subject = request.getParameter("message");
        }
    }


    private void sendingEmailToAdmin(HttpServletResponse response, HttpServletRequest request, String companyName,
                                     String messageCategory, String messageType, String subject) throws SQLException, ServletException, IOException {

        com.fertifa.app.utils.SendEmailToAdmin.sendUser(users.getFirstName() + " " + users.getLastName(), messageCategory, messageType, subject, "info@fertifa.com");
        String messgae = "Your email sent successfully";
        request.getSession().setAttribute("session_success_message", messgae);
        getAllCategories();
        getAllFaqs();
        setRequests(request, response);
        gotopage(request, response);
    }

    private void gotopage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/employee/helpcontact";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void setRequests(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("FaqList", faqList);
        request.setAttribute("FaqCatList", faqCatList);
        request.setAttribute("EmployeeObject", users);
        request.setAttribute("help", "active");
    }

    private void getAllFaqs() throws SQLException {
        faqList = faqController.getAllFaqsForUsers();
    }

    private void getAllCategories() throws SQLException {
        faqCatList = catController.findFaqCatAllForUsers();
    }
}
