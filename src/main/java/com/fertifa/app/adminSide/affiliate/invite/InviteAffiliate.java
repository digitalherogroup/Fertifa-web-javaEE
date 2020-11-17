package com.fertifa.app.adminSide.affiliate.invite;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.models.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/affiliate/invite")
public class InviteAffiliate extends HttpServlet {
    //admin
    private String AdminEmail;
    private int AdminId;
    private int AdminRole;
    private AdminController adminController = new AdminController();
    private Admins adminsObject = new Admins();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean isLogined = AdminCookiManager.isUserLogged(request,response);
            if (isLogined) {
                AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request,response);
                if (userCookie == null) {
                    // go to login page
                    gotoLoginPage(request, response);
                    return;
                }
                AdminEmail = userCookie.getUserEmail();
                AdminId = userCookie.getUserId();
                AdminRole = userCookie.getUserRole();
                inviteAffiliate(request, response);
            } else {
                gotoLoginPage(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                gotoLoginPage(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean isLogined = AdminCookiManager.isUserLogged(request, response);
            if (isLogined) {
                AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
                if (userCookie == null) {
                    // go to login page
                    gotoLoginPage(request, response);
                    return;
                }
                AdminEmail = userCookie.getUserEmail();
                AdminId = userCookie.getUserId();
                AdminRole = userCookie.getUserRole();
                inviteAffiliate(request, response);
            } else {
                gotoLoginPage(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                gotoLoginPage(request, response);
            }
        }
    }

    private void inviteAffiliate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getAdminFullDetails(AdminId);
        SetAttributes(request);
        gotopage(request, response);
    }

    private void SetAttributes(HttpServletRequest request) {
        request.setAttribute("Affiliate", "active");
        request.setAttribute("AdminId", AdminId);
        request.setAttribute("AdminsObject", adminsObject);
    }

    private void gotopage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath() + "/Invite.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }

    private void gotoLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/login/";
        response.sendRedirect(url);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }

}
