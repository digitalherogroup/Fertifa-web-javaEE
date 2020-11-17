package com.fertifa.app.adminSide.directchat;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.ChatMessageController;
import com.fertifa.app.controllers.ChatSessionController;
import com.fertifa.app.controllers.SessionController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.ChatMessages;
import com.fertifa.app.models.ChatSession;
import com.fertifa.app.models.DirectChater;
import com.fertifa.app.models.Users;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RolesAllowed("4")
@WebServlet("/nurse/chat")
public class DirectChatNurse extends HttpServlet {
    private String SessionEmail = "";
    private int CompanyId = 0;
    private int LinkeId = 0;
    private AdminController adminController = new AdminController();
    private SessionController sessionController = new SessionController();
    private List<Admins> adminFullList = new ArrayList<>();
    private UsersController usersController = new UsersController();
    private List<Users> companyList = new ArrayList<>();
    List<ChatSession> sessionByTokenList = new ArrayList<>();
private Admins admins = new Admins();
    List<DirectChater> sessionChatlistRequest = new ArrayList<>();
    List<ChatSession> sessionChatlistRequestFiller = new ArrayList<>();
    List<DirectChater> sessionChatlistNotification = new ArrayList<>();
    List<ChatSession> sessionChatlistNotificationFiller = new ArrayList<>();
    List<DirectChater> sessionChatlistString = new ArrayList<>();
    List<ChatSession> sessionChatlistStringFiller = new ArrayList<>();
    private ChatSessionController chatSessionController = new ChatSessionController();
    List<ChatMessages> sesionChatMessagingList = new ArrayList<>();
    private ChatMessageController chatMessageController = new ChatMessageController();
    private String token = "";
    int UserIdChat = 0;
    String Sessionid = "";
    private String UserID = "";
    private int Type = 0;
    private String AdminEmail;
    private int AdminId;
    private int AdminRole;
    private int StatusFront = 0;
    private String NameSessionFront = "";

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
                admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA,String.valueOf(AdminId));
                directChat(request, response,admins);
            } else {
                AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request,response);
                AdminEmail = userCookie.getUserEmail();
                admins = adminController.getAdmin(AdminConstances.ADMIN_EMAIL_INDATA,AdminEmail);
                getParameters(request,response);
                directChat(request,response,admins);
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
                admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA,String.valueOf(AdminId));
                directChat(request, response,admins);
            } else {

                getParameters(request,response);
                directChat(request,response,admins);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                gotoLoginPage(request, response);
            }
        }
    }


    private boolean CheckAdminInputs(String email, String password) throws SQLException {
        return adminController.validateAdminUsers(email, password);
    }

    private void directChat(HttpServletRequest request, HttpServletResponse response,Admins admins) throws IOException, ServletException, SQLException {

        startAdminControle();
        getParameters(request, response);
        setRequest(request, response);
        gotoPage(request, response);
    }

    private void getUserSessionsChatThird() throws SQLException {
        List<Users> userCatList = new ArrayList<>();
        List<Admins> AdminCatList = new ArrayList<>();
        sessionChatlistStringFiller = new ArrayList<>();
        sessionChatlistStringFiller = chatSessionController.getAllSessionDetailsByType(Constances.CATEGORYMESSAGE);
    }

    private void getUserSessionsChatSecond() throws SQLException {
        List<Users> userCatList = new ArrayList<>();
        List<Admins> AdminCatList = new ArrayList<>();
        sessionChatlistRequestFiller = new ArrayList<>();
        sessionChatlistRequestFiller = chatSessionController.getAllSessionDetailsByType(Constances.CATEGORYREQUEST);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/Messaging.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void getParameters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (request.getParameter("nurseId") != null) {
            admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA,request.getParameter("nurseId"));
        }

        if (request.getParameter("id") != null) {
            UserIdChat = Integer.parseInt(request.getParameter("id"));
        }
        if (request.getParameter("sessionid") != null) {
            token = request.getParameter("sessionid");
        }
        if (UserIdChat == 0 && token.equals("")) {
            getUserSessionsChat();
            getUserSessionsChatSecond();
            getUserSessionsChatThird();
            getChatBySession(0);
        } else {
            checkInputs(token, String.valueOf(UserIdChat), request, response);
        }
    }

    private void checkInputs(String tokenId, String userID, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (tokenId == null || tokenId.equals("") || userID == null || userID.equals("")) {
            gotoErroPage(request, response);
        } else {
            CheckIfTheSessionWithUSerId(tokenId, userID, request, response);
        }
    }


    private void getSessionByToken(String TokenId) {
        sessionByTokenList = chatSessionController.getAllByToken(TokenId);
        if (sessionByTokenList.size() > 0) {
            StatusFront = sessionByTokenList.get(0).getChatSessionStatus();
            NameSessionFront = sessionByTokenList.get(0).getChatSessionName();

        }
    }

    private void CheckIfTheSessionWithUSerId(String tokenId, String userId, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String[] TokenToArray = tokenId.split("_");

        if (CheckIfCompanyIdEqualsChatId(TokenToArray)) {
            getSessionByToken(tokenId);
            Type = sessionByTokenList.get(0).getChatSessionType();
            getUserSessionsChat();
            getUserSessionsChatSecond();
            getUserSessionsChatThird();
            getChatBySession(Type);

            //getSessionByToken(tokenId);
            //getChatBySession();
        } else {
            gotoErroPage(request, response);
        }

    }


    private void gotoErroPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }

    private boolean CheckIfCompanyIdEqualsChatId(String[] tokenToArray) {
        int ToCheck = Integer.parseInt(tokenToArray[1]);
        if (UserIdChat == 4) {
            return true;
        } else {
            int userid = Integer.parseInt(String.valueOf(UserIdChat));
            return userid == ToCheck;
        }
    }
    private void setRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("Messages", "active");

        request.setAttribute("NurseObject", admins);

        request.setAttribute("CompanyList", companyList);

        request.setAttribute("SessionChatlistNotification", sessionChatlistNotificationFiller);
        request.setAttribute("SessionChatlistString", sessionChatlistRequestFiller);
        request.setAttribute("SessionChatlistRequest",sessionChatlistStringFiller );


        request.setAttribute("SesionChatMessagingList", sesionChatMessagingList);
        request.setAttribute("SessionByTokenList", sessionByTokenList);
        request.setAttribute("Token", token);

        request.setAttribute("UserIdChat", UserIdChat);
        request.setAttribute("StatusFront", StatusFront);
        request.setAttribute("NameSessionFront", NameSessionFront);
        request.setAttribute("Type", Type);
        request.setAttribute("SessionChatlistStringSize", sessionChatlistString.size());

    }


    private void getChatBySession(int type) {
        if (type > 0) {
            sesionChatMessagingList = new ArrayList<>();
            sesionChatMessagingList = chatMessageController.getAllByToken(token);
            if (sessionByTokenList.size() > 0) {
                token = sessionByTokenList.get(0).getChatSessionToken();
                StatusFront = sessionByTokenList.get(0).getChatSessionStatus();
                NameSessionFront = sessionByTokenList.get(0).getChatSessionName();
                System.out.println(token);
                System.out.println(StatusFront);
                System.out.println(NameSessionFront);
            }

        } else {
            if (sessionChatlistString.size() > 0) {
                token = sessionChatlistString.get(0).getSession_token();
                UserIdChat = sessionChatlistString.get(0).getFrom_id();

                System.out.println(token);
                sesionChatMessagingList = new ArrayList<>();
                sesionChatMessagingList = chatMessageController.getAllByToken(token);
                if (sesionChatMessagingList.size() > 0) {
                    for (int i = 0; i < sesionChatMessagingList.size(); i++) {
                        System.out.println(sesionChatMessagingList.get(i).getChatBody());
                    }
                } else {
                    //sesionChatMessagingList.add(new ChatMessages("No message to show"));
                }
            }
        }

    }

    private void getUserSessionsChat() throws SQLException {
        sessionChatlistNotificationFiller = new ArrayList<>();
        sessionChatlistNotificationFiller = chatSessionController.getAllSessionDetailsByType(Constances.CATEGORYNOTIFICATION);

    }

    private void startAdminControle() {

        admins=adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA,String .valueOf(AdminId));
    }


    private void gotoLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/login/";
        response.sendRedirect(url);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }


}

