package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.ServiceDateTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimeDateOrderDao {


    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    public List<ServiceDateTime> getByUserId(int userId) throws SQLException {
        List<ServiceDateTime> serviceDateTimeArrayList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "select orders.*,DATE_FORMAT(orders.creation_date, '%d-%m-%Y') formatfed_date, users.firstname, users.lastname, services.title, GROUP_CONCAT(CONCAT_WS('  ', `date`, `time`) SEPARATOR '&') AS 'date_time'\n" +
                    "FROM orders\n" +
                    "LEFT JOIN `dateorder`\n" +
                    "ON `orders`.`id` = `dateorder`.`order_id`\n" +
                    "LEFT JOIN `tiimesorder`\n" +
                    "ON `orders`.`id` = `tiimesorder`.`order_id` AND `dateorder`.`id` = `tiimesorder`.`date_id`\n" +
                    "INNER JOIN `users`\n" +
                    "ON `orders`.`user_id` = `users`.`id`\n" +
                    "INNER JOIN `services`\n" +
                    "ON `orders`.`service_id` = `services`.`id`\n" +
                    "WHERE `user_id` = ?  GROUP BY `id`;";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, userId);
            set = statment.executeQuery();
            ListOfTimeOrders2(set, serviceDateTimeArrayList);
        } catch (SQLException e) {
            e.printStackTrace();
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
        return serviceDateTimeArrayList;
    }

    private void ListOfTimeOrders2(ResultSet set, List<ServiceDateTime> serviceDateTimeArrayList) throws SQLException {
        ServiceDateTime serviceDateTime = null;
        while (set.next()) {
            serviceDateTime = new ServiceDateTime();
            serviceDateTime.setId(set.getInt("id"));
            //serviceDateTime.setOrder_id(set.getInt("order_id"));
            serviceDateTime.setService_id(set.getInt("service_id"));
            serviceDateTime.setUser_id(set.getInt("user_id"));
            serviceDateTime.setDate_time(set.getString("date_time"));
            serviceDateTime.setStatus(set.getInt("status"));
            serviceDateTime.setDate(set.getString("formatfed_date"));
            serviceDateTime.setFirstName(set.getString("firstname"));
            serviceDateTime.setLastName(set.getString("lastname"));
            serviceDateTime.setServiceName(set.getString("title"));
            serviceDateTime.setGetServicePrice(set.getFloat("coast"));

            serviceDateTimeArrayList.add(serviceDateTime);
        }

    }

    public List<ServiceDateTime> getByUserIdFullDetails(int userId) throws SQLException {
        List<ServiceDateTime> serviceDateTimeArrayList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = " SELECT\n" +
                    " orders.*,\n" +
                    " users.firstname,\n" +
                    " users.lastname,\n" +
                    " GROUP_CONCAT( CONCAT_WS( '  ', `date`, `time` ) SEPARATOR ' & ' ) AS 'date_time' \n" +
                    " FROM\n" +
                    " orders\n" +
                    " LEFT JOIN `dateorder` ON `orders`.`id` = `dateorder`.`order_id`\n" +
                    " LEFT JOIN `tiimesorder` ON ( `orders`.`id` = `tiimesorder`.`order_id` AND `dateorder`.`id` = `tiimesorder`.`date_id` )\n" +
                    " INNER JOIN `users` ON `orders`.`user_id` = `users`.`id` \n" +
                    " WHERE\n" +
                    " `user_id` = ? \n" +
                    " GROUP BY\n" +
                    " `orders`.`id` \n" +
                    " ORDER BY\n" +
                    " `orders`.`id` DESC \n" +
                    " LIMIT 1";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, userId);
            set = statment.executeQuery();
            ListOfTimeOrders(set, serviceDateTimeArrayList);
        } catch (SQLException e) {
            e.printStackTrace();
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
        return serviceDateTimeArrayList;
    }

    private void ListOfTimeOrders(ResultSet set, List<ServiceDateTime> serviceDateTimeArrayList) throws SQLException {
        ServiceDateTime serviceDateTime = null;
        while (set.next()) {
            serviceDateTime = new ServiceDateTime();
            serviceDateTime.setId(set.getInt("id"));
            //serviceDateTime.setOrder_id(set.getInt("order_id"));
            serviceDateTime.setService_id(set.getInt("service_id"));
            serviceDateTime.setUser_id(set.getInt("user_id"));
            serviceDateTime.setDate_time(set.getString("date_time"));
            serviceDateTime.setStatus(set.getInt("status"));
            serviceDateTime.setFirstName(set.getString("firstname"));
            serviceDateTime.setLastName(set.getString("lastname"));
            serviceDateTime.setGetServicePrice(set.getFloat("coast"));

            serviceDateTimeArrayList.add(serviceDateTime);
        }
    }

    private void ListOfTimeOrdersWithoutUsername(ResultSet set, List<ServiceDateTime> serviceDateTimeArrayList) throws SQLException {
        ServiceDateTime serviceDateTime = null;
        while (set.next()) {
            serviceDateTime = new ServiceDateTime();
            serviceDateTime.setId(set.getInt("id"));
            //serviceDateTime.setOrder_id(set.getInt("order_id"));
            serviceDateTime.setService_id(set.getInt("service_id"));
            serviceDateTime.setUser_id(set.getInt("user_id"));
            serviceDateTime.setDate_time(set.getString("date_time"));
            serviceDateTime.setStatus(set.getInt("status"));
            serviceDateTime.setGetServicePrice(set.getFloat("coast"));

            serviceDateTimeArrayList.add(serviceDateTime);
        }
    }

    private void ListOfTimeOrdersWithoutName(ResultSet set, List<ServiceDateTime> serviceDateTimeArrayList) throws SQLException {
        ServiceDateTime serviceDateTime = null;
        while (set.next()) {
            serviceDateTime = new ServiceDateTime();
            serviceDateTime.setId(set.getInt("id"));
            //serviceDateTime.setOrder_id(set.getInt("order_id"));
            serviceDateTime.setService_id(set.getInt("service_id"));
            serviceDateTime.setUser_id(set.getInt("user_id"));
            serviceDateTime.setDate_time(set.getString("date_time"));
            serviceDateTime.setStatus(set.getInt("status"));

            serviceDateTime.setGetServicePrice(set.getFloat("coast"));

            serviceDateTimeArrayList.add(serviceDateTime);
        }
    }

    public List<ServiceDateTime> getAll() throws SQLException {
        List<ServiceDateTime> serviceDateTimeArrayList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "select orders.*, users.firstname, users.lastname, services.title, GROUP_CONCAT(CONCAT_WS('  ', `date`, `time`) SEPARATOR '&') AS 'date_time'\n" +
                    "FROM orders\n" +
                    "LEFT JOIN dateorder\n" +
                    "ON orders.id = dateorder.order_id\n" +
                    "LEFT JOIN tiimesorder\n" +
                    "ON orders.id = tiimesorder.order_id AND dateorder.id = tiimesorder.date_id\n" +
                    "INNER JOIN services\n" +
                    "ON orders.service_id = services.id\n" +
                    "INNER JOIN users\n" +
                    "ON orders.user_id = users.id\n" +
                    "GROUP BY `id` ORDER By `id` DESC;";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfTimeOrders(set, serviceDateTimeArrayList);
        } catch (SQLException e) {
            e.printStackTrace();
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
        return serviceDateTimeArrayList;
    }

    public List<ServiceDateTime> getByUserIdAndOrderId(int userIdFromOrder, int dataId) throws SQLException {
        List<ServiceDateTime> serviceDateTimeArrayList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "select orders.*, GROUP_CONCAT(CONCAT_WS('/', `date`, `time`) SEPARATOR '&') AS 'date_time'\n" +
                    "FROM orders\n" +
                    "LEFT JOIN dateorder\n" +
                    "ON orders.id = dateorder.order_id\n" +
                    "LEFT JOIN tiimesorder\n" +
                    "ON orders.id = tiimesorder.order_id AND dateorder.id = tiimesorder.date_id\n" +
                    "WHERE orders.id = " + dataId + " GROUP BY orders.id ORDER By orders.id DESC ";
            System.out.print(sql);
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfTimeOrdersWithoutName(set, serviceDateTimeArrayList);
        } catch (SQLException e) {
            e.printStackTrace();
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
        return serviceDateTimeArrayList;
    }

    public List<ServiceDateTime> getFullOrderDetails(int orderid) throws SQLException {
        List<ServiceDateTime> serviceDateTimeArrayList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
         /*   String sql = "SELECT `orders`.user_id, orders.coast,orders.service_id,date_format(orders.creation_date,\"%d-%m-%Y\") DateString,services.title,\n" +
                    " dateorder.date, tiimesorder.time\n" +
                    " FROM  orders\n" +
                    " Left join services\n" +
                    " ON `services`.id = orders.service_id\n" +
                    " LEFT JOIN `dateorder` ON `orders`.`id` = `dateorder`.`order_id` \n" +
                    " LEFT JOIN  `tiimesorder` ON `orders`.`id` = `tiimesorder`.`order_id` AND  `tiimesorder`.`date_id` =  `dateorder`.`id`\n" +
                    " AND `dateorder`.`id` = `tiimesorder`.`date_id` \n" +
                    " WHERE `orders`.`id`= " + userid + " AND orders.status > 0;";*/
            String sql =  "SELECT `orders`.user_id,orders.id, orders.coast,orders.service_id,date_format(orders.creation_date,\"%d-%m-%Y\") DateString,services.title,\n" +
                    " GROUP_CONCAT(CONCAT_WS('  ', `date`, `time`)  SEPARATOR ' & ') AS 'date_time'\n" +
                    " FROM  orders\n" +
                    " Left join services\n" +
                    " ON `services`.id = orders.service_id\n" +
                    " LEFT JOIN `dateorder` ON `orders`.`id` = `dateorder`.`order_id` LEFT JOIN\n" +
                    " `tiimesorder` ON `orders`.`id` = `tiimesorder`.`order_id` AND  `tiimesorder`.`date_id` =  `dateorder`.`id`\n" +
                    " AND `dateorder`.`id` = `tiimesorder`.`date_id` \n" +
                    " \n" +
                    " WHERE `orders`.`id`= "+ orderid+" GROUP by `orders`.`id`;";
            System.out.print(sql);
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ForShoppingCart(set, serviceDateTimeArrayList);
        } catch (SQLException e) {
            e.printStackTrace();
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
        return serviceDateTimeArrayList;
    }

    private void ForShoppingCart(ResultSet set, List<ServiceDateTime> serviceDateTimeArrayList) throws SQLException {
        ServiceDateTime serviceDateTime = null;
        while (set.next()) {
            serviceDateTime = new ServiceDateTime();
            serviceDateTime.setUser_id(set.getInt("user_id"));
            serviceDateTime.setOrder_id(set.getInt("id"));
            serviceDateTime.setGetServicePrice(set.getFloat("coast"));
            serviceDateTime.setService_id(set.getInt("service_id"));
            serviceDateTime.setDate(set.getString("DateString"));
            serviceDateTime.setServiceName(set.getString("title"));
            serviceDateTime.setDate_time(set.getString("date_time"));
            serviceDateTimeArrayList.add(serviceDateTime);
        }
    }
}
