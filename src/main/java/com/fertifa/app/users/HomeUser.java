package com.fertifa.app.users;

import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.ChatSessionController;
import com.fertifa.app.controllers.HealthHistroyController;
import com.fertifa.app.controllers.NewsController;
import com.fertifa.app.controllers.SessionController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.ChatSession;
import com.fertifa.app.models.NewsModel;
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

@WebServlet("/HomeUser")
public class HomeUser extends HttpServlet {
    private int UserId = 0;
    private UsersController usersController = new UsersController();
    private String SessionUserEmail = "";
    private SessionController sessionController = new SessionController();
    private int Id = 0;
    List<Users> usersList = new ArrayList<>();
    private int userId = 0;
    List<ChatSession> chatSessionList = new ArrayList<>();
    List<NewsModel> newsModelList = new ArrayList<>();
    private int branch_id = 0;
    int healthHistory = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            homeUser(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            homeUser(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void homeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        UnicodeingSevlet(request);
        getSession(request, response);
        getUserIdByEmail(SessionUserEmail);
        getUserId(SessionUserEmail);
        getAllUser();
        getNotification(userId);
        getFertifaNews();
        checkHelthHistory(userId);
        setAttributes(request);
        gotoUserAdminPage(request, response);
    }

    private void checkHelthHistory(int userId) throws SQLException {
        HealthHistroyController healthHistroyController = new HealthHistroyController();
        if (healthHistroyController.getHistoryById(userId).size() > 0) {
            healthHistory = 1;
        }
    }

    private void setAttributes(HttpServletRequest request) {
        request.setAttribute("UserOnline", usersList);
        request.setAttribute("ID", Id);
        request.setAttribute("ChatSessionList", chatSessionList);
        request.setAttribute("NewsModelList", newsModelList);
        request.setAttribute("HealthHistory", healthHistory);
    }

    private void getFertifaNews() throws SQLException {
        NewsController newsController = new NewsController();
        newsModelList = newsController.getAll();
    }

    private void getNotification(int userId) {
        ChatSessionController chatSessionController = new ChatSessionController();
        chatSessionList = chatSessionController.getAllSessionDetailsbyId(userId, Constances.CATEGORYNOTIFICATION);
    }

    private void gotoUserAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("dashboard.jsp").include(request, response);
    }

    private void getAllUser() throws SQLException {
        usersList = usersController.getAllUsersListById(userId);
        Id = usersList.get(0).getId();
        branch_id = usersList.get(0).getBranchId();
    }

    private void getUserIdByEmail(String sessionUserEmail) {
        UserId = usersController.getCompanyIdByEmail(sessionUserEmail);
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


    private void getUserId(String userEmail) {
        userId = usersController.getUserIdByEmail(userEmail);
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
        SessionUserEmail = null;
        sessionController.DistroySession(request);
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
