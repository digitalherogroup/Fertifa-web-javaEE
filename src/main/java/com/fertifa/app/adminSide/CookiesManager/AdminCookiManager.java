package com.fertifa.app.adminSide.CookiesManager;

import com.fertifa.app.controllers.AdminController;
import com.google.gson.Gson;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Base64;

public class AdminCookiManager {

    public static boolean saveDataToCookie(int userId, String userEmail, int roleid,String role, HttpServletResponse response) {

        try {
            AdminCookiManager.UserCookie userCookie = new AdminCookiManager.UserCookie(userId, userEmail, roleid,role);
            String userJson = new Gson().toJson(userCookie);
            // encode user json
            String userEncodedData = Base64.getEncoder().encodeToString(userJson.getBytes());
            if (userEncodedData == null || userEncodedData.isEmpty()) {
                return false;
            }
            Cookie cookie = null;
            cookie = new Cookie("cookieDataAdmin", userEncodedData);
            cookie.setPath("/admin");
            cookie.setHttpOnly(true);

            response.addCookie(cookie);
            //
            // Set the cookie age to 600 seconds (10 minutes). Setting the age
            // to 0 will delete the cookie while giving it a negative value will
            // not store the cookie and it will be deleted when the browser is
            // closed.
            //

            return true;
        } catch (Exception e) {
            System.out.println("can't save data to cookie");
            return false;
        }
    }


    public static AdminCookiManager.UserCookie getCookiData(HttpServletRequest request, HttpServletResponse response) {

        try {
            Cookie[] cookies = request.getCookies();

            if (cookies == null || cookies.length == 0) return null;

            String cookieSessionId = null;


            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName())) {
                    cookieSessionId = cookie.getValue();
                }
            }

            String cookieName = "cookieDataAdmin";

            for (Cookie cookie : cookies) {

                if (cookieName.equals(cookie.getName())) {

                    if (cookie.getValue() == null || cookie.getValue().isEmpty())
                        return null;

                    String encodedData = cookie.getValue();
                    String decodedData = new String(Base64.getDecoder().decode(encodedData));
                    // parse json to cookie model
                    AdminCookiManager.UserCookie userCookie = new Gson().fromJson(decodedData, AdminCookiManager.UserCookie.class);
                    userCookie.setCookieSessionId(cookieSessionId);
                    if (userCookie == null) return null;
                    AdminController adminController = new AdminController();
                    try {
                        if (!adminController.isAdminExsist(userCookie.getUserEmail())) {
                            removeAll(request, response);
                            return null;
                        }
                    } catch (Exception e) {
                        return null;
                    }
                    return userCookie;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static boolean isUserLogged(HttpServletRequest request, HttpServletResponse response) {

        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);

        if (userCookie == null) return false;

        String sessionIdRequest = request.getSession().getId();
        String sessionIdCooki = userCookie.getCookieSessionId();

        return sessionIdRequest != null && sessionIdCooki != null &&
                !sessionIdCooki.isEmpty() && !sessionIdRequest.isEmpty() &&
                sessionIdCooki.equals(sessionIdRequest);
    }


    public static HttpServletResponse removeAll(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) return response;

        // update cookie data

        for (Cookie cookie : cookies) {


            if ("cookieDataAdmin".equals(cookie.getName()) ) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);

            }
        }

        // remove attribute from request
        request.removeAttribute("adminEmail");
        request.removeAttribute("adminid");
        request.removeAttribute("adminRole");
        request.removeAttribute("adminRoleId");

        //invalidate the session if exists
        HttpSession session = request.getSession(true);
        if (session != null) {
            session.invalidate();
        }

        return response;
    }


    public static class UserCookie {
        private int userId;
        private int userRole;
        private String role;
        private String userEmail;
        private String cookieSessionId;

        public UserCookie() {
        }

        public UserCookie(int userId, String userEmail, int roleid,String role) {
            this.userId = userId;
            this.userEmail = userEmail;
            this.userRole = roleid;
            this.role=role;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getCookieSessionId() {
            return cookieSessionId;
        }

        public void setCookieSessionId(String cookieSessionId) {
            this.cookieSessionId = cookieSessionId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getUserRole() {
            return userRole;
        }

        public void setUserRole(int userRole) {
            this.userRole = userRole;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }
    }

}
