package com.fertifa.app.adminSide.employer;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.InvitationController;
import com.fertifa.app.controllers.SessionController;
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

@WebServlet("/admin/customer/employer/resend")
public class ResendInvitationEmployer extends com.fertifa.app.baseUrl.BaseUrl {
    private List<Users> usersList = new ArrayList<>();
    private List<Users> companyList = new ArrayList<>();
    private List<Users> usersShortList = new ArrayList<>();
    private SessionController sessionController = new SessionController();
    private UsersController usersController = new UsersController();
    private List<Admins> adminsList = new ArrayList<>();
    private String SessionEmail = null;
    private int CompanyId = 0;
    private AdminController adminController = new AdminController();
    private List<Admins> adminFullList = new ArrayList<>();
    private String AdminEmail;
    private int AdminId;
    private int AdminRole;
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            resendInvitationEmployee(request, response);
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

    private void resendInvitationEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {

        startAdminControle(request,response);
        getParameters(request);
        AddingNewEmployee(request);
        getAllComapnies();
        setRequests(request);
        gotoPage(request, response);
    }

    private void getAllComapnies() throws SQLException {
        usersList = usersController.getAllCompaniesByRole("company");
    }

    private void AddingNewEmployee(HttpServletRequest request) throws SQLException {
        Users company = usersController.findById(Constances.USER_ID_INDATA,String.valueOf(CompanyId));
       /* usersShortList = usersController.getAllUsersListById(CompanyId);
        String CompanyName = getCompanyNameById(usersShortList);
        String companyEmail = getCompanyEmaiById(usersShortList);*/
        InvitationController invitationController = new InvitationController();
        String token = TokenMaker.buildToken(12);
        if (invitationController.UpdateTokenByUserId(token, CompanyId) > 0) {
            //Update com.fertifa.app.Company by id

            SendingEmailNewCompany.send(token, company.getComapny(), company.getEmail(),  request);
            request.getSession().setAttribute("successNotices", "email sent successfully");
        } else {
            request.getSession().setAttribute("errorNotices", "Something  went wrong");
        }

    }


    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            CompanyId = Integer.parseInt(request.getParameter("id"));
        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/customer/employer";
        response.sendRedirect(url);

    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("UsersList", usersList);
    }
    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
