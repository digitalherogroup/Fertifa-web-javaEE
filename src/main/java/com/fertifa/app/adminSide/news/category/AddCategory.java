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

@WebServlet("/admin/news/category/add/addCategory")
public class AddCategory extends com.fertifa.app.baseUrl.BaseUrl {
    private String categoryName;
    private Admins admins = new Admins();
    private AdminController adminController = new AdminController();
    private NewsCategoryController newsCategoryController = new NewsCategoryController();
    private String description = "no description available";

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            addNewCategory(request, response);
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


    private void addNewCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        GetAdminFullDetails(request, response);
        getParameters(request, response);
        addToData(request, response, categoryName);
    }

    private void addToData(HttpServletRequest request, HttpServletResponse response, String categoryName) throws SQLException, IOException {
        if (newsCategoryController.getByCategoryName(categoryName).size() == 0) {
            NewsCategory newCategoryForNews = new NewsCategory.NewsCategoryBuilder()
                .newsCategory(categoryName)
                .description(description)
                .build();
            if (newsCategoryController.save(newCategoryForNews) > 0) {
                String message = "News category Added Successfully";
                setRequestToPage(request);
                goToPage(request, response, message, "successNotices");
            } else {
                String message = "Something went wrong";
                setRequestToPage(request);
                goToPage(request, response, message, "errorNotices");
            }
        } else {
            String message = "The category already exists";
            setRequestToPage(request);
            goToPage(request, response, message, "errorNotices");
        }
    }

    private void goToPage(HttpServletRequest request, HttpServletResponse response, String message, String notificationType) throws IOException {
        request.getSession().setAttribute(notificationType, message);
        String url = "/admin/news/category/add";
        response.sendRedirect(url);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
    }

    private void GetAdminFullDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

    private void getParameters(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("CategoryName") != null) {
            categoryName = request.getParameter("CategoryName");
        }
        if (request.getParameter("description") != null || request.getParameter("description").equals("")) {
            description = request.getParameter("description");
        } else{
            description = "no description available";
        }
        System.out.println();
    }

}
