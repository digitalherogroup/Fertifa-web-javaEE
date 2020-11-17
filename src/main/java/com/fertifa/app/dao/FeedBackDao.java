package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.models.FeedBackQuestion;
import com.fertifa.app.models.Feedback;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedBackDao {
    /**
     * Data gates
     *
     * @return
     * @throws SQLException
     */
    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    public List<Feedback> showAll() throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `feedbacks` ORDER BY `feedbackrating` DESC";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            feedBackStatment(set, feedbackList);

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
        return feedbackList;
    }


    public List<Feedback> showAllDESC() throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `feedbacks` ORDER BY `feedbackrating` DESC";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            feedBackStatment(set, feedbackList);

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
        return feedbackList;
    }

    private void feedBackS(ResultSet set, List<Feedback> feedbackList) throws SQLException {
        Feedback feedback = null;
        while (set.next()) {
            feedback = new Feedback();
            feedback.setId(set.getInt("id"));
            feedback.setUser_id(set.getInt("user_id"));
            feedback.setFeedbackrating(set.getFloat("feedbackrating"));
            feedback.setFeedback_Id(set.getInt("feedback_id"));
            feedback.setCompanyid(set.getInt("company_id"));
            feedback.setFeedbackStatus(set.getInt("feedbackstatus"));
            feedbackList.add(feedback);
        }
    }

    private void feedBackStatment(ResultSet set, List<Feedback> feedbackList) throws SQLException {
        Feedback feedback = null;
        while (set.next()) {
            feedback = new Feedback();
            feedback.setId(set.getInt("id"));
            feedback.setUser_id(set.getInt("user_id"));
            feedback.setFeedbackrating(set.getFloat("feedbackrating"));
            feedback.setFeedbackStatus(set.getInt("feedbackstatus"));
            feedback.setFeddbackSubject(set.getString("feedbacksubject"));
            feedback.setFeedbackText(set.getString("feddbacktext"));
            feedback.setCreationDate(set.getTimestamp("feedbackdate"));
            feedbackList.add(feedback);
        }
    }

    public List<Feedback> getFeedBackByFeedBackId(int companyid) throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT\n" +
                    " COUNT( feedbacks.feedbackstatus ) qnt,\n" +
                    " ROUND((100 / totals.total) * COUNT( feedbacks.feedbackstatus ), 0) percent,\n" +
                    " totals.total, feedbacks.feedbackstatus,\n" +
                    " feedbacks.feedback_id \n" +
                    "FROM\n" +
                    " feedbacks, \n" +
                    " (\n" +
                    "SELECT\n" +
                    " COUNT( user_id ) AS total, feedback_id\n" +
                    "FROM\n" +
                    " feedbacks\n" +
                    " WHERE feedbacks.company_id = " + companyid + " \n" +
                    " GROUP BY\n" +
                    " feedbacks.feedback_id\n" +
                    " ) AS totals\n" +
                    "WHERE\n" +
                    " feedbacks.feedback_id = totals.feedback_id \n" +
                    " AND feedbacks.company_id = " + companyid + " \n" +
                    "GROUP BY\n" +
                    " feedbacks.feedback_id,\n" +
                    "feedbacks.feedbackstatus;";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            feedBackRatingStatment(set, feedbackList);

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
        return feedbackList;
    }

    private void feedBackRatingStatment(ResultSet set, List<Feedback> feedbackList) throws SQLException {
        Feedback feedback = null;
        while (set.next()) {
            feedback = new Feedback();
            feedback.setFeedbackStatus(set.getInt("feedbackstatus"));
            feedback.setFeedback_Id(set.getInt("feedback_id"));
            feedback.setQnt(set.getInt("qnt"));
            feedback.setPercentage(set.getInt("percent"));
            feedbackList.add(feedback);

        }

    }

    public List<Feedback> getFeedBackByUserId(int userId) throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `feedbacks` WHERE `user_id`=" + userId;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            feedBackStatment(set, feedbackList);

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
        return feedbackList;
    }

    public List<Feedback> getFeedBackByUsersId(int userId) throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `feedbacks` WHERE `user_id`=" + userId;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            feedBackS(set, feedbackList);

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
        return feedbackList;
    }

    public int deleteFeedBack(int id) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "DELETE FROM `feedbacks` WHERE  id=" + id;
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

    /**
     * Add Feed back
     *
     * @param feedback
     * @return
     * @throws SQLException
     */
    public int addFeedback(Feedback feedback) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `feedbacks`" +
                    "(`id`, `user_id`,`feedbackrating`,`feedbackstatus`,`feedbackdate`,`company_id`) "
                    + "VALUES (Default,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentFeedBack(feedback, statment);
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

    public int addFeedbackQuestion(Feedback feedback) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `feedquestion`" +
                    "(`id`, `user_id`,`feedbackrating`,`feedbackstatus`,`feedbackdate`,`feedbacksubject`,`feddbacktext`,`company_id`) "
                    + "VALUES (Default,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentFeedBack(feedback, statment);
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

    private void setStatmentFeedBack(Feedback feedback, PreparedStatement statment) throws SQLException {
        statment.setInt(1, feedback.getUser_id());
        statment.setFloat(2, feedback.getFeedbackrating());
        statment.setInt(3, Constances.FEEDBACKSTATUSPENDING);
        statment.setTimestamp(4, new Timestamp(new Date().getTime()));
        statment.setString(5, feedback.getFeddbackSubject());
        statment.setString(6, feedback.getFeedbackText());
        statment.setInt(7, feedback.getCompanyid());
    }

    public int addUserFeedback(Feedback feedback) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `feedbacks`" +
                    "(`id`, `user_id`,`feedbackstatus`,`feedbackdate`,`feedback_id`,`company_id`) "
                    + "VALUES (Default,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setUserStatmentFeedBack(feedback, statment);
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

    private void setUserStatmentFeedBack(Feedback feedback, PreparedStatement statment) throws SQLException {
        statment.setInt(1, feedback.getUser_id());
        statment.setInt(2, feedback.getFeedbackStatus());
        statment.setTimestamp(3, new Timestamp(new Date().getTime()));
        statment.setInt(4, feedback.getFeedback_Id());
        statment.setInt(5, feedback.getCompanyid());
    }

    /**
     * Approve feedback
     *
     * @param feedbackId
     * @return
     * @throws SQLException
     */
    public int UpdateFeedBackStatusToApprove(int feedbackId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `feedbacks`  " +
                    "SET `feedbackstatus`=? WHERE id=" + feedbackId;
            statment = connection.prepareStatement(sql);
            statment.setInt(1, Constances.FEEDBACKSTATUSAPPROVED);

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


    public List<Feedback> getAllFiveRatings(int rating) throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `feedbacks` WHERE `feedbackstatus`=" + rating;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            feedBackStatment(set, feedbackList);

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
        return feedbackList;
    }

    public List<Feedback> getBetweenRatings(int firstRating, int secondRating) throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `feedbacks` WHERE `feedbackrating`>=" + firstRating + " AND `feedbackrating`<=" + secondRating;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            feedBackStatment(set, feedbackList);

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
        return feedbackList;
    }

    public List<Feedback> GetAllByDate(String date) throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `feedbacks` WHERE `feedbackdate`=" + date;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            feedBackStatment(set, feedbackList);

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
        return feedbackList;
    }

    public List<Feedback> GetAllByTwoDates(String start, String end) throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `feedbacks` WHERE `feedbackdate` between" + "\"" + start + "\"" + " AND " + "\"" + end + "\"";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            feedBackStatment(set, feedbackList);

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
        return feedbackList;
    }

    public int UpdateFeedBackStatusToReject(int feedbackId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `feedbacks`  " +
                    "SET `feedbackstatus`=? WHERE id=" + feedbackId;
            statment = connection.prepareStatement(sql);
            statment.setInt(1, Constances.FEEDBACKSTATUSREJECTED);

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

    public int UpdateFeedBackRating(Feedback feedBack, int id) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `feedbacks`  " +
                    "SET `feedbackrating`=? WHERE id=" + id;
            statment = connection.prepareStatement(sql);
            UpdateFeedBackStatus(feedBack, statment);

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

    private void UpdateFeedBackStatus(Feedback feedBack, PreparedStatement statment) throws SQLException {
        statment.setFloat(1, feedBack.getFeedbackrating());
    }

    public int getFeedBackIdbyDateAndUserId(int userId, int FeedBackStatus) {
        int adminId = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `feedbacks` WHERE `user_id`=? AND `feedbackstatus`=? AND `feedbackId`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, userId);
            statment.setInt(2, FeedBackStatus);
            set = statment.executeQuery();
            while (set.next()) {
                adminId = set.getInt("id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return adminId;
    }

    public int getAllFeedbackCount() {
        int count = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `feedbacks`";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                count = set.getInt(1);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return count;

    }

    public int getAllFeedbackCountQ(int companyId) throws SQLException {
        int count = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT COUNT(*) qnt  FROM `feedquestion` WHERE `company_id`=" + companyId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();

            if (set.next()) {
                count = Integer.parseInt(set.getString("qnt"));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }finally {
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
        return count;

    }

    public int getCountLastThreeMonths(String today, String lastThreeMonths) throws SQLException {
        int count = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT Count(*) FROM `feedbacks` WHERE (`creation_date` BETWEEN'" + today + "' AND '" + lastThreeMonths + "')";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                count = set.getInt(1);
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
        return count;
    }

    public int getCountLastThreeMonthsq(String today, String lastThreeMonths, int CompanyId) throws SQLException {
        int count = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();

            String sql =
                    " SELECT COUNT(*) \n" +
                    " FROM `feedquestion` \n" +
                    " WHERE `company_id`= " + CompanyId +
                    " AND `feedbackstatus` = 1 \n" +
                    " AND DATE_FORMAT( feedbackdate, '%Y-%m-%d' ) >= '" + lastThreeMonths + "'" +
                    " AND DATE_FORMAT( feedbackdate, '%Y-%m-%d' ) <= '" + today + "'";

            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                count = set.getInt(1);
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
        return count;
    }

    public List<Feedback> GetAllByTwoDatesAndRates(String dateFrom, String dateTo, int rating, int CompanyId) throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        String sql;
        try {
            connection = ConnectToData();
            sql = "SELECT * FROM `feedquestion` WHERE `feedbackrating` <> 9000 AND `feedbackstatus`=1 AND `company_id` = " + CompanyId;
            if (rating > 0) {
                sql += " AND `feedbackrating`>=" + rating;

            }

            if (!dateFrom.isEmpty()) {
                sql += " AND DATE_FORMAT( feedbackdate, '%Y-%m-%d' ) >= '" + dateFrom + "'";
            }

            if (!dateTo.isEmpty()) {
                sql += " AND DATE_FORMAT( feedbackdate, '%Y-%m-%d' ) <= '" + dateTo + "'";
            }

            sql += " ORDER BY `feedbackdate` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            feedBackStatment(set, feedbackList);

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
        return feedbackList;
    }

    public List<Feedback> showAllQ() throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `feedbacks` ORDER BY `id` DESC ";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            feedBackStatment(set, feedbackList);

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
        return feedbackList;
    }

    public List<Feedback> AllFeedbacksById(int companyId) throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `feedquestion` WHERE `feedbackstatus`=1 AND `company_id`= " + companyId + " ORDER BY `feedbackdate` DESC ";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            feedBackStatmentQuestion(set, feedbackList);

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
        return feedbackList;
    }

    public List<Feedback> AllFeedbacksBy(int companyId) throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `feedquestion` WHERE `feedbackstatus`=1 AND `company_id`= " + companyId + " ORDER BY `feedbackdate` DESC ";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            feedBackStatmentQuestion(set, feedbackList);

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
        return feedbackList;
    }


    public List<Feedback> showAllQustions() throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `feedquestion` ORDER BY `id` DESC ";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            feedBackStatmentQuestion(set, feedbackList);

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
        return feedbackList;
    }

    private void feedBackStatmentQuestion(ResultSet set, List<Feedback> feedbackList) throws SQLException {
        Feedback feedback = null;
        while (set.next()) {
            feedback = new Feedback();
            feedback.setId(set.getInt("id"));
            feedback.setFeedbackrating(set.getFloat("feedbackrating"));
            feedback.setFeedbackStatus(set.getInt("feedbackstatus"));
            feedback.setCreationDate(set.getTimestamp("feedbackdate"));
            feedback.setFeddbackSubject(set.getString("feedbacksubject"));
            feedback.setFeedbackText(set.getString("feddbacktext"));
            feedback.setCompanyid(set.getInt("company_id"));
            feedbackList.add(feedback);

        }
    }

    public List<Feedback> GetAllByTwoDatesAndRatesbyCompanyId(int companyId, String dateFrom, String dateto) throws SQLException {
        String sql = "";
        List<Feedback> usersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();

            sql = "SELECT * from `feedquestion` where companyid > -1";

            if (companyId > 0) {
                sql += " and `companyid`=" + companyId;

            }

            if (!dateFrom.isEmpty()) {
                sql += " and `creation_date` between" + "\"" + dateFrom + "\"";
            }

            if (!dateto.isEmpty()) {
                sql += " and " + "\"" + dateto + "\"";
            }
            System.out.println("sql" + sql);
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            feedBackStatment(set, usersList);

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
        return usersList;
    }

    public int UpdateFeedBackStatusToRejectQ(int id) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `feedquestion`  " +
                    "SET `feedbackstatus`=? WHERE id=" + id;
            statment = connection.prepareStatement(sql);
            statment.setInt(1, Constances.FEEDBACKSTATUSREJECTED);

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

    public int UpdateFeedBackStatusToApproveQ(int id) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `feedquestion`  " +
                    "SET `feedbackstatus`=? WHERE id=" + id;
            statment = connection.prepareStatement(sql);
            statment.setInt(1, Constances.FEEDBACKSTATUSAPPROVED);

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

    public List<Feedback> GetAllByTwoDatesAndRatesQ(String dateFrom, String dateTo, int rating) throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        String sql;
        try {
            connection = ConnectToData();
            sql = "SELECT * FROM `feedquestion` WHERE `feedbackrating` <> 9000";
            if (rating > 0) {
                sql += " AND `feedbackrating`>=" + rating;

            }

            if (!dateFrom.isEmpty()) {
                sql += " AND `feedbackdate` BETWEEN" + "\"" + dateFrom + "\"";
            }

            if (!dateTo.isEmpty()) {
                sql += " AND " + "\"" + dateTo + "\"";
            }

            sql += " ORDER BY `feedbackdate` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            feedBackStatment(set, feedbackList);

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
        return feedbackList;
    }


    public List<Feedback> AllQuestionQ() throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `feedbacks` " +
                    "ORDER BY `feedbackrating` DESC ";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            feedBackStatment(set, feedbackList);

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
        return feedbackList;
    }

    public int UpdateFeedBackCount(int feedBack, int status) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `feedbackquestions` SET ";

            if (status == 1) {
                sql += " `yes` = `yes` + 1 ";
            } else if (status == 2) {
                sql += " `no` = `no` + 1 ";
            } else if (status == 3) {
                sql += " `maybe` = `maybe` + 1 ";
            }
            sql += " WHERE `id` = " + feedBack;
            statment = connection.prepareStatement(sql);

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

    public int getStatusByFeedBackId(int feedbackId) throws SQLException {
        int count = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `feedbacks` WHERE `feedback_id=`" + feedbackId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                count = set.getInt(1);
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
        return count;
    }

    public int getLastCount(int feedbackId) throws SQLException {
        int count = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `feedbacks` WHERE `feedback_id=`" + feedbackId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                count = set.getInt(1);
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
        return count;
    }

    public List<FeedBackQuestion> AllFeedbacksQues() throws SQLException {
        List<FeedBackQuestion> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `feedbackquestions` ORDER BY `id` DESC ";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            feedBackStatmentFeed(set, feedbackList);

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
        return feedbackList;
    }

    public List<FeedBackQuestion> AllFeedbacksQuesById(int id) throws SQLException {
        List<FeedBackQuestion> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `feedbackquestions` WHERE `id`=" + id;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            feedBackStatmentFeed(set, feedbackList);

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
        return feedbackList;
    }

    private void feedBackStatmentFeed(ResultSet set, List<FeedBackQuestion> feedbackListQues) throws SQLException {
        FeedBackQuestion feedback = null;
        while (set.next()) {
            feedback = new FeedBackQuestion();
            feedback.setId(set.getInt("id"));
            feedback.setFeedBackQuestion(set.getString("feedbackquestion"));
            feedbackListQues.add(feedback);

        }
    }

    public List<Feedback> getAllFeedBacksByGroup() throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `feedbacks` " +
                    "GROUP BY `feedback_id`  ";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            feedBackStatment(set, feedbackList);

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
        return feedbackList;
    }
}
