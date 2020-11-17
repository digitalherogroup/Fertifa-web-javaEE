package com.fertifa.app.company.FeedBack;

import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.FeedBackController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Feedback;
import com.fertifa.app.models.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/FeedBackDataAjax")
public class FeedBackDataAjax extends HttpServlet {
    private UsersController usersController = new UsersController();
    private FeedBackController feedBackController = new FeedBackController();
    private String DateTo;
    private String DateFrom;
    private List<Feedback> feedBackList = new ArrayList<>();
    private Map<String, List<Feedback>> jsonMap = new LinkedHashMap<>();
    private String Rating = "";
    private  Users users = new Users();



    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        feedBackDataAjax(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        feedBackDataAjax(request,response);
    }

    private void feedBackDataAjax(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ParseException {
        getParameters(request);
        getDatabaseByDate(request);
        sendDetailsAjax(feedBackList, response, request);
        setRequest(request, response);
        gotoPage(request, response);

    }


    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath() + "/FeedbackValidation.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }

    private void setRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("jsonMap", jsonMap);
        request.setAttribute("Partners", "active");
        request.setAttribute("ID", users.getId());
        request.setAttribute("AdminsObject", users);
    }

    private void sendDetailsAjax(List<Feedback> feedBackList, HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException, ParseException {
        StartFilterJsonModel startFilterJsonModel = new StartFilterJsonModel();
        float stars = 0;
        List<Feedback> feedbackList2 = new ArrayList<>();
        String newString = "";
        if (feedBackList == null || feedBackList.size() == 0) {
            List<StartFilterModel> startFilterModels = new ArrayList<>();
            startFilterJsonModel.setFeedbackData(startFilterModels);
            startFilterJsonModel.setStatus("success");
            String jsonData = new Gson().toJson(startFilterJsonModel);
            sendStarsData(jsonData, response);
            return;
        }

        for (int i = 0; i < feedBackList.size(); i++) {
            newString = "";
            stars = feedBackList.get(i).getFeedbackrating();

            Feedback feedback = new Feedback();
            float counter = 0;
            if (stars != 0) {
                if (stars % 2 == 0.0) {
                    counter = (int) stars - 1;
                } else {
                    counter = (int) (stars + 1);
                }
            }
            if (counter == 0) {
                newString = ("<span><i class=\"far fa-star feedback-result__stars-rated\"></i></span>");
            } else {
                for (int j = 0; j < stars; j++) {
                    newString += ("<span><i class=\"fas fa-star feedback-result__stars-rated\"></i></span>");
                }
            }
            feedback.setNewString(newString);
            feedbackList2.add(new Feedback(feedBackList.get(i).getFeddbackSubject(),
                    feedBackList.get(i).getFeedbackText(),
                    convertDateWithRegex(feedBackList.get(i).getCreationDate().toString(), "dd/MM/yyyy"),
                    feedBackList.get(i).getUser_id(),
                    feedBackList.get(i).getFeedbackStatus(),
                    feedBackList.get(i).getId(),
                    feedBackList.get(i).getFeedbackrating(),
                    feedback.getNewString()));
            System.out.println(feedback.getNewString());
            System.out.println(feedback.getSimpleDate() + " simple");

        }
        // create feedBack json data
        startFilterJsonModel.setStatus("success");
        startFilterJsonModel.setFeedbackData(getStartModel(feedbackList2));

        String jsonData = new Gson().toJson(startFilterJsonModel);
        sendStarsData(jsonData, response);
    }

    private List<StartFilterModel> getStartModel(List<Feedback> feedbackList2) {
        List<StartFilterModel> startFilterModels = new ArrayList<>();
        if (feedbackList2 != null && feedbackList2.size() > 0) {
            for (int i = 0; i < feedbackList2.size(); i++) {
                StartFilterModel startFilterModel = new StartFilterModel();
                startFilterModel.setDate(feedbackList2.get(i).getSimpleDate());
                startFilterModel.setDescription(feedbackList2.get(i).getFeddbackSubject());
                startFilterModel.setStarts(feedbackList2.get(i).getNewString());
                startFilterModel.setRating(feedbackList2.get(i).getFeedbackrating());
                startFilterModel.setFeedbackText(feedbackList2.get(i).getFeedbackText());
                startFilterModels.add(startFilterModel);
            }
            return startFilterModels;
        } else {
            return new ArrayList<>();
        }
    }

    private void sendStarsData(String json, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.println(mapper.writeValueAsString(json));
        out.flush();
        out.close();
    }

    public static String convertDateWithRegex(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date(convertStringToTimestamp(date).getTime()));
    }

    private static Timestamp convertStringToTimestamp(String dateString) {

        SimpleDateFormat dateFormat = null;
        if (dateString.contains(".")) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        if (dateString.contains(",")) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        Timestamp timestamp = null;
        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(dateString);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    public float countStars(float starsShine) {
        float startsing = starsShine - 5;
        System.out.println(startsing % 2);
        if (startsing % 2.0 == 0) {
            return 0.0f;
        } else {
            return 0.5f;
        }
    }

    private void getDatabaseByDate(HttpServletRequest request) throws SQLException {
        //  feedBackList = feedBackController.GetAllByTwoDates(DateFrom, DateTo);
        if (Rating.equals("")) {
            Rating = "0";
        }
        feedBackList = feedBackController.GetAllByTwoDatesAndRates(DateFrom, DateTo, Integer.parseInt(Rating), users.getId());
        System.out.println(feedBackList.size() + " The Size of stars");
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("dateFrom") != null) {
            DateFrom = request.getParameter("dateFrom");
        }
        if (request.getParameter("dateTo") != null) {
            DateTo = request.getParameter("dateTo");
            System.out.println(DateFrom + "  " + DateTo);
        }
        if (request.getParameter("rating") != null) {
            Rating = request.getParameter("rating");
        }
       users = usersController.findById(Constances.USER_ID_INDATA,request.getParameter("id"));
    }

}
