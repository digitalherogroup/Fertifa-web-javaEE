package com.fertifa.app.faqs;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.FaqCatController;
import com.fertifa.app.controllers.FaqController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Faq;
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

@WebServlet("/admin/faqs/create")
public class AddNewFaq extends com.fertifa.app.baseUrl.BaseUrl {
    private FaqCatController faqCatController = new FaqCatController();
    private FaqController faqController = new FaqController();
    private List<FaqCat> faqCatList = new ArrayList<>();
    private AdminController adminController = new AdminController();
    private int CompanyId = 0;
    int intCategoryFor = 0;
    private Admins admins = new Admins();
    private Faq faq = new Faq();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            addNewFaq(request, response);
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

    private void addNewFaq(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request, response);
        getParameters(request);
        getCategoryFor(request);
        AddingNewFaq(request, response);
    }

    private void getCategoryFor(HttpServletRequest request) throws SQLException {
        List<FaqCat> faqList = faqCatController.findFaqCatById(CompanyId);
        //TODO: change the query to get all faqs as object
        for (int i = 0; i < faqList.size(); i++) {
            intCategoryFor = faqList.get(i).getCategoryFor();
            System.out.println(faqList.get(i).getFaqCatName());
            System.out.println(faqList.get(i).getId());
        }
    }

    private void AddingNewFaq(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (faqController.AddNewFaq(faq) > 0) {
            GetAllCompanies();
            setRequests(request);
            String message = "FAQ added successfully";
            request.getSession().setAttribute("successNotices", message);
            gotoPage(request, response);
        } else {
            GetAllCompanies();
            setRequests(request);
            String message = "Something wrong, please try again";
            request.getSession().setAttribute("errorNotices", message);
            gotoPage(request, response);
        }

    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/faqs/add";
        response.sendRedirect(url);
        //request.getRequestDispatcher(url).forward(request, response);
    }

    private void GetAllCompanies() throws SQLException {
        faqCatList = faqCatController.findFaqCatAll();
        if (faqCatList.size() == 0) {
            faqCatList = new ArrayList<>();
        }
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("FaqCatList", faqCatList);
    }

    private void getParameters(HttpServletRequest request) {

        if (request.getParameter("company") != null) {
            faq.setFaqCatId(Integer.parseInt(request.getParameter("company")));

        }

        if (request.getParameter("question") != null) {
            faq.setFaqQuestion(request.getParameter("question"));
        }

        if (request.getParameter("answer") != null) {
            faq.setFaqAnswear(request.getParameter("answer"));
        }

        if (request.getParameter("category_for") != null) {
            faq.setCategoryFor(Integer.parseInt(request.getParameter("CategoryFor")));
        }
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }


}
