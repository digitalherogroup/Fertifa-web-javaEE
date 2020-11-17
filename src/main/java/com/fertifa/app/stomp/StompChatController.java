package com.fertifa.app.stomp;


import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@Service
@Log4j2

public class StompChatController {
    private GsonConverter gsonConverter = new GsonConverter(new Gson());

    public StompChatController(GsonConverter gsonConverter) {
        this.gsonConverter = gsonConverter;
    }

    public StompChatController() {
    }

    public BaseResponse<?> addNewBookingChat(Map<String, Object> message, int userId, int adminId,Long documentId) {
        Map<String, Object> body;
        MessagingStompChat chatData = MessagingStompChat
                .builder()
                .messageFrom(String.valueOf(userId))
                .messageTo(String.valueOf(adminId))
                .messageBody((String) message.get("message"))
                .received(0L)
                .documentId(documentId)
                .messageType("BOOKING")
                .created(new Timestamp(new Date().getTime()))
                .updated(new Timestamp(new Date().getTime()))
                .build();

        ResponseEntity<BaseResponse<?>> saveChatResponse = save(chatData);
        body = gsonConverter.convertObjectToMapObject(saveChatResponse);
        return new BaseResponse<>(new Date(),HttpStatus.ACCEPTED,HttpStatus.ACCEPTED.value(), body);
    }

    public ResponseEntity<BaseResponse<?>> save(MessagingStompChat chatData) {
        if (null == chatData) throw new NullPointerException();
        BaseResponse<?> response = saveAction(chatData);
        return new ResponseEntity<>(response, response.getStatus());
    }

    public BaseResponse<?> saveAction(MessagingStompChat messageModel) {
        int body=0;
        try {
            saveByEE(messageModel);
            int id = getchatlastid();
            body = id;
        } catch (Exception e) {
            return new BaseResponse<>(new Date(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
        return new BaseResponse<>(new Date(), HttpStatus.ACCEPTED, HttpStatus.ACCEPTED.value(), body);
    }

    private int getchatlastid() throws SQLException {
        int chatId = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String insertQuery = "SELECT id FROM stomp_chat WHERE id=( SELECT max(id) FROM stomp_chat )";
            statment = connection.prepareStatement(insertQuery);
            set = statment.executeQuery();
            while (set.next()) {
                chatId = set.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
        }
        return chatId;
    }

    private int saveByEE(MessagingStompChat messageModel) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `stomp_chat`" +
                    "(`id`, `created`, `updated`, `message_body`,`message_from`,`message_to`,`message_type`,`received`,`document_id`)"
                    + "VALUES (Default,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            addBooking(messageModel, statment);
            rowsAffected = statment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
        }
        return rowsAffected;
    }

    private void addBooking(MessagingStompChat messageModel, PreparedStatement statment) throws SQLException {
        statment.setString(1, messageModel.getCreated().toString());
        statment.setString(2, messageModel.getUpdated().toString());
        statment.setString(3, messageModel.getMessageBody());
        statment.setString(4, messageModel.getMessageFrom());
        statment.setString(5, messageModel.getMessageTo());
        statment.setString(6, messageModel.getMessageType());
        statment.setInt(7,0);
        if(messageModel.getDocumentId() != null) {
            statment.setLong(8, messageModel.getDocumentId());
        }else{
            statment.setLong(8, 0);
        }
    }


    public BaseResponse<?> addNewTestOrderChat(String JsonMessage, int userId, int adminId) {
        Map<String, Object> body;
        MessagingStompChat chatData = MessagingStompChat
                .builder()
                .messageFrom(String.valueOf(userId))
                .messageTo(String.valueOf(adminId))
                .messageBody(JsonMessage)
                .received(0L)
                .messageType("SHOP")
                .created(new Timestamp(new Date().getTime()))
                .updated(new Timestamp(new Date().getTime()))
                .build();

        ResponseEntity<BaseResponse<?>> saveChatResponse = save(chatData);
        body = gsonConverter.convertObjectToMapObject(saveChatResponse);

        return new BaseResponse<>(new Date(),HttpStatus.ACCEPTED,HttpStatus.ACCEPTED.value(), body);
    }

    public Connection getConnectionToDatabase() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://204.93.169.168:3306/fertifab_local_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT",
                    "fertifab_root",
                    "mnbvcxz00A!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
        }
        return connection;
    }

    private Connection ConnectToData() throws SQLException {
        return getConnectionToDatabase();
    }
}
