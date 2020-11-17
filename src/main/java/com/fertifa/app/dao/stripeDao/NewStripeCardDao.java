package com.fertifa.app.dao.stripeDao;


import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.stripe.NewStripeCardResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewStripeCardDao {
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

    public int save(NewStripeCardResponse newStripeCardResponse) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `stripe-card`" +
                    "(`id`, `card_Id`,`last4_digits`,`customer_id`,`created_date`,`card_type`,`ex_month`,`ex_year`," +
                    "`name`,`default_card`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentNewCard(newStripeCardResponse, statment);
            rowsAffected = statment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closingConnections(connection, statment, null);
        }
        return rowsAffected;

    }

    private void setStatmentNewCard(NewStripeCardResponse newStripeCardResponse, PreparedStatement statment) throws SQLException {
        statment.setString(1, newStripeCardResponse.getData().getCard().getId());
        statment.setString(2, newStripeCardResponse.getData().getCard().getLast4());
        statment.setString(3, newStripeCardResponse.getCustomerId());
        statment.setString(4, new Timestamp(new Date().getTime()).toString());
        statment.setString(5, newStripeCardResponse.getData().getCard().getBrand());
        statment.setInt(6, newStripeCardResponse.getData().getCard().getExpMonth().intValue());
        statment.setInt(7, newStripeCardResponse.getData().getCard().getExpYear().intValue());
        statment.setString(8, newStripeCardResponse.getData().getCard().getName());
        statment.setInt(9, newStripeCardResponse.getDefaultCard());
    }

    public List<NewStripeCardResponse> findAllByCustomerId(String customerId) throws SQLException {
        List<NewStripeCardResponse> newStripeCardResponseArrayList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `stripe-card` where  `customer_id`='" + customerId + "'";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfCards(set, newStripeCardResponseArrayList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closingConnections(connection, statment, set);
        }
        return newStripeCardResponseArrayList;
    }

    private void ListOfCards(ResultSet set, List<NewStripeCardResponse> newStripeCardResponseArrayList) throws SQLException {
        NewStripeCardResponse newStripeCardResponse = null;
        while (set.next()) {
            newStripeCardResponse = new NewStripeCardResponse();
            newStripeCardResponse.setCardId(set.getString("card_Id"));
            newStripeCardResponse.setCardType(set.getString("card_type"));
            newStripeCardResponse.setCreatedDate(set.getString("created_date"));
            newStripeCardResponse.setLast4(set.getString("last4_digits"));
            newStripeCardResponse.setCustomerId(set.getString("customer_id"));
            newStripeCardResponse.setMonth(set.getInt("ex_month"));
            newStripeCardResponse.setYear(set.getInt("ex_year"));
            newStripeCardResponse.setUpdatedDate(set.getString("updated_date"));
            newStripeCardResponse.setName(set.getString("name"));
            newStripeCardResponse.setDefaultCard(set.getInt("default_card"));

            newStripeCardResponseArrayList.add(newStripeCardResponse);
        }

    }

    public int deleteCard(String cardId) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "DELETE FROM `stripe-card` WHERE `card_Id`='" + cardId + "'";
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

    public NewStripeCardResponse findCardById(String cardId) throws SQLException {
        NewStripeCardResponse newStripeCardResponse = new NewStripeCardResponse();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `stripe-card` where `card_Id`='" + cardId + "'";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            if (set.next()) {
                newStripeCardResponse = newStripeCardResponseObject(set);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closingConnections(connection, statment, null);
        }
        return newStripeCardResponse;

    }

    private NewStripeCardResponse newStripeCardResponseObject(ResultSet set) throws SQLException {
        return NewStripeCardResponse.builder()
                .cardId(set.getString("card_Id"))
                .cardType(set.getString("card_type"))
                .createdDate(set.getString("created_date"))
                .last4Digits(set.getString("last4_digits"))
                .month(set.getInt("ex_month"))
                .year(set.getInt("ex_year"))
                .customerId(set.getString("customer_id"))
                .name(set.getString("name"))
                .updatedDate(set.getString("updated_date"))
                .defaultCard(set.getInt("default_card"))
                .build();

    }

    public int updateNewStripeCard(NewStripeCardResponse newStripeCardResponse) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `stripe-card` SET `last4_digits`=?,`customer_id`=?,`message`=?,`code`=?,`updated_date`=?,`card_type`=?,`ex_month`=?,`ex_year`=?" +
                    " WHERE `card_Id`=" + newStripeCardResponse.getCardId();
            statment = connection.prepareStatement(sql);
            UpdateCard(newStripeCardResponse, statment);

            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closingConnections(connection, statment, null);
        }
        return rowsUpdated;
    }

    private void UpdateCard(NewStripeCardResponse newStripeCardResponse, PreparedStatement statment) throws SQLException {
        statment.setString(1, newStripeCardResponse.getLast4Digits());
        statment.setString(2, newStripeCardResponse.getCustomerId());
        statment.setString(3, newStripeCardResponse.getMessage());
        statment.setString(4, newStripeCardResponse.getCode());
        statment.setString(5, new Timestamp(new Date().getTime()).toString());
        statment.setString(6, newStripeCardResponse.getData().getCard().getBrand());
        statment.setInt(7, newStripeCardResponse.getMonth());
        statment.setInt(8, newStripeCardResponse.getYear());
    }

    public List<NewStripeCardResponse> findAllByCustomerIdStatusNull(String customerId) throws SQLException {
        List<NewStripeCardResponse> newStripeCardResponseArrayList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `stripe-card` where `default_card`= 0 AND `customer_id`='" + customerId + "'";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfCards(set, newStripeCardResponseArrayList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closingConnections(connection, statment, set);
        }
        return newStripeCardResponseArrayList;
    }

    public NewStripeCardResponse getTheDefaultCard(String customerId) throws SQLException {
        NewStripeCardResponse newStripeCardResponse = new NewStripeCardResponse();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `stripe-card` where `default_card`=1 AND `customer_id`='" + customerId + "'";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            if (set.next()) {
                newStripeCardResponse = newStripeCardResponseObject(set);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closingConnections(connection, statment, null);
        }
        return newStripeCardResponse;
    }

    public int updateStatus(String customerId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `stripe-card` SET `default_card`=?" +
                    " WHERE `customer_id`='" + customerId +"'";
            statment = connection.prepareStatement(sql);
            UpdateCardStatus( statment);

            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closingConnections(connection, statment, null);
        }
        return rowsUpdated;
    }

    private void UpdateCardStatus(PreparedStatement statment) throws SQLException {
        statment.setInt(1, 0);
    }

    public int updateNewStripeCardStatus(NewStripeCardResponse newStripeCardResponse) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `stripe-card` SET `default_card`=?" +
                    " WHERE `card_Id`='" + newStripeCardResponse.getCardId()+"'";
            statment = connection.prepareStatement(sql);
            UpdateCardStatusDefault(newStripeCardResponse, statment);

            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closingConnections(connection, statment, null);
        }
        return rowsUpdated;
    }

    private void UpdateCardStatusDefault(NewStripeCardResponse newStripeCardResponse, PreparedStatement statment) throws SQLException {
        statment.setInt(1, newStripeCardResponse.getDefaultCard());
    }
}
