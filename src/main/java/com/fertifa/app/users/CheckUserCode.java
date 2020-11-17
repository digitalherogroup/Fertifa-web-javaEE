package com.fertifa.app.users;

import com.fertifa.app.controllers.HealthHistroyController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CheckUserCode")
public class CheckUserCode extends HttpServlet {
    private String UserId = "";
    private String Company = "";
    private String Email = "";
    private String Code = "";
    private String userFirstName = "";
    private String userSecondName = "";
    private String userEmail = "";
    private String TokenCode = "";
    private int userId = 0;
    private UsersController usersController = new UsersController();
    private List<Users> usersList = new ArrayList<>();
    private String SessionUserEmail ="";
    int healthHistory = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            checkUserCode(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            checkUserCode(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkUserCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        requestParameters(request);
        getAllUserInfoById(Integer.parseInt(UserId));
        checkHelthHistory(Integer.parseInt(UserId));
        checkInputs(request, response);
    }

    private void checkHelthHistory(int userId) throws SQLException {
        HealthHistroyController healthHistroyController = new HealthHistroyController();
        if (healthHistroyController.getHistoryById(userId).size() > 0) {
            healthHistory = 1;
        }else{
            healthHistory=0;
        }
    }
    private void getAllUserInfoById(int userId) throws SQLException {
        usersList = usersController.getAllUsersListById(userId);
    }
    private void checkInputs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        if (CheckInput(TokenCode, Code)) {
            usersList = usersController.getAllUsersListById(Integer.parseInt(UserId));
            userFirstName = usersList.get(0).getFirstName();
            userSecondName = usersList.get(0).getLastName();
            userEmail = usersList.get(0).getEmail();
            userId = usersList.get(0).getId();
            //getSession(request,response);
            setRequeests(request);
            changeUserStatus(UserId);
            gotoSuccessRegisteredPage(request, response);
        } else {
            setRequeests(request);
            gotoPageWithError(request, response);
        }
    }

    private int changeUserStatus(String UserId) throws SQLException {
        return usersController.ChangeCompanyStatusToActive(UserId);
    }
    /*private void getSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null && !session.isNew()) {
            String sessionId = session.getId();
            session.setAttribute("SessionId",sessionId);
            session.setAttribute("SessionUserEmail", userEmail);
            session.setMaxInactiveInterval(0);
            SessionUserEmail = (String) session.getAttribute("SessionUserEmail");
            System.out.println("SessionUserEmail " + SessionUserEmail);

        }
        else{
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }*/

    private void gotoSuccessRegisteredPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/employee/employee-dashboard";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void gotoPageWithError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "Wrong code please try again");
        request.setAttribute("Tracker", "active");
        request.setAttribute("UserId", UserId);
        request.setAttribute("ID", UserId);
        request.setAttribute("HealthHistory", healthHistory);
        request.getRequestDispatcher("EmployersRegisterCode.jsp").forward(request, response);
    }

    private void setRequeests(HttpServletRequest request) {
        request.setAttribute("UserEmail", userEmail);
        request.setAttribute("UserFirstName", userFirstName);
        request.setAttribute("UserLasttName", userSecondName);
        request.setAttribute("UserOnline", usersList);
        request.setAttribute("UserId", UserId);
        request.setAttribute("ID", UserId);
        request.setAttribute("HealthHistory", healthHistory);
    }

    private boolean CheckInput(String codes, String code) {
        return code.equals(codes);
    }

    private void requestParameters(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            UserId = request.getParameter("id");
        } else if (request.getAttribute("id") != null) {
            UserId = String.valueOf(request.getAttribute("id"));
        }

        HttpSession session = request.getSession();
        TokenCode = (String) session.getAttribute("TokenCode");


        if (request.getParameter("code") != null) {
            Code = request.getParameter("code").trim();
        }
    }
}
