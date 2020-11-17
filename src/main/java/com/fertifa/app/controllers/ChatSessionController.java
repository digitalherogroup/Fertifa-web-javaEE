package com.fertifa.app.controllers;

import com.fertifa.app.dao.ChatDao;
import com.fertifa.app.models.ChatMessages;
import com.fertifa.app.models.ChatSession;
import com.fertifa.app.models.ChatSessionUsers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatSessionController {

    ChatDao chatDao = new ChatDao();


    public List<ChatSession> getAllSessionDetailsbyId(int companyId, int type) {
        return chatDao.getAllSessionDetailsbyId(companyId,type);
    }

    public List<ChatSession> getAllByToken(String tokenId) {
        try {
            return chatDao.getAllByToken(tokenId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public int CreateSession(ChatSession chatSession) throws SQLException {
        return chatDao.CreateSession(chatSession);
    }

    public int CreatSessionUserId(ChatSessionUsers chatSessionUsers) throws SQLException {
        return chatDao.CreatSessionUserId(chatSessionUsers);
    }

    public List<ChatSessionUsers> getAllSessionUsers(int chatSessionUsersId) {
        return chatDao.getAllSessionUsers(chatSessionUsersId);
    }

    public int getSessionUsers(int chatUserId, int chatSessionID) throws SQLException {
        return chatDao.getSessionUsers(chatUserId,chatSessionID);
    }

    public int CreateMessage(ChatMessages chatMessages) throws SQLException {
        return chatDao.CreateMessage(chatMessages);
    }


    public int AddNewSeesionChat(ChatSession chatMessages) throws SQLException {
        return chatDao.AddNewSeesionChat(chatMessages);
    }

    public int CloseSession(String chatSessionUsers) throws SQLException {
        return chatDao.CloseSession(chatSessionUsers);
    }


    public List<ChatSession> getAllSessionDetailsByType(int categoryrequest) {
        return chatDao.getAllSessionDetailsByType(categoryrequest);
    }

    public List<ChatSession> getAllrequests() {
        return chatDao.getAllrequests();
    }

    public List<ChatSession> GetAllByTwoDates(int companyId, String dateFrom, String dateto) throws SQLException {
        return chatDao.GetAllByTwoDates(companyId,dateFrom,dateto);
    }

    public int updateImportanceForSession(String type, String chatSessionId) throws SQLException {
        return chatDao.updateImportaneForSession(type,chatSessionId);
    }

    public Long getLastId() throws SQLException {
        return chatDao.getLastId();
    }
}
