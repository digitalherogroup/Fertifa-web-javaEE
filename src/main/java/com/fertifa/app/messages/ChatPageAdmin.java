package com.fertifa.app.messages;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.ChatMessageController;
import com.fertifa.app.controllers.ChatSessionController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.ChatMessages;
import com.fertifa.app.models.ChatSession;
import com.fertifa.app.models.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/customer/chat/conversation")
public class ChatPageAdmin extends com.fertifa.app.baseUrl.BaseUrl {

    private String TokenId = "";
    private AdminController adminController = new AdminController();

    private List<Users> companyList = new ArrayList<>();
    List<ChatSession> sessionByTokenList = new ArrayList<>();
    List<ChatMessages> sesionChatMessagingList = new ArrayList<>();
    private ChatMessageController chatMessageController = new ChatMessageController();
    private ChatSessionController chatSessionController = new ChatSessionController();
    List<String> chatFrom = new ArrayList<>();
    List<String> chatTo = new ArrayList<>();
    private String UserID = "";
    List<ChatSession> sessionChatlistRequest = new ArrayList<>();
    List<ChatSession> sessionChatlistNotification = new ArrayList<>();
    List<ChatSession> sessionChatlistString = new ArrayList<>();
    private String AdminEmail;
    private int AdminId;
    private int AdminRole;
    private int StatusFront = 0;
    private String NameSessionFront = "";
    private Admins admins = new Admins();

    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            chatPageAdmin(request, response);
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

    private void chatPageAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        startAdminControle(request,response);
        getParameters(request, response);
        SetAttributes(request, response);

        gotoMasterAdminPage(request, response);

    }

    private void gotoMasterAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/MessageSelected.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void SetAttributes(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("Messages", "active");
        request.setAttribute("ID", AdminId);
        request.setAttribute("AdminId", AdminId);
        request.setAttribute("Token", TokenId);
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("CompanyList", companyList);
        request.setAttribute("ChatSessionListByToken", sessionByTokenList);
        request.setAttribute("SesionChatMessagingList", sesionChatMessagingList);
        request.setAttribute("OnlyChatFrom", chatFrom);
        request.setAttribute("OnlyChatTo", chatTo);
        request.setAttribute("ChatSessionListRequest", sessionChatlistRequest);
        request.setAttribute("ChatSessionListNotification", sessionChatlistNotification);
        request.setAttribute("ChatSessionListString", sessionChatlistString);
        request.setAttribute("StatusFront", StatusFront);
    }

    private void getParameters(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        TokenId = "";
        UserID = "";
        if (request.getParameter("sessionid") != null) {
            TokenId = request.getParameter("sessionid");
        }
        if (request.getParameter("id") != null) {
            UserID = request.getParameter("id");
        }
        checkInputs(TokenId, UserID, request, response);

    }

    private void checkInputs(String tokenId, String userID, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (tokenId == null || tokenId.equals("") || userID == null || userID.equals("")) {
            gotoErroPage(request, response);
        } else {
            CheckIfTheSessionWithUSerId(TokenId, UserID, request, response);
        }
    }

    private void gotoErroPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }

    private void CheckIfTheSessionWithUSerId(String tokenId, String userId, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String[] TokenToArray = tokenId.split("_");
        for (int i = 0; i < TokenToArray.length; i++) {
            if (CheckIfCompanyIdEqualsChatId(TokenToArray)) {
                getUserSessionsChat(AdminId);
                getSessionByToken();
                getChatBySession();
            } else {
                gotoErrorPage(request, response);
            }
        }
    }


    private void getUserSessionsChat(int adminId) {
        sessionChatlistRequest = new ArrayList<>();
        sessionChatlistNotification = new ArrayList<>();
        sessionChatlistString = new ArrayList<>();
        sessionChatlistNotification = chatSessionController.getAllSessionDetailsbyId(Integer.parseInt(UserID), Constances.CATEGORYREQUEST);
        sessionChatlistRequest = chatSessionController.getAllSessionDetailsbyId(Integer.parseInt(UserID), Constances.CATEGORYNOTIFICATION);
        sessionChatlistString = chatSessionController.getAllSessionDetailsbyId(Integer.parseInt(UserID), Constances.CATEGORYMESSAGE);

    }

    private void gotoErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }

    private void getChatBySession() {
        sesionChatMessagingList = chatMessageController.getAllByToken(TokenId);
        for (int i = 0; i < sesionChatMessagingList.size(); i++) {
            System.out.println(sesionChatMessagingList.get(i).getDateString());
        }
    }

    private void getSessionByToken() {
        sessionByTokenList = chatSessionController.getAllByToken(TokenId);
        if (sessionByTokenList.size() > 0) {
            StatusFront = sessionByTokenList.get(0).getChatSessionStatus();
            NameSessionFront = sessionByTokenList.get(0).getChatSessionName();

        }
    }


    private boolean CheckIfCompanyIdEqualsChatId(String[] tokenToArray) {
        int ToCheck = Integer.parseInt(tokenToArray[1]);
        int userid = Integer.parseInt(UserID);
        return userid == ToCheck;
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
