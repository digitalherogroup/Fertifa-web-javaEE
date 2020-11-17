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

@WebServlet("/admin/nurse/Delete")
public class DeleteNurse extends com.fertifa.app.baseUrl.BaseUrl {
    private int NersesId = 0;
    private AdminController adminController = new AdminController();
    private List<Admins> nurseList = new ArrayList<>();
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            deleteNurse(request, response);
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

    private void deleteNurse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request, response);
        getParameters(request);
        startDeletProgress(NersesId, request, response);
    }

    private void startDeletProgress(int NersesId, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (adminController.DeleteById(NersesId) > 0) {
            getAllNurses();
            setRequests(request);
            String message = "Nurse Deleted Successfully";
            request.getSession().setAttribute("successNotices", message);
            gotopage(request, response);
        } else {
            getAllNurses();
            setRequests(request);
            String message = "Something went wrong";
            request.getSession().setAttribute("errorNotices", message);
            gotopage(request, response);
        }
    }

    private void gotopage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/nurse";
        response.sendRedirect(url);
    }


    private void setRequests(HttpServletRequest request) {
        request.setAttribute("NurseList", nurseList);
        request.setAttribute("AdminsObject", admins);
    }

    private void getAllNurses() throws SQLException {
        nurseList = adminController.GetAllNurseList();
    }

    private void getParameters(HttpServletRequest request) {
        NersesId = Integer.parseInt(request.getParameter("id"));
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
