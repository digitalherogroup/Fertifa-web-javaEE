package com.fertifa.app.adminSide.Bookings;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.OrderController;
import com.fertifa.app.controllers.ShoppingCartController;
import com.fertifa.app.controllers.TimeDateOrderController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Orders;
import com.fertifa.app.models.ServiceDateTime;
import com.fertifa.app.models.ShoppingCart;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/SendOrderDataAjaxResponse")
public class SendOrderDataAjaxResponse extends HttpServlet {
    private int DataId = 0;
    List<Orders> ordersList = new ArrayList<>();
    OrderController orderController = new OrderController();
    private int orderId = 0;
    private int serviceId = 0;
    private int userIdFromOrder = 0;
    private String TokenId = "";
    private int orderChanged = 0;

    private AdminController adminController = new AdminController();
    private List<ServiceDateTime> serviceDateTimeArrayList = new ArrayList<>();
    private TimeDateOrderController timeDateOrderController = new TimeDateOrderController();
    ShoppingCartController shoppingCartController = new ShoppingCartController();
    private Admins admins = new Admins();



    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendOrderDataAjaxResponse(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendOrderDataAjaxResponse(request, response);
    }

    private void sendOrderDataAjaxResponse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getParameters(request);
        getOrderById(DataId);
        getAllDetailsFromOrder();
        changeStatusOfRequest();
        addToCart(response);

    }

    private void addToCart(HttpServletResponse response) throws SQLException, IOException {
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
                        //AddToShoppingCart(shoppingCart, response, TokenId,arraySize);
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
                   // AddToShoppingCart(shoppingCart, response, TokenId,arraySize);
                    addedCount = shoppingCartController.addShoppingCart(shoppingCart);
                }
            }
            if(addedCount > 0){
                finalStepsSuccess(response,TokenId,userIdFromOrder);
            }else {
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

        String message = "Your request has been confirmed";
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


    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

    private void changeStatusOfRequest() throws SQLException {
        Orders orders = new Orders(Constances.ORDERSTATUSAPPROVED);
        orderChanged = orderController.ChangeStatusToApproved(orders, DataId);
    }

    private void getAllDetailsFromOrder() {
        orderId = ordersList.get(0).getId();
        serviceId = ordersList.get(0).getService_id();
        userIdFromOrder = ordersList.get(0).getUser_id();
        TokenId = ordersList.get(0).getSessionToken();
    }

    private void getOrderById(int dataId) throws SQLException {
        ordersList = orderController.orderById(dataId);
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("data_id") != null) {
            DataId = Integer.parseInt(request.getParameter("data_id"));
        }
    }

}
