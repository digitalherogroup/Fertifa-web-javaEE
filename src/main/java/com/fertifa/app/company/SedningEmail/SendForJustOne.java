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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/employer/SendForJustOne")
public class SendForJustOne extends com.fertifa.app.baseUrl.BaseUrl {
    private String SessionUserEmail = "";
    private UsersController usersController = new UsersController();
    private int CompanyId = 0;

    private String MessageCategory = "";
    private String MessageType = "";
    private String Subject = "";
    private String CompanyName = "";
    private String UserEmailId = "";
    private String UserEmail = "";
    private String UserFirstName = "";
    private String UserLastName = "";
    String Token = "";
    String FirstName = "";
    String LastName = "";
    private int CountUsersLastThreeMonths = 0;
    private int CountAllUsers = 0;
    private int uSerNewId = 0;
    private List<Users> usersList = new ArrayList<>();
    private int CountUsersDeletedLastThreeMonths = 0;
    private List<Users> AllTempUsersList = new ArrayList<>();
    private Users users = new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA,getEmployerId(request,response));
            sendForJustOne(request, response);
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

    private void sendForJustOne(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.getSession().setAttribute("session_error_message", null);
        request.getSession().setAttribute("session_success_message", null);

        getParameters(request);
        countNewUsersForLastThreeMonths();
        countAllusersOfCompany();
        getAllUsers();
        checkIfOwnEmail(users, request, response);
        sendInvitation(request, response);

    }

    private void getAllUsers() throws SQLException {
        AllTempUsersList = usersController.getUsersByCompanyAndUser(users.getId(), Constances.USERS);
    }

    private void checkIfOwnEmail(Users users, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userEmaild = usersController.getUserIdByEmail(UserEmailId);
        if (users.getId() == userEmaild) {
            request.getSession().setAttribute("session_error_message", "You can't send invitation to yourself");
            setRequests(request);
            gotoPage(request, response);
        }
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
        CountUsersDeletedLastThreeMonths = usersController.CountUserDeleteMonths(new Timestamp(new Date().getTime()), timestamp, Constances.USERS, CompanyId);
    }

    private Tokens CreateTokenObject() {
        return new Tokens(uSerNewId, Token, FirstName, LastName, UserEmailId);
    }

    private void sendInvitation(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (usersController.checkIfUserExsistInData(UserEmailId, Constances.USERS)) {
            request.getSession().setAttribute("session_error_message", "The user already exists");
            setRequests(request);
            gotoPage(request, response);
        } else {
            InvitationController invitationController = new InvitationController();
            List<Users> usersListforEmail = new ArrayList<>();
            int userEmaild = usersController.getUserIdByEmail(UserEmailId);
            if (userEmaild > 0) {
                request.getSession().setAttribute("session_error_message", "The user already exists");
                setRequests(request);
                gotoPage(request, response);

            } else {
                Users users = new Users(FirstName, LastName, UserEmailId, 0, Constances.USERS, new Timestamp(new Date().getTime()), this.users.getId(),this.users.getComapny(),true);
                usersController.AddnewUsersInvitation(users);
                uSerNewId = usersController.getUserIdByEmail(UserEmailId);

                Token = TokenMaker.buildToken(12);
                // List<com.fertifa.app.Users> userEmailList = new ArrayList<>();

                if (invitationController.checkUserId(uSerNewId)) {
                    invitationController.UpdateTokenByUserId(Token, uSerNewId);
                    SendEmail(request, response);
                } else {
                    invitationController.AddNewTokenAndUser(CreateTokenObject());
                    SendEmail(request, response);
                }
            }
        }
    }

    private void SendEmail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        SendingEmailNewUser.sendUser(Token, FirstName, LastName, UserEmailId, request, response);
        request.getSession().setAttribute("session_success_message", "Your invitation sent successfully");
        setRequests(request);
        gotoPage(request, response);
    }



    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("EmployerObject", users);
        request.getSession().setAttribute("token", "1");
        String url = "/employer/enrolmentdata";
        response.sendRedirect(url);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }


    private void setRequests(HttpServletRequest request) {

        request.setAttribute("Tracker", "active");
        request.setAttribute("ThreeMonthsCount", CountUsersLastThreeMonths);
        request.setAttribute("CountAllUsers", CountAllUsers);
        request.setAttribute("EmployerObject", users);
        request.setAttribute("Tracker", "active");
        request.setAttribute("CountUsersDeletedLastThreeMonths", CountUsersDeletedLastThreeMonths);
        request.setAttribute("EmployeeModelList", AllTempUsersList);

    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("Email") != null) {
            UserEmailId = request.getParameter("Email");
        }
        if (request.getParameter("Firstname") != null) {
            FirstName = request.getParameter("Firstname");
        }
        if (request.getParameter("Lastname") != null) {
            LastName = request.getParameter("Lastname");
        }

    }


}
