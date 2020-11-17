package com.fertifa.app.users.Cart;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.OrderController;
import com.fertifa.app.controllers.ServoceController;
import com.fertifa.app.controllers.ShoppingCartController;
import com.fertifa.app.controllers.TestingOrdersController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Orders;
import com.fertifa.app.models.Services;
import com.fertifa.app.models.ShoppingCart;
import com.fertifa.app.models.Users;
import com.fertifa.app.stomp.BaseResponse;
import com.fertifa.app.stomp.GsonConverter;
import com.fertifa.app.stomp.StompChatController;
import com.fertifa.app.utils.TimeController;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/employee/SendOrderTestingDataAjax")
public class SendOrderTestingDataAjax extends com.fertifa.app.baseUrl.BaseUrl {
    private int dataId = 0;
    //private TestingOrders testingObject = new TestingOrders();
    private TestingOrdersController testingOrdersController = new TestingOrdersController();
    private Users users = new Users();
    private UsersController usersController = new UsersController();
    private OrderController orderController = new OrderController();
    private ShoppingCartController shoppingCartController = new ShoppingCartController();
    private GsonConverter gsonConverter = new GsonConverter(new Gson());
    private Services serviceObject = new Services();
    private ServoceController servoceController = new ServoceController();


    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            sendOrderTestingDataAjax(request, response);
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

    private void sendOrderTestingDataAjax(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getParameters(request);
        getFullTestingOrderDetailsById(dataId);
        addToCart(request, response);
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int orderId = CratedOrder();
        String TimeNow = getTimeNow();
        float price=serviceObject.getPrice();
        ShoppingCart shoppingCart = new ShoppingCart(orderId, users.getId(), serviceObject.getTitle(),price , TimeNow, 0);
        saveCard(response, shoppingCart);
    }

    private void saveCard(HttpServletResponse response, ShoppingCart shoppingCart) throws SQLException, IOException {
        String urlUser = "";
        StompChatController stompChatService = new StompChatController();

        shoppingCart.setType(1);
        shoppingCart.setServiceId(serviceObject.getId());
        shoppingCart.setChatId(0);
        if (shoppingCartController.AddNewOrderToChat(shoppingCart) > 0) {
            int shoppingCardId = shoppingCartController.getLatestShopID();
            Map<String, Object> mapJson = new LinkedHashMap<>();
            urlUser = "https://chat.fertifabenefits.com/api/v1/" + users.getId();
            Map<String,Object> shoppingMap = new HashMap<>();
            shoppingMap.put("count",0);
            shoppingMap.put("serviceName",shoppingCart.getServiceName());
            shoppingMap.put("price",shoppingCart.getPrice());
            shoppingMap.put("serviceId",dataId);
            shoppingMap.put("type","1");

            List<Map<String,Object>> shoppingCartList = new ArrayList<>();
            shoppingCartList.add(shoppingMap);

            mapJson.put("dataArray",shoppingCartList);
            mapJson.put("taxAmount",0);
            mapJson.put("finalTotal",shoppingCart.getPrice());
            mapJson.put("finalMainTotal",shoppingCart.getPrice());
            String jsonMessage = new Gson().toJson(mapJson);
            BaseResponse<?> responseChat = stompChatService.addNewTestOrderChat(jsonMessage,1,users.getId());
            Map<String,Object> StringBody = gsonConverter.convertObjectToMapObject(((LinkedTreeMap) responseChat.getData()).get("body"));
            double doubleChatId = (double) StringBody.get("data");
            int chatId = (int) doubleChatId;
            //update shopping Card
            int result = shoppingCartController.updateChatIdByShopId(chatId,shoppingCardId);
            if(0 != result) {
                mapJson.put("status", "success");
                mapJson.put("base_url",urlUser);
                JsonToString(mapJson, response);
            }

        } else {
            Map<String, Object> mapJson = new LinkedHashMap<>();
            mapJson.put("status", "error");
            JsonToString(mapJson, response);
        }
    }

    private String getTimeNow() {
        return TimeController.getTodayDate().toString();
    }

    private int CratedOrder() throws SQLException {
        float price = serviceObject.getPrice();
        int serviceId = serviceObject.getId();
        Orders orders = new Orders(users.getId(), serviceId, price, 0);
        return orderController.AddNewOrder(orders) > 0 ? orderController.lastId(users.getId()) : 0;
    }

    private void JsonToString(Map<String, Object> mapJson, HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.println(objectMapper.writeValueAsString(mapJson));
        out.flush();
        out.close();
    }


    private void getFullTestingOrderDetailsById(int testingId) throws SQLException {
        //productsResponseDto = productsService.getOne((long) testingId);
        serviceObject = servoceController.findService(testingId);
        //testingObject = testingOrdersController.findById(testingId);
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("data_id") != null) {
            dataId = Integer.parseInt(request.getParameter("data_id"));
        }
    }

}
