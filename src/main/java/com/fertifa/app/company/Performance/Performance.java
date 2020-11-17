package com.fertifa.app.company.Performance;

import com.fertifa.app.constants.Constances;
import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.ShoppingCartController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.dao.EnrollmentModel;
import com.fertifa.app.models.ShoppingCartFinal;
import com.fertifa.app.models.Users;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@WebServlet("/employer/performance")
public class Performance extends com.fertifa.app.baseUrl.BaseUrl {
    private static double PERCENTAGE_FOR_SAVING = 0.10;
    private static int COAST = 20;

    private UsersController usersController = new UsersController();
    private ShoppingCartFinal shoppingCartFinal = new ShoppingCartFinal();
    private ShoppingCartController shoppingCartController = new ShoppingCartController();
    private float subscribtionCoast = 0;
    private String totalSavingForLastMonth = "";
    private String totalSaving = "";
    private List<Users> usersList = new ArrayList<>();
    private List<EnrollmentModel> EnrollmentList = new ArrayList<>();
    private List<String> dates = new ArrayList<>();
    private List<String> savings = new ArrayList<>();
private Users users = new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA,getEmployerId(request,response));
            performance(request, response);
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

    private void performance(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getTotalSaving(users);
        totalSavingsLastMonth(users);
        subscriptionCoast(users);
        chartDetails(users);
        setRequests(request);
         gotoPage(request, response);
    }

    private void chartDetails(Users users) throws SQLException {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MONTH, -1);

        String lastMonth = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

        String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        EnrollmentList = new ArrayList<>();
        dates = new ArrayList<>();
        savings = new ArrayList<>();
        EnrollmentList = shoppingCartController.getAllSavingDetails(lastMonth, now, users.getId(), PERCENTAGE_FOR_SAVING, 0);
        if (EnrollmentList.size() > 0) {
            for (int i = 0; i < EnrollmentList.size(); i++) {
                dates.add("\"" + EnrollmentList.get(i).getSimpleDate() + "\"");
                savings.add(String.valueOf(EnrollmentList.get(i).getTotal()));
            }
        }
    }

    private void subscriptionCoast(Users users) {

        subscribtionCoast = usersController.getTotalCoastByUsersCount(users.getId(), Constances.FEEDBACKSTATUSAPPROVED, COAST);
        System.out.println(subscribtionCoast);
    }

    private void totalSavingsLastMonth(Users users) throws SQLException {
        String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MONTH, -1);
        String lastMonth = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        totalSavingForLastMonth = shoppingCartController.getTotalSavingsByMonths(lastMonth, now, PERCENTAGE_FOR_SAVING, users.getId(), 0);
        System.out.println("totalSavingForLastMonth " + totalSavingForLastMonth );
    }

    private void getTotalSaving(Users users) throws SQLException {
        String now = "";
        String lastMonth = "";
        totalSaving = shoppingCartController.getTotalSavingsByMonths(lastMonth, now, PERCENTAGE_FOR_SAVING, users.getCompanyId(), 0);
        System.out.println("totalSavingForLastMonth " + totalSaving );
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String url = request.getServletPath()+"/CostPerformance.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("Performance", "active");
        request.setAttribute("EmployerObject", users);
        request.setAttribute("SubscribtionCoast", subscribtionCoast);
        request.setAttribute("TotalSavingForLastMonth", totalSavingForLastMonth);
        request.setAttribute("TotalSaving", totalSaving);
        request.setAttribute("Dates", dates);
        request.setAttribute("Savings", savings);
    }


}
