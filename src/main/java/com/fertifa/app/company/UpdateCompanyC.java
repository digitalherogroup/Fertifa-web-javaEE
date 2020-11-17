package com.fertifa.app.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Users;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/UpdateCompanyC")
@MultipartConfig
public class UpdateCompanyC extends com.fertifa.app.baseUrl.BaseUrl {
    private UsersController usersController = new UsersController();
    private static final String UPLOAD_DIRECTORY = "upload";
    private Users users = new Users();


    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        updateCompany(request,response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        updateCompany(request,response);
    }

    private void updateCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        getParameters(request, response);
        UpdateCompanyDetails(request, response,users);
    }

    private void UpdateCompanyDetails(HttpServletRequest request, HttpServletResponse response) {
    }

    private void UpdateCompanyDetails(HttpServletRequest request, HttpServletResponse response, Users users) throws ServletException, IOException, SQLException {
        if (usersController.UpdateCompanyDetailsById(users, users.getId()) > 0) {
            usersController.updateEmployeeCompanyName(users.getComapny(),users.getId());
            JsonMapMaker(response,users,"success");
            setParameters(request);
            gotoPage(request, response);

        } else {
            JsonMapMaker(response,users,"error");
            setParameters(request);
            gotoPage(request, response);

        }
    }


    private void setParameters(HttpServletRequest request) {
        request.setAttribute("Tracker", "active");
        request.setAttribute("EmployerObject", users);

    }

    private void getParameters(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        System.out.println();
        users = usersController.findById(Constances.USER_ID_INDATA,request.getParameter("id"));
        if (request.getParameter("company_name") != null) {
            users.setComapny(request.getParameter("company_name"));
        }
        if (request.getParameter("user_phone") != null) {
            users.setPhoneNumber(request.getParameter("user_phone"));
        }
        if (request.getParameter("user_address") != null) {
            users.setAddress(request.getParameter("user_address"));
        }

        if (request.getPart("image").getSubmittedFileName() != null) {
            UploadImageToServer(request, response, users);
        } else {
            UpdateCompanyDetails(request, response, users);
        }


    }

    private void UploadImageToServer(HttpServletRequest request, HttpServletResponse response, Users users) throws IOException, ServletException, SQLException {
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + UPLOAD_DIRECTORY;
        //com.fertifa.app.Constances.FFFF = savePath;

        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        for (Part part : request.getParts()) {
            if (part.getName().equals("image")) {
                String fileName = getFileName(part);
                // refines the fileName in case it is an absolute path
                fileName = new File(fileName).getName();

                String filepat = UPLOAD_DIRECTORY + "/" + fileName;
                part.write(savePath + File.separator + fileName);
                //startUplaoding(request,response,filepat);
                AddToData(request, response, filepat, users);
            }

        }
    }

    private void AddToData(HttpServletRequest request, HttpServletResponse response, String filepat, Users users) throws IOException, ServletException, SQLException {
        String newFilePath = filepat;
        users.setUserImage(newFilePath);
        if (usersController.UpdateCompanyDetailsByIdWithimage(users, users.getId()) > 0) {
            usersController.updateEmployeeCompanyName(users.getComapny(),users.getId());
            JsonMapMaker(response,users,"success");
            setParameters(request);
            gotoPage(request, response);

        } else {
            JsonMapMaker(response,users,"error");
            setParameters(request);
            gotoPage(request, response);

        }
    }

    private void JsonMapMaker(HttpServletResponse response, Users users, String status) throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("status",status );
        if(status.contains("error")){
            jsonMap.put("message", "Something went wrong");
        }
        jsonMap.put("user_phone", users.getPhoneNumber());
        jsonMap.put("company_name", users.getComapny());
        jsonMap.put("user_address", users.getAddress());
        jsonMap.put("image_path",users.getUserImage());
        //jsonMap.put("image_path", "http://localhost:8077/" + users.getUserImage());
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.println(mapper.writeValueAsString(jsonMap));
        out.flush();
        out.close();
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

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/employer/employer-dashboard";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }


}

