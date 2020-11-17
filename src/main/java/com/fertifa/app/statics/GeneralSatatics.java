package com.fertifa.app.statics;

import com.fertifa.app.controllers.ChatSessionController;
import com.fertifa.app.controllers.FeedBackController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.ChatSession;
import com.fertifa.app.models.Feedback;
import com.fertifa.app.models.Users;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GeneralSatatics {
    private int pendingUsers = 0;
    private int pendingCompany = 0;
    private int activeUsers = 0;
    private int requesting = 0;
    private int Feedbacks = 0;
    private int activeCompany = 0;
    private int inActiveUsers = 0;
    private int inActiveCompanies = 0;
    private int countLastSevenDays = 0;
    private UsersController usersController = new UsersController();
    private FeedBackController feedBackController = new FeedBackController();
    private List<Users> allUsers = new ArrayList<>();
    private List<Users> allCompany = new ArrayList<>();
    private List<Users> lastSevenDays = new ArrayList<>();
    List<Feedback> feedBackQuestionList = new ArrayList<>();
    List<Feedback> feedBackQuestionListMap = new ArrayList<>();
    List<ChatSession> chatSessionsList = new ArrayList<>();
    List<ChatSession> chatSessionsListMap = new ArrayList<>();
    ChatSessionController chatSessionController = new ChatSessionController();
    private Map<String, String> mapsJson = new LinkedHashMap<>();


    public Map<String, String> getGeneralStatic() throws IOException, SQLException {
        allUsers = getAllUsers();
        allCompany = getAllCompany();
        feedBackQuestionList = getAllFeedBacks();
        chatSessionsList = getSessionRequests();
        pendingUsers = 0;
        activeUsers = 0;
        requesting = 0;
        Feedbacks = 0;
        pendingCompany = 0;
        activeCompany = 0;
        try {
            for (int i = 0; i < allUsers.size(); i++) {
                if (allUsers.get(i).getStatus() == 0) {
                    pendingUsers++;
                } else {
                    activeUsers++;
                }
            }
            for (int i = 0; i < allCompany.size(); i++) {
                if (allCompany.get(i).getStatus() == 0) {
                    pendingCompany++;
                } else {
                    activeCompany++;
                }
            }

            mapsJson.put("status", "success");
            mapsJson.put("allUSers", String.valueOf(CheckFinalValue(allUsers.size())));
            mapsJson.put("pending", String.valueOf(CheckFinalValue(pendingUsers)));
            mapsJson.put("active", String.valueOf(CheckFinalValue(activeUsers)));
            mapsJson.put("inActive", String.valueOf(CheckFinalValue(inActiveUsers)));
            mapsJson.put("FeedBacks", String.valueOf(CheckFinalValue(feedBackQuestionList.size())));
            mapsJson.put("Requests", String.valueOf(CheckFinalValue(chatSessionsList.size())));
            mapsJson.put("allCompanies", String.valueOf(CheckFinalValue(allCompany.size())));
            mapsJson.put("pendingCompanies", String.valueOf(CheckFinalValue(pendingCompany)));
            mapsJson.put("activeCompanies", String.valueOf(CheckFinalValue(activeCompany)));
            mapsJson.put("inActiveCompanies", String.valueOf(CheckFinalValue(inActiveCompanies)));

        } catch (Exception e) {
            e.printStackTrace();
            mapsJson.put("status", "error");
        }
        return mapsJson;
    }

    private List<ChatSession> getSessionRequests() {
        return chatSessionController.getAllrequests();
    }

    public List<Users> getlast5Daysusers() throws SQLException {
        return usersController.getLastFiveDays();
    }



    public Map<String, String> getGeneralStaticWithCompanyId(int companyId, String dateFrom, String dateto) throws SQLException {
        allUsers = new ArrayList<>();
        allCompany = new ArrayList<>();
        feedBackQuestionList = new ArrayList<>();
        chatSessionsList = new ArrayList<>();

        pendingUsers = 0;
        activeUsers = 0;
        pendingCompany = 0;
        activeCompany = 0;
        allUsers = getAllUsersByCompanyId(companyId, dateFrom, dateto);
        feedBackQuestionListMap = getAllFeedBacksWithCompanyId(companyId, dateFrom, dateto);
        chatSessionsListMap = getChatSession(0, dateFrom, dateto);
        try {
            for (int i = 0; i < allUsers.size(); i++) {
                if (allUsers.get(i).getStatus() == 0) {
                    pendingUsers++;
                } else {
                    activeUsers++;
                }
            }

            allCompany = usersController.getCompanyById(companyId);
            if (!CheckCompanySize()) {
                if (CheckFinalValue(pendingCompany) == 0) {
                    pendingCompany++;
                } else {
                    activeCompany++;
                }
            }

            mapsJson.put("Requests", String.valueOf(CheckFinalValue(chatSessionsListMap.size())));
            mapsJson.put("FeedBacks", String.valueOf(CheckFinalValue(feedBackQuestionList.size())));


            mapsJson.put("status", "success");
            mapsJson.put("allUSers", String.valueOf(CheckFinalValue(allUsers.size())));
            mapsJson.put("pending", String.valueOf(CheckFinalValue(pendingUsers)));
            mapsJson.put("active", String.valueOf(CheckFinalValue(activeUsers)));
            mapsJson.put("inActive", String.valueOf(CheckFinalValue(inActiveUsers)));

            mapsJson.put("allCompanies", String.valueOf(CheckFinalValue(allCompany.size())));
            mapsJson.put("pendingCompanies", String.valueOf(CheckFinalValue(pendingCompany)));
            mapsJson.put("activeCompanies", String.valueOf(CheckFinalValue(activeCompany)));
            mapsJson.put("inActiveCompanies", String.valueOf(CheckFinalValue(inActiveCompanies)));


        } catch (Exception e) {
            e.printStackTrace();
            mapsJson.put("status", "error");
        }
        return mapsJson;
    }

    private List<ChatSession> getChatSession(int companyId, String dateFrom, String dateto) throws SQLException {
        return chatSessionController.GetAllByTwoDates(companyId, dateFrom, dateto);

    }

    private List<Feedback> getAllFeedBacksWithCompanyId(int companyId, String dateFrom, String dateto) throws SQLException {
        return feedBackController.GetAllByTwoDatesAndRatesbyCompanyId(companyId, dateFrom, dateto);
    }

    private boolean CheckCompanySize() {
        return allCompany.size() < 1;
    }


    private List<Users> getAllUsersByCompanyId(int CompanyId, String dateFrom, String dateto) throws SQLException {
        return usersController.getAllUsersByCompanyId(CompanyId, dateFrom, dateto);
    }

    private int CheckFinalValue(int size) {
        return size <= 0 ? 0 : size;
    }

    private List<Users> getAllUsers() throws SQLException {
        return usersController.getAllCompaniesByRole("Employees");
    }

    private List<Users> getAllCompany() throws SQLException {
        return usersController.getAllCompaniesByRole("company");
    }

    private List<Feedback> getAllFeedBacks() throws SQLException {
        return feedBackController.AllFeedbacksQ();
    }
}
