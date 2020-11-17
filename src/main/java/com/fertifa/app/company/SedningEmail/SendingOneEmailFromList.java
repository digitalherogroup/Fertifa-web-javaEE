package com.fertifa.app.company.SedningEmail;

import com.fertifa.app.constants.Constances;
import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.InvitationController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Tokens;
import com.fertifa.app.models.Users;
import com.fertifa.app.utils.SendingEmailNewUser;
import com.fertifa.app.utils.TokenMaker;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@WebServlet("/employer/sendingone")
public class SendingOneEmailFromList extends com.fertifa.app.baseUrl.BaseUrl {
    private String SessionUserEmail = "";
    private UsersController usersController = new UsersController();
    private int CompanyId = 0;

    private String CompanyName = "";
    private String UserEmailId = "";
    private String UserEmail = "";
    private String UserFirstName = "";
    private String UserLastName = "";
    String Token = "";
    private int CountUsersLastThreeMonths = 0;
    private int CountAllUsers = 0;
   private Users users = new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA,getEmployerId(request,response));
            sendingOneEmailFromList(request, response);
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

    private void sendingOneEmailFromList(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {

        getParameters(request);
        countNewUsersForLastThreeMonths();
        countAllusersOfCompany();
        sendInvitation(request, response);

    }

    private void countAllusersOfCompany() {
        CountAllUsers = usersController.CountUsers(Constances.USERS, CompanyId);
    }

    private void countNewUsersForLastThreeMonths() {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.MONTH, -3);
        timestamp = new Timestamp(cal.getTime().getTime());
        CountUsersLastThreeMonths = usersController.CountUsersMonths(new Timestamp(new Date().getTime()), timestamp, Constances.USERS, CompanyId);

    }

    private Tokens CreateTokenObject() {
        return new Tokens(Token, CompanyId, SessionUserEmail, CompanyName);
    }

    private void sendInvitation(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        InvitationController invitationController = new InvitationController();
        Token = TokenMaker.buildToken(12);
        if (invitationController.checkUserId(Integer.parseInt(UserEmailId))) {
            invitationController.UpdateTokenByUserId(Token, Integer.parseInt(UserEmailId));
            SendEmail(request,response);
        } else {
            invitationController.AddNewTokenAndCompany(CreateTokenObject());
            SendEmail(request, response);
        }

    }

    private void SendEmail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        SendingEmailNewUser.sendUser(Token, UserFirstName, UserLastName, UserEmail, request, response);
        request.getSession().setAttribute("session_success_message", "Invitation sent successfully");
        setRequests(request);
        gotoPage(request, response);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("EmployerObject", users);
        String url = "/employer/enrolmentdata";
        response.sendRedirect(url);
    }


    private void setRequests(HttpServletRequest request) {
        request.setAttribute("Contact", "active");
        request.setAttribute("ThreeMonthsCount", CountUsersLastThreeMonths);
        request.setAttribute("CountAllUsers", CountAllUsers);
        request.setAttribute("EmployerObject", users);
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            UserEmailId = request.getParameter("id");
        }

    }
}
