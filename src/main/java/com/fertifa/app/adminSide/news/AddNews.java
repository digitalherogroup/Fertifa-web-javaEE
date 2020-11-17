package com.fertifa.app.adminSide.news;


import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.NewsCategoryController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.NewsCategory;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/news/add")
public class AddNews extends com.fertifa.app.baseUrl.BaseUrl {
    private AdminController adminController = new AdminController();
    private Admins admins = new Admins();
    private List<NewsCategory> allNewsCategory = new ArrayList<>();
    private NewsCategoryController newsCategoryController = new NewsCategoryController();

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            addNews(request, response);
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

    private void addNews(HttpServletRequest request, HttpServletResponse response) throws
        IOException, ServletException, SQLException {
        startAdminControle(request, response);
        getAllNewsCategories();
        setRequests(request);
        gotoPage(request, response);
    }

    private void getAllNewsCategories() throws SQLException {
        allNewsCategory = newsCategoryController.showAll();
        System.out.println();
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
        String url = request.getServletPath() + "/AddNews.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("AllCategories",allNewsCategory);
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response)throws SQLException {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }


}
