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

@WebServlet("/admin/category/edit/update")
public class UpdateCategory extends com.fertifa.app.baseUrl.BaseUrl {

    private AdminController adminController = new AdminController();
    private FaqCatController faqCatController = new FaqCatController();
    private List<FaqCat> faqCatList = new ArrayList<>();
    private String CategoryName = null;
    private int CategoryId = 0;
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            updateCategory(request, response);
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

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        startAdminControle(request, response);
        getParameters(request);
        StartUpdateProcecs(CategoryName, CategoryId, request, response);

    }

    private void StartUpdateProcecs(String categoryName, int categoryId, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (faqCatController.updateFaqCategory(CreateFaqCategoryObject(categoryName), categoryId) > 0) {
            GetAllFaqCategories();
            setRequests(request);
            String message = "Successfully Updated the FAQ Category";
            request.getSession().setAttribute("successNotices", message);
            gotopage(request, response);
        } else {
            GetAllFaqCategories();
            setRequests(request);
            String message = "Something wrong, please try again";
            request.getSession().setAttribute("errorNotices", message);
            gotopage(request, response);
        }
    }

    private void GetAllFaqCategories() throws SQLException {
        faqCatList = faqCatController.findFaqCatAll();
    }

    private FaqCat CreateFaqCategoryObject(String categoryName) {
        return new FaqCat(categoryName);
    }


    private void getParameters(HttpServletRequest request) {
        CategoryName = request.getParameter("category");
        CategoryId = Integer.parseInt(request.getParameter("id"));
    }

    private void gotopage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/category";
        request.getRequestDispatcher(url).forward(request, response);
        //response.sendRedirect(url);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
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
