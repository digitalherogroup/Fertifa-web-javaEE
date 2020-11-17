package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.TestingOrders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestingOrdersDao {

    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    public List<TestingOrders> getAll() throws SQLException {
        List<TestingOrders> testingOrdersList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `testorder` ORDER BY `id` DESC ";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            TestingOrderStatment(set, testingOrdersList);

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
        return testingOrdersList;
    }

    private void TestingOrderStatment(ResultSet set, List<TestingOrders> testingOrdersList) throws SQLException {
        TestingOrders testingOrders = null;
        while (set.next()) {
            testingOrders = new TestingOrders();
            testingOrders.setId(set.getInt("id"));
            testingOrders.setTitle(set.getString("title"));
            testingOrders.setDescription(set.getString("description"));
            testingOrders.setImage(set.getString("image"));
            testingOrders.setPrice(set.getFloat("price"));
            testingOrdersList.add(testingOrders);
        }
    }

    public List<TestingOrders> getById(int testingId) throws SQLException {
        List<TestingOrders> testingOrdersList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `testorder` WHERE `id`=" + testingId;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            TestingOrderStatment(set, testingOrdersList);

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
        return testingOrdersList;
    }

    public TestingOrders findById(int dataId) {
        TestingOrders orders = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `testorder` WHERE `id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, dataId);
            set = statment.executeQuery();
            if (set.next()) {
                orders = TestingObject(set);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return orders;
    }

    private TestingOrders TestingObject(ResultSet set) throws SQLException {
        return new TestingOrders(set.getInt("id"),
                set.getString("image"),
                set.getString("title"),
                set.getString("description"),
                set.getFloat("price"));

    }


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

    public TestingOrders findByTestId(int dataId) {
        TestingOrders orders = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = getConnectionToDatabase();
            String sql = "SELECT * FROM `products` WHERE `id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, dataId);
            set = statment.executeQuery();
            if (set.next()) {
                orders = Testing(set);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return orders;
    }

    private TestingOrders Testing(ResultSet set) throws SQLException {
        return new TestingOrders(set.getInt("id"),
                set.getString("image_link"),
                set.getString("title"),
                set.getString("description"),
                set.getFloat("price"));

    }
}
