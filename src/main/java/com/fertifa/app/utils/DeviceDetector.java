package com.fertifa.app.utils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class DeviceDetector extends HttpServlet {

    public static boolean detectDevice(HttpServletRequest request) {
        return !request.getHeader("User-Agent").contains("Mobi");
    }
}

