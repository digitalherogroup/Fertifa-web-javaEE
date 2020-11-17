package com.fertifa.app.dao;


import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.models.Chat;
import com.fertifa.app.models.ChatMessages;
import com.fertifa.app.models.ChatSession;
import com.fertifa.app.models.ChatSessionUsers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatDao {

    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    public int StartChatSession(Chat chat) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `sessions`" +
                    "(`id`, `session_token`,`creation_date`,`type`,`status`) "
                    + "VALUES (Default,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentChatSession(chat, statment);
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

    private void setStatmentChatSession(Chat chat, PreparedStatement statment) throws SQLException {
        statment.setString(1, chat.getChatSession().getChatSessionToken());
        statment.setTimestamp(2, chat.getChatSession().getChatCreationDate());
        statment.setInt(3, chat.getChatSession().getChatSessionType());
        statment.setInt(4, chat.getChatSession().getChatSessionStatus());
    }

    public int getChatSessionIdByToken(String sessionToken) throws SQLException {
        int SessionId = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `sessions` WHERE `session_token`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, sessionToken);
            set = statment.executeQuery();
            while (set.next()) {
                SessionId = set.getInt("id");
            }

        } catch (SQLException exception) {
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
        return SessionId;
    }

    public int AddNewChatSessionUser(Chat chat) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `session_users`" +
                    "(`id`, `session_id`,`user_id`) "
                    + "VALUES (Default,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentChatSessionUser(chat, statment);
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

    private void setStatmentChatSessionUser(Chat chat, PreparedStatement statment) throws SQLException {
        statment.setInt(1, chat.getChatSessionUsers().getChatSessionID());
        statment.setInt(2, chat.getChatSessionUsers().getChatUserId());
    }

    public List<ChatSession> getAllSessionDetailsbyId(int CompanId, int type) {
        List<ChatSession> chatSessionsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT sessions.*, users.id as u_id, users.firstname,users.lastname,users.company,users.companyid, DATE_FORMAT(sessions.creation_date, '%d-%m-%Y') StringDate  FROM `sessions` " +
                    "INNER JOIN users on (sessions.from_id = users.id and sessions.from_id )  WHERE `type`= " + type + " AND (`from_id`=" + CompanId + " OR `to_id`=" + CompanId + ") ORDER BY sessions.creation_date DESC ";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            chatSessionListStatment(set, chatSessionsList);

        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return chatSessionsList;
    }

    public List<ChatSession> getAllSessionDetailsByType(int type) {
        List<ChatSession> chatSessionsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT sessions.*, users.id as u_id, users.firstname,users.lastname,users.company,users.companyid, DATE_FORMAT(sessions.creation_date, '%d-%m-%Y') StringDate" +
                    " FROM sessions " +
                    " INNER JOIN users on (sessions.from_id = users.id and sessions.from_id > 1) or (sessions.to_id = users.id and sessions.to_id > 1)\n" +
                    " Where type = " + type + " order by sessions.creation_date desc; ";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            chatSessionListStatment(set, chatSessionsList);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return chatSessionsList;
    }

    private void chatSession(ResultSet set, List<ChatSession> chatSessionsList) throws SQLException {
        ChatSession chatSession = null;
        while (set.next()) {
            chatSession = new ChatSession();

            chatSession.setChatSessionId(set.getInt("id"));
            chatSession.setChatSessionToken(set.getString("session_token"));
            chatSession.setChatSessionType(set.getInt("type"));
            chatSession.setChatSessionStatus(set.getInt("status"));
            chatSession.setChatFromId(set.getInt("from_id"));
            chatSession.setChatToId(set.getInt("to_id"));
            chatSession.setChatSessionName(set.getString("session_name"));
            //chatSession.setStringDate(set.getString("StringDate"));
            chatSession.setChatClosingDate(set.getTimestamp("close_date"));
            chatSession.setImportant_for_user(set.getInt("important_for_user"));
            chatSession.setImportant_for_admin(set.getInt("important_for_admin"));
            //chatSession.setFirstName(set.getString("firstname"));
            //chatSession.setLasetName(set.getString("lastname"));
            //chatSession.setCompanyName(set.getString("company"));
            chatSessionsList.add(chatSession);

        }
    }

    private void chatSessionListStatment(ResultSet set, List<ChatSession> chatSessionsList) throws SQLException {
        ChatSession chatSession = null;
        while (set.next()) {
            chatSession = new ChatSession();
            chatSession.setChatSessionId(set.getInt("id"));
            chatSession.setUid(set.getInt("u_id"));
            chatSession.setChatSessionToken(set.getString("session_token"));
            chatSession.setChatSessionType(set.getInt("type"));
            chatSession.setChatSessionStatus(set.getInt("status"));
            chatSession.setChatFromId(set.getInt("from_id"));
            chatSession.setChatToId(set.getInt("to_id"));
            chatSession.setChatSessionName(set.getString("session_name"));
            chatSession.setStringDate(set.getString("StringDate"));
            chatSession.setChatClosingDate(set.getTimestamp("close_date"));
            chatSession.setImportant_for_user(set.getInt("important_for_user"));
            chatSession.setImportant_for_admin(set.getInt("important_for_admin"));
            chatSession.setFirstName(set.getString("firstname"));
            chatSession.setLasetName(set.getString("lastname"));
            chatSession.setCompanyName(set.getString("company"));
            chatSessionsList.add(chatSession);

        }
    }

    public List<ChatSession> getAllByToken(String tokenId) throws SQLException {
        List<ChatSession> chatSessionsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *, users.id as u_id,users.firstname,users.lastname,users.company, DATE_FORMAT(sessions.creation_date, '%d-%m-%Y') StringDate " +
                    "FROM `sessions`" +
                    "INNER JOIN users on (sessions.from_id = users.id )" +
                    "   WHERE `session_token`= '" + tokenId + "'";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            chatSessionListStatment(set, chatSessionsList);

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
        return chatSessionsList;
    }

    public List<ChatMessages> getAllMessageByToken(String tokenId) {
        List<ChatMessages> chatSessionsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `messages` WHERE `session_token` LIKE '%" + tokenId + "%'";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            chatMessageListStatment(set, chatSessionsList);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return chatSessionsList;
    }

    private void chatMessageListStatment(ResultSet set, List<ChatMessages> chatSessionsList) throws SQLException {
        ChatMessages chatMessages = null;
        while (set.next()) {
            chatMessages = new ChatMessages();
            chatMessages.setChatMessageId(set.getInt("id"));
            chatMessages.setChatSessionId(set.getInt("session_id"));
            chatMessages.setChatTokenId(set.getString("session_token"));
            chatMessages.setChatFrom(set.getInt("from_id"));
            chatMessages.setChatTo(set.getInt("to_id"));
            chatMessages.setChatCategoryId(set.getInt("category_id"));
            chatMessages.setChatType(set.getInt("type"));
            chatMessages.setChatBody(set.getString("body"));
            chatMessages.setChatMessageStatus(set.getInt("status"));
            chatMessages.setChatCreationDate(set.getTimestamp("creation_date"));
            chatSessionsList.add(chatMessages);
        }
    }

    public int CreateSession(ChatSession chatSession) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `sessions`" +
                    "(`id`, `session_token`,`type`,`from_id`,`to_id`,`session_name`,`status`,`creation_date`,`updatedAt`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            CreatSessionStatment(chatSession, statment);
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

    private void CreatSessionStatment(ChatSession chatSession, PreparedStatement statment) throws SQLException {
        statment.setString(1, chatSession.getChatSessionToken());
        statment.setInt(2, chatSession.getChatSessionType());
        statment.setInt(3, chatSession.getChatFromId());
        statment.setInt(4, chatSession.getChatToId());
        statment.setString(5, chatSession.getChatSessionName());
        statment.setInt(6, 1);
        statment.setTimestamp(7, new Timestamp(new Date().getTime()));
        statment.setTimestamp(8, new Timestamp(new Date().getTime()));
    }

    public int CreatSessionUserId(ChatSessionUsers chatSessionUsers) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `session_users`" +
                    "(`id`, `session_id`,`user_id`) "
                    + "VALUES (Default,?,?)";
            statment = connection.prepareStatement(insertQuery);
            CreatSessionUsersStatment(chatSessionUsers, statment);
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

    private void CreatSessionUsersStatment(ChatSessionUsers chatSessionUsers, PreparedStatement statment) throws SQLException {
        statment.setInt(1, chatSessionUsers.getChatSessionID());
        statment.setInt(2, chatSessionUsers.getChatUserId());
    }

    public List<ChatSessionUsers> getAllSessionUsers(int chatSessionUsersId) {
        List<ChatSessionUsers> chatSessionsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `session_users` WHERE `id`=" + chatSessionUsersId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            chatSessionUsersListStatment(set, chatSessionsList);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return chatSessionsList;
    }

    private void chatSessionUsersListStatment(ResultSet set, List<ChatSessionUsers> chatSessionsList) throws SQLException {
        ChatSessionUsers chatSessionUsers = null;
        while (set.next()) {
            chatSessionUsers = new ChatSessionUsers();
            chatSessionUsers.setChatSessionUsersId(set.getInt("id"));
            chatSessionUsers.setChatSessionID(set.getInt("session_id"));
            chatSessionUsers.setChatUserId(set.getInt("user_id"));
            chatSessionsList.add(chatSessionUsers);
        }
    }

    public int getSessionUsers(int chatUserId, int chatSessionID) throws SQLException {
        int SessionId = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `session_users` WHERE `session_id`=?  AND `user_id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, chatSessionID);
            statment.setInt(2, chatUserId);
            set = statment.executeQuery();
            while (set.next()) {
                SessionId = set.getInt("id");
            }

        } catch (SQLException exception) {
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
        return SessionId;
    }


    public int CreateMessage(ChatMessages chatMessages) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;

        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `messages` (`id`, `session_token`,`from_id`,`to_id`,`type`,`body`,`creation_date`) " +
                    "VALUES (Default,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            CreatMessageStatment(chatMessages, statment);
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

    private void CreatMessageStatment(ChatMessages chatMessages, PreparedStatement statment) throws SQLException {

        statment.setString(1, chatMessages.getChatTokenId());
        statment.setInt(2, chatMessages.getChatFrom());
        statment.setInt(3, chatMessages.getChatTo());
        statment.setInt(4, chatMessages.getChatType());
        statment.setString(5, chatMessages.getChatBody());
        statment.setTimestamp(6, new Timestamp(new Date().getTime()));
    }

    public List<ChatMessages> getMessageLastInsertID(int from, int to, String chatBody) {
        List<ChatMessages> chatSessionsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `messages` WHERE `body`=" + chatBody + " AND `from_id`=" + from + " AND `to_id`=" + to;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            chatMessageListStatmentJson(set, chatSessionsList);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return chatSessionsList;
    }

    private void chatMessageListStatmentJson(ResultSet set, List<ChatMessages> chatSessionsList) throws SQLException {
        ChatMessages chatMessages = null;
        while (set.next()) {
            chatMessages = new ChatMessages();
            chatMessages.setChatMessageId(set.getInt("id"));
            chatMessages.setChatSessionId(set.getInt("session_id"));
            chatMessages.setChatTokenId(set.getString("session_token"));
            chatMessages.setChatFrom(set.getInt("from_id"));
            chatMessages.setChatTo(set.getInt("to_id"));
            chatMessages.setChatCategoryId(set.getInt("category_id"));
            chatMessages.setChatType(set.getInt("type"));
            chatMessages.setChatBody(set.getString("body"));
            chatMessages.setChatCreationDate(set.getTimestamp("creation_date"));
            chatSessionsList.add(chatMessages);
        }
    }


    public int AddNewSeesionChat(ChatSession chatMessages) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `sessions`" +
                    "(`id`, `session_token`,`type`,`status`,`creation_date`,`from_id`,`to_id`,`session_name`) "
                    + "VALUES (Default,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentSessionAdd(chatMessages, statment);
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

    private void setStatmentSessionAdd(ChatSession chatMessages, PreparedStatement statment) throws SQLException {
        statment.setString(1, chatMessages.getChatSessionToken());
        statment.setInt(2, chatMessages.getChatSessionType());
        statment.setInt(3, chatMessages.getChatSessionStatus());
        statment.setTimestamp(4, new Timestamp(new Date().getTime()));
        statment.setInt(5, chatMessages.getChatFromId());
        statment.setInt(6, chatMessages.getChatToId());
        statment.setString(7, chatMessages.getChatSessionName());

    }

    public int CloseSession(String token) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `sessions`  " +
                    "SET  `status`=?  " +
                    "WHERE `session_token`='" + token + "'";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, Constances.TYPESESSIONCLOSEN);

            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
        }
        return rowsUpdated;
    }

    public List<ChatSession> getAllrequests() {
        List<ChatSession> chatSessionsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `sessions`  WHERE `type`=" + 2;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            chatSession(set, chatSessionsList);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return chatSessionsList;
    }

    public List<ChatSession> GetAllByTwoDates(int companyId, String dateFrom, String dateto) throws SQLException {
        String sql = "";
        List<ChatSession> usersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();

            sql = "SELECT * from `feedquestion` where status <> 9000";

            if (companyId > 0) {
                sql += " and `companyid`=" + companyId;

            }

            if (!dateFrom.isEmpty()) {
                sql += " and `creation_date` between" + "\"" + dateFrom + "\"";
            }

            if (!dateto.isEmpty()) {
                sql += " and " + "\"" + dateto + "\"";
            }
            System.out.println("sql" + sql);
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            chatSessionListStatment(set, usersList);

        } catch (SQLException exception) {
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
        return usersList;
    }

    /*
    Updating the importance of Session
     */
    public int updateImportaneForSession(String type, String chatSessionId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `sessions`";

            if (type.equals("admin")) {
                sql += " SET `important_for_admin`= 1 - important_for_admin";
            } else {
                sql += " SET `important_for_user`= 1 - important_for_user";
            }
            sql += " WHERE `session_token`='" + chatSessionId + "'";
            statment = connection.prepareStatement(sql);

            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
        }
        return rowsUpdated;

    }

    public int UpdateSecretDate(Timestamp timestamp, ChatMessages chatMessages) throws SQLException {
        int updateID = 0;
        System.out.println();
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "UPDATE `sessions` SET `creation_date`='" + timestamp + "', `updatedAt`='" + timestamp + "' WHERE `session_token`='" + chatMessages.getChatTokenId() + "'";
            statment = connection.prepareStatement(sql);
            updateID = statment.executeUpdate();
            if (updateID > 0) {
                System.out.println("A Date Updated was updated successfully!");
            }

        } catch (SQLException exception) {
            System.out.println("sqlException in Application in UpdateSecretDate Section  : " + exception);
            exception.printStackTrace();
        } finally {
            if (statment != null) {
                statment.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return updateID;
    }

    public int UpdateSecretDatestart(Timestamp timestamp, String chatSessionToken) throws SQLException {
        int updateID = 0;
        System.out.println();
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "UPDATE `sessions` SET `creation_date`='" + timestamp + "', `updatedAt`='" + timestamp + "' WHERE `session_token`='" + chatSessionToken + "'";
            statment = connection.prepareStatement(sql);
            updateID = statment.executeUpdate();
            if (updateID > 0) {
                System.out.println("A Date Updated was updated successfully!");
            }

        } catch (SQLException exception) {
            System.out.println("sqlException in Application in UpdateSecretDate Section  : " + exception);
            exception.printStackTrace();
        } finally {
            if (statment != null) {
                statment.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return updateID;
    }

    public int getRevievedByToken(String chatTokenId) throws SQLException {
        int revieved = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();

            String sql = "select MAX(id), recived from `messages` where `session_token`='" + chatTokenId + "' AND `recived` = '0'";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery(sql);
            while (set.next()) {
                revieved = set.getInt("recived");
            }

        } catch (SQLException exception) {
            System.out.println("sqlException in Application in UpdateSecretDate Section  : " + exception);
            exception.printStackTrace();
        } finally {
            if (statment != null) {
                statment.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return revieved;
    }

    public Long getLastId() throws SQLException {
        Long sessionId=0l;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT id FROM sessions WHERE id=( SELECT max(id) FROM sessions )";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                sessionId = Long.parseLong(String.valueOf(set.getInt("id")));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            if (statment != null) {
                statment.close();
            }
            if (connection != null) {
                connection.close();
            }if(set != null){
                set.close();
            }
        }
        return sessionId;

    }
}
