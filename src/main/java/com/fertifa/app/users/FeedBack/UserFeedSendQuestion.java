package com.fertifa.app.users.FeedBack;

import com.fertifa.app.constants.Constances;
import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.FeedBackController;
import com.fertifa.app.controllers.FeedBackQuestionController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.FeedBackQuestion;
import com.fertifa.app.models.Feedback;
import com.fertifa.app.models.Users;
import com.fertifa.app.utils.SendEmailToAdmin;
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
import java.util.List;

@WebServlet("/employee/employee-feed-send-question")
public class UserFeedSendQuestion extends com.fertifa.app.baseUrl.BaseUrl {
    private int UserId = 0;
    private UsersController usersController = new UsersController();

    List<Users> usersList = new ArrayList<>();
    private int CompanyId = 0;
    private String Subject = "";
    private String Detail = "";
    private String RatingValue = "";
    List<FeedBackQuestion> feedBackQuestionList = new ArrayList<>();
    List<FeedBackQuestion> filterList = new ArrayList<>();
    FeedBackQuestionController feedBackQuestionController = new FeedBackQuestionController();
    FeedBackController feedBackController = new FeedBackController();
    List<Feedback> feedbackList = new ArrayList<>();
    List<FeedBackQuestion> feedUserQuestionList = new ArrayList<>();
    private Users users =  new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            userFeedSendQuestion(request, response);
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

    private void userFeedSendQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getParameters(request);
        handleSaveFeedBack(request);
        getAllFeedBackQuestions();
        getAllFeedbacks();
        getParameters(request);
        setAttributes(request);
        gotoPage(request, response);
    }


    private void handleSaveFeedBack(HttpServletRequest request) {
        boolean feedbackIsSaved = false;
        try {
            feedbackIsSaved = addFeedBack();
            if (feedbackIsSaved) {
                sendEmailToAdmin(request);
            } else {
                request.getSession().setAttribute("session_error_message", "Your feedback didn't submit, please try again later");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("session_error_message", "Your feedback didn't submit, please try again later");
        }
    }

    private boolean addFeedBack() throws SQLException {
        if (feedBackController.addFeedbackQuestion(getObjectsFeedback()) > 0) {
            System.out.println("Done adding feedback to data");
            return true;
        }
        return false;
    }

    private Feedback getObjectsFeedback() {
        return new Feedback(users.getId(), Constances.FEEDBACKSTATUSPENDING, new Timestamp(new Date().getTime()), Integer.parseInt(RatingValue), Subject, Detail, users.getCompanyId());
    }

    private void getAllFeedbacks() throws SQLException {
        feedbackList = feedBackController.getFeedbackByUserId(UserId);
        if (feedbackList.size() > 0) {
            feedBackQuestionList = new ArrayList<>();
        }
    }


    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/employee/feedbackemployee";
        response.sendRedirect(url);
    }

    private void getAllFeedBackQuestions() throws SQLException {
        feedBackQuestionList = feedBackQuestionController.showAll();

    }

    private void setAttributes(HttpServletRequest request) {
        request.setAttribute("chat", "active");
        request.setAttribute("FeedBackQuestionList", feedBackQuestionList);
        request.setAttribute("EmployeeObject", users);
        request.setAttribute("FeedbackList", feedbackList);
    }


    private void sendEmailToAdmin(HttpServletRequest request) {

        SendEmailToAdmin.sendFeedBack(users.getFirstName(), users.getLastName(), RatingValue, Subject, Detail);
        request.getSession().setAttribute("session_success_message", "Your Feedback has been sent successfully");
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("subject") != null) {
            Subject = request.getParameter("subject");
        }
        if (request.getParameter("details") != null) {
            Detail = request.getParameter("details");
        }

        if (request.getParameter("ratingValue") != null) {
            RatingValue = request.getParameter("ratingValue");
            if (RatingValue.equals("0")) {
                RatingValue = "1";
            }
        }
    }
}
