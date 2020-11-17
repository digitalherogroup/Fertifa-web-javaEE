package com.fertifa.app.company.Delete;

import com.fertifa.app.constants.Constances;
import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Users;
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

@WebServlet("/employer/delete")
public class DeletePermanent extends com.fertifa.app.baseUrl.BaseUrl {
    private String SessionUserEmail = "";
    private UsersController usersController = new UsersController();
    private int CompanyId = 0;
    private String UserEmailId = "";
    private List<Users> usersList = new ArrayList<>();
    private int CountUsersLastThreeMonths = 0;
    private int CountAllUsers = 0;
    private List<Users> AllTempUsersList = new ArrayList<>();
    private Users users = new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA,getEmployerId(request,response));
            deletePermanent(request, response);
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

    private void deletePermanent(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        getParameters(request);
        countNewUsersForLastThreeMonths();
        countAllusersOfCompany();
        getModelList();
        getAllUserInfoById(users);
        deletUserByCompany(request, response);

    }


    private void deletUserByCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (usersController.ChangeUserDeletionStatus(Integer.parseInt(UserEmailId)) > 0) {
            request.getSession().setAttribute("session_success_message","You successfully deleted");
            setRequests(request);
            gotoPage(request, response);
        } else {
            request.getSession().setAttribute("session_error_message","Something went wrong");
            setRequests(request);
            gotoPage(request, response);
        }
    }

    private void getModelList() throws SQLException {
        AllTempUsersList = usersController.getUsersByCompanyAndUser(CompanyId, Constances.USERS);
    }


    private void getAllUserInfoById(Users users) throws SQLException {
        usersList = usersController.getAllUsersListById(users.getId());
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/employer/enrolmentdata";
        response.sendRedirect(url);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("Tracker", "active");
        request.setAttribute("EmployerObject", users);
        request.setAttribute("Tracker", "active");
        request.setAttribute("EmployeeModelList", AllTempUsersList);
        request.setAttribute("ThreeMonthsCount", CountUsersLastThreeMonths);
        request.setAttribute("ThreeMonthsCount", CountUsersLastThreeMonths);
        request.setAttribute("CountAllUsers", CountAllUsers);
        request.setAttribute("UserOnline", usersList);
    }

    private void getParameters(HttpServletRequest request) {
        System.out.println(request.getParameter("idDelete"));
        if (request.getParameter("idDelete") != null) {
            UserEmailId = request.getParameter("idDelete");
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

    }

}