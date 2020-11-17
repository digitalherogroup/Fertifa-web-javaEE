package com.fertifa.app.controllers;

import com.fertifa.app.dao.ChatDao;
import com.fertifa.app.models.Chat;
import com.fertifa.app.services.ChatService;

import java.sql.SQLException;

public class ChatController implements ChatService {

    private ChatDao chatDao = new ChatDao();

    public int AddNewChatSession(Chat chat) throws SQLException {
        return chatDao.StartChatSession(chat);
    }

    @Override
    public int getChatSessionIdByToken(String sessionToken) throws SQLException {
        return chatDao.getChatSessionIdByToken(sessionToken);
    }

    @Override
    public int AddNewChatSessionUser(Chat chat) throws SQLException {
        return chatDao.AddNewChatSessionUser(chat);
    }
}
