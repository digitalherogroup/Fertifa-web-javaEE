package com.fertifa.app.services;

import com.fertifa.app.models.FeedBackQuestion;

import java.sql.SQLException;
import java.util.List;

public interface FeedBackQuestionService {

    List<FeedBackQuestion> showAll() throws SQLException;

    String getFeedBackNameBYId(int feedbackId);

}
