package com.fertifa.app.company.Chat;

import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.ChatSessionController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.ChatSession;
import com.fertifa.app.models.Users;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employer/chat")
public class Messaging extends com.fertifa.app.baseUrl.BaseUrl {
    private int CompanyId = 0;
    private UsersController usersController = new UsersController();
    List<ChatSession> sessionChatlistRequest = new ArrayList<>();
    List<ChatSession> sessionChatlistNotification = new ArrayList<>();
    List<ChatSession> sessionChatlistString = new ArrayList<>();
    private ChatSessionController chatSessionController = new ChatSessionController();
    private Users users = new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA,getEmployerId(request,response));
            messaging(request, response);
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

    private void messaging(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        getUserSessionsChat();
        SetAttributes(request);
        gotoMasterAdminPage(request, response);

    }


    private void getUserSessionsChat() {
        sessionChatlistRequest = new ArrayList<>();
        sessionChatlistNotification = new ArrayList<>();
        sessionChatlistString = new ArrayList<>();

        sessionChatlistNotification = chatSessionController.getAllSessionDetailsbyId(users.getId(), Constances.CATEGORYREQUEST);
        sessionChatlistRequest = chatSessionController.getAllSessionDetailsbyId(users.getId(), Constances.CATEGORYNOTIFICATION);
        sessionChatlistString = chatSessionController.getAllSessionDetailsbyId(users.getId(), Constances.CATEGORYMESSAGE);
    }

    private void gotoMasterAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url =request.getServletPath()+"/Messaging.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void SetAttributes(HttpServletRequest request) {
        request.setAttribute("Messages", "active");
        request.setAttribute("ChatSessionListRequest", sessionChatlistRequest);
        request.setAttribute("ChatSessionListNotification", sessionChatlistNotification);
        request.setAttribute("ChatSessionListString", sessionChatlistString);
        request.setAttribute("EmployerObject", users);
    }


}
