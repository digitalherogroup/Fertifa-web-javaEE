package com.fertifa.app.adminSide.employee;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Admins;
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

@WebServlet("/admin/employee/invite")
public class InviteUsersEmployee extends com.fertifa.app.baseUrl.BaseUrl {
    private UsersController usersController = new UsersController();
    private List<Users> companyList = new ArrayList<>();
    private AdminController adminController = new AdminController();
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            inviteUsersEmployee(request, response);
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

    private void inviteUsersEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        getAdminFullDetails(request,response);
        getAllCompanies();
        setRequests(request);
        gotoPage(request, response);
    }

    private void getAllCompanies() throws SQLException {
        companyList = usersController.getAllCompaniesByRole("company");
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/InvitationsUser.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("CompanyList", companyList);
    }


    private void getAdminFullDetails(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }


}
