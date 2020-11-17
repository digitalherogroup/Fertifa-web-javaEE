package com.fertifa.app.faqs;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.FaqCatController;
import com.fertifa.app.controllers.FaqCatFaqsController;
import com.fertifa.app.controllers.FaqController;
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

@WebServlet("/admin/faqs/delete")
public class DeleteFaq extends com.fertifa.app.baseUrl.BaseUrl {

    private AdminController adminController = new AdminController();
    private FaqController faqController = new FaqController();
    private FaqCatController faqCatController = new FaqCatController();
    private List<FaqCat> faqCategoryList = new ArrayList<>();
    private List<FaqsCatFaqs> faqsCategoriesList = new ArrayList<>();

    private FaqCatFaqsController faqCatFaqsController = new FaqCatFaqsController();
    private int FaqId = 0;
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            deleteFaq(request, response);
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

    private void deleteFaq(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request, response);
        getParameters(request);
        startDeletProgress(FaqId, request, response);
    }

    private void startDeletProgress(int faqId, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (faqController.deleteFaq(faqId) > 0) {
            getAllFaqsWithFaqcCat();
            getAllCategories();
            setRequests(request);
            String message = "You successfully deleted FAQ";
            request.getSession().setAttribute("successNotices", message);
            gotopage(request, response);
        } else {
            getAllFaqsWithFaqcCat();
            getAllCategories();
            setRequests(request);
            String message = "Something went wrong, please try again. ";
            request.getSession().setAttribute("errorNotices", message);
            gotopage(request, response);
        }
    }

    private void getAllCategories() throws SQLException {
        faqCategoryList = faqCatController.findFaqCatAll();
    }


    private void gotopage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/faqs";
        response.sendRedirect(url);
    }


    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("FaqsCategoriesList", faqCategoryList);
        request.setAttribute("FaqsList", faqsCategoriesList);
    }

    private void getAllFaqsWithFaqcCat() throws SQLException {
        faqsCategoriesList = faqCatFaqsController.getAllFasqAndCategories();
    }

    private void getParameters(HttpServletRequest request) {
        FaqId = Integer.parseInt(request.getParameter("id"));
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
