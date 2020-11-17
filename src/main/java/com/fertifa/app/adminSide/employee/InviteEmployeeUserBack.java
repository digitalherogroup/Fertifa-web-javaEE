package com.fertifa.app.adminSide.employee;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.InvitationController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Tokens;
import com.fertifa.app.models.Users;
import com.fertifa.app.utils.SendingEmailNewUser;
import com.fertifa.app.utils.TokenMaker;
import com.fertifa.app.constants.AdminConstances;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/admin/employee/inviter")
public class InviteEmployeeUserBack extends com.fertifa.app.baseUrl.BaseUrl {
    private UsersController usersController = new UsersController();
    private List<Admins> adminsList = new ArrayList<>();
    private List<Users> usersList = new ArrayList<>();
    private AdminController adminController = new AdminController();
    private InvitationController invitationController = new InvitationController();

    private String CompanyEmail = null;
    private String CompanyName = null;
    private String Token = "";
    private String FirstName = "";
    private String LastName = "";
    private int CompanyId =0;
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            inviteEmployeeUserBack(request, response);
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

    private void inviteEmployeeUserBack(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getAdminFullDetails(request,response);
        getParameters(request);
        AddingNewEmployee(request,response);
    }
    private void getAdminFullDetails(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

    private boolean checkUserEmail(String emailCompanyEmail) throws SQLException {
        return usersController.CheckEmail(emailCompanyEmail);
    }

    private void AddingNewEmployee(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (checkIfParametrsGotValues()) {
            checkEmail(request,response);

        } else {

            setRequests(request);
            gotoPage(request, response);
        }
    }

    private void checkEmail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (!checkUserEmail(CompanyEmail)) {
            AddNewCompany(request,response);
        }else {
            String message = "Employee Already exists ";
            request.getSession().setAttribute("errorNotices",message);
            setRequests(request);
            gotoPage(request, response);

        }
    }

    private void AddNewCompany(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (usersController.AddnewUsersInvitation(CreateCompanyCategory()) > 0) {
            String message = "Employee added successfully";
            request.getSession().setAttribute("successNotices",message);
            getTheNewCompanyId();
            CreateToken(request,response);
        }else {
            String message = "com.fertifa.app.Company Something went work, please try again later.";
            request.getSession().setAttribute("errorNotices",message);
            setRequests(request);
            gotoPage(request, response);

        }
    }

    private void CreateToken(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Token = TokenMaker.buildToken(12);
        if (invitationController.AddNewTokenAndUserOne(CreateTokenObject()) > 0) {

            String message = "Token created successfully";
            request.getSession().setAttribute("successNotices",message);
            SendInvitation(request,response);
        }else {
            String message = "Token Something went work, please try again later.";
            request.getSession().setAttribute("errorNotices",message);
            setRequests(request);
            gotoPage(request, response);

        }
    }

    private void SendInvitation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullname = FirstName + " " + LastName;
        SendingEmailNewUser.sendUser(Token, FirstName, LastName, CompanyEmail, request, response);
        String messageIn = "Invitation sent successfully";
        request.getSession().setAttribute("successNotices",messageIn);
        setRequests(request);
        gotoPage(request, response);
    }

    private void getTheNewCompanyId() throws SQLException {
        usersList = usersController.getAllUsersIdies();
        CompanyId = usersController.getlastUserId();
    }


    private Tokens CreateTokenObject() {
        return new Tokens(CompanyId,Token,CompanyEmail,FirstName,LastName);
    }


    private Users CreateCompanyCategory() throws SQLException {
        List<Users> usersList =  new ArrayList<>();
        usersList= usersController.getAllUsersListById(Integer.parseInt(CompanyName));
        String CompanyNameFromId  = usersList.get(0).getComapny();
        return new Users(CompanyEmail,FirstName,LastName, Integer.parseInt(CompanyName), Constances.USERS,Constances.FEEDBACKSTATUSPENDING,new Timestamp(new Date().getTime()),CompanyNameFromId);
    }

    //Checking Parameters values to start building new company details after invitation
    private boolean checkIfParametrsGotValues() {
        return CompanyEmail != null && CompanyName != null ;
    }

    private void getParameters(@NotNull HttpServletRequest request) {
        //TODO: change the waof getting the parametersNames
        if(request.getParameter("companyEmail")!=null){
            CompanyEmail = request.getParameter("companyEmail");
        }
        if(request.getParameter("firstName")!=null){
            FirstName = request.getParameter("firstName");
        }
        if(request.getParameter("lastName")!=null){
            LastName = request.getParameter("lastName");
        }
        if(request.getParameter("selectCompany")!=null){
            CompanyName = request.getParameter("selectCompany");
        }
    }

    private void gotoPage( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/employee/invite";
        request.getRequestDispatcher(url).forward(request, response);

    }

    private void setRequests( HttpServletRequest request) {
        request.setAttribute("AdminFullList",adminsList);
    }

}
