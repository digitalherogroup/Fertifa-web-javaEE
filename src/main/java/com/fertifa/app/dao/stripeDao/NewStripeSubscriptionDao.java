package com.fertifa.app.dao.stripeDao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.stripe.NewStripeSubscriptionResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class NewStripeSubscriptionDao {
    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
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

    public int save(NewStripeSubscriptionResponse newStripeSubscriptionResponse) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `stripe-subscription`" +
                    "(`id`, `subscription_id`,`price`,`status`," +
                    "`billing_cycle`,`payment_cycle`,`latest_invoice`,`creation_date`,`customer_id`,`plan`,`product`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentNewSubscrption(newStripeSubscriptionResponse, statment);
            rowsAffected = statment.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closingConnections(connection,statment,null);
        }
        return rowsAffected;

    }

    private void setStatmentNewSubscrption(NewStripeSubscriptionResponse newStripeSubscriptionResponse, PreparedStatement statment) throws SQLException {
        statment.setString(1, newStripeSubscriptionResponse.getData().getId());
        statment.setInt(2, newStripeSubscriptionResponse.getPrice());
        statment.setString(3, newStripeSubscriptionResponse.getData().getStatus());
        statment.setString(4, String.valueOf(newStripeSubscriptionResponse.getData().getBillingCycleAnchor()));
        statment.setString(5, newStripeSubscriptionResponse.getData().getPlan().getInterval());
        statment.setString(6, newStripeSubscriptionResponse.getData().getLatestInvoice().getId());
        statment.setString(7, new Timestamp(new Date().getTime()).toString());
        statment.setString(8, newStripeSubscriptionResponse.getData().getCustomer().getId());
        statment.setString(9,newStripeSubscriptionResponse.getData().getPlan().getId());
        statment.setString(10,newStripeSubscriptionResponse.getData().getPlan().getProduct().getId());
    }
}
