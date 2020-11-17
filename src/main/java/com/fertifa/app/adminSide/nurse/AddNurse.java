package com.fertifa.app.adminSide.nurse;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.models.Admins;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/admin/nurse/create")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AddNurse extends com.fertifa.app.baseUrl.BaseUrl {

    private AdminController adminController = new AdminController();
    private List<Admins> nurseList = new ArrayList<>();

    private String firstName = "";
    private String lastName = "";
    private String Email = "";
    private int Status = 0;
    private String Address  = "";
    private String PhoneNumber = "";
    private String Password = "";
    private Admins admins = new Admins();


    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            addNurse(request, response);
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

    private void addNurse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getparameters(request);
        checkIfEmail(Email,response,request);


    }

    private void checkIfEmail(String email,HttpServletResponse response, HttpServletRequest request) throws SQLException, ServletException, IOException {
        if(adminController.checkIfEmail(email)){
            getAllNurses(request);
            setRequests(request);
            String message = "Nurse already in the database";
            request.getSession().setAttribute("successNotices",message);
            gotopage(request,response);
        }else{
            StarAddingNurse(request,response);
        }
    }



    private void StarAddingNurse(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (adminController.saveNurse(CreateNurseObject()) > 0) {
            getAllNurses(request);
            setRequests(request);
            String message = "Nurse Added Successfully";
            request.getSession().setAttribute("successNotices",message);
            gotopage(request,response);
        }else{
            setRequests(request);
            String message = "Something went wrong";
            request.getSession().setAttribute("errorNotices",message);
            gotopage(request,response);
        }
    }

    private void gotopage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String url ="/admin/nurse";
        response.sendRedirect(url);
    }


    private void setRequests(HttpServletRequest request) {
        request.setAttribute("NurseList",nurseList);
        request.setAttribute("AdminsObject", admins);
    }

    private void getAllNurses(HttpServletRequest request) throws SQLException {
        nurseList = adminController.GetAllNurseList();
    }

    private Admins CreateNurseObject() {
        Date date = new Date();
        return new Admins(firstName,lastName,Email,Status,Address,PhoneNumber,Password, new Timestamp(date.getTime()),4);
    }


    private void getparameters(HttpServletRequest request) {
        //TODO: change to parammap
        firstName = request.getParameter("firstname");
        lastName = request.getParameter("lastName");
        Email = request.getParameter("email");
        Status = Integer.parseInt(request.getParameter("status"));
        Address  = request.getParameter("address");
        PhoneNumber = request.getParameter("phone");
        Password =request.getParameter("password");

    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
