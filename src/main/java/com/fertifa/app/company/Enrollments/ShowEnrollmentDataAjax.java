package com.fertifa.app.company.Enrollments;

import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fertifa.app.helpser.ChookiManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@WebServlet("/ShowEnrollmentDataAjax")
public class ShowEnrollmentDataAjax extends HttpServlet {
    private String SessionUserEmail = "";
    private UsersController usersController = new UsersController();
    private int CompanyId = 0;
    private List<Users> usersList = new ArrayList<>();
    private String FromDate = "";
    private String EndDate = "";
    private String[] myArrayDates;
    private Integer[] myArrayCount;
    private int CountingUsers = 0;
    private int CountingSixMonthUsers = 0;
    private int CountingYearUsers = 0;
    private Map<String, Object[]> jsonMap = new LinkedHashMap<>();
    private int CompanyRole;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CompanyRole = 2;
            ChookiManager.UserCookie userCookie = ChookiManager.getCookiData(request,response, CompanyRole);
            if (userCookie == null) {
                // go to login page
                gotoLoginPage(request, response);
                return;
            }
            SessionUserEmail = userCookie.getUserEmail();
            CompanyId = Integer.parseInt(userCookie.getUserId());
            //CompanyRole = Integer.parseInt(userCookie.getUserRole());
            showEnrollmentDataAjax(request, response);
        }catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                gotoLoginPage(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CompanyRole = 2;
            ChookiManager.UserCookie userCookie = ChookiManager.getCookiData(request,response, CompanyRole);
            if (userCookie == null) {
                // go to login page
                gotoLoginPage(request, response);
                return;
            }
            SessionUserEmail = userCookie.getUserEmail();
            CompanyId = Integer.parseInt(userCookie.getUserId());
            //CompanyRole = Integer.parseInt(userCookie.getUserRole());
            showEnrollmentDataAjax(request, response);
        }catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                gotoLoginPage(request, response);
            }
        }
    }

    private void showEnrollmentDataAjax(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        UnicodeingSevlet(request);
        getAllUserInfoById(CompanyId);
        getGeneralStatic(request, response);
        SetAttributes(request);
        gotoPage(request, response);
    }
    private void SetAttributes(HttpServletRequest request) {
        request.setAttribute("ID", CompanyId);
        request.setAttribute("UserOnline", usersList);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("jsonMap", jsonMap);
        request.getRequestDispatcher("EnrolmentData.jsp").include(request, response);
        request.getSession().setAttribute("successNotices",null);
        request.getSession().setAttribute("errorNotices",null);
    }

    private void getGeneralStatic(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        if (request.getParameter("dateFrom") != null) {
            FromDate = request.getParameter("dateFrom");
        }
        if (request.getParameter("dateTo") != null) {
            EndDate = request.getParameter("dateTo");
        }


        Timestamp timestamp = new Timestamp(new Date().getTime());


        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.MONTH, -6);
        String timestampSixLaster = new Timestamp(cal.getTime().getTime()).toString();

        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.MONTH, -12);
        String timestampYearLaster = new Timestamp(cal.getTime().getTime()).toString();

        CountingUsers = usersController.getCountAllUsers(CompanyId);
        CountingSixMonthUsers = usersController.getCountAllUsersForSix(timestamp, timestampSixLaster, CompanyId);
        CountingYearUsers = usersController.getCountAllUsersForSix(timestamp, timestampYearLaster, CompanyId);

        usersList = usersController.getCountAllUsersByCompanyId(CompanyId, EndDate, FromDate);

        myArrayDates = new String[usersList.size()];
        myArrayCount = new Integer[usersList.size()];
        String[] MyArrAyStatus = {"success"};

        jsonMap = new LinkedHashMap<>();
        if (usersList.size() > 0) {
            jsonMap.put("status", MyArrAyStatus);
            for (int i = 0; i < usersList.size(); i++) {
                System.out.println(usersList.get(i).getDateString());
                myArrayDates[i] = usersList.get(i).getDateString();
                System.out.println(usersList.get(i).getId());
                myArrayCount[i] = usersList.get(i).getId();
            }
            //usersListExport.add("'" + usersList.get(i).getDateString() + "'");
            jsonMap.put("dates", myArrayDates);
            jsonMap.put("count", myArrayCount);


            ObjectMapper mapper = new ObjectMapper();
            PrintWriter out = response.getWriter();
            out.println(mapper.writeValueAsString(jsonMap));
            out.flush();
            out.close();
        } else {
            String[] MyArrAyStatusError = {"error"};
            jsonMap.put("status",MyArrAyStatusError);
            ObjectMapper mapper = new ObjectMapper();
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            out.println(mapper.writeValueAsString(jsonMap));
            out.flush();
            out.close();
        }
    }

    private void getAllUserInfoById(int userId) throws SQLException {
        usersList =  usersController.getAllUsersListById(userId);
    }

    private void gotoLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (CompanyRole == 2) {
            response.sendRedirect("employerloginpage.jsp");
        } else if (CompanyRole == 3) {
            response.sendRedirect("employeeloginpage.jsp");
        } else {
            response.sendRedirect("splash.jsp");
        }
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
