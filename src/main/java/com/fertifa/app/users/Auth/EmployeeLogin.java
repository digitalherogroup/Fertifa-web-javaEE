package com.fertifa.app.users.Auth;

import com.fertifa.app.controllers.ChatSessionController;
import com.fertifa.app.controllers.EmployerDiscountModelController;
import com.fertifa.app.controllers.HealthHistroyController;
import com.fertifa.app.controllers.NewsController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.helpser.ChookiManager;
import com.fertifa.app.models.ChatSession;
import com.fertifa.app.models.EmployerDiscountsModel;
import com.fertifa.app.models.NewsModel;
import com.fertifa.app.models.Users;
import org.springframework.stereotype.Controller;

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

@Controller
@WebServlet("/employee/employee-dashboard")
public class EmployeeLogin extends HttpServlet {
    private String userEmail = "";
    private String userPassword = "";
    private UsersController usersController = new UsersController();
    private List<ChatSession> chatSessionList = new ArrayList();
    private List<NewsModel> newsModelList = new ArrayList();
    private int healthHistory = 0;
    private Users users = new Users();
    private String userArgentMobile = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.113 Mobile Safari/537.36";
    EmployerDiscountsModel employerDiscountsModel = new EmployerDiscountsModel();
    EmployerDiscountModelController employerDiscountModelController = new EmployerDiscountModelController();

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
        request.getSession().setAttribute("message", null);
        request.getSession().setAttribute("session_success_message", null);
        users = new Users();
        if (ChookiManager.isUserLogged(request, response, 3)) {
            ChookiManager.UserCookie userCookie = ChookiManager.getCookiData(request, response, 3);
            HttpSession session = request.getSession();
            assert (userCookie != null);
            createSessionsWithCookie(session, userCookie);
            users = usersController.findById("id", userCookie.getUserId());
            if (users == null) {
                ChookiManager.removeAll(request, response, 3);
                getParameters(request, response);
                if (!validation()) {
                    GotoLogin(request, response);
                } else {
                    startInputChecks(request, response);
                }
            } else {
                SetAttributes(request);
                gotoMasterAdminPage(request, response);
            }
        } else {
            userEmail = null;
            userPassword = null;
            getParameters(request, response);
            if (validation()) {
                GotoLogin(request, response);
                return;
            }
            startInputChecks(request, response);
        }
    }


    private void createSessionsWithCookie(HttpSession session, ChookiManager.UserCookie userCookie) {
        session.setAttribute("userEmail", userCookie.getUserEmail());
        session.setAttribute("employeeEmail", userCookie.getUserEmail());
        session.setAttribute("userId", userCookie.getUserId());
        session.setAttribute("userRole", userCookie.getUserRole());
        session.setAttribute("active", userCookie.getActive());

        session.setMaxInactiveInterval(0);
    }

    private void createSessionsWithUser(HttpSession session, Users users) {
        session.setAttribute("employeeEmail", users.getEmail());
        session.setAttribute("userEmail", users.getEmail());
        session.setAttribute("userId", users.getId());
        session.setAttribute("userRole", users.getBranchId());
        session.setAttribute("active", users.getStatus());

        session.setMaxInactiveInterval(0);
    }

    private boolean validation() {
        return (userEmail == null) || (userPassword == null) || (userEmail.isEmpty()) || (userPassword.isEmpty());
    }

    private void startInputChecks(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (CheckUsersInputs(userEmail, userPassword)) {
            users = usersController.findById("email", userEmail);
            if (isValideUserRole(users)) {
                handleSession(request, response, users);
            } else {
                GotoLoginPage(request, response);
            }
        } else if (CheckUserStatus(userEmail, userPassword)) {
            users = usersController.findById("email", userEmail);
            if (users.getStatus() == 4) {
                gotoPaymentPage(request,response);
            } else {
                GotoLoginPage(request, response);
            }
        } else {
            GotoLoginPage(request, response);
        }
    }

    private boolean CheckUserStatus(String email, String password) throws SQLException {
        return usersController.validateUserStatus(email, password);
    }

    private boolean isValideUserRole(Users users) {
        if (users == null) return false;
        return users.getBranchId() == 3;
    }

    private void handleSession(HttpServletRequest request, HttpServletResponse response, Users users) throws IOException, SQLException, ServletException {
        HttpSession session = request.getSession();
        createSessionsWithUser(session, users);

        ChookiManager.saveDataToCookie(String.valueOf(users.getId()), users.getEmail(), String.valueOf(users.getBranchId()), 3, users.getStatus(), response);
        SetAttributes(request);
        gotoMasterAdminPage(request, response);
    }

    private void GotoLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (users.getStatus() == 2) {
            request.getSession().setAttribute("message", "Your account is currently inactive.\n\n <div style='max-width:400px;text-align:center;margin:0 auto;'>This may have been due to a request of your employer \n\nor because of an unpaid invoice. \nPlease call Fertifa on 020 7459 4476 or e-mail us at\n\n<a href = \"mailto: info@fertifa.com\"> info@fertifa.com</a>\n\n if you believe this is incorrect.</div>");
        } else if (users.getStatus() == 4) {
            gotoMasterAdminPage(request, response);
        } else {
            request.getSession().setAttribute("message", "Your email or password is incorrect. \n\nPlease try again or click \"forgot your password?\"  \n\n to reset your password");
        }
        String url = "/employee";
        response.sendRedirect(url);
    }

    private void gotoPaymentPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String url = "/employee/subscription/payment-dashboard.jsp";
        getDiscountRates();
        request.setAttribute("EmployerDiscounts", employerDiscountsModel);
        request.setAttribute("EmployeeObject", users);
        request.setAttribute("ID", users.getId());
        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    private void getDiscountRates() throws SQLException {
        employerDiscountsModel = employerDiscountModelController.findById(users.getCompanyId());
    }

    private void checkHelthHistory(int userId) throws SQLException {
        HealthHistroyController healthHistroyController = new HealthHistroyController();
        if (healthHistroyController.getHistoryById(userId).size() > 0) {
            healthHistory = 1;
        } else {
            healthHistory = 0;
        }
    }

    private void getNotification(int userId) {
        ChatSessionController chatSessionController = new ChatSessionController();
        chatSessionList = chatSessionController.getAllSessionDetailsbyId(userId, 2);
    }

    private void GotoLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        if (users.getStatus() == 2) {
            request.getSession().setAttribute("message", "Your account is currently inactive.\n\n <div style='max-width:400px;text-align:center;margin:0 auto;'>This may have been due to a request of your employer \n\nor because of an unpaid invoice. \nPlease call Fertifa on 020 7459 4476 or e-mail us at\n\n<a href = \"mailto: info@fertifa.com\"> info@fertifa.com</a>\n\n if you believe this is incorrect.</div>");

        } else if (users.getStatus() == 4) {
            gotoMasterAdminPage(request, response);
        } else {
            request.getSession().setAttribute("message", "Your email or password is incorrect. \n\n Please try again or click \"forgot your password?\"  \n\n to reset your password");
        }
        String url = "/employee";
        response.sendRedirect(url);
    }

    private void gotoMasterAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (isValideUserRole(users) && users.getStatus() != 4) {
            getNews();
            getNotification(users.getId());
            if (request.getHeader("User-Agent").equals(userArgentMobile)) {
                String url = "https://m.fertifabenefits.com/employee/employee-dashboard";
                response.sendRedirect(url);
                return;
            } else {
                String url = request.getServletPath() + "/dashboard.jsp";
                request.getRequestDispatcher(url).forward(request, response);
            }
        } else if (isValideUserRole(users) && users.getStatus() == 4) {
            gotoPaymentPage(request, response);
        } else {
            GotoLogin(request, response);
        }
    }

    private void getNews() throws SQLException {
        NewsController newsController = new NewsController();
        newsModelList = new ArrayList<>();
        newsModelList = newsController.getAll();
    }

    private void SetAttributes(HttpServletRequest request) throws SQLException {
        checkHelthHistory(users.getId());
        getNotification(users.getId());
        getFertifaNews();
        request.setAttribute("NewsModelList", newsModelList);
        request.setAttribute("EmployeeObject", users);
        request.setAttribute("HealthHistory", healthHistory);
        request.setAttribute("ChatSessionList", chatSessionList);
    }

    private void getFertifaNews() throws SQLException {
        NewsController newsController = new NewsController();
        newsModelList = new ArrayList<>();
        newsModelList = newsController.getAll();
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