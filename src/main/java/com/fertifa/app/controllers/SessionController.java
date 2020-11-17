package com.fertifa.app.controllers;

import com.fertifa.app.services.SessionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionController implements SessionService {

    private String SessionEmail = null;


    @Override
    public boolean getSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session.getId() != null) {
            SessionEmail = (String) session.getAttribute("adminsemail");
            return SessionEmail != null;
        }
        return SessionEmail == null;
    }


    @Override
    public String getAdminUsername(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session.getId() != null) {
            return (String) session.getAttribute("adminsemail");
        } else {
            DistroySession(request);
        }
        return null;
    }

    @Override
    public boolean DistroySession(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        // session.invalidate();
        session.setAttribute("adminsemail", null);
        session.invalidate();
        return false;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////com.fertifa.app.Company Session

    /**
     * com.fertifa.app.Company Session Section
     *
     * @param request
     * @return
     */
    @Override
    public String getCompanyUsername(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
            if (session.getAttribute("SessionId") != null) {
                return (String) session.getAttribute("SessionUserEmail");
            } else {
                DistroyCompanySession(request);
            }

        return null;
    }


    @Override
    public boolean DistroyCompanySession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        // session.invalidate();
        session.removeAttribute("SessionUserEmail");
        session.removeAttribute("SessionId");
        session.invalidate();
        return true;
    }

    @Override
    public boolean getCompanySession(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("SessionId") != null || session.getAttribute("SessionUserEmail") != null) {
            SessionEmail = (String) session.getAttribute("SessionUserEmail");
            return SessionEmail != null;
        }
        return SessionEmail == null;
    }


}
