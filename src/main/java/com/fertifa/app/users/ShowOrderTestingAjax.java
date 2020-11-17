package com.fertifa.app.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.TestingOrdersController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.TestingOrders;
import com.fertifa.app.models.Users;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;


@WebServlet("/employee/ShowOrderTestingAjax")
public class ShowOrderTestingAjax extends com.fertifa.app.baseUrl.BaseUrl {

    private UsersController usersController = new UsersController();
    private TestingOrders testingOrders = new TestingOrders();
    private TestingOrdersController testingOrdersController = new TestingOrdersController();
    private Users users = new Users();
    private int DataId;

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        log.info("===============service===============");
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            showOrderTestingAjax(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        log.info("===============doGet===============");
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        log.info("===============doPost===============");
        super.doPost(request, response);
    }

    private void showOrderTestingAjax(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        getParameters(request);
        getServiceById(DataId);
        sendAjaxService(request, response);
    }

    private void sendAjaxService(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (null != testingOrders) {
//            log.info("===============sendAjaxService===============");
            String imageLink = "<img src=" +testingOrders.getImage()+" alt=\"\">";
            Map<String, Object> jsonMap = new LinkedHashMap<>();
            jsonMap.put("status", "success");
            jsonMap.put("id", testingOrders.getId());
            jsonMap.put("title", testingOrders.getTitle());
            jsonMap.put("description", testingOrders.getDescription());
            jsonMap.put("price", testingOrders.getPrice());
            jsonMap.put("image", imageLink);
            StringToJson(response, jsonMap);
           // gotoPage(request, response);
        } else {
            Map<String, Object> jsonMap = new LinkedHashMap<>();
            jsonMap.put("status", "error");
            StringToJson(response, jsonMap);
            //gotoPage(request, response);
        }
    }

    private void StringToJson(HttpServletResponse response, Map<String, Object> jsonMap) throws IOException {
//        log.info("===============StringToJson===============");
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.println(objectMapper.writeValueAsString(jsonMap));
        out.flush();
        out.close();
    }


    private void getParameters(HttpServletRequest request) {
//        log.info("===============getParameters===============");
        if (request.getParameter("data_id") != null) {
            DataId = Integer.parseInt(request.getParameter("data_id"));
        }
    }

    private void getServiceById(long dataId) throws SQLException {
//        log.info("===============getParameters===============");
        //productsResponseDto = productService.getOne(dataId);
        testingOrders = testingOrdersController.findByTestId((int) dataId);
//        log.info("===============productsResponseDto===============",testingOrders);
    }



}
