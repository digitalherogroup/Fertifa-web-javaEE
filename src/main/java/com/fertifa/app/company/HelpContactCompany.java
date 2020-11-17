package com.fertifa.app.company;

import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.FaqCatController;
import com.fertifa.app.controllers.FaqController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Faq;
import com.fertifa.app.models.FaqCat;
import com.fertifa.app.models.Users;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employer/help")
public class HelpContactCompany extends com.fertifa.app.baseUrl.BaseUrl {
    private UsersController usersController = new UsersController();
    FaqCatController catController = new FaqCatController();
    List<FaqCat> faqCatList = new ArrayList<>();
    FaqController faqController = new FaqController();
    List<Faq> faqList = new ArrayList<>();
    private int IDCat = 0;
    private Users users = new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployerId(request, response));
            helpContactCompany(request, response);
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

    private void helpContactCompany(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getparameters(request);
        getAllCategories();
        getAllFaqs();
        setRequests(request, response);
        gotopage(request, response);
    }

    private void getparameters(HttpServletRequest request) {
        IDCat = 0;
        if (request.getParameter("Id") != null) {
            IDCat = Integer.parseInt(request.getParameter("Id"));
        }
    }


    private void setRequests(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("FaqList", faqList);
        request.setAttribute("FaqCatList", faqCatList);
        request.setAttribute("EmployerObject", users);

    }

    private void getAllFaqs() throws SQLException {
        if (IDCat > 0) {
            faqList = faqController.getFaqBYFaqId(IDCat);
        } else {
            faqList = new ArrayList<>();
        }
    }

    private void getAllCategories() throws SQLException {
        if (catController == null) {
            faqCatList = new ArrayList<>();
        } else {
            faqCatList = catController.findFaqCatAllForCompany();
            if (faqCatList == null)
                faqCatList = new ArrayList<>();
        }
    }

    private void gotopage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("Contact", "active");
        String url = request.getServletPath() + "/HelpContact.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("session_success_message", null);
        request.getSession().setAttribute("session_error_message", null);
    }


}