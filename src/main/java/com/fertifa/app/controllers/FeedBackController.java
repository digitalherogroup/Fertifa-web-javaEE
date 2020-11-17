package com.fertifa.app.controllers;

import com.fertifa.app.dao.FeedBackDao;
import com.fertifa.app.models.FeedBackQuestion;
import com.fertifa.app.models.Feedback;
import com.fertifa.app.services.FeedBackService;

import java.sql.SQLException;
import java.util.List;

public class FeedBackController implements FeedBackService {
    private FeedBackDao feedBackDao = new FeedBackDao();

    @Override
    public List<Feedback> AllFeedbacks() throws SQLException {
        return feedBackDao.showAll();
    }
    public List<Feedback> AllFeedbacksQ() throws SQLException {
        return feedBackDao.showAllQ();
    }

    @Override
    public List<Feedback> getFeedBackById(int feedbackId) throws SQLException {
        return feedBackDao.getFeedBackByFeedBackId(feedbackId);
    }

    @Override
    public List<Feedback> getFeedbackByUserId(int userId) throws SQLException {
        return feedBackDao.getFeedBackByUserId(userId);
    }

    public List<Feedback> getFeedBackByUsersId(int userId) throws SQLException {
        return feedBackDao.getFeedBackByUsersId(userId);
    }


    @Override
    public int deleteFeedBack(int id) throws SQLException {
        return feedBackDao.deleteFeedBack(id);
    }

    @Override
    public int addFeedback(Feedback feedback) throws SQLException {
        return feedBackDao.addFeedback(feedback);
    }

    @Override
    public int UpdateFeedBackStatusToApprove(int FeedbackId) throws SQLException {
        return feedBackDao.UpdateFeedBackStatusToApprove(FeedbackId);
    }

    @Override
    public int UpdateFeedBackStatusToReject(int FeedbackId) throws SQLException {
        return feedBackDao.UpdateFeedBackStatusToReject(FeedbackId);
    }

    @Override
    public List<Feedback> GetAllbyRating(int rating) throws SQLException {
        return feedBackDao.getAllFiveRatings(rating);
    }

    @Override
    public List<Feedback> GetAllByBetweenRating(int firstRating, int secondRating) throws SQLException {
        return feedBackDao.getBetweenRatings(firstRating,secondRating);
    }

    @Override
    public List<Feedback> GetAllByDate(String date) throws SQLException {
        return feedBackDao.GetAllByDate(date);
    }

    @Override
    public List<Feedback> GetAllByTwoDates(String start, String end) throws SQLException {
        return feedBackDao.GetAllByTwoDates(start,end);
    }

    @Override
    public int UpdateFeedBackRating(Feedback feedBack, int id) throws SQLException {
        return feedBackDao.UpdateFeedBackRating(feedBack,id);

    }


    public int addUserFeedback(Feedback feedBack) throws SQLException {
        return feedBackDao.addUserFeedback(feedBack);

    }

    public int getAllFeedbackCount() {
        return feedBackDao.getAllFeedbackCount();
    }

    public int getCountLastThreeMonths(String today, String lastThreeMonths) throws SQLException {
        return feedBackDao.getCountLastThreeMonths(today,lastThreeMonths);
    }

    public List<Feedback> GetAllByTwoDatesAndRates(String dateFrom, String dateTo, int rating, int CompanyId) throws SQLException {
        return feedBackDao.GetAllByTwoDatesAndRates(dateFrom,dateTo,rating, CompanyId);
    }

    public int addFeedbackQuestion(Feedback objectsFeedback) throws SQLException {
        return feedBackDao.addFeedbackQuestion(objectsFeedback);
    }

    public List<Feedback> GetAllByTwoDatesAndRatesbyCompanyId(int companyId, String dateFrom, String dateto) throws SQLException {
        return feedBackDao.GetAllByTwoDatesAndRatesbyCompanyId(companyId,dateFrom,dateto);
    }

    public int UpdateFeedBackStatusToRejectQ(int id) throws SQLException {
        return feedBackDao.UpdateFeedBackStatusToRejectQ(id);
    }

    public int UpdateFeedBackStatusToApproveQ(int id) throws SQLException {
        return feedBackDao.UpdateFeedBackStatusToApproveQ(id);
    }

    public List<Feedback> GetAllByTwoDatesAndRatesQ(String dateFrom, String dateTo, int rating) throws SQLException {
        return feedBackDao.GetAllByTwoDatesAndRatesQ(dateFrom,dateTo,rating);
    }

    public int getCountLastThreeMonthsq(String today, String lastThreeMonths, int CompanyId) throws SQLException {
        return feedBackDao.getCountLastThreeMonthsq(today,lastThreeMonths,CompanyId);
    }

    public int getAllFeedbackCountQ(int company) throws SQLException {
        return feedBackDao.getAllFeedbackCountQ(company);
    }

    public List<Feedback> AllQuestionQ() throws SQLException {
        return feedBackDao.AllQuestionQ();
    }

    public int UpdateFeedBackCount(int feedbackId, int count) throws SQLException {
        return feedBackDao.UpdateFeedBackCount(feedbackId, count);
    }

    public int getStatusByFeedBackId(int feedbackId) throws SQLException {
        return feedBackDao.getStatusByFeedBackId(feedbackId);

    }

    public int getLastCount(int feedbackId) throws SQLException {
        return feedBackDao.getLastCount(feedbackId);
    }

    public List<FeedBackQuestion> AllFeedbacksQues() throws SQLException {
        return feedBackDao.AllFeedbacksQues();
    }

    public List<FeedBackQuestion> AllFeedbacksQuesById(int feeedbackId) throws SQLException {
        return feedBackDao.AllFeedbacksQuesById(feeedbackId);
    }

    public List<Feedback> showAllQustions() throws SQLException {
        return feedBackDao.showAllQustions();
    }

    public List<Feedback> getAllFeedBacksByGroup() throws SQLException {
        return feedBackDao.getAllFeedBacksByGroup();
    }

    public List<Feedback> AllFeedbacksById(int companyId) throws SQLException {
        return feedBackDao.AllFeedbacksById(companyId);
    }

    public List<Feedback> AllFeedbacksBy(int companyId) throws SQLException {
        return feedBackDao.AllFeedbacksBy(companyId);
    }
}
