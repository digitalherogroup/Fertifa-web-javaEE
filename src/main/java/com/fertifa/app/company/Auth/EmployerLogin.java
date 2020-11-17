package com.fertifa.app.company.Auth;


import com.fertifa.app.constants.Constances;
import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.helpser.ChookiManager;
import com.fertifa.app.models.Users;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@RolesAllowed("2")
@WebServlet("/employer/employer-dashboard")
public class EmployerLogin extends com.fertifa.app.baseUrl.BaseUrl {
    private String userEmail = "";
    private String userPassword = "";
    private UsersController usersController = new UsersController();
    private Users users = new Users();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userEmail = "";
        userPassword = "";
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userEmail = "";
        userPassword = "";
        try {
            loginCheck(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loginCheck(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (ChookiManager.isUserLogged(request, response, 2)) {
            ChookiManager.UserCookie userCookie = ChookiManager.getCookiData(request, response, 2);
            HttpSession session = request.getSession();
            assert userCookie != null;
            createSessionsWithCookie(session, userCookie);
            users = usersController.findById(Constances.USER_ID_INDATA, userCookie.getUserId());
            if (users == null) {
                ChookiManager.removeAll(request, response, 2);
                getParameters(request, response);
                if (!validation()) {
                    GotoLogin(request, response);
                    return;
                } else {
                    startInputChecks(request, response);
                }
            } else {
                SetAttributes(request);
                gotoMasterAdminPage(request, response, users);
            }
        } else {
            userEmail = "";
            userPassword = "";
            getParameters(request, response);
            if(validation()) {
                GotoLogin(request, response);
                return;
            }else {
                startInputChecks(request, response);
            }
        }
    }

    private void createSessionsWithCookie(HttpSession session, ChookiManager.UserCookie userCookie) {
        // put corresponding data to session
        session.setAttribute("userEmail", userCookie.getUserEmail());
        session.setAttribute("employerEmail", userCookie.getUserEmail());
        session.setAttribute("userId", userCookie.getUserId());
        session.setAttribute("userRole", userCookie.getUserRole());
        //setting session to expiry in 0
        session.setMaxInactiveInterval(0);
    }


    private void createSessionsWithUser(HttpSession session, Users users) {
        // put corresponding data to session
        session.setAttribute("employerEmail", users.getEmail());
        session.setAttribute("userEmail", users.getEmail());
        session.setAttribute("userId", users.getId());
        session.setAttribute("userRole", users.getBranchId());
        //setting session to expiry in 0
        session.setMaxInactiveInterval(0);
    }

    private boolean validation() {
        return userEmail == null || userPassword == null || userEmail.isEmpty() || userPassword.isEmpty();
    }

    private void startInputChecks(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        if (CheckUsersInputs(userEmail, userPassword)) {
            // user input validation successfully
            users = usersController.findById(EmployerConstance.EMPLOYER_EMAIL_INDATA, userEmail);
            if (isValideUserRole(users)) {
                handleSession(request, response, users);
            } else {
                GotoLogin(request, response);
            }
        } else {
            GotoLogin(request, response);
        }
    }

    private boolean isValideUserRole(Users users) {
        if (users == null) return false;
        return users.getBranchId() == (EmployerConstance.EMPLOYE_ROLE_ID);
    }

    private void handleSession(HttpServletRequest request, HttpServletResponse response, Users users) throws IOException, SQLException, ServletException {
        // get session from request
        HttpSession session = request.getSession();
        createSessionsWithUser(session, users);

        ChookiManager.saveDataToCookie(String.valueOf(users.getId()), users.getEmail(), String.valueOf(users.getBranchId()), 2,users.getStatus(), response);
        SetAttributes(request);
        gotoMasterAdminPage(request, response, users);
    }


    private void GotoLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("message", "Your email or password is incorrect. \n\nPlease try again or click \"forgot your password?\" \n\nto reset your password");
        String url = "/employer";
        response.sendRedirect(url);
    }


    private void gotoMasterAdminPage(HttpServletRequest request, HttpServletResponse response, Users users) throws ServletException, IOException, SQLException {
        if (isValideUserRole(users)) {
            String url = request.getServletPath() + "/dashboard.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } else {
            GotoLogin(request, response);
        }
    }


    private void SetAttributes(HttpServletRequest request) {
        request.setAttribute("EmployerObject", users);
    }

    private boolean CheckUsersInputs(String email, String password) throws SQLException {
        return usersController.validateUserImputs(email, password);
    }

    private void getParameters(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("email") != null) {
            userEmail = request.getParameter("email");
        }
        if (request.getParameter("password") != null) {
            userPassword = request.getParameter("password");
        }
    }
}