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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/admin/customer/employee/update")
public class UpdateEmployeers extends com.fertifa.app.baseUrl.BaseUrl {
    private String firstName = "";
    private String Status = "";
    private String lastName = "";
    private String email = "";
    private String company = "";
    private String Address = "";
    private String Phone = "";
    private String Age = "";
    private String Gender = "";
    private int userid = 0;

    private AdminController adminController = new AdminController();
    private UsersController usersController = new UsersController();
    private List<Admins> adminFullList = new ArrayList<>();
    private List<Users> companyList = new ArrayList<>();
    private int CompanyId = 0;
    private List<Users> usersList = new ArrayList<>();
    private Admins admins = new Admins();
    private int companyId;

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            updateEmployee(request, response);
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

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request, response);
        getParameters(request);
        StartUpdatingUsers(companyId, request, response);
    }

    private void StartUpdatingUsers(int companyId, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (usersController.UpdateUserDetail(CreateObjectOfUsers(), userid) > 0) {
            getAllUsers();
            getAllCompanies();
            setRequests(request);
            String message = "Employee updated successfully";

            request.getSession().setAttribute("successNotices", message);
            gotopage(request, response, message);
        } else {
            getAllUsers();
            getAllCompanies();
            setRequests(request);
            String message = "Something went wrong";
            request.getSession().setAttribute("errorNotices", message);
            gotopage(request, response,message);
        }
    }

    private void getAllUsers() throws SQLException {
        usersList = usersController.getAllUsers("Employees");
    }

    private void gotopage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        String url = "/admin/customer/employee";
        response.sendRedirect(url);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("UsersList", usersList);
        request.setAttribute("UserId", userid);
        request.setAttribute("CompanyList", companyList);
    }

    private void getAllCompanies() throws SQLException {
        companyList = usersController.getAllCompaniesByRole("company");
    }

    private Users CreateObjectOfUsers() {
        Date date = new Date();
        return new Users(firstName, lastName, Address, Phone, new Timestamp(date.getTime()), Integer.parseInt(Gender), Integer.parseInt(Status), company, Integer.parseInt(Age),companyId);
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            userid = Integer.parseInt(request.getParameter("id"));
        }
        if (request.getParameter("firstname") != null) {
            firstName = request.getParameter("firstname");
        }
        if (request.getParameter("lastname") != null) {
            lastName = request.getParameter("lastname");
        }
        if (request.getParameter("company") != null) {
            company = request.getParameter("company");

        }
        if (request.getParameter("status") != null) {
            Status = request.getParameter("status");
        }
        if (request.getParameter("address") != null) {
            Address = request.getParameter("address");
        }
        if (request.getParameter("phone") != null) {
            Phone = request.getParameter("phone");
        }
        if (request.getParameter("gender") != null) {
            Gender = request.getParameter("gender");
        }
        if (request.getParameter("age") != null) {
            Age = request.getParameter("age");
        }
        getCompanyId(company);
    }

    private void getCompanyId(String company) {
        companyId = usersController.getCompanyIdByName(company);
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }


}
