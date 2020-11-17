package com.fertifa.app.controllers;

import com.fertifa.app.dao.FeedbackQuestionDao;
import com.fertifa.app.models.FeedBackQuestion;
import com.fertifa.app.services.FeedBackQuestionService;

import java.sql.SQLException;
import java.util.List;

public class FeedBackQuestionController implements FeedBackQuestionService {

    FeedbackQuestionDao feedbackQuestionDao = new FeedbackQuestionDao();

    @Override
    public List<FeedBackQuestion> showAll() throws SQLException {
        return feedbackQuestionDao.showAll();
    }

    @Override
    public String getFeedBackNameBYId(int feedbackId) {
        return feedbackQuestionDao.getFeedBackNameBYId(feedbackId);
    }

}
