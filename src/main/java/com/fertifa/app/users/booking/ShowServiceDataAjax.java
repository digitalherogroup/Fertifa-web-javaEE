package com.fertifa.app.users.booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fertifa.app.controllers.ServoceController;
import com.fertifa.app.models.Services;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/employee/ShowServiceDataAjax")
@Log4j2
public class ShowServiceDataAjax extends HttpServlet {

    private int DataId=0;
    Services serviceObject = new Services();
    ServoceController serviceController = new ServoceController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("===============doPost===============");
        doGet(request,response);
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("===============doGet===============");
        showServiceDataAjax(request,response);
    }

    private void showServiceDataAjax(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getParameters(request);
        getServiceById(DataId);
        sendAjaxService(request,response);
    }

    private void sendAjaxService(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        if(serviceObject != null){
            log.info("===============productsResponseDto===============");
            String imageLink = "<img src=" +serviceObject.getImageLink()+" alt=''>";
            Map<String,Object> jsonMap = new LinkedHashMap<>();
            jsonMap.put("status","success");
            JsonToString(jsonMap,serviceObject,imageLink,response);

        }else{
            Map<String,Object> jsonMap = new LinkedHashMap<>();
            jsonMap.put("status","error");
            JsonResponse(jsonMap,response);
        }
    }

    private void JsonToString(Map<String, Object> jsonMap, Services serviceObject, String imageLink, HttpServletResponse response) throws IOException {
        log.info("===============JsonToString===============");
        jsonMap.put("id",serviceObject.getId());
        jsonMap.put("title",serviceObject.getTitle());
        jsonMap.put("description",serviceObject.getDescription());
        jsonMap.put("price",serviceObject.getPrice());
        jsonMap.put("image",imageLink);
        JsonResponse(jsonMap,response);
    }

    private void JsonResponse(Map<String, Object> jsonMap, HttpServletResponse response) throws IOException {
        log.info("===============JsonResponse===============");
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.println(objectMapper.writeValueAsString(jsonMap));
        out.flush();
        out.close();
    }

    private void getServiceById(int dataId) throws SQLException {
        log.info("===============getServiceById===============");
        //productsResponseDto = productsService.getOne(Long.valueOf(dataId));
        log.info("===============productsResponseDto===============",serviceObject);
        serviceObject = serviceController.findService(dataId);
    }

    private void getParameters(HttpServletRequest request) {
        if(request.getParameter("data_id")!=null){
            DataId = Integer.parseInt(request.getParameter("data_id"));
        }
        log.info("===============DataId===============",DataId);
    }
}
