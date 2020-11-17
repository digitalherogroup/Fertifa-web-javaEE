package com.fertifa.app.users.Cart;

import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.ShoppingCartController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.List;
import java.util.Map;

@WebServlet("/employee/deletecarddetail")
public class DeleteCardDetail extends com.fertifa.app.baseUrl.BaseUrl {
    private int orderId=0;
    private UsersController usersController = new UsersController();
    private ShoppingCartController shoppingCartController = new ShoppingCartController();
    private List<com.fertifa.app.models.ShoppingCart> shoppingCartList  = new ArrayList<>();
    private int data_id =0;
    private Users users = new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            deleteCardDetail(request, response);
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

    private void deleteCardDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getParameters(request);
        getAllCartByuser(users.getId());
        DeleteOrderFromCart(data_id,users.getId(),response);
        getOrderId();
        setRequests(request);
       // gotoPage(request,response);
    }

    private void getParameters(HttpServletRequest request) {
        if(request.getParameter("data_id")!= null){
            data_id = Integer.parseInt(request.getParameter("data_id"));
        }
    }

    private void DeleteOrderFromCart(int data_id, int UserId,HttpServletResponse response) throws SQLException, IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        if(shoppingCartController.deleteOrderByUseridAndOrderId(data_id,UserId)>0){
            jsonMap.put("status","success");
           JsonObjectResponse(jsonMap,response);
        }else{
            jsonMap.put("status","error");
            JsonObjectResponse(jsonMap,response);
        }

    }

    private void JsonObjectResponse(Map<String, Object> jsonMap, HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.println(objectMapper.writeValueAsString(jsonMap));
        out.flush();
        out.close();
    }

    private void getOrderId() {
        if(shoppingCartList.size()>0) {
            orderId = shoppingCartList.get(0).getOrder_id();
        }
    }

    private void getAllCartByuser(int userId) throws SQLException {
        shoppingCartList = shoppingCartController.getAllById(userId);
    }



    private void setRequests(HttpServletRequest request) {
        //request.setAttribute("orders","active");
        request.setAttribute("OrderId",orderId);
        request.setAttribute("EmployeeObject",users);
        request.setAttribute("ShoppingCartList",shoppingCartList);
    }


}
