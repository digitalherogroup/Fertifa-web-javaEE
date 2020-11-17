package com.fertifa.app.adminSide.affiliate;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.AffiliateController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Affiliate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/affiliate")
public class Affiliates extends HttpServlet {
    //admin
    private String AdminEmail;
    private int AdminId;
    private int AdminRole;
    private AdminController adminController = new AdminController();
    private Admins adminsObject = new Admins();

    //Affiliates
    private AffiliateController affiliateController = new AffiliateController();
    private List<Affiliate> affiliateList = new ArrayList<>();

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
                affiliate(request, response);
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
                affiliate(request, response);
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


    private void affiliate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //getAdminFullDetails(AdminId);
        getAllAffiliates();
        SetAttributes(request);
        gotopage(request, response);
    }

    private void getAllAffiliates() throws SQLException {
        affiliateList = affiliateController.findAll();
    }

    private void SetAttributes(HttpServletRequest request) {
        request.setAttribute("Affiliate", "active");
        request.setAttribute("AdminsObject", adminsObject);
        request.setAttribute("Affiliates", affiliateList);
    }

    private void gotopage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url =request.getServletPath()+"/Affiliates.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }

   /* private void getAdminFullDetails(int adminId) {
        adminsObject = adminController.getAdminsById(adminId);
    }*/

    private void gotoLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/login/";
        response.sendRedirect(url);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }
}
