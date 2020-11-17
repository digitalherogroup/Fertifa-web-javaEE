package com.fertifa.app.adminSide.employer;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.PackageController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Packages;
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

@WebServlet("/admin/customer/employer/edit")
public class EditEmployer extends com.fertifa.app.baseUrl.BaseUrl {
    private AdminController adminController = new AdminController();
    private UsersController usersController = new UsersController();
    private List<Users> CompanyList = new ArrayList<>();
    private int CompanyId = 0;
    private PackageController packageController = new PackageController();
    List<Packages> packageList = new ArrayList<>();
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            editEmployee(request, response);
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

    private void editEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getParameters(request);
        getEmployeeById(CompanyId);
        getAllPackages();
        setRequests(request);
        gotoPage(request, response);
    }

    private void getAllPackages() throws SQLException {
        packageList = packageController.getAllpackages();
    }

    private void getEmployeeById(int companyId) throws SQLException {
        CompanyList = usersController.getAllUsersListById(companyId);
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            CompanyId = Integer.parseInt(request.getParameter("id"));
        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url= request.getServletPath()+"/EditEmployer.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("CompanyList", CompanyList);
        request.setAttribute("CompanyId", CompanyId);
        request.setAttribute("packageList", packageList);
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }
}
