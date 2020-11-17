package com.fertifa.app.detailsprofile;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.models.Admins;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/nurse/detail")
public class NurseDetail extends com.fertifa.app.baseUrl.BaseUrl {

    private AdminController adminController = new AdminController();
    private List<Admins> adminFullList = new ArrayList<>();
    private List<Admins> AdminNurseList = new ArrayList<>();
    private int Id =0;
    private Admins admins = new Admins();


    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            nurseDetail(request, response);
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

    private void nurseDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getParameters(request);
        getAdminById(Id);
        setRequests(request);
        gotoPage(request,response);

    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/ProfileAdmin.jsp";
        request.getRequestDispatcher(url).forward(request,response);
        request.getSession().setAttribute("successNotices",null);
        request.getSession().setAttribute("errorNotices",null);
    }
    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject",admins);
        request.setAttribute("AdminFullList", AdminNurseList);
    }

    private void getAdminById(int id) throws SQLException {
        AdminNurseList = adminController.getAllAdminListById(id);
    }

    private void getParameters(HttpServletRequest request) {
        Id = Integer.parseInt(request.getParameter("id"));
    }


    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}

