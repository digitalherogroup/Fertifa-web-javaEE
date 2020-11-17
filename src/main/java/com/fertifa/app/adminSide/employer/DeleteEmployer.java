package com.fertifa.app.adminSide.employer;

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

@WebServlet("/admin/customer/employer/delete")
public class DeleteEmployer extends com.fertifa.app.baseUrl.BaseUrl {

    private List<Users> usersList = new ArrayList<>();
    private UsersController usersController = new UsersController();
    private AdminController adminController = new AdminController();
    private int DeleteByID = 0;
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            deleteEmployee(request, response);
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

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getParameters(request);
        StartDeletingEmployee(DeleteByID, request, response);
    }

    private void StartDeletingEmployee(int deleteid, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (usersController.DeleteUser(deleteid) > 0) {
            List<Users> usersListToDelete = usersController.getAllUsers();
           /* for (int i = 0; i <usersListToDelete.size() ; i++) {
                usersController.DeletUserByCompanyId(DeleteByID);
            }*/
            getAllComapnies();
            setRequests(request);
            String message = "Employer Deleted successfully";
            request.getSession().setAttribute("successNotices", message);
            gotopage(request, response);
        } else {
            getAllComapnies();
            setRequests(request);
            String message = "Something went wrong";
            request.getSession().setAttribute("errorNotices", message);
            gotopage(request, response);
        }
    }


    private void gotopage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/customer/employer";
        response.sendRedirect(url);
    }


    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("UsersList", usersList);
    }

    private void getAllComapnies() throws SQLException {
        usersList = usersController.getAllCompaniesByRole("company");
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            DeleteByID = Integer.parseInt(request.getParameter("id"));
        }
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
