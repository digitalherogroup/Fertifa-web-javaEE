/*
package com.fertifa.app.Messaging;

import com.fertifa.app.Constances.com.fertifa.app.Constances;
import com.fertifa.app.Controllers.ChatController;
import com.fertifa.app.Controllers.SessionController;
import com.fertifa.app.Controllers.UsersController;
import com.fertifa.app.Models.Chat;
import com.fertifa.app.Models.ChatMessages;
import com.fertifa.app.Models.ChatSession;
import com.fertifa.app.Models.ChatSessionUsers;
import com.fertifa.app.Notifications.com.fertifa.app.Notifications;
import com.fertifa.app.Utils.MDFive;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

@WebServlet("/CreateSessionForChat")
public class CreateSessionForChat extends HttpServlet {
    private String startingSeessionChat = "";
    private String SessionUserEmail = "";
    private String SessionType = "";
    private String SessionToken="";
    private SessionController sessionController = new SessionController();
    private ChatController chatController = new ChatController();
    private UsersController usersController = new UsersController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            createSessionForChat(request,response);
        } catch (NoSuchAlgorithmException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            createSessionForChat(request,response);
        } catch (NoSuchAlgorithmException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void createSessionForChat(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, NoSuchAlgorithmException, SQLException {
        getParameters(request);
        UnicodeingSevlet(request);
        getSession(request, response);
        CreateChatDataBase(request,response);
    }

    private void CreateChatDataBase(HttpServletRequest request, HttpServletResponse response) throws IOException, NoSuchAlgorithmException, SQLException, ServletException {
        if(chatController.AddNewChatSession(CreatChatSessionObject(request,response))>0){
            creatSessionUser(request,response);
        }else{
            String message = "Something Went wrong in Chat Session ";
            com.fertifa.app.Notifications.ErrorNotify(request,message);
            gotoPage(request,response);
        }
    }

    private void creatSessionUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if(chatController.AddNewChatSessionUser(CreatChatSessionUserObject(request,response))>0){
            String message = "Your chat room is ready ";
            com.fertifa.app.Notifications.SuccessNotify(request,message);
            gotoPage(request,response);
        }else{
            String message = "Something Went wrong in Add New Session User ";
            com.fertifa.app.Notifications.ErrorNotify(request,message);
            gotoPage(request,response);
        }
    }

    private Chat CreatChatSessionUserObject(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        return new Chat(CreateSessionUser(request,response));
    }

    private ChatSessionUsers CreateSessionUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        return new ChatSessionUsers(getSessionIdByToken(SessionToken),Integer.parseInt(getUserID(request,response)));
    }

    private int getSessionIdByToken(String sessionToken) throws SQLException {
        return chatController.getChatSessionIdByToken(sessionToken);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("com.fertifa.app.Messaging/MessagingCompany.jsp").forward(request, response);
    }


    private Chat CreatChatSessionObject(HttpServletRequest request, HttpServletResponse response) throws IOException, NoSuchAlgorithmException {
        SessionToken = createChatToken(request,response);
        //return new Chat(new ChatSession(SessionToken,new Timestamp(new Date().getTime()),Integer.parseInt(SessionType), Integer.parseInt(com.fertifa.app.Constances.SESSIONOPENSTRING)));
        return new Chat(CreateSessionObject());
    }

    */
/*private ChatSession CreateSessionObject() {
        return new ChatSession(SessionToken,new Timestamp(new Date().getTime()),com.fertifa.app.Constances.CATEGORYMESSAGE, com.fertifa.app.Constances.TYPESESSIONPENDING);
    }*//*


    private String createChatToken(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, IOException {
        String tokenWithId = createToken(request,response);
        return MDFive.getMD5(tokenWithId);
    }

    private String createToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return getTodaydate(request,response)+"-"+getUserID(request,response);

    }

    private String getUserID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return String.valueOf(usersController.getUserIdByEmail(String.valueOf(sessionController.getCompanySession(request))));
    }

    private String getTodaydate(HttpServletRequest request, HttpServletResponse response) {
        return String.valueOf(new Timestamp(new Date().getTime()));
    }

    private void getParameters(HttpServletRequest request) {
        if(request.getParameter("sessionTitle")!=null) {
            startingSeessionChat = request.getParameter("sessionTitle");
            System.out.println(startingSeessionChat);
        }
        if(request.getParameter("sessionType")!=null) {
            SessionType = request.getParameter("sessionType");
            SessionType="Something";
            System.out.println(startingSeessionChat);
        }
    }

    private void gotoLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionUserEmail = null;
        sessionController.DistroySession(request);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    private void UnicodeingSevlet(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

    }

    private void getSession(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        sessionController.getCompanyUsername(request);
        if (!sessionController.getCompanySession(request)) {
            gotoLoginPage(request, response);
        } else {
            SessionUserEmail = sessionController.getCompanyUsername(request);
            System.out.println(SessionUserEmail);
        }
    }
}
*/
