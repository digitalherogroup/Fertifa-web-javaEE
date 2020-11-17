package com.fertifa.app.users;

import com.fertifa.app.controllers.InvitationController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.utils.DeviceDetector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CheckInvitationUser")
public class CheckInvitationUser extends HttpServlet {
    private String TokenId = "";
    private String Company = "";
    private InvitationController invitationController = new InvitationController();
    private String UserEmail = "";
    private String firstName = "";
    private String lastName = "";
    private int UserId = 0;
    private int Branch = 0;
    private List<String> ageList = new ArrayList<>();
    UsersController usersController = new UsersController();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            checkInvitationUser(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            checkInvitationUser(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkInvitationUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {

        getparamerts(request, response);

    }

    private void AddAgeList() {
        ageList = new ArrayList<>();
        for (int i = 18; i < 101; i++) {
            ageList.add(String.valueOf(i));
        }
    }

    private void checkIncomes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (ValidateInvitation(TokenId, firstName, lastName)) {
            getListOfTheCompany(TokenId);
            setRequests(request);
            gotoRegisterPage(request, response);
        } else {
            setRequests(request);
            gotoErroPage(request, response);
        }
    }

    private void gotoRegisterPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("EmployersRegister.jsp").forward(request, response);
    }

    private void gotoErroPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("message", "active");
        request.setAttribute("UserId", UserId);
        request.setAttribute("TokenId", TokenId);
        request.setAttribute("UserEmail", UserEmail);
        request.setAttribute("UserFirstName", firstName);
        request.setAttribute("UserLasttName", lastName);
        request.setAttribute("AgeList", ageList);

    }

    private void getListOfTheCompany(String tokenId) throws SQLException {
        UserEmail = invitationController.GetCompanyByTokenId(tokenId);
        UserId = usersController.getUserIdByEmail(UserEmail);
    }

    private boolean ValidateInvitation(String tokenId, String firstName, String lastName) throws SQLException {
        return invitationController.CheckingInvitationUser(tokenId, firstName, lastName);
    }

    private void getparamerts(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        if (request.getParameter("branch_id") != null) {
            Branch = Integer.parseInt(request.getParameter("branch_id"));
        }
        TokenId = request.getParameter("TokenidToCheck");
        firstName = request.getParameter("first");
        lastName = request.getParameter("last");

        checkDevice(request, response);
    }

    private void checkDevice(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        if (!DeviceDetector.detectDevice(request)) {
            String url = "http://m.second.fertifabenefits.com/confirms.jsp?tokenid=" + TokenId + "&first=" + firstName + "&last=" + lastName;
            System.out.println(url);
            response.sendRedirect(url);
            return;
        } else {
            AddAgeList();
            checkIncomes(request, response);
        }
    }
}
