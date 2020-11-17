package com.fertifa.app.adminSide.news.category;


import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.NewsCategoryController;
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

@WebServlet("/admin/news/category/delete")
public class DeleteCategory extends com.fertifa.app.baseUrl.BaseUrl {
    private int NewsCategoryId =0;
    private AdminController adminController = new AdminController();
    private NewsCategoryController newsCategoryController = new NewsCategoryController();
    private Admins admins = new Admins();
    private String NewsCategory = "";

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(checkCookie(request,response)){
            super.service(request, response);
            deleteNewsCategory(request,response);
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


    private void deleteNewsCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        startAdminControle(request,response);
        getParameters(request);

        startDeletProgress(NewsCategoryId,request,response);
    }

    private void getParameters(HttpServletRequest request) throws SQLException {
        NewsCategoryId = Integer.parseInt(request.getParameter("id"));
        NewsCategory = newsCategoryController.getCategoryNameById(NewsCategoryId);
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response)throws SQLException {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

    private void startDeletProgress(int NewsCategoryId, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if(newsCategoryController.DeleteCategoryNews(NewsCategoryId) > 0){

            deleteUnderNews(NewsCategory);
            setRequests(request);
            String message = "News Deleted Successfully";
            request.getSession().setAttribute("successNotices",message);
            gotoPage(request,response);
        }else{

            setRequests(request);
            String message = "Something went wrong";
            request.getSession().setAttribute("errorNotices",message);
            gotoPage(request,response);
        }
    }

    private void deleteUnderNews(String newsCategory) throws SQLException {
        List<NewsModel> newsCategoriesOne = new ArrayList<>();
        List<NewsModel> newsCategoriesTwo = new ArrayList<>();
        NewsController newsController = new NewsController();
        newsCategoriesOne = newsController.getAllNewsWithCategorysOne(newsCategory);
        newsCategoriesTwo = newsController.getAllNewsWithCategorysTwo(newsCategory);
        if(newsCategoriesOne.size() > 0) {
            for (NewsModel newsModel : newsCategoriesOne) {
                newsController.updateCategoriesOne(newsCategory, newsModel.getId());
            }
        } if (newsCategoriesTwo.size() > 0){
            for (NewsModel newsModel : newsCategoriesTwo) {
                newsController.updateCategoriesTwo(newsCategory, newsModel.getId());
            }
        }
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/news/category";
        response.sendRedirect(url);

    }
}
