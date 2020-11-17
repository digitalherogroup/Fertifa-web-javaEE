package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.HealthHistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HealthHistoryDao {

    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    public int save(HealthHistory healthHistory) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `healthhistory`" +
                    "(`id`, `user_id`,`content`,`created_date`) "
                    + "VALUES (Default,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmenthealthHistory(healthHistory, statment);
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

    private void setStatmenthealthHistory(HealthHistory healthHistory, PreparedStatement statment) throws SQLException {
        statment.setInt(1, healthHistory.getUser_id());
        statment.setString(2, healthHistory.getContent());
        statment.setTimestamp(3,new Timestamp(new Date().getTime()));
    }

    public List<HealthHistory> getHistoryById(int userId) throws SQLException {
        List<HealthHistory> packagesList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *,  DATE_FORMAT( created_date, '%d/%m/%y' ) healthhistory_date  FROM `healthhistory` WHERE `user_id`=" + userId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfHealthHistory(set, packagesList);

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
        return packagesList;
    }

    private void ListOfHealthHistory(ResultSet set, List<HealthHistory> packagesList) throws SQLException {
        HealthHistory healthHistory = null;
        while (set.next()) {
            healthHistory = new HealthHistory();
            healthHistory.setId(set.getInt("id"));
            healthHistory.setUser_id(set.getInt("user_id"));
            healthHistory.setContent(set.getString("content"));
            healthHistory.setCreatedDate(set.getString("healthhistory_date"));
            packagesList.add(healthHistory);
        }
    }

    public int updatesave(HealthHistory healthHistory, int userId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `healthhistory`  " +
                    "SET `content`=? WHERE `user_id`=" + userId;
            statment = connection.prepareStatement(sql);
            statment.setString(1, healthHistory.getContent());

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
}
