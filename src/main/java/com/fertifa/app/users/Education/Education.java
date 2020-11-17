package com.fertifa.app.users.Education;


import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.NewsController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.NewsModel;
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

@WebServlet("/employee/education")
public class Education extends com.fertifa.app.baseUrl.BaseUrl {
    private int UserId = 0;
    private UsersController usersController = new UsersController();
    List<Users> usersList = new ArrayList<>();
    List<NewsModel> newsModelList = new ArrayList<>();
    NewsController newsController = new NewsController();
    private Users users = new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            education(request, response);
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

    private void education(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getAllNews();
        setRequests(request);
        gotoPage(request, response);
    }


    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/Education.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("education", "active");
        request.setAttribute("NewsModelList", newsModelList);
        request.setAttribute("EmployeeObject", users);
    }

    private void getAllNews() throws SQLException {
        newsModelList = newsController.getAll();
    }
}
