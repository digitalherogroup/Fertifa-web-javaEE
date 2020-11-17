package com.fertifa.app.company.Performance;

import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.ShoppingCartController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.dao.EnrollmentModel;
import com.fertifa.app.models.ShoppingCartFinal;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/ShowCostPerformanceDataAjax")
public class ShowCostPerformanceDataAjax extends HttpServlet {
    private String SessionUserEmail = "";
    private UsersController usersController = new UsersController();
    private int CompanyId = 0;
    private int CompanyRole = 0;
    private ShoppingCartFinal shoppingCartFinal = new ShoppingCartFinal();
    private ShoppingCartController shoppingCartController = new ShoppingCartController();
    private int subscribtionCoast = 0;
    private String totalSavingForLastMonth = "";
    private String totalSaving = "";
    private List<Users> usersList = new ArrayList<>();
    private List<EnrollmentModel> EnrollmentList = new ArrayList<>();
    private List<String> dates = new ArrayList<>();
    private List<String> savings = new ArrayList<>();

    private String DateFrom = "";
    private String DateTo = "";
    private static double PERCENTAGE_FOR_SAVING = 0.10;
    private static int COAST = 20;

    private String dateToStamp;
    private String dateFromStamp;
    Map<String, Object> mapJson = new LinkedHashMap<>();


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
            showCostPerformanceDataAjax(request, response);
        } catch (Exception e) {
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
            showCostPerformanceDataAjax(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                gotoLoginPage(request, response);
            }
        }
    }

    private void showCostPerformanceDataAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        UnicodeingSevlet(request);
        getparameters(request);
        getCompanyFullListById(CompanyId);
        getTotalSaving();
        totalSavingsLastMonth();
        subscriptionCoast();
        chartDetails(request, response);
        setRequests(request);
        // gotoPage(request, response);
    }

    private void getparameters(HttpServletRequest request) {
        if(request.getParameter("dateFrom")!= null){
            DateFrom = request.getParameter("dateFrom");
            dateFromStamp = DateFrom;
        }
        if(request.getParameter("dateTo")!= null){
            DateTo = request.getParameter("dateTo");
            dateToStamp = DateTo;
        }
    }

    private void chartDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        EnrollmentList = new ArrayList<>();
        dates = new ArrayList<>();
        savings = new ArrayList<>();
        EnrollmentList = shoppingCartController.getAllSavingDetails(dateFromStamp, dateToStamp, CompanyId, PERCENTAGE_FOR_SAVING, 0);
        if (EnrollmentList.size() > 0) {
            for (int i = 0; i < EnrollmentList.size(); i++) {
                dates.add(EnrollmentList.get(i).getSimpleDate());
                savings.add(String.valueOf(EnrollmentList.get(i).getTotal()));
            }
            gotoSuccesJson(dates, savings, response, request);
        } else {
            sendEmptyJson(response);
        }
    }

    private void gotoSuccesJson(List<String> dates, List<String> savings, HttpServletResponse response, HttpServletRequest request) throws IOException {
        mapJson = new LinkedHashMap<>();
        mapJson.put("status", "success");
        mapJson.put("dates", dates);
        mapJson.put("count", savings);
        sendJsonDetails(response, mapJson);

    }

    private void sendJsonDetails(HttpServletResponse response, Map<String, Object> mapJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.println(mapper.writeValueAsString(mapJson));
        out.flush();
        out.close();
    }

    private void sendEmptyJson(HttpServletResponse response) throws IOException {
        mapJson = new LinkedHashMap<>();
        mapJson.put("status", "success");
        mapJson.put("dates", new ArrayList<>());
        mapJson.put("count", new ArrayList<>());
        sendJsonDetails(response, mapJson);
    }

    private void subscriptionCoast() {

        subscribtionCoast = usersController.getTotalCoastByUsersCount(CompanyId, Constances.FEEDBACKSTATUSAPPROVED, COAST);
        System.out.println(subscribtionCoast);
    }

    private void totalSavingsLastMonth() throws SQLException {

        totalSavingForLastMonth = shoppingCartController.getTotalSavingsByMonths(dateFromStamp, dateToStamp, PERCENTAGE_FOR_SAVING, CompanyId, 0);
        System.out.println(totalSavingForLastMonth);
    }

    private void getTotalSaving() throws SQLException {

        totalSaving = shoppingCartController.getTotalSavingsByMonths(dateFromStamp, dateToStamp, PERCENTAGE_FOR_SAVING, CompanyId, 0);
        System.out.println(totalSaving);
    }


    private void getCompanyFullListById(int companyId) throws SQLException {
        usersList = usersController.getAllUsersListById(companyId);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("CostPerformance.jsp").forward(request, response);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("ID", CompanyId);
        request.setAttribute("Performance", "active");
        request.setAttribute("UserOnline", usersList);
        request.setAttribute("SubscribtionCoast", subscribtionCoast);
        request.setAttribute("TotalSavingForLastMonth", totalSavingForLastMonth);
        request.setAttribute("TotalSaving", totalSaving);
        request.setAttribute("Dates", dates);
        request.setAttribute("Savings", savings);
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

    private void UnicodeingSevlet(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

    }
}
