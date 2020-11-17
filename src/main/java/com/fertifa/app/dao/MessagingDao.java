package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.ChatMessages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessagingDao {

    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    public List<ChatMessages> getAllByToken(String tokenId) throws SQLException {
        List<ChatMessages> chatMessages = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT `id`, `session_token`, `from_id`, `to_id`, `category_id`, `type`, `body`, `creation_date`, DATE_FORMAT(creation_date, '%H:%i') DateString FROM `messages` WHERE `session_token`='" + tokenId + "' ORDER BY `creation_date` ASC";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            chatMessagingListStatment(set, chatMessages);

        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return chatMessages;
    }

    private void chatMessagingListStatment(ResultSet set, List<ChatMessages> chatMessages) throws SQLException {
        ChatMessages chatMessaging = null;
        while (set.next()) {
            chatMessaging = new ChatMessages();
            chatMessaging.setChatMessageId(set.getInt("id"));

            chatMessaging.setChatTokenId(set.getString("session_token"));
            chatMessaging.setChatFrom(set.getInt("from_id"));
            chatMessaging.setChatTo(set.getInt("to_id"));
            chatMessaging.setChatCategoryId(set.getInt("category_id"));
            chatMessaging.setChatType(set.getInt("type"));
            chatMessaging.setChatBody(set.getString("body"));
            chatMessaging.setChatCreationDate(set.getTimestamp("creation_date"));
            chatMessaging.setDateString(set.getString("DateString"));


            chatMessages.add(chatMessaging);

        }
    }
}
