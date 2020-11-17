package com.fertifa.app.adminSide.employee;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.constants.Constances;
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

@WebServlet("/admin/customer/employee")
public class EmployeesForAdmin extends com.fertifa.app.baseUrl.BaseUrl {
    private List<Users> usersList = new ArrayList<>();
    private List<Users> companyList = new ArrayList<>();
    private UsersController usersController = new UsersController();
    private AdminController adminController = new AdminController();

    private ArrayList<String> colletingList = new ArrayList<>();
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            employeesForAdmin(request, response);
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

    private void employeesForAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        startAdminControle(request,response);
        getAllComapnies();
        getAllUsers();
        setRequests(request);
        gotoPage(request,response);

    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("UsersList", usersList);
        request.setAttribute("CompanyList", companyList);
        request.setAttribute("ACTIVE", Constances.ACTIVENOW);
        request.setAttribute("PENDING", Constances.PENDING);
        request.setAttribute("INACTIVE", Constances.INACTIVE);
        request.setAttribute("FORDLETE", Constances.FORDELETE);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/EmployeesData.jsp";
        request.getRequestDispatcher(url).forward(request,response);
        request.getSession().setAttribute("successNotices",null);
        request.getSession().setAttribute("errorNotices",null);
    }

    private void getAllUsers() throws SQLException {
        usersList = usersController.getAllUsers("Employees");
    }

    private void getAllComapnies() throws SQLException {
        companyList = usersController.getAllCompaniesByRole("company");

    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
