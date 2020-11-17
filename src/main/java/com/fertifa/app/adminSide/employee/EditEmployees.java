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

@WebServlet("/admin/customer/employee/edit")
public class EditEmployees extends com.fertifa.app.baseUrl.BaseUrl {

    private AdminController adminController = new AdminController();
    private UsersController usersController = new UsersController();
    private List<Admins> adminFullList = new ArrayList<>();
    private List<Users> usersList = new ArrayList<>();
    private List<Users> companyList = new ArrayList<>();
    private int UserId = 0;
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            editEmployees(request, response);
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

    private void editEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getParameters(request);
        getEmployeeById(UserId);
        getAllCompanies();
        setRequests(request);
        gotoPage(request, response);
    }

    private void getAllCompanies() throws SQLException {
        companyList = usersController.getAllCompaniesByRole("company");
    }

    private void getEmployeeById(int companyId) throws SQLException {
        usersList = usersController.getAllUsersListById(companyId);
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            UserId = Integer.parseInt(request.getParameter("id"));
        }
    }


    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/EditEmployees.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("successNotices",null);
        request.getSession().setAttribute("errorNotices",null);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("UsersList", usersList);
        request.setAttribute("UserId", UserId);
        request.setAttribute("CompanyList", companyList);
    }


    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}

