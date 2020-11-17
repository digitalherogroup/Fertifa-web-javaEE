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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employee/feedbackemployee")
public class FeedBackUser extends com.fertifa.app.baseUrl.BaseUrl {

    private UsersController usersController = new UsersController();
    List<FeedBackQuestion> feedBackQuestionList = new ArrayList<>();
    FeedBackQuestionController feedBackQuestionController = new FeedBackQuestionController();
    FeedBackController feedBackController = new FeedBackController();
    List<Feedback> feedbackList = new ArrayList<>();
    private Users users =  new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            feedBackUser(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    private void feedBackUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getAllFeedBackQuestions();
        getAllFeedbacks();
        setAttributes(request);
        gotoPage(request, response);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url =  request.getServletPath()+"/UserFeedBack.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("session_success_message", null);
        request.getSession().setAttribute("session_error_message", null);
    }

    private void getAllFeedbacks() throws SQLException {
        feedbackList = feedBackController.getFeedBackByUsersId(users.getId());
        if (feedbackList.size() > 0) {
            feedBackQuestionList = new ArrayList<>();
        }
    }

    private void setAttributes(HttpServletRequest request) {
        request.setAttribute("chat", "active");
        request.setAttribute("FeedBackQuestionList", feedBackQuestionList);
        request.setAttribute("EmployeeObject", users);
        request.setAttribute("FeedbackList", feedbackList);
        request.setAttribute("SizeFeedback", feedBackQuestionList.size());
    }

    private void getAllFeedBackQuestions() throws SQLException {
        feedBackQuestionList = feedBackQuestionController.showAll();
    }
}
