package com.fertifa.app.faqs;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.FaqCatController;
import com.fertifa.app.controllers.FaqController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Faq;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/faqs/edit")
public class EditFaqQAndA extends com.fertifa.app.baseUrl.BaseUrl {

    private int FaqId = 0;
    private AdminController adminController = new AdminController();
    private FaqController faqController = new FaqController();
    private FaqCatController faqCatController = new FaqCatController();
    private List<Faq> faqsListById = new ArrayList<>();
    private String faqCategoryName = null;
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            editFaqQAndA(request, response);
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

    private void editFaqQAndA(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getParameters(request);
        GetFaqQuestionsById(FaqId);
        setRequests(request);
        gotoEditFaqQuestionPage(request, response);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("FaqsById", faqsListById);
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("FaqCategory", faqCategoryName);
    }

    private void gotoEditFaqQuestionPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/Editfaq.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("successNotices",null);
        request.getSession().setAttribute("errorNotices",null);
    }

    private void GetFaqQuestionsById(int faqId) throws SQLException {
        faqsListById = faqController.getFaqById(faqId);
        getFaqCategoryName(faqsListById.get(0).getFaqCatId());
    }

    private void getFaqCategoryName(int faqCatId) throws SQLException {
        faqCategoryName = faqCatController.FindCategoryNameById(faqCatId);
    }


    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("Q") != null) {
            FaqId = Integer.parseInt(request.getParameter("Q"));
        }
    }


    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
