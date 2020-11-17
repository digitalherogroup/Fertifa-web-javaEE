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

@WebServlet("/admin/customer/employee/delete")
public class
DeleteEmployees extends com.fertifa.app.baseUrl.BaseUrl {
    private List<Users> usersList = new ArrayList<>();
    private List<Users> companyList = new ArrayList<>();
    private UsersController usersController = new UsersController();
    private AdminController adminController = new AdminController();
    private int UserId = 0;
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            deleteEmployees(request, response);
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

    private void deleteEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        startAdminControle(request,response);
        getParameters(request);
        startDeletingUser(UserId,request,response);
    }

    private void startDeletingUser(int userId,HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if(usersController.DeleteUser(userId) > 0){
            getAllComapnies();
            setRequests(request);
            request.getSession().setAttribute("successNotices","Employee Deleted Successfully");
            gotopage(request,response);
        }else{
            getAllComapnies();
            setRequests(request);
            request.getSession().setAttribute("errorNotices","Something went wrong");
            gotopage(request,response);
        }
    }

    private void gotopage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "/admin/customer/employee";
        response.sendRedirect(url);

    }

    private void getParameters(HttpServletRequest request) {
        if(request.getParameter("id")  != null) {
            UserId = Integer.parseInt(request.getParameter("id"));
        }
    }


    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("UsersList", usersList);
        request.setAttribute("CompanyList", companyList);
    }

    private void getAllComapnies() throws SQLException {
        companyList = usersController.getAllCompaniesByRole("company");

    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
