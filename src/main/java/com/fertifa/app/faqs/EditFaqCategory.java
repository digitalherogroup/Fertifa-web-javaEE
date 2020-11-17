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

@WebServlet("/admin/category/edit")
public class EditFaqCategory extends com.fertifa.app.baseUrl.BaseUrl {

     private AdminController adminController = new AdminController();
        private FaqCatController faqCatController = new FaqCatController();
    private List<Admins> adminFullList = new ArrayList<>();
    private List<FaqCat> faqCatList = new ArrayList<>();
    private int CategoryId = 0;
    private String CateGoryName = null;
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            editFaqCategory(request, response);
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

    private void editFaqCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        startAdminControle(request,response);
        getParameters(request);
        GetfaqCategoryById(CategoryId);
        setRequests(request);
        gotoPage(request, response);
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            CategoryId = Integer.parseInt(request.getParameter("id"));
        }
    }

    private void GetfaqCategoryById(int categoryId) throws SQLException {
        faqCatList = getCategoryById(categoryId);
        CateGoryName = getCategoryName(faqCatList);
    }

    private List<FaqCat> getCategoryById(int categoryId) throws SQLException {
        return faqCatController.findFaqCatById(categoryId);
    }

    private String getCategoryName(List<FaqCat> faqCatById) {
        return faqCatById.get(0).getFaqCatName();
    }


    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/EditCategory.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("successNotices",null);
        request.getSession().setAttribute("errorNotices",null);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("CateGoryName", CateGoryName);
        request.setAttribute("CategoryId", CategoryId);

    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}


