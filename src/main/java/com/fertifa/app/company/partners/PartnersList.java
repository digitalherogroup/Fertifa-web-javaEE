package com.fertifa.app.company.partners;

import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.PartnerController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Partners;
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

@WebServlet("/employer/partners")
public class PartnersList extends com.fertifa.app.baseUrl.BaseUrl {
    private UsersController usersController = new UsersController();
    List<Partners> partnersList = new ArrayList<>();
    List<Users> companyLList = new ArrayList<>();

    PartnerController partnerController = new PartnerController();
   private Users users=new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA,getEmployerId(request,response));
            partnersList(request, response);
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

    private void partnersList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getALlPartners();
        setRequests(request, response);
        gotoPaage(request, response);
    }


    private void gotoPaage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/Partners.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }


    private void setRequests(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("Partners", "active");
        request.setAttribute("PartnersList", partnersList);
        request.setAttribute("CompanyLList", companyLList);
        request.setAttribute("EmployerObject", users);
    }

    private void getALlPartners() throws SQLException {
        partnersList = partnerController.showAll();
    }


}
