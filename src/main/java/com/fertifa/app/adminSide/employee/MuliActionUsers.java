package com.fertifa.app.adminSide.employee;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Users;
import com.fertifa.app.utils.SendingEmailNewCompany;
import com.fertifa.app.utils.TokenMaker;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/admin/customer/employee/multi-invitation")
public class MuliActionUsers extends com.fertifa.app.baseUrl.BaseUrl {
    private List<Users> usersList = new ArrayList<>();
    private ArrayList<String> colletingList = new ArrayList<>();
    private UsersController usersController = new UsersController();
    private List<Users> companyList = new ArrayList<>();
    private AdminController adminController = new AdminController();
    Users users = new Users();
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            muliActionUsers(request, response);
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

    private void muliActionUsers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getParameters(request);
        getAllComapnies();
        getAllUsers();
        checkDestination(request,response);
        setRequests(request);
        gotoPage(request, response);
    }

    private void getAllComapnies() throws SQLException {
        companyList = usersController.getAllCompaniesByRole("company");
    }

    private void checkDestination(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        ArrayList<String> workingList = new ArrayList<>(colletingList);
        int key=0;
        key = getTheKeyValue();

           if (colletingList.get(key).equals("delete")) {
               workingList.remove(key);
               DeleteUsers(workingList,request,response);
           } else if (colletingList.get(key).equals("invitation")) {
               workingList.remove(key);
               sendInvitations(workingList, request);
           }
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("UsersList", usersList);
        request.setAttribute("CompanyList", companyList);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/customer/employee";
        response.sendRedirect(url);
    }

    private void sendInvitations(ArrayList<String> workingList, HttpServletRequest request) throws SQLException {
        int count=0;
        for (int i = 0; i < workingList.size(); i++) {
            if(checkUserInvitation(workingList.get(i))){
                count++;
                int UsersId = Integer.parseInt(workingList.get(i));
                users = usersController.findById(Constances.USER_ID_INDATA,String.valueOf(UsersId));
                SendingEmailNewCompany.send((TokenMaker.buildToken(12)), users.getFirstName() + " " + users.getLastName(), users.getEmail(), request);
            }

        }if(count > 0){
            String message = "Employers Invited successfully";
            request.getSession().setAttribute("successNotices", message);
        }else{
            String message = "Something went wrong";
            request.getSession().setAttribute("errorNotices", message);
        }
    }

    private boolean checkUserInvitation(String s) throws SQLException {
        List<Users> userStatusList = new ArrayList<>();
        userStatusList = usersController.getAllUsersListById(Integer.parseInt(s));
        return userStatusList.get(0).getStatus() != 0;
    }

    private void DeleteUsers(ArrayList<String> colletingList,HttpServletRequest request, HttpServletResponse response) throws SQLException {
        for (String str : colletingList) {
            if (UserDeleted(str) > 0) {
               request.getSession().setAttribute("successNotices", "Deleted Successfully");
            } else {
                request.getSession().setAttribute("errorNotices", "Something went Wrong");
            }

        }
    }

    private int UserDeleted(String strId) throws SQLException {
        return usersController.DeleteUser(Integer.parseInt(strId));
    }

    private int getTheKeyValue() {
        return colletingList.size() - 1;
    }

    private void getAllUsers() throws SQLException {
        usersList = usersController.getAllCompaniesByRole("Employees");
    }

    private void getParameters(HttpServletRequest request) {
        colletingList = new ArrayList<>();
        Enumeration<String> en = request.getParameterNames();
        while (en.hasMoreElements()) {
            Object objOri = en.nextElement();
            String param = String.valueOf(objOri);
            String value = request.getParameter(param);
            colletingList.add(value);
        }
        checkNewCollection(colletingList);
    }

    private void checkNewCollection(ArrayList<String> colletingList) {
        String all = colletingList.get(0);
        if (colletingList.get(0).equals("on")) {
            colletingList.remove(all);
        }
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
