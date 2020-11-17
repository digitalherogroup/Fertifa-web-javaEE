package com.fertifa.app.company.Tracker;

import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/employer/enrolment")
public class Tracker extends com.fertifa.app.baseUrl.BaseUrl {

    private UsersController usersController = new UsersController();
    private List<Users> usersList = new ArrayList<>();
    private List<Users> usersOnlineList = new ArrayList<>();

    private String FromDate = "";
    private String EndDate = "";
    List<String> usersListExport = new ArrayList<>();
    List<Integer> datesListExport = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();
    int CountingUsers = 0;
    int CountingSixMonthUsers = 0;
    int CountingYearUsers = 0;
    String[] myArrayDates;
    int[] myArrayCount;

    private Users users = new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA,getEmployerId(request,response));
            tracker(request, response);
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

    private void tracker(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getParameters(request);
        getData(response);
        requestPage(request);
        gotoPage(request, response);
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("dateFrom") != null) {
            FromDate = request.getParameter("dateFrom");
        }
        if (request.getParameter("dateTo") != null) {
            EndDate = request.getParameter("dateTo");
        }
    }

    public Date nextDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    private void getData(HttpServletResponse response) throws SQLException, IOException {
        usersList = new ArrayList<>();
        usersListExport = new ArrayList<>();
        datesListExport = new ArrayList<>();
        if (FromDate == null || FromDate.equals("") && EndDate == null || EndDate.equals("")) {
            Timestamp timestamp = new Timestamp(new Date().getTime());
            String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(new Date().getTime());
            cal.add(Calendar.DAY_OF_MONTH, -5);
            String timestampLaster = new Timestamp(cal.getTime().getTime()).toString();
            String lastMonth = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

            cal.setTimeInMillis(new Date().getTime());
            cal.add(Calendar.MONTH, -6);
            String timestampSixLaster = new Timestamp(cal.getTime().getTime()).toString();

            cal.setTimeInMillis(timestamp.getTime());
            cal.add(Calendar.MONTH, -12);
            String timestampYearLaster = new Timestamp(cal.getTime().getTime()).toString();

            CountingUsers = usersController.getCountAllUsers(users.getId());
            CountingSixMonthUsers = usersController.getCountAllUsersForSix(timestamp, timestampSixLaster, users.getId());
            CountingYearUsers = usersController.getCountAllUsersForSix(timestamp, timestampYearLaster, users.getId());

            usersList = usersController.getCountAllUsersByCompanyId(users.getId(), now, lastMonth);

            myArrayDates = new String[usersList.size()];
            myArrayCount = new int[usersList.size()];
            for (int i = 0; i < usersList.size(); i++) {
                System.out.println(usersList.get(i).getDateString());
                myArrayDates[i] = usersList.get(i).getDateString();
                System.out.println(usersList.get(i).getId());
                myArrayCount[i] = usersList.get(i).getId();
            }
        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/EnrolmentData.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void requestPage(HttpServletRequest request) throws JsonProcessingException {
        request.setAttribute("Enrollment", "active");

        request.setAttribute("EmployerObject", users);
        request.setAttribute("UserOnline", usersOnlineList);
        request.setAttribute("UsersListExport", usersListExport);
        request.setAttribute("DatesListExport", datesListExport);
        request.setAttribute("CountingUsers", CountingUsers);
        request.setAttribute("CountingSixMonthUsers", CountingSixMonthUsers);
        request.setAttribute("CountingYearUsers", CountingYearUsers);
        request.setAttribute("MyArrayDates", objectMapper.writeValueAsString(myArrayDates));
        request.setAttribute("MyArrayCount", objectMapper.writeValueAsString(myArrayCount));
    }


}
