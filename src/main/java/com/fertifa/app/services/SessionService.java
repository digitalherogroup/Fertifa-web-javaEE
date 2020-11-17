package com.fertifa.app.services;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface SessionService {
    boolean getSession(HttpServletRequest request) throws IOException;
    boolean getCompanySession(HttpServletRequest request) throws IOException;
    String getAdminUsername(HttpServletRequest request);
    boolean DistroySession(HttpServletRequest request);
    boolean DistroyCompanySession(HttpServletRequest request);
    String getCompanyUsername(HttpServletRequest request);
}
