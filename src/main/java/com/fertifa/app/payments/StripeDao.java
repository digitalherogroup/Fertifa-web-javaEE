package com.fertifa.app.payments;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.models.ShoppingCart;
import com.fertifa.app.models.StripeUsers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StripeDao {

    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }


    public int save(User user, int userid, String stripeid) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `stripeusers`" +
                    "(`id`,`stripe_user_id`, `cardholderfirstname`, `cardholderlastname`, `cardholderemail`," +
                    "`cardholderphone`,`cardholderaddress`,`cardholdercity`,`cardholdercountry`,`cardholderstat`," +
                    "`cardholderpostcode`,`user_id`,`status`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            statment.setString(1, stripeid);
            statment.setString(2, user.getFirst_name());
            statment.setString(3, user.getLast_name());
            statment.setString(4, user.getEmail());
            statment.setString(5, user.getPhoneNumber());
            statment.setString(6, user.getAddress());
            statment.setString(7, user.getCity());
            statment.setString(8, user.getCountry());
            statment.setString(9, user.getState());
            statment.setString(10, user.getPostal_code());
            statment.setInt(11, userid);
            statment.setInt(12, Constances.STIPEUSERNEWSSTATUS);
            rowsAffected = statment.executeUpdate();
        } catch (Exception e) {
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

    public int saveOrder(User user, Order order, ShoppingCart shoppingCart, int companyid, int serviceid) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `shoppingcartfinal` " +
                    "(`id`, `stripe_order_amount`, `user_id`,`company_id`,`service_id`,`serviceName`," +
                    "`total_price`,`order_dates`," +
                    "`stripe_order_datecreated`,`cardholderfirstname`,`cardholderlastname`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            statment.setInt(1, order.getChargeAmountDollars());
            System.out.println("payment details " + order.getChargeAmountDollars());
            statment.setInt(2, user.getFertifaUser_id());
            System.out.println("payment details " + user.getFertifaUser_id());
            statment.setInt(3, companyid);
            System.out.println("payment details " + companyid);
            statment.setInt(4, serviceid);
            System.out.println("payment details " + serviceid);
            statment.setString(5,shoppingCart.getServiceName());
            System.out.println("payment details " + shoppingCart.getServiceName());
            statment.setFloat(6,shoppingCart.getPrice());
            System.out.println("payment details " + shoppingCart.getPrice());
            statment.setString(7,shoppingCart.getOrder_date());
            System.out.println("payment details " + shoppingCart.getOrder_date());
            statment.setString(8,shoppingCart.getOrder_date());
            System.out.println("payment details " + shoppingCart.getOrder_date());
            statment.setString(9,user.getFirst_name());
            System.out.println("payment details " + user.getFirst_name());
            statment.setString(10,user.getLast_name());
            System.out.println("payment details " + user.getLast_name());

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

    public String getStripIdByUserId(int userId) {
        String rowsUpdated = "";
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `stripeusers` WHERE `user_id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, userId);
            set = statment.executeQuery();
            while (set.next()) {
                rowsUpdated = set.getString("stripe_user_id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return rowsUpdated;
    }

    public List<StripeUsers> getStripeUsersByStripe(String stripeId) {
        List<StripeUsers> stripeUsersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `stripeusers` WHERE `stripe_user_id`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, stripeId);
            set = statment.executeQuery();
            StripeUsers stripeUsers = null;
            while (set.next()) {
                stripeUsers = new StripeUsers();
                stripeUsers.setId(set.getInt("id"));
                stripeUsers.setStatus(set.getInt("status"));
                stripeUsers.setUser_id(set.getInt("user_id"));
                stripeUsers.setCardholderfirstname(set.getString("cardholderfirstname"));
                stripeUsers.setCardholderlastname(set.getString("cardholderlastname"));
                stripeUsers.setCardholderphone(set.getString("cardholderphone"));
                stripeUsers.setCardholderemail(set.getString("cardholderemail"));
                stripeUsers.setCardholderaddress(set.getString("cardholderaddress"));
                stripeUsers.setCardholdercity(set.getString("cardholderemail"));
                stripeUsers.setCardholdercountry(set.getString("cardholdercountry"));
                stripeUsers.setCardholderstat(set.getString("cardholderstat"));
                stripeUsers.setCardholderpostcode(set.getString("cardholderpostcode"));
                stripeUsers.setStripe_user_id(set.getString("stripe_user_id"));
                stripeUsersList.add(stripeUsers);

            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return stripeUsersList;
    }
}
