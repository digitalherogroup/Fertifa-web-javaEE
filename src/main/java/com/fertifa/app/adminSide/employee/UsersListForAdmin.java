package com.fertifa.app.adminSide.employee;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.RoleController;
import com.fertifa.app.controllers.SessionController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Users;

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

@WebServlet("/UsersListForAdmin")
public class UsersListForAdmin extends HttpServlet {
    private String SessionEmail = null;
    private RoleController roleController = new RoleController();
    private AdminController adminController = new AdminController();
    private List<Users> usersList = new ArrayList<>();
    private SessionController sessionController = new SessionController();
    private List<Admins> adminFullList = new ArrayList<>();
    private int AdminId;
    private String AdminEmail;
    private int AdminRole;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean isLogined = AdminCookiManager.isUserLogged(request,response);
            if (isLogined) {
                AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request,response);
                if (userCookie == null) {
                    // go to login page
                    gotoLoginPage(request, response);
                    return;
                }
                AdminEmail = userCookie.getUserEmail();
                AdminId = userCookie.getUserId();
                AdminRole = userCookie.getUserRole();
                usersListForAdmin(request, response);
            } else {
                gotoLoginPage(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                gotoLoginPage(request, response);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean isLogined = AdminCookiManager.isUserLogged(request,response);
            if (isLogined) {
                AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request,response);
                if (userCookie == null) {
                    // go to login page
                    gotoLoginPage(request, response);
                    return;
                }
                AdminEmail = userCookie.getUserEmail();
                AdminId = userCookie.getUserId();
                AdminRole = userCookie.getUserRole();
                usersListForAdmin(request, response);
            } else {
                gotoLoginPage(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                gotoLoginPage(request, response);
            }
        }
    }

    private void usersListForAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {

        UnicodeingSevlet(request);
        startAdminControle();
        setRequests(request);
        gotoPage(request,response);

    }

    /**
     * All com.fertifa.app.Admin Actions
     */
    private void startAdminControle() {
        getAdminFullDetails(AdminId);
    }
    private void getAdminFullDetails(int adminId) {
        adminFullList = adminController.getAllAdminListById(adminId);
    }

    /**
     * Setting requests
     * @param request
     */
    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminFullList", adminFullList);
        request.setAttribute("UsersList",usersList);
        request.setAttribute("AdminId", AdminId);
        request.setAttribute("ID", AdminId);
    }

    /**
     * Sending request to the users page
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("com/fertifa/app/adminSide/Users.jsp").forward(request,response);
        request.getSession().setAttribute("successNotices",null);
        request.getSession().setAttribute("errorNotices",null);
    }


    private int getRoleById(String RoleName) throws SQLException {
        return roleController.getRoleNameById(RoleName);
    }

    private void gotoLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("com/fertifa/app/adminSide/SignIn.jsp").forward(request,response);
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
