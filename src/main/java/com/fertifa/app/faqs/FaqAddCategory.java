package com.fertifa.app.faqs;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.models.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/category/add")
public class FaqAddCategory extends com.fertifa.app.baseUrl.BaseUrl {

    private AdminController adminController = new AdminController();
    private Admins admins = new Admins();

    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            faqAddCategory(request, response);
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

    private void faqAddCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        startAdminControle(request,response);
        setRequests(request);
        gotoPage(request,response);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url =request.getServletPath() + "/CreateFaqCategory.jsp";
        request.getRequestDispatcher(url).forward(request,response);
        request.getSession().setAttribute("successNotices",null);
        request.getSession().setAttribute("errorNotices",null);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}