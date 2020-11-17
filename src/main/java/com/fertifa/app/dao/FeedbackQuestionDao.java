package com.fertifa.app.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.FeedBackQuestion;

public class FeedbackQuestionDao {

    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }


    public List<FeedBackQuestion> showAll() throws SQLException {
        List<FeedBackQuestion> feedBackQuestionList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        FeedBackQuestion feedBackQuestion = null;
        try {
            connection = ConnectToData();
            String sql = "select * from `feedbackquestions`";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            while (set.next()) {
                feedBackQuestion = new FeedBackQuestion();
                feedBackQuestion.setId(set.getInt("id"));
                feedBackQuestion.setFeedBackQuestion(set.getString("feedbackquestion"));
                feedBackQuestionList.add(feedBackQuestion);
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
        return feedBackQuestionList;
    }

    public String getFeedBackNameBYId(int feedbackId) {
        String feedbackName = "";
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `feedbackquestions` WHERE `id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, feedbackId);
            set = statment.executeQuery();
            while (set.next()) {
                feedbackName = set.getString("feedbackquestion");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return feedbackName;
    }


}
