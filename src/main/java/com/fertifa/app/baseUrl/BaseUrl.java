package com.fertifa.app.baseUrl;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.adminSide.communication.client.AdminSignalingClient;
import com.fertifa.app.adminSide.communication.signal.AdminCallSignaling;
import com.fertifa.app.helpser.ChookiManager;
import com.fertifa.app.users.communication.client.EmployeeSignalingClient;
import com.fertifa.app.users.communication.signal.EmployeeCallSignaling;

import javax.annotation.security.DeclareRoles;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@DeclareRoles({"1", "2", "3"})
//@WebListener
public class BaseUrl extends HttpServlet {
    private int CompanyRole = 0;

    private int CompanyId = 0;

    private String SessionUserEmail = "";

    private static String servletName = "";

    private String AdminEmail;

    private int AdminId;

    private int AdminRoleid;

    private String AdminRole;

    private Properties site = new Properties(System.getProperties());

    static String BaseUrl = "";

    private int userRole;

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.userRole = getUserRoleForServlet(request, response);
        if (request.getMethod().equals("GET")) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }


    private int getUserRoleForServlet(HttpServletRequest request, HttpServletResponse response) {
        String requestUrl = request.getServletPath();
        if (requestUrl.startsWith("/admin")) {
           /* try {
                startAdminSignalingRequest(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            return 1;
        }
        if (requestUrl.startsWith("/employer")) {
            return 2;
        }
        if (requestUrl.startsWith("/employee")) {
           /* try {
                startEmployeeSignalingRequest(request, response);
            } catch (ServletException | NamingException | IOException e) {
                e.printStackTrace();
            }*/
            return 3;
        }
        return -1;
    }

    protected void startEmployeeSignalingRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, NamingException, IOException {
        EmployeeSignalingClient
                .getInstance(new EmployeeCallSignaling())
                .start();
    }

    protected void startAdminSignalingRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, NamingException, IOException {

        AdminSignalingClient
                .getInstance(new AdminCallSignaling())
                .addReqRes(request, response)
                .start();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (this.userRole == 1) {
            checkAdminCookies(request, response);
        } else if (this.userRole == 2) {

            checkEmployerCookies(request, response);
        } else if (this.userRole == 3) {
            checkEmployeeCookies(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (this.userRole == 1) {
            checkAdminCookies(request, response);
        } else if (this.userRole == 2) {
            checkEmployerCookies(request, response);
        } else if (this.userRole == 3) {
            checkEmployeeCookies(request, response);
        }
    }

    private void checkEmployeeCookies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ChookiManager.UserCookie userCookie = ChookiManager.getCookiData(request, response, 3);
            if (userCookie == null) {
                response.sendRedirect("employeeloginpage.jsp");
                return;
            }
            this.SessionUserEmail = userCookie.getUserEmail();
            this.CompanyId = Integer.parseInt(userCookie.getUserId());
            this.CompanyRole = Integer.parseInt(userCookie.getUserRole());
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                response.sendRedirect("employeeloginpage.jsp");
            } else {
                response.sendRedirect("splash.jsp");
            }
        }

    }

    private void checkEmployerCookies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ChookiManager.UserCookie userCookie = ChookiManager.getCookiData(request, response, 2);
            if (userCookie == null) {
                String url = "/employer/login/";
                response.sendRedirect(url);
                return;
            }
            this.SessionUserEmail = userCookie.getUserEmail();
            this.CompanyId = Integer.parseInt(userCookie.getUserId());
            this.CompanyRole = Integer.parseInt(userCookie.getUserRole());
            if (request != null && request.getServletPath() != null && (request
                    .getServletPath().equals("/employer/login") || request
                    .getServletPath().equals("/employer/login/"))) {
                String url = "/employer/employer-dashboard";
                response.sendRedirect(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                String url = "/employer/login/";
                response.sendRedirect(url);
            } else {
                String url = "/employer/login/";
                response.sendRedirect(url);
            }
        }

    }

    private void checkAdminCookies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean isLogined = AdminCookiManager.isUserLogged(request, response);
            if (isLogined) {
                AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
                if (userCookie == null) {
                    String url = "/admin/login/";
                    response.sendRedirect(url);
                    return;
                }
                this.AdminEmail = userCookie.getUserEmail();
                this.AdminId = userCookie.getUserId();
                this.AdminRoleid = userCookie.getUserRole();
                this.AdminRole = userCookie.getRole();
                return;
            }
            String url = "/admin/login/";
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                String url = "/admin/login/";
                response.sendRedirect(url);
            }
        }
    }

    public boolean checkCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if ((request.getCookies()).length <= 1 || !AdminCookiManager.isUserLogged(request, response)) {
            String url = "/admin/login/";
            response.sendRedirect(url);
            return false;
        }
        return true;
    }

    public boolean checkCookieManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if ((request.getCookies()).length <= 1 || !ChookiManager.isUserLogged(request, response, 2)) {
            String url = "/employer/login/";
            response.sendRedirect(url);
            return false;
        }
        return true;
    }

    public boolean employeeCookieManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if ((request.getCookies()).length <= 1 || !ChookiManager.isUserLogged(request, response, 3)) {
            String url = "/employee/login/";
            response.sendRedirect(url);
            return false;
        }
        return true;
    }

    public String getEmployerId(HttpServletRequest request, HttpServletResponse response) {
        ChookiManager.UserCookie userCookie = ChookiManager.getCookiData(request, response, 2);
        assert userCookie != null;
        return userCookie.getUserId();
    }

    public String getEmployeeId(HttpServletRequest request, HttpServletResponse response) {
        ChookiManager.UserCookie userCookie = ChookiManager.getCookiData(request, response, 3);
        assert userCookie != null;
        return userCookie.getUserId();
    }


    public static String baseUrl(HttpServletRequest request) {
        return request.getHeader("Host") + "/";
    }
}
