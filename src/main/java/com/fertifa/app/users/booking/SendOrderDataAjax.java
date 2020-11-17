package com.fertifa.app.users.booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.ChatSessionController;
import com.fertifa.app.controllers.DateOrderController;
import com.fertifa.app.controllers.OrderController;
import com.fertifa.app.controllers.ServoceController;
import com.fertifa.app.controllers.TimeDateOrderController;
import com.fertifa.app.controllers.TimesOrderController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.ChatSession;
import com.fertifa.app.models.DateOrder;
import com.fertifa.app.models.Orders;
import com.fertifa.app.models.ServiceDateTime;
import com.fertifa.app.models.Services;
import com.fertifa.app.models.TimesOrder;
import com.fertifa.app.models.Users;
import com.fertifa.app.stomp.BaseResponse;
import com.fertifa.app.stomp.StompChatController;
import com.fertifa.app.utils.TokenMaker;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/employee/SendOrderDataAjax")
public class SendOrderDataAjax extends com.fertifa.app.baseUrl.BaseUrl {

    private ServoceController servoceController = new ServoceController();
    private List<Services> servicesList = new ArrayList<>();
    private UsersController usersController = new UsersController();
    private int DateId = 0;
    private Services serviceObject = new Services();
    private DateOrderController dateOrderController = new DateOrderController();
    private TimesOrderController timesOrderController = new TimesOrderController();
    private List<String> getDatesTimes = new ArrayList<>();
    private int TimerId = 0;
    private int DaterId = 0;
    private int timeId = 0;
    private int OrderID = 0;
    private String sesionToken = "";
    private String productsName ="";
    private OrderController orderController = new OrderController();
    private Users users = new Users();

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            sendOrderDataAjax(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void sendOrderDataAjax(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        getParameters(request);
        extractDatesTimes();
        getFullDetails(response);

    }

    private void getFullDetails(HttpServletResponse response) throws SQLException, IOException {
        String requestMessage = "";
        String urlUser = "";
        ChatSessionController sessionController = new ChatSessionController();
        Long bookingId = 0l;
        requestMessage = "Request for " + serviceObject.getTitle();
        Timestamp now = new Timestamp(new Date().getTime());
        System.out.println("NOW " + now);
        ChatSession chatMessages = new ChatSession(sesionToken, 2, 1, now, users.getId(), 1, "Request");
        if (sessionController.AddNewSeesionChat(chatMessages) > 0) {


        } else {
            System.out.println("Error");
            return;
        }

        String message = "";
        List<TimesOrder> timesOrdersList = new ArrayList<>();
        Map<String, Object> jsonMap = new LinkedHashMap<>();
        List<ServiceDateTime> list = new ArrayList<>();
        TimeDateOrderController timeDateOrderController = new TimeDateOrderController();
        list = timeDateOrderController.getByUserIdFullDetails(users.getId());
        int serviceId = list.get(0).getService_id();
        bookingId = Long.valueOf(orderController.lastId(users.getId()));

        message = "Hello,\n This is " + users.getFirstName() + " " + users.getLastName() + ", \n Please review my request for the service "
                + productsName + " \n for  dates: " + list.get(0).getDate_time() + ", \n\n please confirm my request";

        //urlUser = Constances.WEBSITE + "employee/chat?id=" + sesionToken;
        urlUser = "https://chat.fertifabenefits.com/api/v1/" + users.getId();

        StompChatController stompChatService = new StompChatController();
        jsonMap.put("message", message);
        jsonMap.put("documentId",bookingId);
        jsonMap.put("status", "success");

        jsonMap.put("session_token", sesionToken);
        BaseResponse<?> responseChat = stompChatService.addNewBookingChat(jsonMap,1,users.getId(),bookingId);
        if(responseChat.getCode() >= 200 && responseChat.getCode() <= 299){
            jsonMap.put("base_url", urlUser);
            JsonObjectResponse(jsonMap, response);
        }else{
            jsonMap.put("status", "error");
            JsonObjectResponse(jsonMap, response);
        }
    }


    private void JsonObjectResponse(Map<String, Object> jsonMap, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.println(mapper.writeValueAsString(jsonMap));
        out.flush();
        out.close();
    }

    private void extractDatesTimes() throws SQLException {
        int orderLastId =0;
        int timeOrderId = 0;
        for (int i = 0; i < getDatesTimes.size(); i++) {
            DaterId = 0;
            DateId = 0;

            if (getDatesTimes.get(i).contains("selectedDate")) {
                List<DateOrder> orderDate = new ArrayList<>();
                DateOrder dateOrder = new DateOrder(getDatesTimes.get(i + 1), OrderID);
                String timefrom = getDatesTimes.get(i + 3);
                String timeto = getDatesTimes.get(i + 5);
                System.out.println(timefrom + " " + timeto);
                dateOrderController.AddNewDateOrder(dateOrder);
                orderLastId = dateOrderController.getLastOrderDateid();
                TimesOrder timesOrder = new TimesOrder(timefrom + " - " + timeto, orderLastId, OrderID);
                timesOrderController.AddNewTimeOrder(timesOrder, orderLastId);
                timeOrderId = timesOrderController.getLastTimeId();
            }
        }
        orderController.UpdateDateTimeIdbyOrderId(timeOrderId, orderLastId, OrderID);
    }


    private void getParameters(HttpServletRequest request) throws SQLException {
        Map<String, String> mapsData = new LinkedHashMap<>();
        getDatesTimes = new ArrayList<>();
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            Object obj = e.nextElement();
            String fieldName = (String) obj;
            String fieldValue = request.getParameter(fieldName);
            mapsData.put(fieldName, fieldValue);
        }

        for (Map.Entry<String, String> entry : mapsData.entrySet()) {
            String k = entry.getKey();
            if (k.equals("data_id")) {
                String value = entry.getValue();
                getServiceFullListBYId(value);
                AddOrderToData();
            } else {
                String value = entry.getValue();
                System.out.println(value);
                getDatesTimes.add(value);
            }
        }
    }

    private void AddOrderToData() throws SQLException {
        sesionToken = TokenMaker.buildToken(9) + "_" + users.getId();
        Orders orders = new Orders(users.getId(), serviceObject.getId(), serviceObject.getPrice(),
                DateId, timeId, Constances.ORDERSTATUSNEW, sesionToken, new Timestamp(new Date().getTime()));
        orderController.AddNewOrder(orders);
        OrderID = orderController.lastId(users.getId());
    }

    private void getServiceFullListBYId(String value) throws SQLException {
        serviceObject = servoceController.findService(Integer.parseInt(value));
        productsName = servoceController.getProductNameById(Integer.parseInt(value));
    }
}
