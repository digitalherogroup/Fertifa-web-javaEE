package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.NewsCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NewsCategoryDao {
    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    public int save(NewsCategory newsCategory) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `newscategory`" +
                "(`id`, `newscategoryname`,`newscategorydescription`)"
                + "VALUES (Default,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentFonewsCategory(newsCategory, statment);
            rowsAffected = statment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(statment, null, connection);
        }

        return rowsAffected;
    }

    private void setStatmentFonewsCategory(NewsCategory newsCategory, PreparedStatement statement) throws SQLException {
        statement.setString(1, newsCategory.getNewsCategory());
        statement.setString(2, newsCategory.getDescription());
    }

    public List<NewsCategory> getByCategoryName(String name) throws SQLException {
        List<NewsCategory> newsCategoryArrayList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `newscategory` Where `newscategoryname`='" + name +"'";
            statement = connection.prepareStatement(sql);
            set = statement.executeQuery(sql);
            newsCategoryStatement(set, newsCategoryArrayList);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(statement, set, connection);
        }
        return newsCategoryArrayList;
    }

    private void closeAll(Statement statement, ResultSet set, Connection connection) throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (set != null) {
            set.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    private void newsCategoryStatement(ResultSet set, List<NewsCategory> newsModelList) throws SQLException {
        NewsCategory newsCategory = null;
        while (set.next()) {
            newsCategory = new NewsCategory.NewsCategoryBuilder()
                .id(set.getInt("id"))
                .newsCategory(set.getString("newscategoryname"))
                .description(set.getString("newscategorydescription"))
                .build();
            newsModelList.add(newsCategory);
        }
    }



    public NewsCategory getByCategoryId(int newsCategoryId) throws SQLException {
        NewsCategory newsCategory = new NewsCategory(new NewsCategory.NewsCategoryBuilder());
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `newscategory` where `id`=" + newsCategoryId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            if (set.next()) {
                newsCategory = newsCategoryObject(set);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(statment, set, connection);
        }
        return newsCategory;
    }

    private NewsCategory newsCategoryObject(ResultSet set) throws SQLException {
        return new NewsCategory.NewsCategoryBuilder()
            .id(set.getInt("id"))
            .newsCategory(set.getString("newscategoryname"))
            .description(set.getString("newscategorydescription"))
            .build();
    }

    public int update(NewsCategory newsObject) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `newscategory`  " +
                "SET `newscategoryname`=?,`newscategorydescription`=? WHERE `id`=" + newsObject.getId();
            statment = connection.prepareStatement(sql);
            UpdateObject(newsObject, statment);

            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeAll(statment, null, connection);
        }
        return rowsUpdated;
    }

    private void UpdateObject(NewsCategory newsObject, PreparedStatement statment) throws SQLException {
        statment.setString(1, newsObject.getNewsCategory());
        statment.setString(2, newsObject.getDescription());
    }

    public List<NewsCategory> showAll() throws SQLException {
        List<NewsCategory> newsCategoryArrayList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `newscategory`";
            statement = connection.prepareStatement(sql);
            set = statement.executeQuery(sql);
            newsCategoryStatement(set, newsCategoryArrayList);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(statement, set, connection);
        }
        return newsCategoryArrayList;

    }


    public int deleteById(int newsCategoryId) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "DELETE FROM `newscategory` WHERE `id`=" + newsCategoryId;
            statment = connection.prepareStatement(sql);

            rowsDeleted = statment.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A News was deleted successfully!");
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

    public String getCategoryNameById(int newsCategoryId) throws SQLException {
       String categoryName = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT `newscategoryname` FROM `newscategory` where `id`="+newsCategoryId;
            statement = connection.prepareStatement(sql);
            set = statement.executeQuery(sql);
            while (set.next()) {
                categoryName = set.getString("newscategoryname");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if(set != null){
                set.close();
            }
        }
        return categoryName;
    }

    public int getLastID() throws SQLException {
        int catId = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            //SELECT `id` FROM `orders` WHERE `id`=( SELECT max(`id`) FROM `orders` )
            String sql = "SELECT `id` FROM `news` WHERE `id`= (Select  MAX(`id`) FROM `news` where `status`=1)";
            statement = connection.prepareStatement(sql);
            set = statement.executeQuery(sql);
            while (set.next()) {
                catId = set.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if(set != null){
                set.close();
            }
        }
        return catId;
    }
}
