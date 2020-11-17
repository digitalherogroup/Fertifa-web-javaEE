package com.fertifa.app.adminSide.employee;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Users;
import com.fertifa.app.utils.SendingEmailNewCompany;
import com.fertifa.app.utils.TokenMaker;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/customer/employee/resend")
public class ResendInvitationEmployees extends com.fertifa.app.baseUrl.BaseUrl {
    private List<Users> usersList = new ArrayList<>();
    private List<Users> companyList = new ArrayList<>();
    private UsersController usersController = new UsersController();
    private Users users = new Users();
    private AdminController adminController = new AdminController();
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            resendInvitationEmployees(request, response);
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

    private void resendInvitationEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getParameters(request);
        AddingNewEmployees(request,users);
        getAllComapnies();
        getAllUsers();
        setRequests(request);
        gotoPage(request, response);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/customer/employee";
        response.sendRedirect(url);

    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("UsersList", usersList);
        request.setAttribute("CompanyList", companyList);
    }

    private void getAllUsers() throws SQLException {
        usersList = usersController.getAllUsers("Employees");
    }

    private void getAllComapnies() throws SQLException {
        companyList = usersController.getAllCompaniesByRole("employer");

    }

    private void AddingNewEmployees(HttpServletRequest request,Users users) throws SQLException {
        SendingEmailNewCompany.send((TokenMaker.buildToken(12)), users.getFirstName() + " " + users.getLastName(), users.getEmail(),  request);
        String message = "Invitation send successfully";
        request.getSession().setAttribute("successNotices", message);
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            users = usersController.findById(Constances.USER_ID_INDATA,String.valueOf(request.getParameter("id")));
        }
    }
    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
