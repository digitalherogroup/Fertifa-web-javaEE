package com.fertifa.app.utils;

import com.fertifa.app.baseUrl.BaseUrl;
import com.fertifa.app.dao.ChatDao;
import com.fertifa.app.models.ChatMessages;

import java.sql.SQLException;
import java.sql.Timestamp;

public class ChangeDateOfSession extends BaseUrl {

    public void changeDateOfMessage(Timestamp timestamp, ChatMessages chatMessages) throws SQLException {
        ChatDao chatDao = new ChatDao();
        if(chatDao.UpdateSecretDate(timestamp,chatMessages) > 0){
            System.out.println("Done updating session");
        }else{
            System.out.println("changeDateOfMessage not updated");
        }
    }

    public void changeDateOfMessageDate(Timestamp timestamp, String chatSessionToken) throws SQLException {
        ChatDao  chatDao = new ChatDao();
        if(chatDao.UpdateSecretDatestart(timestamp,chatSessionToken) > 0){
            System.out.println("Done updating session");
        }else{
            System.out.println("changeDateOfMessage not updated");
        }
    }
}
