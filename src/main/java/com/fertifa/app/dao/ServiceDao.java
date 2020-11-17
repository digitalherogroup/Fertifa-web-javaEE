package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao {

    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    public List<Services> getAll(int type) throws SQLException {
        List<Services> faqsCatFaqsList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        Services services = null;
        try {
            connection = ConnectToData();
            String sql = "select * from `services` WHERE `type`="+type;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            while (set.next()) {
                services = new Services();
                services.setId(set.getInt("id"));
                services.setImageLink(set.getString("image"));
                services.setTitle(set.getString("title"));
                services.setDescription(set.getString("description"));
                services.setPrice(set.getFloat("price"));
                faqsCatFaqsList.add(services);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (set != null) {
                set.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return faqsCatFaqsList;
    }



    public List<Services> getByID(int id) throws SQLException {
        List<Services> faqsCatFaqsList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        Services services = null;
        try {
            connection = ConnectToData();
            String sql = "select * from `services` WHERE `id`=" + id;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            while (set.next()) {
                services = new Services();
                services.setId(set.getInt("id"));
                services.setImageLink(set.getString("image"));
                services.setTitle(set.getString("title"));
                services.setDescription(set.getString("description"));
                services.setPrice(set.getFloat("price"));
                faqsCatFaqsList.add(services);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (set != null) {
                set.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return faqsCatFaqsList;
    }

    public String getServiceNameById(int getServiceNameById) {
        String serviceName = "";
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT `title` FROM `services` where id=" + getServiceNameById;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                serviceName = set.getString("title");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return serviceName;
    }

    public String getProductNameById(int getServiceNameById) {
        String serviceName = "";
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = getConnectionToDatabase();
            String sql = "SELECT `title` FROM `products` where id=" + getServiceNameById;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                serviceName = set.getString("title");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return serviceName;
    }

    public Services findById(int dataId) {
        Services service = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `services` WHERE `id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, dataId);
            set = statment.executeQuery();
            if (set.next()) {
                service = ServiceObject(set);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return service;
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


    public Services findService(int dataId) {
        Services service = null;
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
                service = Services(set);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return service;
    }


    private Services Services(ResultSet set) throws SQLException {
        return new Services(set.getInt("id"),
                set.getString("image_link"),
                set.getString("title"),
                set.getString("description"),
                set.getFloat("price"));
    }


    private Services ServiceObject(ResultSet set) throws SQLException {
        return new Services(set.getInt("id"),
                set.getString("image"),
                set.getString("title"),
                set.getString("description"),
                set.getFloat("price"));
    }
}
