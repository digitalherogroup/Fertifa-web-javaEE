package com.fertifa.app.adminSide.nurse;

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

@WebServlet("/admin/nurse/edit")
public class EditNurses extends com.fertifa.app.baseUrl.BaseUrl {
    private AdminController adminController = new AdminController();
    private List<Admins> nursList = new ArrayList<>();
    private int newsId = 0;
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            editNurses(request, response);
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

    private void editNurses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        startAdminControle(request,response);
        getParameters(request);
        getNuresById(newsId);
        setRequests(request);
        gotoPage(request, response);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url  = request.getServletPath()+"/EditNurse.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("NurseList", nursList);
    }

    private void getNuresById(int id) throws SQLException {
        nursList = adminController.getAllAdminListById(id);
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            newsId = Integer.parseInt(request.getParameter("id"));
        }
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
