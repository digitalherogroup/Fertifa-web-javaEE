package com.fertifa.app.services;

import com.fertifa.app.models.Feedback;
import java.sql.SQLException;
import java.util.List;

public interface FeedBackService {
    List<Feedback> AllFeedbacks() throws SQLException;
    List<Feedback> getFeedBackById(int feedbackId) throws SQLException;
    List<Feedback> getFeedbackByUserId(int userId) throws SQLException;
    int deleteFeedBack(int id) throws SQLException;
    int addFeedback(Feedback feedback) throws SQLException;
    int UpdateFeedBackStatusToApprove(int FeedbackId) throws SQLException;
    int UpdateFeedBackStatusToReject(int FeedbackId) throws SQLException;
    List<Feedback> GetAllbyRating(int rating) throws SQLException;
    List<Feedback> GetAllByBetweenRating(int firstRating,int secondRating) throws SQLException;
    List<Feedback> GetAllByDate(String date) throws SQLException;
    List<Feedback> GetAllByTwoDates(String start, String end) throws SQLException;


    int UpdateFeedBackRating(Feedback feedBack, int id) throws SQLException;
}
