package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.models.Orders;
import com.fertifa.app.models.ShoppingCartFinal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    public List<Orders> showAll() throws SQLException {
        List<Orders> newsModelList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `orders` ORDER By `id` DESC ";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            orderStatment(set, newsModelList);

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
        return newsModelList;
    }

    private void orderStatment(ResultSet set, List<Orders> newsModelList) throws SQLException {
        Orders orders = null;
        while (set.next()) {
            orders = new Orders();
            orders.setId(set.getInt("id"));
            orders.setUser_id(set.getInt("user_id"));
            orders.setDate_id(set.getInt("date_id"));
            orders.setAppointment(set.getString("appointment"));
            orders.setClinic(set.getString("clinic"));
            orders.setAddress(set.getString("address"));
            orders.setCoast(set.getFloat("coast"));
            orders.setStatus(set.getInt("status"));
            orders.setService_id(set.getInt("service_id"));
            orders.setTimeId(set.getInt("timesid"));
            orders.setStatus(set.getInt("status"));
            orders.setSessionToken(set.getString("session_token"));
            newsModelList.add(orders);
        }
    }

    public List<Orders> orderById(int id) throws SQLException {
        List<Orders> ordersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `orders` where `id`=" + id;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            orderStatment(set, ordersList);

        } catch (SQLException exception) {
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
        return ordersList;
    }

    public List<Orders> UserOrderById(int userId) throws SQLException {
        List<Orders> ordersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `orders` where `user_id`=" + userId + " ORDER BY `id` DESC ";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            orderStatment(set, ordersList);

        } catch (SQLException exception) {
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
        return ordersList;
    }

    public List<Orders> UserShoppingCartFinalById(int userId) throws SQLException {
        List<Orders> ordersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `shoppingcartfinal` where `user_id`=" + userId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            orderStatment(set, ordersList);

        } catch (SQLException exception) {
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
        return ordersList;
    }

    public int SelectLastOrderId(int userid) throws SQLException {
        int serviceId = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT id FROM orders WHERE id=( SELECT max(id) FROM orders )";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                serviceId = set.getInt("id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return serviceId;


    }


    public List<ShoppingCartFinal> UserOrderShoopingById(int userId) throws SQLException {
        List<ShoppingCartFinal> ordersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `shoppingcartfinal` where `user_id`=" + userId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ShoppingUserorderStatment(set, ordersList);

        } catch (SQLException exception) {
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
        return ordersList;
    }

    private void ShoppingUserorderStatment(ResultSet set, List<ShoppingCartFinal> ordersList) throws SQLException {
        ShoppingCartFinal shoppingCartFinal = null;
        while (set.next()) {
            shoppingCartFinal = new ShoppingCartFinal();
            shoppingCartFinal.setId(set.getInt("id"));
            shoppingCartFinal.setUser_id(set.getInt("user_id"));
            shoppingCartFinal.setOrder_id(set.getInt("order_id"));
            shoppingCartFinal.setServiceName(set.getString("serviceName"));
            shoppingCartFinal.setTotal_price(set.getFloat("total_price"));
            shoppingCartFinal.setOrder_dates(set.getString("order_dates"));
            shoppingCartFinal.setStatus(set.getInt("status"));
            shoppingCartFinal.setStripe_user_id(set.getString("stripe_order_id"));
            ordersList.add(shoppingCartFinal);
        }
    }

    public int AddNewOrder(Orders orders) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `orders`" +
                    "(`id`, `user_id`,`date_id`,`service_id`,`coast`,`status`,`timesid`,`creation_date`,`session_token`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentOrderAdd(orders, statment);
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


    private void setStatmentOrderAdd(Orders orders, PreparedStatement statment) throws SQLException {
        statment.setInt(1, orders.getUser_id());
        statment.setInt(2, orders.getDate_id());
        statment.setInt(3, orders.getService_id());
        statment.setFloat(4, orders.getCoast());
        statment.setInt(5, orders.getStatus());
        statment.setInt(6, orders.getTimeId());
        statment.setTimestamp(7, orders.getCreation_date());
        statment.setString(8, orders.getSessionToken());
    }

    public int AddNewOrderFirst(Orders orders) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `orders`" +
                    "(`id`, `user_id`,`service_id`,`coast`,`status`) "
                    + "VALUES (Default,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentOrderAddFirst(orders, statment);
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

    private void setStatmentOrderAddFirst(Orders orders, PreparedStatement statment) throws SQLException {
        statment.setInt(1, orders.getUser_id());
        statment.setInt(2, orders.getService_id());
        statment.setFloat(3, orders.getCoast());
        statment.setInt(4, orders.getStatus());

    }

    public int UpdateOrderDetailWithOrderId(Orders orders, int id) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `orders`  " +
                    "SET  orders=?  " +
                    "WHERE id=" + id;
            statment = connection.prepareStatement(sql);
            statment.setInt(1, orders.getDate_id());

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

    public int ChangeStatusToApproved(Orders orders, int orderId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `orders`  " +
                    "SET  `status`=?  " +
                    "WHERE id=" + orderId;
            statment = connection.prepareStatement(sql);
            statment.setInt(1, 1);

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

    public int ChangeStatusToChanged(int orderId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `orders`  " +
                    "SET  `status`=?  " +
                    "WHERE id=" + orderId;
            statment = connection.prepareStatement(sql);
            statment.setInt(1, Constances.ORDERSTATUSCHANGED);

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


    public List<Orders> getAllByOrderId(int dataId) throws SQLException {
        List<Orders> ordersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `orders` where `id`=" + dataId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            orderStatment(set, ordersList);

        } catch (SQLException exception) {
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
        return ordersList;
    }

    public int getServiceIdByUserid(int userId) {
        int serviceId = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `orders` WHERE `user_id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, userId);
            set = statment.executeQuery();
            while (set.next()) {
                serviceId = set.getInt("service_id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return serviceId;
    }

    public int UpdateDateTimeIdbyOrderId(int timeid, int dateId, int orderid) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `orders`  " +
                    "SET  date_id=?,timesid=?  " +
                    "WHERE id=" + orderid;
            statment = connection.prepareStatement(sql);
            statment.setInt(1, dateId);
            statment.setInt(2, timeid);

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

    public List<ShoppingCartFinal> UserShoppingFinalMyOrderById(int userId) throws SQLException {
        List<ShoppingCartFinal> ordersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
         /* String sql = "SELECT \n" +
                    "    `shoppingcartfinal`.`serviceName`, \n" +
                    "    `shoppingcartfinal`.`id`,\n" +
                    "    `shoppingcartfinal`.`total_price`,\n" +
                    "    `shoppingcart`.`price`,\n" +
                    "    DATE_FORMAT(shoppingcartfinal.order_dates,\"%d-%m-%Y\") AS SimpleDate,\n" +
                    "    \n" +
                    "    `dateorder`.`date`,\n" +
                    "    GROUP_CONCAT(CONCAT_WS('  ', `date`, `time`)\n" +
                    "        SEPARATOR ' & ') AS 'date_time'\n" +
                    " FROM  shoppingcartfinal\n" +
                    "    LEFT JOIN\n" +
                    "    `orders`\n" +
                    "    ON `shoppingcartfinal`.`order_id` = `orders`.`id`\n" +
                    "        LEFT JOIN\n" +
                    "    `dateorder` ON `orders`.`id` = `dateorder`.`order_id`\n" +
                    "        LEFT JOIN\n" +
                    "    `tiimesorder` ON `orders`.`id` = `tiimesorder`.`order_id` AND  `tiimesorder`.`date_id` =  `dateorder`.`id`\n" +
                    "        AND `dateorder`.`id` = `tiimesorder`.`date_id`\n" +
                    "         LEFT JOIN\n" +
                    "    `shoppingcart`\n" +
                    "    ON `shoppingcartfinal`.`order_id` = `shoppingcart`.`order_id`\n" +
                    " WHERE\n" +
                    "    `shoppingcartfinal`.`user_id`= ? GROUP by `shoppingcartfinal`.`order_id`;";*/
         String sql = "SELECT \n" +
                 "    `shoppingcartfinal`.id, \n" +
                 "    `shoppingcartfinal`.`order_id`, \n" +
                 "    `shoppingcartfinal`.`serviceName`, \n" +
                 "    `shoppingcartfinal`.`user_id`, \n" +
                 "    `shoppingcartfinal`.`price`, \n" +
                 "    `shoppingcart`.`order_dates`,`shoppingcart`.`status`,\n" +
                 "    DATE_FORMAT(shoppingcartfinal.order_dates,\"%d-%m-%Y\") AS SimpleDate\n" +
                 " FROM  shoppingcartfinal\n" +
                 "    LEFT JOIN\n" +
                 "    `shoppingcart`\n" +
                 "    ON `shoppingcart`.`order_id` = `shoppingcartfinal`.`order_id`\n" +
                 " WHERE\n" +
                 "    `shoppingcartfinal`.`user_id`= ? AND `shoppingcart`.`status` = 1 group by `shoppingcart`.`id`;";
            statment = connection.prepareStatement(sql);
            statment.setInt(1,userId);
            set = statment.executeQuery();
            ShoppingCartFinal shoppingCartFinal = null;
            while (set.next()) {
                shoppingCartFinal = new ShoppingCartFinal();
                shoppingCartFinal.setId(set.getInt("id"));
                shoppingCartFinal.setServiceName(set.getString("serviceName"));
                shoppingCartFinal.setPrice(set.getFloat("price"));
                shoppingCartFinal.setSimpleDate(set.getString("SimpleDate"));
                shoppingCartFinal.setOrder_dates(set.getString("order_dates"));
                ordersList.add(shoppingCartFinal);
            }

        } catch (SQLException exception) {
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
        return ordersList;
    }

    public int lastId(int userId) throws SQLException {
        int orderID = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT `id` FROM `orders` WHERE `id`=( SELECT max(`id`) FROM `orders` )";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                orderID = set.getInt("id");
            }

        } catch (SQLException exception) {
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
        return orderID;
    }
}
