package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.models.FaqCat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FaqCatDao {
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
     * Check is Category exists
     * @param category
     * @return
     */
    public boolean isCategoryexsits(String category) throws SQLException {
        boolean isValidAdminUser = false;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM  `faqcat` WHERE faqcatname=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, category);
            set = statment.executeQuery();
            while (set.next()) {
                isValidAdminUser = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }finally {
            if(connection != null){
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return isValidAdminUser;
    }


    /**
     * Adding new FAQ Category
     * @param faqCat
     * @return
     * @throws SQLException
     */
    public int AddNewFaqCategory(FaqCat faqCat) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `faqcat`" +
                    "(`id`, `faqcatname`,`categoryfor`) "
                    + "VALUES (Default,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentFaqCategory(faqCat, statment);
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

    /**
     * Statment for update FAQ Category
     * @param faqCat
     * @param statment
     * @throws SQLException
     */
    private void setStatmentFaqCategory(FaqCat faqCat, PreparedStatement statment) throws SQLException {
        statment.setString(1, faqCat.getFaqCatName());
        statment.setInt(2,faqCat.getCategoryFor());

    }
    /**
     * Update Faq Category
     *
     * @param faqCat
     * @param catId
     * @return
     * @throws SQLException
     */
    public int updateFaqCategory(FaqCat faqCat, int catId) throws SQLException {

            int rowsUpdated = 0;
            Connection connection = null;
            PreparedStatement statment = null;
            try {
                connection = ConnectToData();
                String sql = "UPDATE `faqcat`  " +
                        "SET faqcatname=? WHERE id=" + catId;
                statment = connection.prepareStatement(sql);
                setStatmentFoUpdateFaqCategory(faqCat, statment);

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
     * get all FAQ
     *
     * @return
     * @throws SQLException
     */
    public List<FaqCat> getAllFaqCategory() throws SQLException {
        List<FaqCat> faqCats = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `faqcat`";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            faqCategoryStatment(set, faqCats);

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
        return faqCats;
    }

    /**
     * Find faq Category by id
     * @param id
     * @return
     * @throws SQLException
     */
    public List<FaqCat> getFaqCategoryBYId(int id) throws SQLException {
        List<FaqCat> faqCats = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `faqcat` WHERE id="+id;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            faqCategoryStatment(set, faqCats);

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
        return faqCats;
    }

    /**
     * Returns category name by Category id
     * @param id
     * @return
     */
    public String getFaqCatNameById(int id) throws SQLException {
        String catName =null;
        Connection connection =null;
        Statement statement = null;
        ResultSet set = null;
        try{
            connection = ConnectToData();
            String sql ="SELECT `faqcatname` FROM `faqcat` WHERE id="+id;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            while (set.next()){
                catName = set.getString("faqcatname");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
            if(set != null){
                set.close();
            }
        }
        return catName;
    }

    /**
     * Delet Faq Category By Id
     * @param id
     * @return
     * @throws SQLException
     */
    public int deleteFaqCategory(int id) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "DELETE FROM `faqcat` WHERE  id=" + id;
            statment = connection.prepareStatement(sql);

            rowsDeleted = statment.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A Message was deleted successfully!");
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

    /**
     * Faq Cat Statment
     * @param set
     * @param faqCats
     * @throws SQLException
     */
    private void faqCategoryStatment(ResultSet set, List<FaqCat> faqCats) throws SQLException {
        FaqCat faqCat = null;
        while (set.next()) {
            faqCat = new FaqCat();
            faqCat.setId(set.getInt("id"));
            faqCat.setFaqCatName(set.getString("faqcatname"));
            faqCat.setCategoryFor(set.getInt("categoryfor"));
            faqCats.add(faqCat);
        }
    }


    /**
     * Update Statment
     * @param faqCat
     * @param statment
     * @throws SQLException
     */
    private void setStatmentFoUpdateFaqCategory(FaqCat faqCat, PreparedStatement statment) throws SQLException {
        statment.setString(1, faqCat.getFaqCatName());
    }


    public List<FaqCat> findFaqCatAllForCompany() throws SQLException {
        List<FaqCat> faqCats = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `faqcat` WHERE `categoryfor`="+ Constances.COMPANY;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            faqCategoryStatment(set, faqCats);

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
        return faqCats;
    }


    public List<FaqCat> findFaqCatAllForUsers() throws SQLException {
        List<FaqCat> faqCats = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `faqcat` WHERE categoryfor="+ Constances.USERS;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            faqCategoryStatment(set, faqCats);

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
        return faqCats;
    }
}
