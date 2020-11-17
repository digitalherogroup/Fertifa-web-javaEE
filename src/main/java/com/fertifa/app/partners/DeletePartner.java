package com.fertifa.app.partners;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.PartnerController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Partners;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/partners/delete")
public class DeletePartner extends com.fertifa.app.baseUrl.BaseUrl {

    private int PartnerId =0;
    private AdminController adminController = new AdminController();
    private PartnerController partnerController = new PartnerController();
    private List<Partners> partnersList = new ArrayList<>();
    private Admins admins = new Admins();


    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            deletePartner(request, response);
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

    private void deletePartner(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getParameters(request);
        startDeletProgress(PartnerId,request,response);
    }

    private void startDeletProgress(int PartnerId, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if(partnerController.DeletePartner(PartnerId) > 0){
            getAllPartners();
            setRequests(request);
            String message = "Partner Deleted Successfully";
            request.getSession().setAttribute("successNotices",message);
            gotopage(request,response);
        }else{
            getAllPartners();
            setRequests(request);
            String message = "Something went wrong";
            request.getSession().setAttribute("errorNotices",message);
            gotopage(request,response);
        }
    }

    private void gotopage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/partners";
        response.sendRedirect(url);

    }


    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("PartnersFullList", partnersList);
    }

    private void getAllPartners() throws SQLException {
        partnersList = partnerController.showAll();
    }

    private void getParameters(HttpServletRequest request)
    {
        PartnerId = Integer.parseInt(request.getParameter("id"));
    }


    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
