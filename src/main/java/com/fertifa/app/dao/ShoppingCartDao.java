package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.models.ShoppingCardSalesModel;
import com.fertifa.app.models.ShoppingCart;
import com.fertifa.app.models.ShoppingCartFinal;
import com.fertifa.app.payments.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCartDao {
    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    public int AddNewOrder(ShoppingCart shoppingCart) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `shoppingcart`" +
                    "(`id`, `user_id`,`order_id`,`serviceName`,`price`,`order_dates`,`status`) "
                    + "VALUES (Default,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentShopping(shoppingCart, statment);
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

    public int AddNewOrderToChat(ShoppingCart shoppingCart) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `shoppingcart`" +
                    "(`id`, `user_id`,`order_id`,`serviceName`,`price`,`order_dates`,`status`,`type`,`serviceId`,`chatId`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentChat(shoppingCart, statment);
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

    private void setStatmentChat(ShoppingCart shoppingCart, PreparedStatement statment) throws SQLException {
        statment.setInt(1, shoppingCart.getUser_id());
        statment.setInt(2, shoppingCart.getOrder_id());
        statment.setString(3, shoppingCart.getServiceName());
        statment.setFloat(4, shoppingCart.getPrice());
        statment.setString(5, shoppingCart.getOrder_date());
        statment.setInt(6, shoppingCart.getStatus());
        statment.setInt(7,shoppingCart.getType());
        statment.setInt(8,shoppingCart.getServiceId());
        statment.setInt(9,shoppingCart.getChatId());
    }


    private void setStatmentShopping(ShoppingCart shoppingCart, PreparedStatement statment) throws SQLException {
        statment.setInt(1, shoppingCart.getUser_id());
        statment.setInt(2, shoppingCart.getOrder_id());
        statment.setString(3, shoppingCart.getServiceName());
        statment.setFloat(4, shoppingCart.getPrice());
        statment.setString(5, shoppingCart.getOrder_date());
        statment.setInt(6, shoppingCart.getStatus());
    }

    public List<ShoppingCart> getAllById(int userId) throws SQLException {
        List<ShoppingCart> shoppingCartList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `shoppingcart` WHERE `user_id`=" + userId + " AND `status` = 0" ;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfShoppingCart(set, shoppingCartList);

        } catch (Exception exception) {
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
        return shoppingCartList;
    }

    public List<ShoppingCart> getAllOrdersById(int userId) throws SQLException {
        List<ShoppingCart> shoppingCartList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `shoppingcart` WHERE `user_id`=" + userId ;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfShoppingCart(set, shoppingCartList);

        } catch (Exception exception) {
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
        return shoppingCartList;
    }

    private void ListOfShoppingCart(ResultSet set, List<ShoppingCart> shoppingCartList) throws SQLException {
        ShoppingCart shoppingCart = null;
        while (set.next()) {
            shoppingCart = new ShoppingCart();
            shoppingCart.setId(set.getInt("id"));
            shoppingCart.setOrder_date(set.getString("order_dates"));
            shoppingCart.setOrder_id(set.getInt("order_id"));
            shoppingCart.setServiceName(set.getString("serviceName"));
            shoppingCart.setPrice(set.getFloat("price"));
            shoppingCart.setUser_id(set.getInt("user_id"));
            shoppingCart.setStatus(set.getInt("status"));
            shoppingCart.setPriceId(set.getString("priceId"));
            shoppingCart.setProductId(set.getString("productId"));
            shoppingCart.setInvoiceUrl(set.getString("invoiceId"));
            shoppingCart.setPaymentId(set.getString("paymentId"));
            shoppingCartList.add(shoppingCart);
        }
    }

    public int deleteOrderByUseridAndOrderId(int id, int userIdFromWeb) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "DELETE FROM `shoppingcart` WHERE  `id`=" + id;
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
            if (connection != null) {
                connection.close();
            }
        }
        return rowsDeleted;
    }

    public int deleteOrderByOrderId(int orderid, int userid) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "DELETE FROM `shoppingcart` WHERE  `user_id`=" + userid + "AND `order_id`=" + orderid;
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
            if (connection != null) {
                connection.close();
            }
        }
        return rowsDeleted;
    }

    public ShoppingCart getObjectById(int orderid) throws SQLException {
        ShoppingCart shoppingCart = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `shoppingcart` WHERE `id`=" + orderid;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                shoppingCart = new ShoppingCart();
                shoppingCart.setId(set.getInt("id"));
                shoppingCart.setOrder_date(set.getString("order_dates"));
                shoppingCart.setOrder_id(set.getInt("order_id"));
                shoppingCart.setServiceName(set.getString("serviceName"));
                shoppingCart.setPrice(set.getFloat("price"));
                shoppingCart.setUser_id(set.getInt("user_id"));
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
        return shoppingCart;
    }




    public ShoppingCart getObjectByUserId(int orderid) throws SQLException {
        ShoppingCart shoppingCart = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `shoppingcart` WHERE `user_id`=" + orderid;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                shoppingCart = new ShoppingCart();
                shoppingCart.setId(set.getInt("id"));
                shoppingCart.setOrder_date(set.getString("order_dates"));
                shoppingCart.setOrder_id(set.getInt("order_id"));
                shoppingCart.setServiceName(set.getString("serviceName"));
                shoppingCart.setPrice(set.getFloat("price"));
                shoppingCart.setUser_id(set.getInt("user_id"));
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
        return shoppingCart;
    }

    public int AddToFinal(int usersCompanyId,int serviceId, int UserId, User user, String orderId, ShoppingCart shoppingCart, int total, String stripeId) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();

            String insertQuery = "INSERT INTO `shoppingcartfinal` (`id`,`order_id`, `serviceName`,`total_price`,`order_dates`,`status`,`cardholderfirstname`,`cardholderlastname`, `cardholderphone`,`cardholderemail`,`cardholderaddress`,`cardholdercity`,`cardholdercountry`,\n" +
                    "            `cardholderstat`, `cardholderpostcode`,`stripe_order_id`,`stripe_order_amount`,`stripe_order_datecreated`,`stripe_user_id`,`price`,`user_id`,`service_id`,`company_id`,`priceId`,`productId`,`paymentId`,`invoiceId`) VALUES (Default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);

            statment.setInt(1, shoppingCart.getOrder_id());
            statment.setString(2, shoppingCart.getServiceName());
            statment.setInt(3, total);
            statment.setTimestamp(4, new Timestamp(new Date().getTime()));
            statment.setInt(5, Constances.STIPEUSERPAYEDSSTATUS);
            statment.setString(6, user.getFirst_name());
            statment.setString(7, user.getLast_name());
            statment.setString(8, user.getPhoneNumber());
            statment.setString(9, user.getEmail());
            statment.setString(10, user.getAddress());
            statment.setString(11, user.getCity());
            statment.setString(12, user.getCountry());
            statment.setString(13, user.getState());
            statment.setString(14, user.getPostal_code());
            statment.setString(15, orderId);
            statment.setInt(16, 0);
            statment.setTimestamp(17, new Timestamp(new Date().getTime()));
            statment.setString(18, stripeId);
            statment.setFloat(19, shoppingCart.getPrice());
            statment.setInt(20, UserId);
            statment.setInt(21, serviceId);
            statment.setInt(22, usersCompanyId);
            statment.setString(23,shoppingCart.getPriceId());
            statment.setString(24,shoppingCart.getProductId());
            statment.setString(25,shoppingCart.getPaymentId());
            statment.setString(26,shoppingCart.getInvoiceUrl());


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

    public List<ShoppingCartFinal> GetAllByDates(String sevenDays, String today, int ServiceId, int CompanyId) throws SQLException {
        List<ShoppingCartFinal> shoppingCartFinalList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        ShoppingCartFinal shoppingCart = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `shoppingcartfinal` WHERE `status`<> 9000";

            if (CompanyId > 0) {
                sql += " and `company_id`=" + CompanyId;

            }

            if (ServiceId > 0) {
                sql += " and `service_id`=" + ServiceId;

            }

            if (sevenDays != "") {
                sql += " and `order_dates` >=" + "\"" + sevenDays + "\"";
            }

            if (today != "") {
                sql += " AND `order_dates`<= " + "\"" + today + "\"";
            }


            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            while (set.next()) {
                shoppingCart = new ShoppingCartFinal();
                shoppingCart.setId(set.getInt("id"));
                shoppingCart.setUser_id(set.getInt("user_id"));
                shoppingCart.setOrder_id(set.getInt("order_id"));
                shoppingCart.setCardholderfirstname(set.getString("cardholderfirstname"));
                shoppingCart.setCardholderlastname(set.getString("cardholderlastname"));
                shoppingCart.setServiceName(set.getString("serviceName"));
                shoppingCart.setOrder_dates(String.valueOf(set.getTimestamp("order_dates")));
                shoppingCart.setStatus(set.getInt("status"));
                shoppingCart.setTotal_price(set.getInt("total_price"));
                shoppingCart.setStripe_order_id(set.getString("stripe_order_id"));
                shoppingCart.setStripe_user_id(set.getString("stripe_user_id"));
                shoppingCart.setStripe_order_datecreated("stripe_order_datecreated");
                shoppingCartFinalList.add(shoppingCart);

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
        return shoppingCartFinalList;
    }

    public List<ShoppingCardSalesModel> getSalesShoppingCard(String sevenDays, String today, int ServiceId, int CompanyId) throws SQLException {
        List<ShoppingCardSalesModel> shoppingSalesList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();

            String sql = " SELECT \n" +
                    " ROUND(SUM((total_price * 0.1)), 2) as day_total,\n" +
                    " DATE_FORMAT( order_dates, '%d.%m' ) DateString, \n" +
                    " DATE_FORMAT( order_dates, '%d.%m.%y' ) check_date \n" +
                    " FROM \n" +
                    " shoppingcartfinal \n" +
                    " WHERE status <> 9000 \n";


            if (CompanyId > 0) {
                sql += " and `company_id`=" + CompanyId;
            }

            if (ServiceId > 0) {
                sql += " and `service_id`=" + ServiceId;
            }

            if (sevenDays != "") {
                sql += " and DATE_FORMAT( order_dates, '%Y-%m-%d' ) >= " + "\"" + sevenDays + "\"";
            }

            if (today != "") {
                sql += " AND DATE_FORMAT( order_dates, '%Y-%m-%d' ) <= " + "\"" + today + "\"";
            }

            sql += " GROUP BY check_date";

            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            ShoppingCardSalesModel shoppingSaleModel = null;
            while (set.next()) {
                shoppingSaleModel = new ShoppingCardSalesModel();
                shoppingSaleModel.setDayTotal(set.getFloat("day_total"));
                shoppingSaleModel.setDateString(set.getString("DateString"));
                shoppingSalesList.add(shoppingSaleModel);
            }

        } catch (Exception e) {
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
        return shoppingSalesList;
    }

    public List<ShoppingCartFinal> getListObjectByUserId(int userId) throws SQLException {
        List<ShoppingCartFinal> shoppingCartList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            //String sql = "SELECT *  FROM `shoppingcart` WHERE `user_id`=" + userId + " AND `status`> 0 ORDER BY `id` DESC";
            String sql ="SELECT \n" +
                    "    `shoppingcartfinal`.id, \n" +
                    "    `shoppingcartfinal`.`order_id`, \n" +
                    "    `shoppingcartfinal`.`serviceName`, \n" +
                    "    `shoppingcartfinal`.`user_id`, \n" +
                    "    `shoppingcartfinal`.`price`, \n" +
                    "    `shoppingcart`.`order_dates`,`shoppingcart`.`status`,\n" +
                    "    DATE_FORMAT(shoppingcartfinal.order_dates,\"%d-%m-%Y\") AS SimpleDate\n" +
                    " FROM  shoppingcartfinal\n" +
                    "    INNER JOIN\n" +
                    "    `shoppingcart`\n" +
                    "    ON `shoppingcartfinal`.`order_id` = `shoppingcart`.`order_id`\n" +
                    " WHERE\n" +
                    "    `shoppingcartfinal`.`user_id`= " + userId + " AND `shoppingcart`.`status` = 1 GROUP by `shoppingcart`.`id` ORDER BY `shoppingcartfinal`.`id` DESC;";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfShoppingCartFinalJourney(set, shoppingCartList);

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
        return shoppingCartList;
    }

    private void ListOfShoppingCartFinalJourney(ResultSet set, List<ShoppingCartFinal> shoppingCartList) throws SQLException {
        ShoppingCartFinal shoppingCart = null;
        while (set.next()) {
            shoppingCart = new ShoppingCartFinal();
            shoppingCart.setUser_id(set.getInt("user_id"));
            shoppingCart.setOrder_id(set.getInt("order_id"));
            shoppingCart.setServiceName(set.getString("serviceName"));
            shoppingCart.setOrder_dates(set.getString("order_dates"));
            shoppingCartList.add(shoppingCart);

        }
    }


    private void ListOfShoppingCartFinal(ResultSet set, List<ShoppingCartFinal> shoppingCartList) throws SQLException {
        ShoppingCartFinal shoppingCart = null;
        while (set.next()) {
            shoppingCart = new ShoppingCartFinal();
            shoppingCart.setId(set.getInt("id"));
            shoppingCart.setUser_id(set.getInt("user_id"));
            shoppingCart.setOrder_id(set.getInt("order_id"));
            shoppingCart.setCardholderfirstname(set.getString("cardholderfirstname"));
            shoppingCart.setCardholderlastname(set.getString("cardholderlastname"));
            shoppingCart.setServiceName(set.getString("serviceName"));
            shoppingCart.setOrder_dates(String.valueOf(set.getTimestamp("order_dates")));
            shoppingCart.setStatus(set.getInt("status"));
            shoppingCart.setTotal_price(set.getInt("total_price"));
            shoppingCart.setStripe_order_id(set.getString("stripe_order_id"));
            shoppingCart.setStripe_user_id(set.getString("stripe_user_id"));
            shoppingCart.setStripe_order_datecreated("stripe_order_datecreated");
            shoppingCartList.add(shoppingCart);

        }
    }


    public String getTotalSavingsByMonths(String lastMonth, String now, double percentage, int companyId, int serviceId) throws SQLException {

        String testFloat ="";
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        String insertQuery = "";
        try {
            connection = ConnectToData();
            insertQuery = "SELECT round(sum((total_price * " + percentage + ")), 2) as your_total " +
                    "FROM shoppingcartfinal WHERE company_id =" + companyId;
            if (lastMonth != null && !lastMonth.isEmpty()) {
                insertQuery += " AND date_format(`order_dates`, '%Y-%m-%d') >='" + lastMonth + "'";
            }
            if (now != null && !now.isEmpty()) {
                insertQuery += " AND date_format(`order_dates`, '%Y-%m-%d') <='" + now + "'";
            }
            insertQuery += " group by company_id";

            statment = connection.prepareStatement(insertQuery);
            set = statment.executeQuery();
            while (set.next()) {
                testFloat = set.getString("your_total");
            }

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

        return testFloat;
    }

    public List<EnrollmentModel> getAllSavingDetails(String lastMonth, String now, double percentag, int companyId, int serviceId) throws SQLException {
        List<EnrollmentModel> shoppingCartFinalList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * ," +
                    " ROUND(SUM((total_price * " + percentag + ")), 2) as day_total,\n" +
                    " DATE_FORMAT( order_dates, '%d.%m' ) DateString,\n" +
                    " DATE_FORMAT( order_dates, '%d.%m.%y' ) check_date\n" +
                    "FROM\n" +
                    " shoppingcartfinal\n" +
                    "WHERE\n" +
                    " company_id > -1\n";
            if (lastMonth != null) {
                sql += " AND DATE(order_dates) >= '" + lastMonth + "'";
            }
            if (now != null) {
                sql += " AND DATE(order_dates) <= '" + now + "'";
            }
            sql += " AND company_id = " + companyId + " GROUP BY check_date";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfDatesAndSaving(set, shoppingCartFinalList);

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
        return shoppingCartFinalList;
    }

    private void ListOfDatesAndSaving(ResultSet set, List<EnrollmentModel> shoppingCartFinalList) throws SQLException {
        EnrollmentModel shoppingCart = null;
        while (set.next()) {
            shoppingCart = new EnrollmentModel();
            String dateSimple = set.getString("DateString");
            shoppingCart.setSimpleDate(dateSimple);
            System.out.println(shoppingCart.getSimpleDate());
            int total = set.getInt("day_total");
            shoppingCart.setTotal(total);
            System.out.println(total);
           /* shoppingCart.setTotal(set.getInt("day_total"));
            shoppingCart.setSimpleDate(set.getString("DateString"));
            shoppingCart.setDate(set.getString("check_date"));*/
            shoppingCartFinalList.add(shoppingCart);
        }
    }

    public int getlatestID() {
        int userId = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT id FROM shoppingcartfinal WHERE id=( SELECT max(id) FROM shoppingcartfinal )";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                userId = set.getInt("id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return userId;
    }

    public int getLatestShopID() {
        int shopId = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT id FROM shoppingcart WHERE id=( SELECT max(id) FROM shoppingcart)";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                shopId = set.getInt("id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return shopId;
    }

    public int UpdateStatusById(int orderIdFroShop, int userId) throws SQLException {
        int updateID = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "UPDATE `shoppingcart` SET `status`=1 WHERE `order_id`=" + orderIdFroShop;
            statment = connection.prepareStatement(sql);
            updateID = statment.executeUpdate();
            if (updateID > 0) {
                System.out.println("A FeedBack was deleted successfully!");
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

    public boolean getAllByOrderId(int dataId) throws SQLException {
        boolean isEqual = false;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection =null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM  `shoppingcart` WHERE `order_id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, dataId);
            set = statment.executeQuery();
            while (set.next()) {
                isEqual = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return isEqual;
    }

    public int updateShoppingCartByOrderId(ShoppingCart shoppingCart, int dataId) throws SQLException {
        int updateID = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "UPDATE `shoppingcart` SET `order_dates`=? WHERE `order_id`=" + dataId;
            statment = connection.prepareStatement(sql);
            statment.setString(1,shoppingCart.getOrder_date());
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


    public int updateShoppingCartByUserId(ShoppingCart shoppingCart, int dataId) throws SQLException {
        int updateID = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "UPDATE `shoppingcart` SET `order_dates`=? WHERE `user_Id`=" + dataId;
            statment = connection.prepareStatement(sql);
            statment.setString(1,shoppingCart.getOrder_date());
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

    public int updateChatIdByShopId(int chatId, int shoppingCardId) throws SQLException {
        int updateID = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "UPDATE `shoppingcart` SET `chatId`=? WHERE `id`=" + shoppingCardId;
            statment = connection.prepareStatement(sql);
            statment.setInt(1, chatId);
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


    public int updateShoppingCardWithPayment(ShoppingCart shoppingCartModel, int id) throws SQLException {
        int updateID = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "UPDATE `shoppingcart` SET `paymentId`=?,`invoiceId`=? WHERE `user_id`=" + id;
            statment = connection.prepareStatement(sql);
            statment.setString(1, shoppingCartModel.getPaymentId());
            statment.setString(2, shoppingCartModel.getInvoiceUrl());
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
