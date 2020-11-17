package com.fertifa.app.adminSide.news.category;


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

@WebServlet("/admin/news/category/edit/update")
public class UpdateNewsCategory extends com.fertifa.app.baseUrl.BaseUrl {
    private String Title = "";
    private String description = "";
    private int Id = 0;
    private Admins admins = new Admins();
    private AdminController adminController = new AdminController();
    private NewsCategoryController newsCategoryController = new NewsCategoryController();
    private List<NewsModel> allNews = new ArrayList<>();
    private NewsController newsController = new NewsController();
    private List<NewsModel> newsModel = new ArrayList<>();
    private String oldCategory ="";

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            updateNewNews(request, response);
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

    private void updateNewNews(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request, response);
        getParameters(request, response);
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) throws SQLException{
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

    private void getParameters(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        if (request.getParameter("id") != null) {
            Id = Integer.parseInt(request.getParameter("id"));
        }
        if (request.getParameter("updatetitle") != null) {
            Title = request.getParameter("updatetitle");
        }
        if (request.getParameter("description") != null) {
            description = request.getParameter("description");
        }
        if(request.getParameter("OldCategory") != null){
            oldCategory = request.getParameter("OldCategory");
        }

        AddToDetails(request, response);
    }

    private void checkTheNewForFirstCategoryChanges() throws SQLException {
        allNews =new ArrayList<>();
        allNews = newsController.getAllNewsWithCategoryOne(oldCategory);
        assert allNews != null;
        for (int i = 0; i < allNews.size(); i++) {
            newsController.updateCategoryOneByNewsId(Title,allNews.get(i).getId());
        }
    }

    private void checkTheNewForSecondCategoryChanges() throws SQLException {
        allNews =new ArrayList<>();
        allNews = newsController.getAllNewsWithCategoryTwo(oldCategory);
        assert allNews != null;
        for (int i = 0; i < allNews.size(); i++) {
            newsController.updateCategoryTwoByNewsId(Title,allNews.get(i).getId());
        }
    }

    private void AddToDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        NewsCategory newsObject = new NewsCategory.NewsCategoryBuilder()
            .id(Id)
            .newsCategory(Title)
            .description(description)
            .build();
        if (newsCategoryController.update(newsObject) > 0) {
            checkTheNewForCategoryChanges();
            setRequestToPage(request);
            String message = "News Category Updated Successfully";
            request.getSession().setAttribute("successNotices", message);
            gotopage(request, response);

        } else {
            setRequestToPage(request);
            String message = "Something went wrong.";
            request.getSession().setAttribute("errorNotices", message);
            gotopage(request, response);
        }
    }

    private void checkTheNewForCategoryChanges() throws SQLException {
        checkTheNewForFirstCategoryChanges();
        checkTheNewForSecondCategoryChanges();
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
    }

    private void gotopage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        String url = "/admin/news/category";
        response.sendRedirect(url);
    }
}
