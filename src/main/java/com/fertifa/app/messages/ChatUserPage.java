package com.fertifa.app.messages;

import com.fertifa.app.constants.Constances;
import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.ChatMessageController;
import com.fertifa.app.controllers.ChatSessionController;
import com.fertifa.app.controllers.SessionController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.ChatMessages;
import com.fertifa.app.models.ChatSession;
import com.fertifa.app.models.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employee/chat/conversation")
public class ChatUserPage extends com.fertifa.app.baseUrl.BaseUrl {

    private String TokenId = "";
    private SessionController sessionController = new SessionController();
    private List<Admins> adminFullList = new ArrayList<>();
    private List<Users> companyList = new ArrayList<>();
    List<ChatSession> sessionByTokenList = new ArrayList<>();
    List<ChatMessages> sesionChatMessagingList = new ArrayList<>();
    private ChatMessageController chatMessageController = new ChatMessageController();
    private ChatSessionController chatSessionController = new ChatSessionController();
    private Timestamp ChatSessionDate;
    List<String> chatFrom = new ArrayList<>();
    List<String> chatTo = new ArrayList<>();

    List<ChatSession> sessionChatlistRequest = new ArrayList<>();
    List<ChatSession> sessionChatlistNotification = new ArrayList<>();
    List<ChatSession> sessionChatlistString = new ArrayList<>();
    private int UserRole;
    private int StatusFront =0;
    private String NameSessionFront="";
    private Users users = new Users();
    private UsersController usersController = new UsersController();


    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA,getEmployeeId(request,response));
            chatUserPage(request, response);
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

    private void chatUserPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getParameters(request, response);
        setrequest(request, response);
        gotoPage(request, response);

    }

    private void getSessionByToken(String TokenId) {
        sessionByTokenList = chatSessionController.getAllByToken(TokenId);
        if (sessionByTokenList.size() > 0) {
            StatusFront = sessionByTokenList.get(0).getChatSessionStatus();
            NameSessionFront = sessionByTokenList.get(0).getChatSessionName();

        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/MessageUserSelected.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void setrequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("Messages", "active");
        request.setAttribute("StatusFront", StatusFront);
        request.setAttribute("EmployeeObject",users);

        request.setAttribute("Token", TokenId);

        request.setAttribute("CompanyList", companyList);
        request.setAttribute("ChatSessionListByToken", sessionByTokenList);
        request.setAttribute("ChatSessionDate", ChatSessionDate);
        request.setAttribute("SesionChatMessagingList", sesionChatMessagingList);
        request.setAttribute("OnlyChatFrom", chatFrom);
        request.setAttribute("OnlyChatTo", chatTo);
        request.setAttribute("ChatSessionListRequest", sessionChatlistRequest);
        request.setAttribute("ChatSessionListNotification", sessionChatlistNotification);
        request.setAttribute("ChatSessionListString", sessionChatlistString);
        request.setAttribute("SesionChatMessagingList", sesionChatMessagingList);

    }


    private void getSessionByToken() {
        sessionChatlistRequest = new ArrayList<>();
        sessionChatlistNotification = new ArrayList<>();
        sessionChatlistString = new ArrayList<>();

        sessionChatlistNotification = chatSessionController.getAllSessionDetailsbyId(users.getId(), Constances.CATEGORYREQUEST);
        sessionChatlistRequest = chatSessionController.getAllSessionDetailsbyId(users.getId(), Constances.CATEGORYNOTIFICATION);
        sessionChatlistString = chatSessionController.getAllSessionDetailsbyId(users.getId(), Constances.CATEGORYMESSAGE);
        sessionByTokenList = chatSessionController.getAllByToken(TokenId);
        sesionChatMessagingList = chatMessageController.getAllByToken(TokenId);
    }

    private void getParameters(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TokenId = "";
        if (request.getParameter("id") != null) {
            TokenId = request.getParameter("id");
        }
        checkInputs(TokenId, request, response);
    }

    private void checkInputs(String tokenId, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (tokenId == null || tokenId.equals("")) {
            gotoErrorPage(request, response);
        } else {
            CheckIfTheSessionWithUSerId(TokenId, request, response);
        }
    }


    private void CheckIfTheSessionWithUSerId(String tokenId, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String[] TokenToArray = tokenId.split("_");
        for (int i = 0; i < TokenToArray.length; i++) {
            if (CheckIfCompanyIdEqualsChatId(TokenToArray)) {
                getSessionByToken(tokenId);
                getSessionByToken();
                getChatBySession();
            } else {
                gotoErrorPage(request, response);
            }
        }
    }

    private void getChatBySession() {
        sesionChatMessagingList = chatMessageController.getAllByToken(TokenId);
        sessionByTokenList = chatSessionController.getAllByToken(TokenId);
        if(sessionByTokenList.size()>0){
            StatusFront = sessionByTokenList.get(0).getChatSessionStatus();
        }
        for (int i = 0; i < sesionChatMessagingList.size(); i++) {
            System.out.println(sesionChatMessagingList.get(i).getDateString());
        }
    }

    private void gotoErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }

    private boolean CheckIfCompanyIdEqualsChatId(String[] tokenToArray) {
        int ToCheck = Integer.parseInt(tokenToArray[1]);
        return ToCheck == users.getId();
    }

}
