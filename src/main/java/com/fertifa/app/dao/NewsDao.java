package com.fertifa.app.dao;


import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.adminSide.news.NewsRequest;
import com.fertifa.app.adminSide.news.NewsResponse;
import com.fertifa.app.models.NewsModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsDao {
    /**
     * Data gates
     *
     * @return
     * @throws SQLException
     */
    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    /**
     * get all AdminSide.news
     *
     * @return
     * @throws SQLException
     */
    public List<NewsModel> getAll() throws SQLException {
        List<NewsModel> newsModelList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `news` ORDER BY `id` DESC ";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            newsStatment(set, newsModelList);

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

    public List<NewsModel> getAllActives() throws SQLException {
        List<NewsModel> newsModelList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `news` where `status`= 1 ORDER BY `id` DESC ";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            newsStatment(set, newsModelList);

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

    /**
     * get Model by id
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public List<NewsModel> getById(int id) throws SQLException {
        List<NewsModel> newsModelList = new ArrayList<>();
        NewsModel newsModel = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `news` WHERE id=" + id;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            newsStatment(set, newsModelList);

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

    public int save(NewsRequest newsModel) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `news`" +
                "(`id`, `title`, `shortdescription`, `description`,`thumbnailurl`,`creation_date`,`status`,`categoryone`,`categorytwo`) "
                + "VALUES (Default,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            NewsNewObject(newsModel, statment);
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

    private void NewsNewObject(NewsRequest newsModel, PreparedStatement statement) throws SQLException {
        statement.setString(1, newsModel.getTitle());
        statement.setString(2, newsModel.getDescription());
        statement.setString(3, newsModel.getContent());
        statement.setString(4, newsModel.getImageLink());
        statement.setTimestamp(5, new Timestamp(new Date().getTime()));
        statement.setInt(6, newsModel.getCategoryActive());
        statement.setString(7, newsModel.getCategoryId().get(0));
        if (newsModel.getCategoryId().size() != 1) {
            statement.setString(8, newsModel.getCategoryId().get(1));
        } else {
            statement.setString(8, null);
        }
    }


    private void setStatmentFoUpdateNewsfinal(NewsModel newsModel, PreparedStatement statment) throws SQLException {
        statment.setString(1, newsModel.getTitle());
        statment.setString(2, newsModel.getShortDescription());
        statment.setString(3, newsModel.getDescription());
        statment.setString(4, newsModel.getThumbnailUrl());
        statment.setTimestamp(5, new Timestamp(new Date().getTime()));

    }

    /**
     * Validating AdminSide.news
     *
     * @param title
     * @param shortDescription
     * @param description
     * @param thumbnailUrl
     * @return
     */
    public boolean validateNewsData(String title, String shortDescription, String description, String thumbnailUrl) {
        boolean isValidAdminUser = false;
        PreparedStatement statment = null;
        Connection connection = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM  `news` WHERE `title`=? AND `shortdescription`=? AND `description`=? AND `thumbnailurl`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, title);
            statment.setString(2, shortDescription);
            statment.setString(3, description);
            statment.setString(4, thumbnailUrl);
            set = statment.executeQuery();
            while (set.next()) {
                isValidAdminUser = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return isValidAdminUser;
    }


    /**
     * Update AdminSide.news
     *
     * @param newsModel
     * @param newsId
     * @return
     * @throws SQLException
     */
    public int update(NewsModel newsModel, int newsId) throws SQLException {

        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `news`  " +
                "SET title=?,shortdescription=?,description=?,thumbnailurl=?,imageupdatedate=? WHERE id=" + newsId;
            statment = connection.prepareStatement(sql);
            setStatmentFoUpdateNewsDates(newsModel, statment);

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

    private void setStatmentFoUpdateNewsDates(NewsModel newsModel, PreparedStatement statment) throws SQLException {
        statment.setString(1, newsModel.getTitle());
        statment.setString(2, newsModel.getShortDescription());
        statment.setString(3, newsModel.getDescription());
        statment.setString(4, newsModel.getThumbnailUrl());
        statment.setTimestamp(5, new Timestamp(new Date().getTime()));
    }

    /**
     * Updat image of AdminSide.news
     *
     * @param newsModel
     * @param id
     * @return
     * @throws SQLException
     */
    public int UpdateImage(NewsModel newsModel, int id) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `news`  " +
                "SET  thumbnailurl=?,title=?,shortdescription=?,description=?  " +
                "WHERE id=" + id;
            statment = connection.prepareStatement(sql);
            statment.setString(1, newsModel.getThumbnailUrl());
            statment.setString(2, newsModel.getTitle());
            statment.setString(3, newsModel.getShortDescription());
            statment.setString(4, newsModel.getDescription());

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

    /**
     * Delete AdminSide.news
     *
     * @param newsId
     * @return
     * @throws SQLException
     */
    public int deleteNews(int newsId) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "DELETE FROM `news` WHERE  id=" + newsId;
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

    private void setStatmentFoUpdateNews(NewsModel newsModel, PreparedStatement statment) throws SQLException {
        statment.setString(1, newsModel.getTitle());
        statment.setString(2, newsModel.getShortDescription());
        statment.setString(3, newsModel.getDescription());

    }

    private void newsStatment(ResultSet set, List<NewsModel> newsModelList) throws SQLException {
        NewsModel newsModel = null;
        while (set.next()) {
            newsModel = new NewsModel();
            newsModel.setId(set.getInt("id"));
            newsModel.setTitle(set.getString("title"));
            newsModel.setShortDescription(set.getString("shortdescription"));
            newsModel.setDescription(set.getString("description"));
            newsModel.setThumbnailUrl(set.getString("thumbnailurl"));
            newsModel.setCreation_Date(set.getTimestamp("creation_date"));
            newsModel.setStatus(set.getString("status"));
            newsModel.setCategoryOne(set.getString("categoryone"));
            newsModel.setCategoryTwo(set.getString("categorytwo"));
            newsModelList.add(newsModel);
        }
    }


    public int getPerivousId(int newsId) {
        int id = 0;
        int idForStatus = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "select * from `news` where `status`=1 AND `id`=(select min(`id`) from `news` where id>" + newsId + ") and status =1";
            statment = connection.prepareStatement(sql);

            set = statment.executeQuery();
            while (set.next()) {
                id = set.getInt(1);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }

        return id;
    }

    public int getNextId(int newsId) {
        int id = 0;
        int idForStatus = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "select * from `news` where `status`=1 AND `id`=(select min(`id`) from `news` where id <" + newsId + ") and status = 1";
            statment = connection.prepareStatement(sql);

            set = statment.executeQuery();
            while (set.next()) {
                id = set.getInt(1);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }

        return id;
    }


    public boolean checkboxDuplicated(String title) throws SQLException {
        boolean isTrue = false;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `news` WHERE `title`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, title);
            set = statment.executeQuery();
            while (set.next()) {
                isTrue = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            if (statment != null) {
                statment.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return isTrue;
    }

    public NewsResponse getNewsObjectById(int newsId) throws SQLException {
        NewsResponse newsResponse = new NewsResponse(new NewsResponse.NewsResponseBuild());
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `news` where `id`=" + newsId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            if (set.next()) {
                newsResponse = NewsRequestObject(set);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statment != null) {
                statment.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return newsResponse;
    }

    private NewsResponse NewsRequestObject(ResultSet set) throws SQLException {
        return new NewsResponse.NewsResponseBuild()
            .id(set.getInt("id"))
            .title(set.getString("title"))
            .shortDescription(set.getString("shortdescription"))
            .Description(set.getString("description"))
            .thumbnailURL(set.getString("thumbnailurl"))
            .creation_date(set.getTimestamp("creation_date"))
            .status(set.getInt("status"))
            .categoryOne(set.getString("categoryone"))
            .categoryTwo(set.getString("categorytwo"))
            .build();
    }

    public int updateObject(NewsRequest newsRequest) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `news`  " +
                "SET `title`=?,`shortdescription`=?,`description`=?,`thumbnailurl`=?,`status`=?,`categoryone`=?,`categorytwo`=?,`imageupdatedate`=? WHERE id=" + newsRequest.getId();
            statement = connection.prepareStatement(sql);
            UpdateNews(newsRequest, statement);

            rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return rowsUpdated;
    }

    private void UpdateNews(NewsRequest newsRequest, PreparedStatement statement) throws SQLException {
        statement.setString(1, newsRequest.getTitle());
        statement.setString(2, newsRequest.getDescription());
        statement.setString(3, newsRequest.getContent());
        statement.setString(4, newsRequest.getImageLink());
        statement.setInt(5, newsRequest.getCategoryActive());
        statement.setTimestamp(8, new Timestamp(new Date().getTime()));
        statement.setString(6, newsRequest.getCategoryId().get(0));
        if (newsRequest.getCategoryId().size() != 1) {
            statement.setString(7, newsRequest.getCategoryId().get(1));
        } else {
            statement.setString(7, null);
        }
    }

    public void updateCategoryOneByNewsId(String newsCategory, int id) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `news`  " +
                "SET `categoryone`=? WHERE id=" + id;
            statement = connection.prepareStatement(sql);
            statement.setString(1, newsCategory);

            rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            System.out.println("exception in updateCategoryOneByNewsId " + exception.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        System.out.println("updateCategoryOneByNewsId updated successfully");
    }

    public void updateCategoryTwoByNewsId(String newsCategory, int id) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `news`  " +
                "SET `categorytwo`=? WHERE id=" + id;
            statement = connection.prepareStatement(sql);
            statement.setString(1, newsCategory);

            rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("exception in updateCategoryTwoByNewsId " + exception.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        System.out.println("updateCategoryTwoByNewsId updated successfully");
    }

    public List<NewsModel> getAllNewsWithCategoryOne(String oldCategory) throws SQLException {
        List<NewsModel> newsModelList = new ArrayList<>();
        NewsModel newsModel = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `news` WHERE `categoryone`='" + oldCategory + "'";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            newsStatment(set, newsModelList);

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

    public List<NewsModel> getAllNewsWithCategoryTwo(String oldCategory) throws SQLException {
        List<NewsModel> newsModelList = new ArrayList<>();
        NewsModel newsModel = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `news` WHERE `categorytwo`='" + oldCategory + "'";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            newsStatment(set, newsModelList);

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

    public List<NewsModel> getNextPreviews(int newsId) throws SQLException {
        List<NewsModel> newsModelList = new ArrayList<>();
        NewsModel newsModel = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `news` WHERE id = " + newsId +
                " and status = 1 " +
                " UNION " +
                " SELECT * FROM `news` where id = (SELECT MAX(id) FROM `news` WHERE id < " + newsId + " and status = 1) " +
                " UNION " +
                " SELECT * FROM `news` where  id = (SELECT MIN(id) FROM `news` WHERE id > " + newsId + " and status = 1) order by id asc";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            newsStatment(set, newsModelList);

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

    public List<NewsModel> getAllNewsWithCategorysOne(String newsCategory) throws SQLException {
        List<NewsModel> newsModelList = new ArrayList<>();
        NewsModel newsModel = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `news` WHERE `categoryOne`='" + newsCategory + "'";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            newsStatment(set, newsModelList);

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

    public List<NewsModel> getAllNewsWithCategorysTwo(String newsCategory) throws SQLException {
        List<NewsModel> newsModelList = new ArrayList<>();
        NewsModel newsModel = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `news` WHERE `categorytwo`='" + newsCategory + "'";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            newsStatment(set, newsModelList);

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

    public int updateCategoriesOne(String newsCategory, int id) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `news`" +
                " set categoryone = ?" +
                " Where id=" + id;
            statement = connection.prepareStatement(sql);
            statement.setString(1, "");

            rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return rowsUpdated;
    }

    public int updateCategoriesTwo(String newsCategory, int id) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `news`" +
                " set categorytwo = ?" +
                " Where id=" + id;
            statement = connection.prepareStatement(sql);
            statement.setString(1, "");

            rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return rowsUpdated;
    }

    public List<NewsModel> getAllPagination(int start) throws SQLException {
        List<NewsModel> newsModelList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `news` LIMIT " + start + ", 10";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            newsStatment(set, newsModelList);

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

    public int countNews() throws SQLException {
        int countedNews = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT COUNT(*) as counter FROM `news`";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            while (set.next()){
                countedNews = set.getInt("counter");
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
        return countedNews;
    }
}
