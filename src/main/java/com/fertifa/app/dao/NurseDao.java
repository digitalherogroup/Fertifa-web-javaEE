package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class NurseDao {
    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

   /* public boolean save(com.fertifa.app.Partners newPartnerWithoutImage) {
       *//* int rowsAffected = 0;
        com.fertifa.app.Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `admins`" +
                    "(`id`, `firstname`, `createddate`, ``) "
                    + "VALUES (Default,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentFoSavePartner(newPartner, statment);
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

        return rowsAffected;*//*
    }*/
}
