package com.fertifa.app.users;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Users;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/UpdateUsersC")
@MultipartConfig
public class UpdateUsersC extends HttpServlet {
    private UsersController usersController = new UsersController();
    private static final String UPLOAD_DIRECTORY = "upload";
    private Users users  = new Users();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        updateUsersC(request,response,users);
    }

    private void updateUsersC(HttpServletRequest request, HttpServletResponse response,Users users) throws IOException, ServletException, SQLException {
        getParameters(request, response);
        //UpdateUserDetails(request, response,users);
    }

    private void UpdateUserDetails(HttpServletRequest request, HttpServletResponse response,Users users) throws ServletException, IOException, SQLException {

        if (usersController.UpdateUserMainPage(users, users.getId()) > 0) {
            Map<String, String> jsonMap = new HashMap<>();
            jsonMap.put("status", "success");
            JsonResponse(request, response, users, jsonMap);
        } else {
            Map<String, String> jsonMap = new HashMap<>();
            jsonMap.put("status", "error");
            jsonMap.put("message", "Something went wrong");
            JsonResponse(request, response, users, jsonMap);
        }
    }

    private void JsonResponse(HttpServletRequest request, HttpServletResponse response, Users users, Map<String, String> jsonMap) throws IOException, ServletException {
        jsonMap.put("user_phone", users.getPhoneNumber());
        jsonMap.put("first_name", users.getFirstName());
        jsonMap.put("last_name", users.getLastName());
        jsonMap.put("user_address", users.getAddress());
        JsonToString(response,jsonMap,request);
    }

    private void JsonResponseImage(HttpServletRequest request, HttpServletResponse response, Users users, Map<String, String> jsonMap) throws IOException, ServletException {
        jsonMap.put("user_phone", users.getPhoneNumber());
        jsonMap.put("first_name", users.getFirstName());
        jsonMap.put("last_name", users.getLastName());
        jsonMap.put("user_address", users.getAddress());
        jsonMap.put("image_path", Constances.WEBSITE + "/" + users.getUserImage());
        JsonToString(response,jsonMap,request);
    }

    private void JsonToString(HttpServletResponse response, Map<String, String> jsonMap, HttpServletRequest request) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.println(mapper.writeValueAsString(jsonMap));
        out.flush();
        out.close();
        setParameters(request);
        gotoPage(request, response);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "employee/employee-dashboard";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void setParameters(HttpServletRequest request) {
        request.setAttribute("Tracker", "active");
        request.setAttribute("EmployeeObject", users);
    }

    private void getParameters(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        if (request.getParameter("id") != null) {
            users = usersController.findById(Constances.USER_ID_INDATA, request.getParameter("id"));
        }
        if (request.getParameter("first_name") != null) {
            users.setFirstName(request.getParameter("first_name"));
        }
        if (request.getParameter("last_name") != null) {
            users.setLastName(request.getParameter("last_name"));
        }
        if (request.getParameter("user_phone") != null) {
            users.setPhoneNumber(request.getParameter("user_phone"));
        }
        if (request.getParameter("user_address") != null) {
            users.setAddress(request.getParameter("user_address"));
        }
        /* if (request.getPart("image").getSubmittedFileName() != null) {
            UploadImageToServer(request, response, users);
        } else {
            UpdateCompanyDetails(request, response, users);
        }*/
        if (request.getPart("image").getSubmittedFileName()  != null) {
            UploadImageToServer(request, response,users);
        } else {
            UpdateUserDetails(request, response,users);
        }

    }

    private void UploadImageToServer(HttpServletRequest request, HttpServletResponse response,Users users) throws IOException, ServletException, SQLException {
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + UPLOAD_DIRECTORY;
        //Constances.FFFF = savePath;

        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        for (Part part : request.getParts()) {
            if (part.getName().equals("image")) {
                String fileName = getFileName(part);
                if (new File(fileName).getName() == null) {
                    UpdateUserDetails(request, response,users);
                }
                fileName = new File(fileName).getName();

                String filepat = UPLOAD_DIRECTORY + "/" + fileName;
                part.write(savePath + File.separator + fileName);
                //startUplaoding(request,response,filepat);
                AddToData(request, response, filepat,users);
            }

        }
    }

    private void AddToData(HttpServletRequest request, HttpServletResponse response, String filepat,Users users) throws IOException, ServletException, SQLException {
        String newFilePath = filepat;
        users.setUserImage(newFilePath);
        if (usersController.UpdateUsersDetailsByIdWithimage(users, users.getId()) > 0) {
            Map<String, String> jsonMap = new HashMap<>();
            jsonMap.put("status", "success");
            JsonResponseImage(request,response,users,jsonMap);
        } else {
            Map<String, String> jsonMap = new HashMap<>();
            jsonMap.put("status", "error");
            JsonResponseImage(request,response,users,jsonMap);
        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
