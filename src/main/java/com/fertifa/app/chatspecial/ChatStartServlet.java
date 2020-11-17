package com.fertifa.app.chatspecial;


import com.fertifa.app.com.beautyit.com.messanger.ChatStart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/Start/Chat")
public class ChatStartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        startChat(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        startChat(request, response);
    }

    private void startChat(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String chatId = "1";

      ChatStart.startChat(chatId);
        String url = "http://localhost:9090/api/v1/1";
        response.sendRedirect(url);
       // request.getRequestDispatcher(url).forward(request, response);
    }
}
