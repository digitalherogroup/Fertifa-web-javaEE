package com.fertifa.app.company.enrolmentdata;

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
import java.util.*;

@WebServlet("/employer/enrolmentdata")
public class EnrollmentDataCompany extends com.fertifa.app.baseUrl.BaseUrl {
    private String messageUploadErrors = "";
    private int userInvitingId = 0;
    private List<Users> employeeModels = new ArrayList<>();
    private List<Users> AllTempUsersList = new ArrayList<>();
    private UsersController usersController = new UsersController();
    private ArrayList<String> colletingList = new ArrayList<>();

    private int CountUsersLastThreeMonths = 0;
    private int CountUsersDeletedLastThreeMonths = 0;
    private int CountAllUsers = 0;
    private InvitationController invitationController = new InvitationController();
    String token = "";
    String firstName = "";
    String lastName = "";
    String email = "";
    int userId = 0;
    private Users users = new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA,getEmployerId(request,response));
            enrollmentDataCompany(request, response);
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

    private void enrollmentDataCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        getParameters(request, response);
        countNewUsersForLastThreeMonths();
        countAllusersOfCompany();
        getRequests(request, response);
        gotoPage(request, response);
    }

    private void countAllusersOfCompany() {
        CountAllUsers = usersController.CountUsers(Constances.USERS, users.getId());
    }

    private void countNewUsersForLastThreeMonths() {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Timestamp timestamp2 = new Timestamp(new Date().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.MONTH, -1);
        timestamp = new Timestamp(cal.getTime().getTime());
        CountUsersLastThreeMonths = usersController.CountUsersMonths(timestamp, timestamp, Constances.USERS, users.getId());
        CountUsersDeletedLastThreeMonths = usersController.CountUserDeleteMonths(new Timestamp(new Date().getTime()), timestamp, Constances.USERS, users.getId());
    }


    private void getParameters(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        if (request.getAttribute("EmployeeModelList") != null) {
            AllTempUsersList = (List<Users>) request.getAttribute("EmployeeModelList");

        } else {
            AllTempUsersList = usersController.getUsersByCompanyAndUser(users.getId(), Constances.USERS);
        }
        colletingList = new ArrayList<>();
        Enumeration<String> en = request.getParameterNames();
        if (en.hasMoreElements()) {
            while (en.hasMoreElements()) {
                Object objOri = en.nextElement();
                String param = String.valueOf(objOri);
                String value = request.getParameter(param);
                colletingList.add(value);
                if (startSingleIvintation(request, response, colletingList)) {
                    AllTempUsersList = usersController.getUsersByCompanyAndUser(users.getId(), Constances.USERS);
                } else {
                    AllTempUsersList = usersController.getUsersByCompanyAndUser(users.getId(), Constances.USERS);
                }
            }

        } else {
            AllTempUsersList = usersController.getUsersByCompanyAndUser(users.getId(), Constances.USERS);

        }
    }


    private boolean startSingleIvintation(HttpServletRequest request, HttpServletResponse response, ArrayList<String> colletingList) throws SQLException, ServletException, IOException {
        boolean checked = false;
        if (!colletingList.contains("on")) {
            if (SingelAdd(request, response)) {
                checked = true;
            }
        } else {
            if (GroupAdd(request, response)) {
                return true;
            }
        }
        return checked;
    }

    private boolean GroupAdd(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        boolean emailsend = false;
        for (int j = 1; j < colletingList.size(); j++) {
            userInvitingId = Integer.parseInt(colletingList.get(j));
            employeeModels = usersController.getAllUsersListById(userInvitingId);

            firstName = employeeModels.get(0).getFirstName();
            lastName = employeeModels.get(0).getLastName();
            email = employeeModels.get(0).getEmail();
            userId = employeeModels.get(0).getId();
            token = (TokenMaker.buildToken(12));

            if (invitationController.AddNewTokenAndUser(CreateUserObject()) > 0) {
                SendingEmailNewUser.sendUser(token, firstName, lastName, email, request, response);
                request.getSession().setAttribute("session_success_message", "invitation sent successfully");
                emailsend = true;
            }

        }
        return emailsend;
    }

    private Tokens CreateUserObject() {
        return new Tokens(userId, token, firstName, lastName, email);
    }

    private boolean SingelAdd(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        boolean emailsend = false;
        for (int j = 0; j < colletingList.size(); j++) {
            userInvitingId = Integer.parseInt(colletingList.get(j));
            employeeModels = usersController.getAllUsersListById(userInvitingId);
            if (checkNameInput(employeeModels)) {
                continue;
            } else {
                firstName = employeeModels.get(0).getFirstName();
            }

            if (checkLastNameinput(employeeModels)) {
                continue;
            } else {
                lastName = employeeModels.get(0).getLastName();
            }
            if (checkEmailinput(employeeModels)) {
                continue;
            } else {
                email = employeeModels.get(0).getEmail();
            }

            userId = employeeModels.get(0).getId();
            token = (TokenMaker.buildToken(12));
            if (invitationController.AddNewTokenAndUser(CreateUserObject()) > 0) {
                SendingEmailNewUser.sendUser(token, firstName, lastName, email, request, response);
                String message = "invitation sent successfully";
                request.getSession().setAttribute("session_success_message", message);
                emailsend = true;
            }
        }
        return emailsend;
    }

    private boolean checkEmailinput(List<Users> employeeModels) {
        return employeeModels.get(0).getEmail().toLowerCase().equals("email")
                || employeeModels.get(0).getEmail().toLowerCase().equals("")
                || employeeModels.get(0).getEmail().toLowerCase().equals(" ")
                || employeeModels.get(0).getEmail().toLowerCase().equals("  ")
                || !employeeModels.get(0).getEmail().toLowerCase().contains("@")
                && employeeModels.get(0).getEmail() == null;
    }

    private boolean checkLastNameinput(List<Users> employeeModels) {
        return employeeModels.get(0).getLastName().toLowerCase().equals("lastname")
                || employeeModels.get(0).getLastName().toLowerCase().equals("last name")
                || employeeModels.get(0).getLastName().toLowerCase().equals("name")
                || employeeModels.get(0).getLastName().toLowerCase().equals("")
                || employeeModels.get(0).getLastName().toLowerCase().equals(" ")
                || employeeModels.get(0).getLastName().toLowerCase().equals("  ")
                || employeeModels.get(0).getLastName().toLowerCase().equals("last")
                && employeeModels.get(0).getLastName() == null;
    }

    private boolean checkNameInput(List<Users> employeeModels) {
        return employeeModels.get(0).getFirstName().toLowerCase().equals("firstname")
                || employeeModels.get(0).getFirstName().toLowerCase().equals("first name")
                || employeeModels.get(0).getFirstName().toLowerCase().equals("name")
                || employeeModels.get(0).getFirstName().toLowerCase().equals("")
                || employeeModels.get(0).getFirstName().toLowerCase().equals(" ")
                || employeeModels.get(0).getFirstName().toLowerCase().equals("  ")
                || employeeModels.get(0).getFirstName().toLowerCase().equals("first")
                && employeeModels.get(0).getFirstName() == null;
    }

    private void getRequests(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("Tracker", "active");
        request.setAttribute("MessageUploadErrors", messageUploadErrors);
        request.setAttribute("EmployeeModelList", AllTempUsersList);
        request.setAttribute("ThreeMonthsCount", CountUsersLastThreeMonths);
        request.setAttribute("CountAllUsers", CountAllUsers);
        request.setAttribute("EmployerObject", users);
        request.setAttribute("CountUsersDeletedLastThreeMonths", CountUsersDeletedLastThreeMonths);

    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        if (request.getSession().getAttribute("token") == null) {
            request.getSession().setAttribute("token", "2");
        }
        String url = request.getServletPath()+"/EmployeeTracker.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("session_error_message", null);
        request.getSession().setAttribute("session_success_message", null);
    }
}
