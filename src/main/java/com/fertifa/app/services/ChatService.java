package com.fertifa.app.services;

import com.fertifa.app.models.Chat;

import java.sql.SQLException;

public interface ChatService {
    int AddNewChatSession(Chat chat) throws SQLException;

    int getChatSessionIdByToken(String sessionToken) throws SQLException;

    int AddNewChatSessionUser(Chat chat) throws SQLException;


}
