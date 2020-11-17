package com.fertifa.app.users.FeedBack;

import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.FeedBackController;
import com.fertifa.app.controllers.FeedBackQuestionController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.FeedBackQuestion;
import com.fertifa.app.models.Feedback;
import com.fertifa.app.models.Users;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/employee/submitfeedback")
public class UserFeedBackSubmit extends com.fertifa.app.baseUrl.BaseUrl {

    private UsersController usersController = new UsersController();
    List<FeedBackQuestion> feedBackQuestionList = new ArrayList<>();
    FeedBackQuestionController feedBackQuestionController = new FeedBackQuestionController();
    FeedBackController feedBackController = new FeedBackController();
    List<Feedback> feedbackList = new ArrayList<>();
    private Users users = new Users();

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            userFeedBackSubmit(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    private void userFeedBackSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getParameters(request);
        getAllFeedBackQuestions();
        getAllFeedbacks();
        setAttributes(request);
        gotoPage(request, response);
    }


    private void getAllFeedbacks() throws SQLException {
        feedbackList = feedBackController.getFeedBackByUsersId(users.getId());
        if (feedbackList.size() > 0) {
            feedBackQuestionList = new ArrayList<>();
        }
    }


    private void getParameters(HttpServletRequest request) throws SQLException {
        try {
            List<String> list = new ArrayList<>();
            Enumeration e = request.getParameterNames();

            while (e.hasMoreElements()) {
                Object obj = e.nextElement();
                String fieldName = (String) obj;
                String fieldValue = request.getParameter(fieldName);
                list.add(fieldName + "-" + fieldValue);
            }
            Feedback feedBack = null;
            for (int i = 0; i < list.size(); i++) {
                String[] converter = list.get(i).split("-");
                System.out.println(converter.toString());
                System.out.println(converter[1] + " " + converter[2]);
                int feedbackId = Integer.parseInt(converter[1]);
                int FeedBackStatus = Integer.parseInt(converter[2]);
                Timestamp time = new Timestamp(new Date().getTime());
                feedBack = new Feedback(users.getId(), FeedBackStatus, time, feedbackId, users.getCompanyId());
                feedBackController.addUserFeedback(feedBack);
            }
            // questions saved successfully
            request.getSession().setAttribute("session_success_message", "Thank you for your comments");
        } catch (Exception e) {
            // question can't save to database
            request.getSession().setAttribute("session_error_message", "Something went wrong");
        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/employee/feedbackemployee";
        response.sendRedirect(url);
    }

    private void setAttributes(HttpServletRequest request) {

        request.setAttribute("FeedBackQuestionList", feedBackQuestionList);
        request.setAttribute("EmployeeObject", users);
        request.setAttribute("FeedbackList", feedbackList);
        request.setAttribute("SizeFeedback", feedBackQuestionList.size());
    }

    private void getAllFeedBackQuestions() throws SQLException {
        feedBackQuestionList = feedBackQuestionController.showAll();
    }


}
