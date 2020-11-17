package com.fertifa.app.company.MulitInvitations;

import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.TempEmpoController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Users;
import com.fertifa.app.utils.SendingEmailNewCompany;
import com.fertifa.app.utils.TokenMaker;
import com.fertifa.app.helpser.ChookiManager;
import com.fertifa.app.parser.EmployeeModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
@WebServlet("/MuliyInvitations")
public class MuliyInvitations extends HttpServlet {
    private List<EmployeeModel> usersList = new ArrayList<>();
    private ArrayList<String> colletingList = new ArrayList<>();
    private UsersController usersController = new UsersController();
    private List<EmployeeModel> usersListForInvitation = new ArrayList<>();
    private String SessionUserEmail = null;
    private int adminId = 0;
    private TempEmpoController tempEmpoController = new TempEmpoController();
    private List<Users> adminFullList = new ArrayList<>();
    private int successNumber=0;
    private int successDeleteNumber=0;
    private int CompanyId=0;
    private List<EmployeeModel> employeeModelList = new ArrayList<>();
    private List<Users> usersListc =  new ArrayList<>();
    private int CompanyRole;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CompanyRole = 2;
            ChookiManager.UserCookie userCookie = ChookiManager.getCookiData(request,response, CompanyRole);
            if (userCookie == null) {
                // go to login page
                gotoLoginPage(request, response);
                return;
            }
            SessionUserEmail = userCookie.getUserEmail();
            CompanyId = Integer.parseInt(userCookie.getUserId());
            //CompanyRole = Integer.parseInt(userCookie.getUserRole());
            muliyInvitations(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                gotoLoginPage(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CompanyRole = 2;
            ChookiManager.UserCookie userCookie = ChookiManager.getCookiData(request,response, CompanyRole);
            if (userCookie == null) {
                // go to login page
                gotoLoginPage(request, response);
                return;
            }
            SessionUserEmail = userCookie.getUserEmail();
            CompanyId = Integer.parseInt(userCookie.getUserId());
            //CompanyRole = Integer.parseInt(userCookie.getUserRole());
            muliyInvitations(request, response);
        }catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                gotoLoginPage(request, response);
            }
        }
    }

    private void muliyInvitations(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        UnicodeingSevlet(request);
        startAdminControle();
        getAllUserInfoById(CompanyId);
        getParameters(request);
        getAllComapnies();
        checkDestination(request);
        setRequests(request);
        gotoPage(request, response);
    }

    private void getAllUserInfoById(int userId) throws SQLException {
        usersListc =  usersController.getAllUsersListById(userId);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("EmployeeTracker.jsp").forward(request, response);
    }

    private void getAllComapnies() throws SQLException {
        usersList = tempEmpoController.GetAllUsers();
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("Tracker","active");
        request.setAttribute("ID", CompanyId);
        request.setAttribute("AdminFullList", adminFullList);
        request.setAttribute("EmployeeModelList", employeeModelList);
        request.setAttribute("UserOnline", usersListc);
    }


    private void checkDestination(HttpServletRequest request) throws SQLException {
        ArrayList<String> workingList = new ArrayList<>();
        workingList = new ArrayList<>(colletingList);
        sendInvitations(workingList, request);
    }

    private int getTheKeyVaue() {
        return colletingList.size() - 1;
    }

    private void sendInvitations(ArrayList<String> workingList, HttpServletRequest request) throws SQLException {
        int count =0;
        for (int i = 0; i < workingList.size(); i++) {
            int UsersId = Integer.parseInt(workingList.get(i));
            usersListForInvitation = tempEmpoController.getUserById(UsersId);
            String fullName = usersListForInvitation.get(0).getFirsName() + " " + usersListForInvitation.get(0).getLastName();
            String email = usersListForInvitation.get(0).getEmail();

            SendingEmailNewCompany.send((TokenMaker.buildToken(12)), fullName, email,  request);
            count++;
            AddNewInvitedUsers(workingList);

        }
        System.out.println(count);
    }

    private void AddNewInvitedUsers(ArrayList<String> workingList) throws SQLException {
        int countInvitation =0;
        for (int i = 0; i <workingList.size() ; i++) {
            int UsersId = Integer.parseInt(workingList.get(i));
            usersListForInvitation = tempEmpoController.getUserById(UsersId);
            String firstName = usersListForInvitation.get(0).getFirsName();
            String lastName = usersListForInvitation.get(0).getLastName();
            String email = usersListForInvitation.get(0).getEmail();
            int status = Constances.PENDING;
            int branchId = Constances.USERS;
            Timestamp creationDate = usersListForInvitation.get(0).getCreated_date();
            int companyId =(int)usersListForInvitation.get(0).getCompany_id();
            Users users = new Users(firstName,lastName,email,status,branchId,creationDate,companyId);
            successNumber = usersController.AddnewCompanyInvitation(users);
            countInvitation++;
        }
        System.out.println(countInvitation);
        if(successNumber>0){
            DeleteTempUsers(workingList);
        }
    }

    private void DeleteTempUsers(ArrayList<String> workingList) throws SQLException {
        int countDelete =0;
        for (int i = 0; i <workingList.size() ; i++) {
            int UsersId = Integer.parseInt(workingList.get(i));
            successDeleteNumber  = tempEmpoController.DeleteById(UsersId);
            countDelete++;
        }
        System.out.println(countDelete);
        if(successNumber>0){
            getAllTempUsers();
        }
    }

    private void getAllTempUsers() throws SQLException {
        employeeModelList = tempEmpoController.GetAllUsers();
    }

    private void DeleteUsers(ArrayList<String> colletingList) throws SQLException {
        for (String str : colletingList) {
            if (UserDeleted(str) > 0) {
                System.out.println("Deleted successfully");
            } else {
                System.out.println("something wrong");
            }

        }
    }

    private int UserDeleted(String strId) throws SQLException {
        return usersController.DeleteUser(Integer.parseInt(strId));
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
    /**
     * All com.fertifa.app.Admin Actions
     */
    private void startAdminControle() throws SQLException {
        getAdminIdByEmail();
        getAdminFullDetails(adminId);
    }

    /**
     * com.fertifa.app.Admin full details
     * @param adminId
     */
    private void getAdminFullDetails(int adminId) throws SQLException {
        adminFullList = usersController.getAllUsersListById(adminId);
    }

    /**
     * Get admin Id by Session email
     */
    private void getAdminIdByEmail() {
        adminId = usersController.getCompanyIdByEmail(SessionUserEmail);
    }

    /**
     * goto login page when user session ends
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void gotoLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (CompanyRole == 2) {
            response.sendRedirect("employerloginpage.jsp");
        } else if (CompanyRole == 3) {
            response.sendRedirect("employeeloginpage.jsp");
        } else {
            response.sendRedirect("splash.jsp");
        }
    }

    /**
     * Unicode utf8 decleration
     *
     * @param request
     * @throws UnsupportedEncodingException
     */
    private void UnicodeingSevlet(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
    }

}

