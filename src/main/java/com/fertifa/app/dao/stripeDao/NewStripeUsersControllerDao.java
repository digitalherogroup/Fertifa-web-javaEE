package com.fertifa.app.dao.stripeDao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.stripe.NewStripeUsersResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

public class NewStripeUsersControllerDao {

    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    public int save(NewStripeUsersResponse newStripeUsersResponse) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `stripe-customer`" +
                    "(`id`,`stripeUserUrl`,`stripeBalance`,`creationDate`,`customerId`,`user_id`,`city`,`address`,`country`,`zipcode`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentNewCustomer(newStripeUsersResponse, statment);
            rowsAffected = statment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closingConnections(connection, statment, null);
        }
        return rowsAffected;

    }

    private void setStatmentNewCustomer(NewStripeUsersResponse newStripeUsersResponse, PreparedStatement statment) throws SQLException {
        statment.setString(1, newStripeUsersResponse.getData().getSources().getUrl());
        statment.setFloat(2, newStripeUsersResponse.getData().getBalance());
        statment.setString(3, new Timestamp(new Date().getTime()).toString());
        statment.setString(4, newStripeUsersResponse.getData().getId());
        statment.setInt(5, newStripeUsersResponse.getUserId());
        statment.setString(6, newStripeUsersResponse.getCity());
        statment.setString(7, newStripeUsersResponse.getAddress());
        statment.setString(8, newStripeUsersResponse.getCountry());
        statment.setString(9, newStripeUsersResponse.getZipCode());
    }

    private void closingConnections(Connection connection, PreparedStatement statment, ResultSet set) throws SQLException {
        if (connection != null) {
            connection.close();
        }
        if (set != null) {
            set.close();
        }
        if (statment != null) {
            statment.close();
        }
    }

    public NewStripeUsersResponse getStripUserObjectByUserId(int id) throws SQLException {
        NewStripeUsersResponse newStripeUsersResponse = new NewStripeUsersResponse();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `stripe-customer` where `user_id`=" + id;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            if (set.next()) {
                newStripeUsersResponse = UserObject(set);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closingConnections(connection, statment, set);
        }
        return newStripeUsersResponse;
    }

    private NewStripeUsersResponse UserObject(ResultSet set) throws SQLException {
        return NewStripeUsersResponse
                .builder()
                .userId(set.getInt("user_id"))
                .stripeUserUrl(Arrays.asList(set.getString("stripeUserUrl")))
                .stripeBalance(set.getInt("stripeBalance"))
                .customerId(set.getString("customerId"))
                .creationDate(set.getString("creationDate"))
                .address(set.getString("address"))
                .city(set.getString("city"))
                .country(set.getString("country"))
                .zipCode(set.getString("zipcode"))
                .build();
    }

    public int deleteStripeUSer(String customerId) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "DELETE FROM `stripe-customer` WHERE  `customerId`=" + customerId;
            statment = connection.prepareStatement(sql);

            rowsDeleted = statment.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A Message was deleted successfully!");
            }

        } catch (SQLException exception) {
            System.out.println("sqlException in Application in CATEGORY DELETE  Section  : " + exception);
            exception.printStackTrace();
        } finally {
            closingConnections(connection, statment, null);
        }
        return rowsDeleted;
    }
}
