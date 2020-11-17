package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.TimesOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimesOrderDao {

    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }


    public int AddNewTimeOrder(TimesOrder timesOrder, int dateid) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `tiimesorder`" +
                    "(`id`, `time`,`date_id`,`order_id`) "
                    + "VALUES (Default,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            statment.setString(1,timesOrder.getTime());
            statment.setInt(2,timesOrder.getDate_id());
            statment.setInt(3,timesOrder.getOrder_id());
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


    public List<TimesOrder> getLastOrderTimeid() throws SQLException {
        List<TimesOrder> timesOrderList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `tiimesorder`";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            setStatmentAll(set, timesOrderList);

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
        return timesOrderList;

    }

    private void setStatmentAll(ResultSet set, List<TimesOrder> timesOrderList) throws SQLException {
        TimesOrder timesOrder = null;
        while (set.next()) {
            timesOrder = new TimesOrder();
            timesOrder.setId(set.getInt("id"));
            timesOrder.setTime(set.getString("time"));
            timesOrderList.add(timesOrder);
        }
    }

    public List<TimesOrder> getTimeOrderByOrderId(int order_id) throws SQLException {
        List<TimesOrder> timesOrderList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try{
            connection = ConnectToData();
            String sql = "SELECT * FROM `dateorder` WHERE `order_id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, order_id);
            set = statment.executeQuery();
            ListOfTimeOrders(set,timesOrderList);
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
        return timesOrderList;
    }

    private void ListOfTimeOrders(ResultSet set, List<TimesOrder> timesOrderList) throws SQLException {
        TimesOrder timesOrder = null;
        while (set.next()){
            timesOrder.setId(set.getInt("id"));
            timesOrder.setTime(set.getString("time"));
            timesOrder.setDate_id(set.getInt("date_id"));
            timesOrder.setOrder_id(set.getInt("order_id"));
            timesOrderList.add(timesOrder);
        }
    }

    public int deleteByOrderId(int oderId) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "DELETE FROM `tiimesorder` WHERE `order_id`=" + oderId;
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

    public int getLastTimeId() throws SQLException {
        int dateOrderList = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT id FROM tiimesorder WHERE id=( SELECT max(id) FROM tiimesorder );";
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
}
