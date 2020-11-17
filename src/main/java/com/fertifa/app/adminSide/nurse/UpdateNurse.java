package com.fertifa.app.adminSide.nurse;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.models.Admins;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/nurse/update")
public class UpdateNurse extends com.fertifa.app.baseUrl.BaseUrl {

    private AdminController adminController = new AdminController();
    private List<Admins> adminFullList = new ArrayList<>();
    private List<Admins> nurseList = new ArrayList<>();
    private String FirstName = "";
    private String LastName = "";
    private String Address = "";
    private String Password = "";
    private String PhoneNumber ="";
    private int Status = 0;
    private int Id = 0;
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            updateNurse(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    private void updateNurse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getParameters(request,response);
        StartUpdatingProcess(request,response);
    }

    private void StartUpdatingProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if(adminController.UpdatNurseById(CreateNurseObject(),Id,request,response) > 0){
            getAllNurses();
            setRequests(request);
            String message = "Nurse Updated Successfully";
            request.getSession().setAttribute("successNotices",message);
            gotopage(request,response);
        }else{
            getAllNurses();
            setRequests(request);
            String message = "Something went wrong";
            request.getSession().setAttribute("errorNotices",message);
            gotopage(request,response);
        }
    }


    private void gotopage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String url = "/admin/nurse";
     response.sendRedirect(url);
        //request.getRequestDispatcher(url).forward(request,response);
        request.getSession().setAttribute("successNotices",null);
        request.getSession().setAttribute("errorNotices",null);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("NurseList",nurseList);
        request.setAttribute("AdminsObject", admins);
    }

    private void getAllNurses() throws SQLException {
        nurseList = adminController.GetAllNurseList();
    }

    private Admins CreateNurseObject() {
        return new Admins(FirstName,LastName,Status,Address,PhoneNumber,Password,4);
    }

    private void getParameters(HttpServletRequest request, HttpServletResponse response) {
        if(request.getParameter("id")!=null){
            Id = Integer.parseInt(request.getParameter("id"));
        }
        if(request.getParameter("firstname")!=null) {
            FirstName = request.getParameter("firstname");
        }
        if(request.getParameter("lastName")!=null) {
            LastName = request.getParameter("lastName");
        }
        if(request.getParameter("password")!=null){
            Password = request.getParameter("password");
        }
        if(request.getParameter("status")!=null){
            Status =Integer.parseInt(request.getParameter("status"));
        }
        if(request.getParameter("address")!= null){
            Address = request.getParameter("address");
        }
        if(request.getParameter("phone")!=  null){
            PhoneNumber = request.getParameter("phone");
        }

    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }


}
