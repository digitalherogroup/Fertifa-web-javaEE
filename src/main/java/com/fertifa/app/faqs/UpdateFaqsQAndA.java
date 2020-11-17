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

@WebServlet("/admin/faqs/update")
public class UpdateFaqsQAndA extends com.fertifa.app.baseUrl.BaseUrl {

    private AdminController adminController = new AdminController();
    private FaqController faqController = new FaqController();
    private FaqCatController faqCatController = new FaqCatController();
       private List<Faq> faqsListById = new ArrayList<>();
    private String Question = null;
    private String Answer = null;
    private int FaqId = 0;
    private String faqCategoryName = null;
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            updateFaqsQAndA(request, response);
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

    private void updateFaqsQAndA(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getParameters(request);
        StartUpdateProces(FaqId, request, response);
    }


    private void StartUpdateProces(int faqId, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if(faqController.UpdateFaqAnsAndQues(CreateObject(),faqId) > 0 ) {
            GetFaqQuestionsById(FaqId);
            setRequests(request);
            String message = "Successfully Updated the FAQ ";
            request.getSession().setAttribute("successNotices",message);
            gotopage(request,response);
        }else{
            GetFaqQuestionsById(FaqId);
            setRequests(request);
            String message = "Something wrong, please try again";
            request.getSession().setAttribute("errorNotices",message);
            gotopage(request,response);
        }
    }

    private void gotopage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String url = "/admin/faqs";
        request.getRequestDispatcher(url).forward(request,response);
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


    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("FaqsById", faqsListById);
        request.setAttribute("FaqCategory", faqCategoryName);
    }

    private Faq CreateObject() {
        return new Faq(Question, Answer);
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("question") != null) {
            Question = request.getParameter("question");
        } else {
            Question = "";
        }

        if (request.getParameter("answer") != null) {
            Answer = request.getParameter("answer");
        } else {
            Answer = "";
        }

        if (request.getParameter("id") != null) {
            FaqId = Integer.parseInt(request.getParameter("id"));
        } else {
            FaqId = 0;
        }
    }


    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
