package com.fertifa.app.controllers;

import com.fertifa.app.dao.MessagingDao;
import com.fertifa.app.models.ChatMessages;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatMessageController {

    MessagingDao messgaingDao = new MessagingDao();

    public List<ChatMessages> getAllByToken(String tokenId) {
        try {
            return messgaingDao.getAllByToken(tokenId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
