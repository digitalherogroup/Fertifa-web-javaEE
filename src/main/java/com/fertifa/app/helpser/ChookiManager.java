package com.fertifa.app.helpser;

import com.fertifa.app.controllers.UsersController;
import com.google.gson.Gson;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Base64;

public class ChookiManager {
    public static boolean saveDataToCookie(String userId, String userEmail, String userRole, int role, int status, HttpServletResponse response) {

        try {
            boolean isCompany = role == 2;
            UserCookie userCookie = new UserCookie(userId, userRole, userEmail, status);
            String userJson = new Gson().toJson(userCookie);
            // encode user json
            String userEncodedData = Base64.getEncoder().encodeToString(userJson.getBytes());
            if (userEncodedData == null || userEncodedData.isEmpty()) {
                return false;
            }
            Cookie cookie = null;

            if (isCompany) {
                cookie = new Cookie("cookieDataEmployer", userEncodedData);
                cookie.setPath("/employer");
                cookie.setHttpOnly(true);
            } else {
                cookie = new Cookie("cookieDataEmployee", userEncodedData);
                cookie.setPath("/employee");
                cookie.setHttpOnly(true);
            }

            response.addCookie(cookie);
            //
            // Set the cookie age to 600 seconds (10 minutes). Setting the age
            // to 0 will delete the cookie while giving it a negative value will
            // not store the cookie and it will be deleted when the browser is
            // closed.
            cookie.setMaxAge(60 * 60 * 24);
            return true;
        } catch (Exception e) {
            System.out.println("can't save data to cookie");
            return false;
        }
    }


    public static UserCookie getCookiData(HttpServletRequest request, HttpServletResponse response, int role) {
        boolean isCompany = role == 2;

        try {
            Cookie[] cookies = request.getCookies();

            if (cookies == null || cookies.length == 0) return null;

            String cookieSessionId = null;

            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName())) {
                    cookieSessionId = cookie.getValue();

                }
            }

            String cookieName = isCompany ? "cookieDataEmployer" : "cookieDataEmployee";
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    if (cookie.getValue() == null || cookie.getValue().isEmpty())
                        return null;

                    String encodedData = cookie.getValue();
                    String decodedData = new String(Base64.getDecoder().decode(encodedData));
                    // parse json to cookie model
                    UserCookie userCookie = new Gson().fromJson(decodedData, UserCookie.class);
                    userCookie.setCookieSessionId(cookieSessionId);
                    if (userCookie == null) return null;
                    // Check user if user deleted
                    UsersController usersController = new UsersController();
                    try {
                        if (!usersController.userIsExists(userCookie.getUserEmail())) {
                            removeAll(request, response, role);
                            return null;

                        } else if (!String.valueOf(usersController.getStatusByEmail(userCookie.getUserEmail())).equals(userCookie.active)) {
                            removeAll(request, response, role);
                            return null;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
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

    public static boolean isUserLogged(HttpServletRequest request, HttpServletResponse response, int role) {

        boolean isCompany = role == 1;

        ChookiManager.UserCookie userCookie = ChookiManager.getCookiData(request, response, role);

        if (userCookie == null) return false;

        String sessionIdRequest = request.getSession().getId();
        String sessionIdCooki = userCookie.getCookieSessionId();

        return sessionIdRequest != null && sessionIdCooki != null &&
                !sessionIdCooki.isEmpty() && !sessionIdRequest.isEmpty() &&
                sessionIdCooki.equals(sessionIdRequest);
    }

    public static HttpServletResponse removeAllEmployee(HttpServletRequest request, HttpServletResponse response, int role) {

        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) return response;

        // update cookie data

        for (Cookie cookie : cookies) {

            if ("cookieDataEmployee".equals(cookie.getName())) {
                cookie.setValue(null);
                cookie.setPath("/employee");
                cookie.setHttpOnly(true);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }

        }

        // remove attribute from request
        request.removeAttribute("employeeEmail");
        request.removeAttribute("userEmail");
        request.removeAttribute("userId");
        request.removeAttribute("userRole");
        request.removeAttribute("active");

        //invalidate the session if exists
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return response;
    }


    public static HttpServletResponse removeAllEmployer(HttpServletRequest request, HttpServletResponse response, int role) {

        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) return response;

        // update cookie data

        for (Cookie cookie : cookies) {

            if ("cookieDataEmployer".equals(cookie.getName())) {
                cookie.setValue(null);
                cookie.setPath("/employer");
                cookie.setHttpOnly(true);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }

        }

        // remove attribute from request
        request.removeAttribute("employerEmail");
        request.removeAttribute("userEmail");
        request.removeAttribute("userId");
        request.removeAttribute("userRole");
        request.removeAttribute("active");

        //invalidate the session if exists
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return response;
    }

    public static HttpServletResponse removeAll(HttpServletRequest request, HttpServletResponse response, int role) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) return response;
        // update cookie data
        for (Cookie cookie : cookies) {
            if ("cookieDataEmployer".equals(cookie.getName())) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            } else if ("cookieDataEmployee".equals(cookie.getName())) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

// remove attribute from request
        request.removeAttribute("userEmail");
        request.removeAttribute("userId");
        request.removeAttribute("userRole");
        request.removeAttribute("active");

        //invalidate the session if exists
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return response;
    }

    public static class UserCookie {
        private String userId;
        private String userRole;
        private String userEmail;
        private String cookieSessionId;
        private String active;

        public UserCookie() {
        }

        public UserCookie(String userId, String userRole, String userEmail) {
            this.userId = userId;
            this.userRole = userRole;
            this.userEmail = userEmail;
        }

        public UserCookie(String userId, String userRole, String userEmail, String cookieSessionId) {
            this.userId = userId;
            this.userRole = userRole;
            this.userEmail = userEmail;
            this.cookieSessionId = cookieSessionId;
        }

        public UserCookie(String userId, String userRole, String userEmail, String cookieSessionId, String active) {
            this.userId = userId;
            this.userRole = userRole;
            this.userEmail = userEmail;
            this.cookieSessionId = cookieSessionId;
            this.active = active;
        }

        public UserCookie(String userId, String userRole, String userEmail, int status) {
            this.userId = userId;
            this.userRole = userRole;
            this.userEmail = userEmail;
            this.active = String.valueOf(status);
        }

        public String getCookieSessionId() {
            return cookieSessionId;
        }

        public void setCookieSessionId(String cookieSessionId) {
            this.cookieSessionId = cookieSessionId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserRole() {
            return userRole;
        }

        public void setUserRole(String userRole) {
            this.userRole = userRole;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }
    }
}