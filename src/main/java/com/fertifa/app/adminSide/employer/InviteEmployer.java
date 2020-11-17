package com.fertifa.app.adminSide.employer;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.PackageController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Packages;
import com.sun.istack.NotNull;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/employer/invite")
public class InviteEmployer extends com.fertifa.app.baseUrl.BaseUrl {
    private AdminController adminController = new AdminController();
    private PackageController packageController = new PackageController();
    private String CompanyEmail = null;
    private String CompanyName = null;
    private Admins admins = new Admins();
    List<Packages> packageList = new ArrayList<>();

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            inviteEmployee(request, response);
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

    private void inviteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        getAdminFullDetails(request, response);
        getParameters(request);
        setRequests(request);
        gotoPage(request, response);
    }

    private void getAdminFullDetails(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }


    private void getParameters(@NotNull HttpServletRequest request) {
        if (request.getParameter("companyEmail") != null) {
            CompanyEmail = request.getParameter("companyEmail");
        }
        if (request.getParameter("companyName") != null) {
            CompanyName = request.getParameter("companyName");
        }

    }

    private void gotoPage(@NotNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath() + "/Invitations.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }

    private void setRequests(@NotNull HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
    }

}
