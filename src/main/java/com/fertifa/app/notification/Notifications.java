package com.fertifa.app.notification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public  class Notifications {

    public static void WorningNotify(HttpServletRequest request, String message){
        HttpSession session = request.getSession();
        session.setAttribute("notices", message);
    }

    public static void ClearNotify(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("notices", "");
        session.setAttribute("successNotices", "");
        session.setAttribute("errorNotices", "");
    }

    public static void SuccessNotify(HttpServletRequest request, String message){
        HttpSession session = request.getSession();
        session.setAttribute("successNotices", message);
    }

    public static void ErrorNotify(HttpServletRequest request, String message){
        HttpSession session = request.getSession();
        session.setAttribute("errorNotices", message);
    }
}
