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

@WebServlet("/admin/category/add/create")
public class NewCategory extends com.fertifa.app.baseUrl.BaseUrl {

    private AdminController adminController = new AdminController();
    private String Category = "";
    private String CategoryFor = "";
    private FaqCatController faqCatController = new FaqCatController();
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            newCategory(request, response);
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

    private void newCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getParameters(request);
        AddingNewCategory(request, response);
    }


    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("category") != null) {
            Category = request.getParameter("category");
        }
        if(request.getParameter("category_for")!=null){
            CategoryFor = request.getParameter("category_for");
        }
    }

    private void AddingNewCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (!faqCatController.CheckIfCategoryExists(Category.toLowerCase().trim())) {
            if (faqCatController.addFaqCat(CreateObjectFaqCategory()) > 0) {
                setRequests(request);
                String message = "Category created successfully";
                request.getSession().setAttribute("successNotices",message);
                gotoPage(request, response);
            } else {
                setRequests(request);
                String message = "Something went wrong";
                request.getSession().setAttribute("errorNotices",message);
                gotoPage(request, response);
            }
        } else {
            setRequests(request);
            String message = "Category already exists.";
            request.getSession().setAttribute("errorNotices",message);
            gotoPage(request, response);
        }
    }


    private FaqCat CreateObjectFaqCategory() {
        return new FaqCat(Category,Integer.parseInt(CategoryFor));
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String url = "/admin/category/add";
        response.sendRedirect(url);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
    }


    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }
}
