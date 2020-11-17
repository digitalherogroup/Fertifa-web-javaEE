package com.fertifa.app.faqs;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.FaqCatController;
import com.fertifa.app.controllers.FaqCatFaqsController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.FaqCat;
import com.fertifa.app.models.FaqsCatFaqs;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/faqs")
public class Faqs extends com.fertifa.app.baseUrl.BaseUrl {
    private AdminController adminController = new AdminController();
    private FaqCatFaqsController faqCatFaqsController = new FaqCatFaqsController();
    private FaqCatController faqCatController = new FaqCatController();
    private List<Admins> adminFullList = new ArrayList<>();
    private List<FaqsCatFaqs> faqsCategoriesList = new ArrayList<>();
    private List<FaqCat> faqCategoryList = new ArrayList<>();
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            faqs(request, response);
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

    private void faqs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        startAdminControle(request,response);
        getAllFaqsWithFaqcCat();
        getAllCategories();
        setRequests(request);
        gotoPage(request, response);
    }

   private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("FaqsCategoriesList", faqCategoryList);
        request.setAttribute("FaqsList", faqsCategoriesList);
    }

    private void getAllCategories() throws SQLException {
        faqCategoryList = faqCatController.findFaqCatAll();
    }

    private void getAllFaqsWithFaqcCat() throws SQLException {
        faqsCategoriesList = faqCatFaqsController.getAllFasqAndCategories();
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/faqs.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }


}

