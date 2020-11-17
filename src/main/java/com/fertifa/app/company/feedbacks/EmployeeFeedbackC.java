package com.fertifa.app.company.feedbacks;

import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.FeedBackController;
import com.fertifa.app.controllers.FeedBackQuestionController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.FeedBackQuestion;
import com.fertifa.app.models.Feedback;
import com.fertifa.app.models.SurveyFedback;
import com.fertifa.app.models.Users;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/employer/feedback")
public class EmployeeFeedbackC extends com.fertifa.app.baseUrl.BaseUrl {
    private UsersController usersController = new UsersController();
    private FeedBackController feedBackController = new FeedBackController();
    private List<Feedback> feedbackList = new ArrayList<>();
    private List<Feedback> FeedbackListQ = new ArrayList<>();
    private List<Feedback> feedbackListPercentage = new ArrayList<>();

    private Map<String, Integer> empFeedbackStarsAmount = new LinkedHashMap<>();

    private int totalFeedback = 0;
    private int ocountLastThreeMonths = 0;
    private List<Feedback> ratingSting = new ArrayList<>();
    private List<SurveyFedback.Question> servayFeedbackList = new ArrayList<>();
    private List<FeedBackQuestion> feedBackQuestionList = new ArrayList<>();
    FeedBackQuestionController feedBackQuestion = new FeedBackQuestionController();

    private Users users = new Users();


    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployerId(request, response));
            employeeFeddbackC(request, response);
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

    private void employeeFeddbackC(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getALlFeedBacks();
        countStars();
        countAllFeedbacks();
        getQuestionFeedback();
        requestPage(request);
        gotoPage(request, response);
    }

    private void getQuestionFeedback() throws SQLException {
        FeedbackListQ = feedBackController.AllFeedbacksQ();
        if(FeedbackListQ == null) FeedbackListQ =  new ArrayList<>();

    }


    private void countAllFeedbacks() throws SQLException {
        totalFeedback = feedBackController.getAllFeedbackCountQ(users.getCompanyId());
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MONTH, -3);
        String lastThreeMonths = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        ocountLastThreeMonths = feedBackController.getCountLastThreeMonthsq(today, lastThreeMonths, users.getId());
    }

    private void requestPage(HttpServletRequest request) {
        request.setAttribute("AllStars", getAllStarts());
        request.setAttribute("FiveStars", empFeedbackStarsAmount.get("5"));
        request.setAttribute("FourStars", empFeedbackStarsAmount.get("4"));
        request.setAttribute("ThreeStars", empFeedbackStarsAmount.get("3"));
        request.setAttribute("TwoStars", empFeedbackStarsAmount.get("2"));
        request.setAttribute("Stars", empFeedbackStarsAmount.get("1"));
        request.setAttribute("feedbackrating", starPercentage());
        request.setAttribute("FeedbackList", feedbackList);
        request.setAttribute("FeedbackListQ", FeedbackListQ);
        request.setAttribute("TotalFeedback", totalFeedback);
        request.setAttribute("CountLastThreeMonths", ocountLastThreeMonths);
        request.setAttribute("EmployerObject", users);
        request.setAttribute("TotalStars", feedbackList.size());
        request.setAttribute("RatingSting", ratingSting);
        request.setAttribute("ServayFeedbackList", servayFeedbackList);
    }

    private void countStars() {

        empFeedbackStarsAmount.put("1", 0);
        empFeedbackStarsAmount.put("2", 0);
        empFeedbackStarsAmount.put("3", 0);
        empFeedbackStarsAmount.put("4", 0);
        empFeedbackStarsAmount.put("5", 0);

        if (feedbackList == null || feedbackList.size() == 0) return;


        for (Feedback feedback : feedbackList) {

            if (feedback.getFeedbackrating() == 1) {
                updateEmpFeedbackStars("1");
            } else if (feedback.getFeedbackrating() == 2) {
                updateEmpFeedbackStars("2");
            } else if (feedback.getFeedbackrating() == 3) {
                updateEmpFeedbackStars("3");
            } else if (feedback.getFeedbackrating() == 4) {
                updateEmpFeedbackStars("4");
            } else if (feedback.getFeedbackrating() == 5) {
                updateEmpFeedbackStars("5");
            }
        }
    }

    private void updateEmpFeedbackStars(String key) {
        for (Map.Entry<String, Integer> entry : empFeedbackStarsAmount.entrySet()) {
            if (entry.getKey().equals(key)) {
                int incrementedStarts = entry.getValue() + 1;
                entry.setValue(incrementedStarts);
            }
        }
    }

    private int getAllStarts() {
        int startsAmount = 0;
        for (Map.Entry<String, Integer> entry : empFeedbackStarsAmount.entrySet()) {
            startsAmount += entry.getValue();
        }
        return startsAmount;
    }

    private double starPercentage() {
        double sum = 0;
        for (Map.Entry<String, Integer> entry : empFeedbackStarsAmount.entrySet()) {
            if (entry.getValue() != 0) {
                sum += (Integer.parseInt(entry.getKey()) * entry.getValue());
            }
        }

        DecimalFormat df2 = new DecimalFormat("###.#");
        return Double.parseDouble(df2.format(sum / getAllStarts()));
    }

    private void getALlFeedBacks() throws SQLException {
        feedbackList = feedBackController.AllFeedbacksById(users.getId());
        if (feedbackList == null) feedbackList = new ArrayList<>();
        getRatings();
    }

    private void getRatings() throws SQLException {
        feedbackListPercentage = feedBackController.getFeedBackById(users.getId());
        if(feedbackListPercentage == null) feedbackListPercentage = new ArrayList<>();
        handleSurveyFeedbacks();

    }

    private void handleSurveyFeedbacks() throws SQLException {
        feedBackQuestionList = new ArrayList<>();
        servayFeedbackList = new ArrayList<>();
        feedBackQuestionList = feedBackQuestion.showAll();
        if(feedBackQuestionList != null) {
            String feedBackQuestions = "";
            int yesPercentage = 0;
            int noPercentage = 0;
            int maybePercentage = 0;
            for (int i = 0; i < feedBackQuestionList.size(); i++) {
                yesPercentage = 0;
                noPercentage = 0;
                maybePercentage = 0;
                feedBackQuestions = feedBackQuestionList.get(i).getFeedBackQuestion();
                for (int j = 0; j < feedbackListPercentage.size(); j++) {
                    if (feedBackQuestionList.get(i).getId() == feedbackListPercentage.get(j).getFeedback_Id()) {
                        int feedbackStatus = feedbackListPercentage.get(j).getFeedbackStatus();
                        switch (feedbackStatus) {
                            case 1:
                                yesPercentage = feedbackListPercentage.get(j).getPercentage();
                                break;
                            case 2:
                                noPercentage = feedbackListPercentage.get(j).getPercentage();
                                break;
                            case 3:
                                maybePercentage = feedbackListPercentage.get(j).getPercentage();
                                break;
                        }
                    }
                }
                servayFeedbackList.add(new SurveyFedback.Question(feedBackQuestions, yesPercentage, noPercentage, maybePercentage));
                if(servayFeedbackList == null) servayFeedbackList = new ArrayList<>();
            }
        }else{
            feedBackQuestionList = new ArrayList<>();
        }

    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("Feedback", "active");
        String url = request.getServletPath() + "/UserFeedBackCompany.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }


}
