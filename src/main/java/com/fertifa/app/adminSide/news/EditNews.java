package com.fertifa.app.adminSide.news;


import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.NewsCategoryController;
import com.fertifa.app.controllers.NewsController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.NewsCategory;
import com.fertifa.app.models.NewsModel;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/news/edit")

public class EditNews extends com.fertifa.app.baseUrl.BaseUrl {

    private AdminController adminController = new AdminController();
    private NewsController newsController = new NewsController();
    private List<NewsModel> newsList = new ArrayList<>();
    private int newsId = 0;
    private Admins admins = new Admins();
    private NewsResponse newsResponse = new NewsResponse(new NewsResponse.NewsResponseBuild());
    private List<NewsCategory> allNewsCategory = new ArrayList<>();
    private NewsCategoryController newsCategoryController = new NewsCategoryController();

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            editNews(request, response);
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

    private void editNews(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request, response);
        getParameters(request);
        getNewsById(newsId);
        getAllNewsCategories();
        setRequests(request);
        gotoPage(request, response);
    }

    private void getAllNewsCategories() throws SQLException {
        allNewsCategory = newsCategoryController.showAll();
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        clearChasch(response);
        String url = request.getServletPath() + "/EditNews.jsp";
        request.getRequestDispatcher(url).forward(request, response);

        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }

    private void clearChasch(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("NewsList", newsResponse);
        request.setAttribute("AllCategories",allNewsCategory);
    }

    private void getNewsById(int newsId) throws SQLException {
        newsResponse = newsController.getNewsObjectById(newsId);
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            newsId = Integer.parseInt(request.getParameter("id"));
        }
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response)throws SQLException {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }
}
