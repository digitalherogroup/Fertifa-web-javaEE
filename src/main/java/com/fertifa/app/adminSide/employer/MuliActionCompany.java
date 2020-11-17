package com.fertifa.app.adminSide.employer;


import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.InvitationController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Users;
import com.fertifa.app.utils.SendingEmailNewCompany;
import com.fertifa.app.utils.TokenMaker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet( "/admin/customer/employer/action")
public class MuliActionCompany extends com.fertifa.app.baseUrl.BaseUrl
{
    private List<Users> usersList;
    private ArrayList<String> colletingList;
    private UsersController usersController;
    private AdminController adminController;
    private Admins admins;

    public MuliActionCompany() {
        this.usersList = new ArrayList<>();
        this.colletingList = new ArrayList<>();
        this.usersController = new UsersController();
        this.adminController = new AdminController();
        this.admins = new Admins();
    }

    public void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        try {
            if (this.checkCookie(request, response)) {
                super.service(request, response);
                this.muliActionCompany(request, response);
            }
        }
        catch (Exception ex) {
           ex.printStackTrace();
        }
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    private void muliActionCompany(final HttpServletRequest request, final HttpServletResponse response) throws SQLException, ServletException, IOException {
        startAdminControle(request, response);
       getParameters(request);
        getAllComapnies();
        checkDestination(request, response);
    }

    private void getAllComapnies() throws SQLException {
       usersList = usersController.getAllCompaniesByRole("Company");
    }

    private void setRequests(final HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("UsersList", usersList);
    }

    private void checkDestination(final HttpServletRequest request, final HttpServletResponse response) throws SQLException, ServletException, IOException {
        final ArrayList<String> workingList = new ArrayList<>(this.colletingList);
        int key = 0;
        key = this.getTheKeyValue();
        if (this.colletingList.get(key).equals("delete")) {
            workingList.remove(key);
            this.DeleteUsers(workingList, request, response);
        }
        else if (this.colletingList.get(key).equals("invitation")) {
            workingList.remove(key);
            this.sendInvitations(workingList, request, response);
        }
    }

    private int getTheKeyValue() {
        return this.colletingList.size() - 1;
    }

    private void sendInvitations(final ArrayList<String> workingList, final HttpServletRequest request, final HttpServletResponse response) throws SQLException, ServletException, IOException {
        int count = 0;
        for (int i = 0; i < workingList.size(); ++i) {
            if (this.checkUserInvitation(workingList.get(i))) {
                final int UsersId = Integer.parseInt(workingList.get(i));
                final Users users = this.usersController.findById("id", String.valueOf(UsersId));
                final InvitationController invitationController = new InvitationController();
                final String token = TokenMaker.buildToken(12);
                invitationController.UpdateTokenByUserId(token, users.getId());
                SendingEmailNewCompany.send(TokenMaker.buildToken(12), users.getComapny(), users.getEmail(), request);
                ++count;
            }
        }
        if (count > 0) {
            final String message = "Employers Invited successfully";
            request.getSession().setAttribute("successNotices", message);
            this.setRequests(request);
            this.gotoPage(request, response);
        }
        else {
            final String message = "Something went wrong";
            request.getSession().setAttribute("errorNotices", message);
            this.setRequests(request);
            this.gotoPage(request, response);
        }
    }

    private boolean checkUserInvitation(final String s) throws SQLException {
        List<Users> userStatusList = new ArrayList<Users>();
        userStatusList = usersController.getAllUsersListById(Integer.parseInt(s));
        return userStatusList.get(0).getStatus() != 0;
    }

    private void DeleteUsers(final ArrayList<String> colletingList, final HttpServletRequest request, final HttpServletResponse response) throws SQLException, ServletException, IOException {
        int count = 0;
        for (final String str : colletingList) {
            if (this.UserDeleted(str) > 0) {
                ++count;
            }
        }
        if (count > 0) {
            final String message = "User Deleted successfully";
            request.getSession().setAttribute("successNotices", message);
            this.setRequests(request);
            this.gotoPage(request, response);
        }
        else {
            final String message = "Something went wrong";
            request.getSession().setAttribute("errorNotices", message);
            this.setRequests(request);
            this.gotoPage(request, response);
        }
    }

    private void gotoPage(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String url = "/admin/customer/employer";
        response.sendRedirect(url);
    }

    private int UserDeleted(final String strId) throws SQLException {
        return this.usersController.ChangeUserStatusAdminDelete(Integer.parseInt(strId));
    }

    private void getParameters(final HttpServletRequest request) {
        this.colletingList = new ArrayList<>();
        final Enumeration<String> en = request.getParameterNames();
        while (en.hasMoreElements()) {
            final Object objOri = en.nextElement();
            final String param = String.valueOf(objOri);
            final String value = request.getParameter(param);
            this.colletingList.add(value);
        }
        this.checkNewCollection(this.colletingList);
    }

    private void checkNewCollection(final ArrayList<String> colletingList) {
        final String all = colletingList.get(0);
        if (colletingList.get(0).equals("on")) {
            colletingList.remove(all);
        }
    }

    private void startAdminControle(final HttpServletRequest request, final HttpServletResponse response) throws SQLException {
        final AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        this.admins = this.adminController.getAdmin("id", String.valueOf(userCookie.getUserId()));
    }
}