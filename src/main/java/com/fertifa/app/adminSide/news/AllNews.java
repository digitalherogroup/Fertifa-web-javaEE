package com.fertifa.app.adminSide.news;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.NewsController;
import com.fertifa.app.models.Admins;
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

@WebServlet("/admin/news")
public class AllNews extends com.fertifa.app.baseUrl.BaseUrl {

    private AdminController adminController = new AdminController();
    private NewsController newsController = new NewsController();
    private List<NewsModel> newsList = new ArrayList<>();
    private Admins admins = new Admins();
    private int start = 0;
    private int countNews = 0;

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            allNews(request, response);
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

    private void allNews(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request, response);
        getparameters(request);
        getNewsCount();
        getAllNews();
        setRequests(request);
        gotoPage(request, response);
    }

    private void getNewsCount() throws SQLException {
        countNews = newsController.countNews();

    }

    private void getparameters(HttpServletRequest request) {
        if(request.getParameter("pageindex")!=null){
            start = Integer.parseInt(request.getParameter("pageindex"));
        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        String url = request.getServletPath() + "/AllNews.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("NewsList", newsList);
        request.setAttribute("CountNews", countNews);
        request.setAttribute("Start", start);
    }

    private void getAllNews() throws SQLException {
        newsList = newsController.getAll();
        //newsList = newsController.getAllPagination(start);
        System.out.println();
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
