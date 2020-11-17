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

@WebServlet("/employee/education/educations")
public class Educations extends com.fertifa.app.baseUrl.BaseUrl {

    private UsersController usersController = new UsersController();
    List<NewsModel> newsModelList = new ArrayList<>();
    List<NewsModel> fullNewsModelList = new ArrayList<>();
    NewsController newsController = new NewsController();
    private int newsId = 0;
    private int perId = 0;
    private int nextId = 0;
    private Users users = new Users();

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            educations(request, response);
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

    private void educations(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getParameters(request);
        getNewsById();
        getNextPerId();
        getAllNews();
        getAttributes(request);
        gotoPage(request, response);
    }

    private void getNextPerId() {
        perId = newsController.getPerivousId(newsId);
        nextId = newsController.getNextId(newsId);
    }

    private void getAllNews() throws SQLException {
        fullNewsModelList = newsController.getAll();
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/EduSingle.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void getAttributes(HttpServletRequest request) {
        request.setAttribute("education", "active");
        request.setAttribute("NewsModelList", newsModelList);
        request.setAttribute("FullNewsModelList", fullNewsModelList);
        request.setAttribute("EmployeeObject", users);
        request.setAttribute("perId", perId);
        request.setAttribute("nextId", nextId);
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            newsId = Integer.parseInt(request.getParameter("id"));
        }
    }

    private void getNewsById() throws SQLException {
        newsModelList = newsController.getById(newsId);
    }
}
