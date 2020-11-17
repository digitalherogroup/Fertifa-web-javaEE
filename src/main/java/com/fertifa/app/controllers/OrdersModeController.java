package com.fertifa.app.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdersModeController {

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

    public int updateLocalOrder(String invoiceId,String paymentId,String priceId,String productId) throws SQLException {
        int updateID = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = getConnectionToDatabase();
            String sql = "UPDATE `orders` SET `payment_id`=?," +
                    " `invoice_id`=?, `status`=?" +
                    " WHERE `price_id`=? and `product_id`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1,paymentId);
            statment.setString(2,invoiceId);
            statment.setInt(3,1);
            statment.setString(4,priceId);
            statment.setString(5,productId);

            updateID = statment.executeUpdate();
            if (updateID > 0) {
                System.out.println("A shoppingCart was Updated successfully!");
            }

        } catch (SQLException exception) {
            System.out.println("sqlException in Application in CATEGORY DELETE  Section  : " + exception);
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
}

