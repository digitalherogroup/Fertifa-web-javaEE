package com.fertifa.app.company;

import com.fertifa.app.controllers.SessionController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Users;
import com.fertifa.app.helpser.ChookiManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/HomeCompany")
public class HomeCompany extends HttpServlet {
    private String SessionUserEmail = "";
    private SessionController sessionController = new SessionController();
    private UsersController usersController = new UsersController();
    private List<Users> usersList =  new ArrayList<>();
    private int Id=0;
    private int branch_id=0;
    private int CompanyId = 0;
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
            homeCompany(request,response);
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
            homeCompany(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                gotoLoginPage(request, response);
            }
        }
    }

    private void homeCompany(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        UnicodeingSevlet(request);
        getSession(request, response);
        getCompanyIdByEmail(SessionUserEmail);
        getAllUserInfoById(CompanyId);
        setRequests(request);
        gotoPage(request,response);
    }
    private void getAllUserInfoById(int userId) throws SQLException {
        usersList =  usersController.getAllUsersListById(userId);
        Id = usersList.get(0).getId();
        branch_id = usersList.get(0).getBranchId();
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("dashboard.jsp").forward(request,response);
    }

    private void getCompanyIdByEmail(String sessionUserEmail) {
        CompanyId = usersController.getCompanyIdByEmail(sessionUserEmail);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("ID", CompanyId);
        request.setAttribute("UserOnline", usersList);

    }

    private void getSession(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        sessionController.getCompanyUsername(request);

        if (!sessionController.getCompanySession(request)) {
            gotoLoginPage(request, response);
        } else {
            SessionUserEmail = sessionController.getCompanyUsername(request);
            System.out.println(SessionUserEmail);
        }
    }

    /**
     * goto login page when user session ends
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void gotoLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (CompanyRole ==2) {
            response.sendRedirect("employerloginpage.jsp");
        } else if (CompanyRole == 3){
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
