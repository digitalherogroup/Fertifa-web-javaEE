package com.fertifa.app.adminSide.Bookings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.DateOrderController;
import com.fertifa.app.controllers.OrderController;
import com.fertifa.app.controllers.ServoceController;
import com.fertifa.app.controllers.ShoppingCartController;
import com.fertifa.app.controllers.TimeDateOrderController;
import com.fertifa.app.controllers.TimesOrderController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.DateOrder;
import com.fertifa.app.models.Orders;
import com.fertifa.app.models.ServiceDateTime;
import com.fertifa.app.models.Services;
import com.fertifa.app.models.ShoppingCart;
import com.fertifa.app.models.TimesOrder;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/ManageBookRequestAjax")
public class ManageBookRequestAjax extends HttpServlet {
    private int Id = 0;
    private int DataId = 0;

    List<Services> servicesList = new ArrayList<>();
    ServoceController servoceController = new ServoceController();
    OrderController orderController = new OrderController();

    private AdminController adminController = new AdminController();
    List<String> getDatesTimes = new ArrayList<>();
    DateOrderController dateOrderController = new DateOrderController();
    TimesOrderController timesOrderController = new TimesOrderController();
    private int DateId = 0;
    private int DaterId = 0;
    private int TimerId = 0;
    private List<ServiceDateTime> serviceDateTimeArrayList = new ArrayList<>();
    private TimeDateOrderController timeDateOrderController = new TimeDateOrderController();

    ShoppingCartController shoppingCartController = new ShoppingCartController();

    private Admins admins = new Admins();


    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        manageBookRequestAjax(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void manageBookRequestAjax(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request, response);
        getParameters(request);
        extractDatesTimes(response, request);
    }

    private void extractDatesTimes(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        String tokenByOrderId = "";
        int userID = 0;
        for (int i = 0; i < getDatesTimes.size(); i++) {
            DaterId = 0;
            DateId = 0;
            if (getDatesTimes.get(i).contains("selectedDate")) {

                List<Orders> ordersList = new ArrayList<>();
                ordersList = orderController.getAllByOrderId(DataId);
                tokenByOrderId = ordersList.get(0).getSessionToken();
                userID = ordersList.get(0).getUser_id();

                List<DateOrder> orderDate = new ArrayList<>();
                String timefrom = getDatesTimes.get(i + 3);
                String timeto = getDatesTimes.get(i + 5);
                DateOrder dateOrder = new DateOrder(getDatesTimes.get(i + 1), DataId);
                int orderCheck = dateOrderController.AddNewDateOrder(dateOrder);
                int orderLastId = dateOrderController.getLastOrderDateid();

                TimesOrder timesOrder = new TimesOrder(timefrom + " - " + timeto, orderLastId, DataId);
                int timerCheck = timesOrderController.AddNewTimeOrder(timesOrder, DataId);

                DaterId = 0;
                TimerId = 0;

                System.out.println();
            }
        }
        FinalizeThoeOrder(request, response, tokenByOrderId, userID);

    }

    private void FinalizeThoeOrder(HttpServletRequest request, HttpServletResponse response, String tokenByOrderId, int userID) throws SQLException, IOException {

        ShoppingCart shoppingCart = new ShoppingCart();
        String datesToChange = "";
        int arraySize = 0;
        int addedCount = 0;
        serviceDateTimeArrayList = new ArrayList<>();
        serviceDateTimeArrayList = timeDateOrderController.getFullOrderDetails(DataId);
        if (serviceDateTimeArrayList.size() > 0) {
            for (int i = 0; i < serviceDateTimeArrayList.size(); i++) {
                datesToChange = serviceDateTimeArrayList.get(i).getDate_time();
                if (datesToChange.contains("&")) {
                    String[] datesSplit = datesToChange.split("&");
                    arraySize = datesSplit.length;
                    for (int j = 0; j < datesSplit.length; j++) {
                        shoppingCart = new ShoppingCart(
                                serviceDateTimeArrayList.get(i).getOrder_id(),
                                serviceDateTimeArrayList.get(i).getUser_id(),
                                serviceDateTimeArrayList.get(i).getServiceName(),
                                serviceDateTimeArrayList.get(i).getGetServicePrice(),
                                datesSplit[j],
                                serviceDateTimeArrayList.get(i).getStatus());
                        //AddToShoppingCart(shoppingCart, response, tokenByOrderId,arraySize,userID);
                        addedCount = shoppingCartController.addShoppingCart(shoppingCart);
                    }
                } else {
                    shoppingCart = new ShoppingCart(
                            serviceDateTimeArrayList.get(i).getOrder_id(),
                            serviceDateTimeArrayList.get(i).getUser_id(),
                            serviceDateTimeArrayList.get(i).getServiceName(),
                            serviceDateTimeArrayList.get(i).getGetServicePrice(),
                            serviceDateTimeArrayList.get(i).getDate_time(),
                            serviceDateTimeArrayList.get(i).getStatus());
                    //AddToShoppingCart(shoppingCart, response, tokenByOrderId,arraySize,userID);
                    addedCount = shoppingCartController.addShoppingCart(shoppingCart);
                }
            }
            if (addedCount > 0) {
                finalStepsSuccess(response, tokenByOrderId, userID);
            } else {
                finalStepsError(response);
            }
        }
    }

    private void finalStepsError(HttpServletResponse response) throws IOException {
        Map<String, Object> jsonMap = new LinkedHashMap<>();
        jsonMap.put("status", "error");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.println(mapper.writeValueAsString(jsonMap));
        out.flush();
        out.close();
    }


    private void finalStepsSuccess(HttpServletResponse response, String tokenByOrderId, int userId) throws SQLException, IOException {
        Map<String, Object> jsonMap = new LinkedHashMap<>();

        String message = "We just changed your bookings dates or time, please to confirm go to your shopping cart";
        jsonMap.put("status", "success");
        jsonMap.put("message", message);
        jsonMap.put("user_id", userId);
        jsonMap.put("session_token", tokenByOrderId);

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.println(mapper.writeValueAsString(jsonMap));
        out.flush();
        out.close();
    }


    private void getParameters(HttpServletRequest request) throws SQLException {
        if (request.getParameter("data_id") != null) {
            DataId = Integer.parseInt(request.getParameter("data_id"));
        }
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
                UpdateOrderToData();
            } else {
                String value = entry.getValue();
                System.out.println(value);
                getDatesTimes.add(value);
            }
        }
    }

    private void UpdateOrderToData() throws SQLException {
        /* Orders orders = new Orders(com.fertifa.app.Constances.ORDERSTATUSCHANGED);*/
        orderController.ChangeStatusToChanged(DataId);
        //Deleting order by order id
        dateOrderController.deleteByOrderId(DataId);
        //Deleting time
        timesOrderController.deleteByOrderId(DataId);

    }

    private void getServiceFullListBYId(String value) throws SQLException {
        servicesList = servoceController.getById(Integer.parseInt(value));
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }
}
