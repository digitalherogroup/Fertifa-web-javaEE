package com.fertifa.app.adminSide.Auth;


import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Users;
import com.fertifa.app.statics.GeneralSatatics;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RolesAllowed("1")
@WebServlet(urlPatterns = {"/admin/admin-dashboard"})
public class AdminLogin extends HttpServlet {

    private String email = null;
    private String password = null;
    private AdminController adminController = new AdminController();
    private int adminId = 0;
    private int adminRole = 0;
    private int adminRoleInPage = 0;
    private String adminRoleString = null;
    private Admins adminFullList = new Admins();
    private UsersController usersController = new UsersController();
    private List<Users> companyList = new ArrayList<>();
    private List<Admins> NurseFullList = new ArrayList<>();

    private Map<String, String> userMapAll = new LinkedHashMap<>();
    private String AllUsers = null;
    private String pendingUsers = null;
    private String ActiveUsers = null;
    private String inActiveUsers = null;

    private String AllCompanies = null;
    private String pendingCompanies = null;
    private String ActiveCompanies = null;
    private String inActiveComapnies = null;
    private String Requestsing = null;
    private String Ratings = null;
    List<String> myArrayDates;
    List<Integer> myArrayCount;
    List<Integer> myArrayCountCom;
    private List<Users> usersStaticList = new ArrayList<>();
    private List<Users> CompanyStaticList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            startAdminMaster(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    private void startAdminMaster(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        HttpSession session = request.getSession();
        if (AdminCookiManager.isUserLogged(request, response)) {
            AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
            Admins admins = adminController.getAdmin(AdminConstances.ADMIN_EMAIL_INDATA, userCookie.getUserEmail());
            SetSessions(session, admins);
            SwitchRoles(admins, request, response);
        } else {
            adminId = 0;
            adminRoleInPage = 0;
            email = "";
            getParameters(request);
            validation(request, response);
        }
    }

    private void SetSessions(HttpSession session, Admins admins) {
        session.setAttribute("adminEmail", admins.getEmail());
        session.setAttribute("adminId", admins.getId());
        session.setAttribute("adminRoleId", admins.getBranchid());
        session.setAttribute("adminRole", admins.getRole());
        session.setMaxInactiveInterval(0);
    }

    private void validation(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (CheckAdminInputs(email, password)) {
            Admins admins = adminController.getAdmin(AdminConstances.ADMIN_EMAIL_INDATA, email);
            SwitchRoles(admins, request, response);

        } else if (email.equals("") || password == null) {
            GotoLoginPage(request, response);
        } else {
            request.getSession().setAttribute("adminMessage","Your login details are not correct");
            GotoLoginPage(request, response);
        }
    }


    private void SwitchRoles(Admins admins, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        System.out.println(admins.getBranchid());
        switch (admins.getBranchid()) {
            case 1:
                startAdminControle(admins, request, response);
                getGeneralStatic();
                getAllCompanies();
                SetAttributes(request);
                gotoMasterAdminPage(admins, request, response);
                break;
            case 4:
                startNurseControle(admins,request, response);
                SetAttributes(request);
                gotoNursesAdmin(admins,request, response);
                break;
            default:
                AdminCookiManager.removeAll(request, response);
                startAdminControle(admins, request, response);
                GotoLoginPage(request, response);
                break;
        }
    }

    private void startNurseControle(Admins nurse,HttpServletRequest request, HttpServletResponse response) {
        if (AdminCookiManager.isUserLogged(request, response)) {
            AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
            adminId = userCookie.getUserId();
            adminRole = userCookie.getUserRole();
            email = userCookie.getUserEmail();
            nurse = adminController.getAdmin(AdminConstances.ADMIN_EMAIL_INDATA,email);
            HttpSession session = request.getSession();
            // put corresponding data to session
            session.setAttribute("adminEmail", nurse.getEmail());
            session.setAttribute("adminId", nurse.getId());
            session.setAttribute("adminRoleString", nurse.getRole());
            session.setAttribute("adminRole", nurse.getBranchid());
            //setting session to expiry in 0
            session.setMaxInactiveInterval(0);
        } else {
            getNurseFullDetails(nurse.getId());
        }

    }

    private void getNurseFullDetails(int nurseId) {
        NurseFullList = adminController.getAllAdminListById(nurseId);
    }

    private void startAdminControle(Admins admins, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        if (AdminCookiManager.isUserLogged(request, response)) {
            admins = adminController.getAdmin(AdminConstances.ADMIN_EMAIL_INDATA, userCookie.getUserEmail());
            SetSessions(session, admins);
        }

        getAdminFullDetails(admins.getId());
    }

    private void getAdminFullDetails(int adminId) {
        adminFullList = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(adminId));
    }

    private void getAllCompanies() throws SQLException {
        companyList = usersController.getAllCompaniesByRole("company");
    }

    private void getGeneralStatic() throws IOException, SQLException {
        GeneralSatatics generalSatatics = new GeneralSatatics();
        userMapAll = generalSatatics.getGeneralStatic();
        AllUsers = userMapAll.get("allUSers");
        pendingUsers = userMapAll.get("pending");
        ActiveUsers = userMapAll.get("active");
        inActiveUsers = userMapAll.get("inActive");
        AllCompanies = userMapAll.get("allCompanies");
        pendingCompanies = userMapAll.get("pendingCompanies");
        ActiveCompanies = userMapAll.get("activeCompanies");
        inActiveComapnies = userMapAll.get("inActiveCompanies");
        Requestsing = userMapAll.get("Requests");
        Ratings = userMapAll.get("com/fertifa/app/feedbacks");
        usersStaticList = generalSatatics.getlast5Daysusers();

        myArrayDates = new ArrayList<>(usersStaticList.size());
        myArrayCount = new ArrayList<>();
        myArrayCountCom = new ArrayList<>();
        if (usersStaticList.size() > 0) {
            for (int i = 0; i < usersStaticList.size(); i++) {
                String[] gg = new String[usersStaticList.size()];
                if (usersStaticList.get(i).getBranchId() == 3) {
                    myArrayCount.add(usersStaticList.get(i).getCount());
                    myArrayCountCom.add(0);
                } else {
                    myArrayCount.add(0);
                    myArrayCountCom.add(usersStaticList.get(i).getCount());
                }
                myArrayDates.add("\"" + usersStaticList.get(i).getDateString() + "\"");
            }
        } else {
            myArrayDates = new ArrayList<>();
            myArrayCount = new ArrayList<>();
        }

    }

    private void gotoNursesAdmin(Admins admins, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminCookiManager.saveDataToCookie(admins.getId(), admins.getEmail(), 4, "NURSE", response);
        request.getSession().setAttribute("adminRole", 4);
        request.getSession().setMaxInactiveInterval(0);
        String url = "/nurse/chat?nurseId=" + admins.getId();
        response.sendRedirect(url);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }


    private void gotoMasterAdminPage(Admins admins, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminCookiManager.saveDataToCookie(admins.getId(), admins.getEmail(), admins.getBranchid(), admins.getRole(), response);
        request.getSession().setAttribute("adminRole", admins.getId());
        request.getSession().setAttribute("adminEmail", admins.getEmail());
        String url = request.getServletPath() + "/admin-dashboard.jsp";
        request.getRequestDispatcher(url).forward(request, response);

        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);

    }

    private void SetAttributes(HttpServletRequest request) {
        request.setAttribute("allUsers", AllUsers);
        request.setAttribute("pendingUsers", pendingUsers);
        request.setAttribute("activeUsers", ActiveUsers);
        request.setAttribute("inActiveUsers", inActiveUsers);

        request.setAttribute("allCompanies", AllCompanies);
        request.setAttribute("pendingCompanies", pendingCompanies);
        request.setAttribute("activeCompanies", ActiveCompanies);
        request.setAttribute("inActiveComapnies", inActiveComapnies);

        request.setAttribute("AdminsObject", adminFullList);
        request.setAttribute("CompanyList", companyList);
        request.setAttribute("AdminId", adminId);
        request.setAttribute("ID", adminId);
        request.setAttribute("Ratings", Ratings);
        request.setAttribute("ID", adminId);
        request.setAttribute("Requestsing", Requestsing);

        request.setAttribute("UsersStaticList", usersStaticList);
        request.setAttribute("CompanyStaticList", CompanyStaticList);

        request.setAttribute("myArrayDates", myArrayDates);
        request.setAttribute("myArrayCount", myArrayCount);
        request.setAttribute("myArrayCountCom", myArrayCountCom);
        request.setAttribute("NurseFullList", NurseFullList);
        request.setAttribute("myArrayCountCom", myArrayCountCom);
    }

    private void GotoLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/login/";
        response.sendRedirect(url);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }

    private boolean CheckAdminInputs(String email, String password) throws SQLException {
        return adminController.validateAdminUsers(email, password);
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameterValues("login") != null) {
            String[] params = request.getParameterValues("login");
            email = params[0];
            password = params[1];
        }

    }

}
