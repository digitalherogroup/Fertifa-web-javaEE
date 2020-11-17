package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.models.Faq;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FaqsDao {

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
     * Show all FAQS
     *
     * @return
     * @throws SQLException
     */
    public List<Faq> showAllFaqs() throws SQLException {
        List<Faq> faqsList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `faqs`";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            faqsStatment(set, faqsList);

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
        return faqsList;
    }

    /**
     * Find faq by id
     * @param id
     * @return
     * @throws SQLException
     */
    public List<Faq> getFaqBYId(int id) throws SQLException {
        List<Faq> faqList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `faqs` WHERE id="+id;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            faqsStatment(set, faqList);

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
        return faqList;
    }

    public List<Faq> getFaqBYFaqId(int id) throws SQLException {
        List<Faq> faqList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `faqs` WHERE `faqcatid`="+id;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            faqsStatment(set, faqList);

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
        return faqList;
    }

    /**
     * Delet Faq  By Id
     * @param id
     * @return
     * @throws SQLException
     */
    public int deleteFaq(int id) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "DELETE FROM `faqs` WHERE  id=" + id;
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
     * Update Faq Question and Answer
     *
     * @param faq
     * @param faqId
     * @return
     * @throws SQLException
     */
    public int updateFaqQuesAnsw(Faq faq, int faqId) throws SQLException {
        {
            int rowsUpdated = 0;
            Connection connection = null;
            PreparedStatement statment = null;
            try {
                connection = ConnectToData();
                String sql = "UPDATE `faqs`  " +
                        "SET `faqquestion`=?,`faqanswear`=? WHERE `id`=" + faqId;
                statment = connection.prepareStatement(sql);
                setStatmentFoUpdateFaqStrings(faq, statment);

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
    }

    /**
     * Update Faq Category id
     *
     * @param faq
     * @param faqId
     * @return
     * @throws SQLException
     */
    public int updateFaqCategoryInFaq(Faq faq, int faqId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `faqs`  " +
                    "SET `faqcatid`=? WHERE id=" + faqId;
            statment = connection.prepareStatement(sql);
            setStatmentFoUpdateFaqCategory(faq, statment);

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
     * Adding new FAQ
     *
     * @param faq
     * @return
     * @throws SQLException
     */
    public int AddNewFaq(Faq faq) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `faqs`" +
                    "(`id`, `faqcatid`,`faqquestion`,`faqanswear`,`categoryfor`) "
                    + "VALUES (Default,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentFaq(faq, statment);
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
     * Statement for update FAQ
     *
     * @param faq
     * @param statment
     * @throws SQLException
     */
    private void setStatmentFaq(Faq faq, PreparedStatement statment) throws SQLException {
        statment.setInt(1, faq.getFaqCatId());
        statment.setString(2, faq.getFaqQuestion());
        statment.setString(3, faq.getFaqAnswear());
        statment.setInt(4,faq.getCategoryFor());
    }

    /**
     * com.fertifa.app.Faqs Statement to all data
     *
     * @param set
     * @param faqsList
     * @throws SQLException
     */
    private void faqsStatment(ResultSet set, List<Faq> faqsList) throws SQLException {
        Faq faq = null;
        while (set.next()) {
            faq = new Faq();
            faq.setId(set.getInt("id"));
            faq.setFaqCatId(set.getInt("faqcatid"));
            faq.setFaqQuestion(set.getString("faqquestion"));
            faq.setFaqAnswear(set.getString("faqanswear"));
            faqsList.add(faq);
        }
    }

    /**
     * Update Statement for Strings
     *
     * @param faq
     * @param statment
     * @throws SQLException
     */
    private void setStatmentFoUpdateFaqStrings(Faq faq, PreparedStatement statment) throws SQLException {
        statment.setString(1, faq.getFaqQuestion());
        statment.setString(2, faq.getFaqAnswear());
    }

    /**
     * Update Statement for Category of FAQ
     *
     * @param faq
     * @param statment
     * @throws SQLException
     */
    private void setStatmentFoUpdateFaqCategory(Faq faq, PreparedStatement statment) throws SQLException {
        statment.setInt(1,faq.getFaqCatId());
    }

    public List<Faq> getAllFaqsForCompany() throws SQLException {
        List<Faq> faqList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `faqs` WHERE `categoryfor`="+ Constances.COMPANY;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            faqsStatment(set, faqList);

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
        return faqList;
    }

    public List<Faq> getAllFaqsForUsers() throws SQLException {
        List<Faq> faqList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `faqs` WHERE `categoryfor`="+ Constances.USERS;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            faqsStatment(set, faqList);

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
        return faqList;
    }

    public List<Faq> GetFaqByFaqcCatId(int intCategoryFor) throws SQLException {
        List<Faq> faqList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `faqs` WHERE `faqcatid`="+ intCategoryFor;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            faqsStatment(set, faqList);

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
        return faqList;
    }
}
