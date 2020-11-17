package com.fertifa.app.users.Modal;

import com.fertifa.app.constants.Constances;
import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.ChatSessionController;
import com.fertifa.app.controllers.HealthHistroyController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.ChatSession;
import com.fertifa.app.models.HealthHistory;
import com.fertifa.app.models.Users;
import com.fertifa.app.utils.TokenMaker;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@WebServlet("/employee/SendEnquiryDataAjax")
public class SendEnquiryDataAjax extends com.fertifa.app.baseUrl.BaseUrl {
    private UsersController usersController = new UsersController();
    private Users users = new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            sendEnquiryDataAjax(request, response);
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

    private void sendEnquiryDataAjax(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {


        getParameters(request, response);
        setAttributes(request);
        //sendRequests(request);
        //gotoPage(request,response);

    }
    private void setAttributes(HttpServletRequest request) throws SQLException {
        request.setAttribute("EmployeeObject",users);
    }

    private void getParameters(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Map<String, String> finalMap = new HashMap<>();

        List<HealthHistory> checkIfEmpty = new ArrayList<>();
        for (Map.Entry<String, String[]> entry: map.entrySet()) {
            finalMap.put(entry.getKey(), entry.getValue()[0]);
        }
        String contentJson = new Gson().toJson(finalMap);
        if (contentJson != null && !contentJson.isEmpty()) {
            // save helpth history to database
            HealthHistroyController healthHistroyController = new HealthHistroyController();
            ChatSessionController chatSessionController = new ChatSessionController();

            checkIfEmpty = healthHistroyController.getHistoryById(users.getId());
            if(checkIfEmpty.size() > 0){
                int status = healthHistroyController.updatesave(new HealthHistory(users.getId(), contentJson),users.getId());
                Map<String, String> mapJson = new HashMap<>();
                mapJson.put("status", "success");
                mapJson.put("message", "Your data inserted successfully");
                mapJson.put("saveType", "update");
                JsonObjectResponse(mapJson,response);
            }else {
                int status = healthHistroyController.save(new HealthHistory(users.getId(), contentJson));
                if (status > 0) {

                    if (checkIfEmpty.size() > 0) {

                    } else {
                        String token = TokenMaker.buildToken(6) + "_" + users.getId();
                        String title = "User updated his/her enquiry form";

                        int from = users.getId();
                        int to = 1;
                        System.out.println(token);
                        ChatSession chatSession = new ChatSession(token, Constances.CATEGORYNOTIFICATION, 1,from,to, new Timestamp(new Date().getTime()), users.getId(), 1, title);
                        Map<String, String> mapJson = new HashMap<>();
                        chatSessionController.CreateSession(chatSession);
                        mapJson.put("status", "success");
                        mapJson.put("saveType", "insert");
                        mapJson.put("sessionId", token);
                        mapJson.put("message", "Your enquiry form has been updated successfully");
                        mapJson.put("requestMessage", title);
                        JsonObjectResponse(mapJson,response);
                    }

                } else {
                    Map<String, String> mapJson = new HashMap<>();
                    mapJson.put("status", "error");
                    mapJson.put("message", "Something went wrong");
                    JsonObjectResponse(mapJson,response);

                }
            }
        }
    }

    private void JsonObjectResponse(Map<String, String> mapJson, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.println(mapper.writeValueAsString(mapJson));
        out.flush();
        out.close();
    }
}
