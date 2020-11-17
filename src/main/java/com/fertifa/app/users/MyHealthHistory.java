package com.fertifa.app.users;

import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.HealthHistroyController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.HealthHistory;
import com.fertifa.app.models.Users;
import com.fertifa.app.users.Modal.HelthHistoryModel;
import com.google.gson.Gson;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employee/health-history")
public class MyHealthHistory extends com.fertifa.app.baseUrl.BaseUrl {

    private UsersController usersController = new UsersController();
    List<HealthHistory> healthHistoriesList = new ArrayList<>();
    HealthHistroyController healthHistroyController = new HealthHistroyController();
    List<HelthHistoryModel> helthHistoryModel = new ArrayList<>();
    private Users users = new Users();
    int healthHistory = 0;


    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            myHealthHistory(request, response);
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

    private void myHealthHistory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
             getHistoryById(users.getId());
        getRequests(request);
        gotoPage(request, response);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/MyHealthHistory.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void getRequests(HttpServletRequest request) {
        request.setAttribute("health", "active");
        request.setAttribute("HealthHistory", healthHistory);
        request.setAttribute("HealthHistoriesList", helthHistoryModel);
        request.setAttribute("EmployeeObject", users);
    }

    private void getHistoryById(int userId) throws SQLException {
        healthHistoriesList = new ArrayList<>();
        healthHistoriesList = healthHistroyController.getHistoryById(userId);

        if (healthHistoriesList == null || healthHistoriesList.size() == 0) {
           healthHistoriesList = new ArrayList<>();
            healthHistory = 0;
            helthHistoryModel = new ArrayList<>();
        } else {
            // success case
            String contentJson = healthHistoriesList.get(0).getContent();
            healthHistory = 1;
            helthHistoryModel = new ArrayList<>();
            helthHistoryModel.add(new Gson().fromJson(contentJson, HelthHistoryModel.class));
            if (helthHistoryModel == null) {
                // can not deserialize json to model
                System.out.println();
            } else {
                // Parsing success
                System.out.println();
            }
        }
    }
}
