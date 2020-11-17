package com.fertifa.app.adminSide.employer;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.ChatSessionController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.ChatSession;
import com.fertifa.app.models.Users;
import com.fertifa.app.constants.AdminConstances;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/customer/chat")
public class MessagingAdmin extends com.fertifa.app.baseUrl.BaseUrl {

    private int adminId = 0;
    private int LinkeId = 0;
    private AdminController adminController = new AdminController();
    private List<Users> companyList = new ArrayList<>();
    private List<ChatSession> sessionChatlistRequest = new ArrayList<>();
    private List<ChatSession> sessionChatlistNotification = new ArrayList<>();
    private List<ChatSession> sessionChatlistString = new ArrayList<>();
    private ChatSessionController chatSessionController = new ChatSessionController();
    private Admins admins = new Admins();

    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            messagingAdmin(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    private void messagingAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        startAdminControle(request,response);
        getParameters(request);
        getUserSessionsChat(adminId);
        SetAttributes(request);
        gotoMasterAdminPage(request,response);

    }

    private void getParameters(HttpServletRequest request) {
        if(request.getParameter("id")!=null) {
            LinkeId = Integer.parseInt(request.getParameter("id"));
        }
    }

    private void gotoMasterAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  url  = request.getServletPath()+"/Messages.jsp";
        request.getRequestDispatcher(url).forward(request,response);
        request.getSession().setAttribute("successNotices",null);
        request.getSession().setAttribute("errorNotices",null);
    }
    private void SetAttributes(HttpServletRequest request) {
        request.setAttribute("Messages","active");
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("CompanyList", companyList);
        request.setAttribute("ChatSessionListRequest", sessionChatlistRequest);
        request.setAttribute("ChatSessionListNotification", sessionChatlistNotification);
        request.setAttribute("ChatSessionListString", sessionChatlistString);
    }

    private void getUserSessionsChat(int adminId) {
        sessionChatlistRequest=new ArrayList<>();
        sessionChatlistNotification=new ArrayList<>();
        sessionChatlistString=new ArrayList<>();
        sessionChatlistNotification= chatSessionController.getAllSessionDetailsbyId(LinkeId, Constances.CATEGORYREQUEST);
        sessionChatlistRequest  = chatSessionController.getAllSessionDetailsbyId(LinkeId, Constances.CATEGORYNOTIFICATION);
        sessionChatlistString = chatSessionController.getAllSessionDetailsbyId(LinkeId, Constances.CATEGORYMESSAGE);
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
