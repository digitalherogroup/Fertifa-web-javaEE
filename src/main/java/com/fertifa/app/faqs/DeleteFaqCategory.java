package com.fertifa.app.faqs;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.FaqCatController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.FaqCat;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/category/delete")
public class DeleteFaqCategory extends com.fertifa.app.baseUrl.BaseUrl {

    private int CategoryId = 0;
    private AdminController adminController = new AdminController();
    private FaqCatController faqCatController = new FaqCatController();
    private List<FaqCat> faqCatList = new ArrayList<>();
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            deleteFaqCategory(request, response);
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

    private void deleteFaqCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getParameters(request);
        startDeletProgress(CategoryId, request, response);

    }

    private void getParameters(HttpServletRequest request) {
        CategoryId = Integer.parseInt(request.getParameter("id"));
    }

    private void startDeletProgress(int categoryId, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (faqCatController.deletefaqCatById(categoryId) > 0) {
            getAllFaqCategories();
            setRequests(request);
            String message = "You successfully deleted FAQ Category ";
            request.getSession().setAttribute("successNotices", message);
            gotopage(request, response);
        } else {
            getAllFaqCategories();
            setRequests(request);
            String message = "Something went wrong, please try again. ";
            request.getSession().setAttribute("errorNotices", message);
            gotopage(request, response);
        }

    }

    private void getAllFaqCategories() throws SQLException {
        faqCatList = faqCatController.findFaqCatAll();
    }


    private void gotopage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/category";
        response.sendRedirect(url);

    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("FaqCatList", faqCatList);

    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
