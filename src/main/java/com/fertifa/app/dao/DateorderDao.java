package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.DateOrder;
import com.fertifa.app.products.model.products.Products;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DateorderDao {

    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }


    public int AddNewDateOrder(DateOrder dateOrder) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `dateorder`" +
                    "(`id`, `date`,`order_id`) "
                    + "VALUES (Default,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentOrderDateAdd(dateOrder, statment);
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

    private void setStatmentOrderDateAdd(DateOrder dateOrder, PreparedStatement statment) throws SQLException {
        statment.setString(1,dateOrder.getDate());
        statment.setInt(2,dateOrder.getOrder_id());
    }

    public int getAllOrdersDate() throws SQLException {
        int dateOrderList = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT id FROM dateorder WHERE id=( SELECT max(id) FROM dateorder );";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            while (set.next()){
                dateOrderList = set.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return dateOrderList;
    }

    private void setStatmentAll(ResultSet set, List<DateOrder> dateOrderList) throws SQLException {
        DateOrder dateOrder = null;
        while (set.next()) {
            dateOrder = new DateOrder();
            dateOrder.setId(set.getInt("id"));
            dateOrder.setDate(set.getString("date"));
            dateOrderList.add(dateOrder);
        }
    }

    public List<DateOrder> getAllOrdersByUserId(int orderid) throws SQLException {
        List<DateOrder> dateOrderList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try{
            connection = ConnectToData();
            String sql = "SELECT * FROM `dateorder` WHERE `order_id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, orderid);
            set = statment.executeQuery();
            ListOfOrders(set,dateOrderList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null){
                connection.close();
            }
            if(statment != null){
                statment.close();
            }
            if(set != null){
                set.close();
            }
        }
        return dateOrderList;
    }

    private void ListOfOrders(ResultSet set, List<DateOrder> dateOrderList) throws SQLException {
        DateOrder dateOrder = null;
        while (set.next()) {
            dateOrder = new DateOrder();
            dateOrder.setId(set.getInt("id"));
            dateOrder.setOrder_id(set.getInt("order_id"));
            dateOrder.setDate(set.getString("date"));
            dateOrderList.add(dateOrder);
        }
    }

    public int deleteByOrderId(int orderid) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "DELETE FROM `dateorder` WHERE `order_id`=" + orderid;
            statment = connection.prepareStatement(sql);
            rowsDeleted = statment.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A FeedBack was deleted successfully!");
            }

        } catch (SQLException exception) {
            System.out.println("sqlException in Application in CATEGORY DELETE  Section  : " + exception);
            exception.printStackTrace();
        } finally {
            if (statment != null) {
                statment.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        return rowsDeleted;
    }

    public Products getProductById(String value) {
        return null;
    }
}
