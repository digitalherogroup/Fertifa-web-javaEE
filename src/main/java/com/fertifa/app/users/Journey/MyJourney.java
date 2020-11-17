package com.fertifa.app.users.Journey;

import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.ShoppingCartController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.dao.HealthHistoryDao;
import com.fertifa.app.models.HealthHistory;
import com.fertifa.app.models.ShoppingCartFinal;
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

@WebServlet("/employee/journey")
public class MyJourney extends com.fertifa.app.baseUrl.BaseUrl {
    private int UserId = 0;
    private UsersController usersController = new UsersController();
    private HealthHistoryDao healthHistoryDao = new HealthHistoryDao();
    private String createTimeHealthHistory;
    private List<ShoppingCartFinal> journeyList = new ArrayList<>();
    private List<ShoppingCartFinal> left = new ArrayList<>();
    private  List<ShoppingCartFinal> rigth = new ArrayList<>();
    private List<HealthHistory> journeyHeatList = new ArrayList<>();

    private Users users = new Users();
    private String userRegistrationDate = "";
    private List<Users> usersList = new ArrayList<>();
    private int helthHistorySize = 0;

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            myJourney(request, response);
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

    private void myJourney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        getUserJourney(users.getId());
        getCreateTimeHealthHistory(users.getId());
        getAllUser();
        setAttributes(request);
        gotoPage(request, response);
    }

    private void getUserJourney(int userId) throws SQLException {
        helthHistorySize = getHelthHistorySize();

        journeyList = new ArrayList<>();
        left = new ArrayList<>();
        rigth = new ArrayList<>();
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        journeyList = shoppingCartController.getListObjectByUserId(userId);

    }


    private int getHelthHistorySize() {
        try {
            List<HealthHistory> helthHistroy = healthHistoryDao.getHistoryById(users.getId());
            return helthHistroy.size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String url = request.getServletPath()+"/Journey.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0);
    }

    private void setAttributes(HttpServletRequest request) {
        request.setAttribute("HealthSize", helthHistorySize);
        request.setAttribute("journeyListSize", journeyList.size());
        request.setAttribute("MyJourney", "active");
        request.setAttribute("UsersList", usersList);
        request.setAttribute("EmployeeObject", users);
        request.setAttribute("ID", users.getId());
        request.setAttribute("Left", left);
        request.setAttribute("Rigth", rigth);
        request.setAttribute("JourneyList", journeyList);
        request.setAttribute("UserRegistrationDate", userRegistrationDate);
        request.setAttribute("CreateTimeHealthHistory", createTimeHealthHistory);

    }

    private void getAllUser() throws SQLException {
        usersList = new ArrayList<>();
        usersList = usersController.getAllUsersListById(users.getId());
        if (usersList != null && usersList.size() != 0) {
            String getCreationDate = usersList.get(0).getGetCreationDate();
            String [] reationDateArra = getCreationDate.split("-");
            userRegistrationDate = reationDateArra[2];
        } else {
            usersList = new ArrayList<>();
            userRegistrationDate = null;
        }
    }

    private void getCreateTimeHealthHistory(int id) throws SQLException {
        createTimeHealthHistory = "";
        journeyHeatList = new ArrayList<>();
        journeyHeatList = healthHistoryDao.getHistoryById(id);
        if (journeyHeatList.size() > 0) {
            createTimeHealthHistory = journeyHeatList.get(0).getCreatedDate();
        } else {
            journeyHeatList = new ArrayList<>();
            createTimeHealthHistory = null;
        }
    }
}
