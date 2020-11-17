package com.fertifa.app.adminSide.news.category;


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

@WebServlet("/admin/news/category")
public class AllNewsCategory extends com.fertifa.app.baseUrl.BaseUrl {
    private Admins admins = new Admins();
    private AdminController adminController = new AdminController();
    private List<NewsCategory> allNewsCategory = new ArrayList<>();
    private NewsCategoryController newsCategoryController = new NewsCategoryController();
    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(checkCookie(request,response)){
            super.service(request, response);
            showAllNewsCategory(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    private void showAllNewsCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        startAdminControl(request, response);
        getAllNews();
        setRequests(request);
        gotoPage(request, response);
    }

    private void getAllNews() throws SQLException {
        allNewsCategory = newsCategoryController.showAll();
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath() + "/AllNewsCategory.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("successNotices",null);
        request.getSession().setAttribute("errorNotices", null);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("AllNewsCategory", allNewsCategory);
    }

    private void startAdminControl(HttpServletRequest request, HttpServletResponse response)throws SQLException {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }
}
