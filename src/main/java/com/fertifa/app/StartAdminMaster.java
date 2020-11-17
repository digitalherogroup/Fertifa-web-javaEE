package com.fertifa.app;

import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.RoleController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Users;
import com.fertifa.app.statics.GeneralSatatics;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/StartAdminMaster")

public class StartAdminMaster extends HttpServlet {
    private String email = null;
    private String password = null;
    private String SessionEmail = null;
    private AdminController adminController = new AdminController();
    private int adminId = 0;
    private int adminRole = 0;
    private String userRole = null;
    private List<Admins> adminFullList = new ArrayList<>();
    private RoleController roleController = new RoleController();
    private UsersController usersController = new UsersController();
    private List<Users> companyList = new ArrayList<>();

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
        try {
            startAdminMaster(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Application Spark start
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws SQLException
     * @throws ServletException
     */
    private void startAdminMaster(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {

        UnicodeingSevlet(request);
        getParameters(request);
        validation(request, response);

    }

    /**
     * Validation process
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    private void validation(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (CheckAdminInputs(email, password)) {
            adminId = getAdminId(email);
            adminRole = getAdminRoleID(adminId);
            userRole = getAdminRole(adminRole);
            SwitchRoles(userRole, request, response);
        } else {
            GotoLoginPage(request, response);
        }
    }

    /**
     * Get admin role id
     *
     * @param adminId
     * @return
     */
    private int getAdminRoleID(int adminId) {
        return adminController.CheckAdminRoleId(adminId);
    }

    /**
     * com.fertifa.app.Admin role
     *
     * @param roleID
     * @return
     */
    private String getAdminRole(int roleID) {
        return roleController.getRoleByUserId(roleID);
    }

    /**
     * com.fertifa.app.Admin Id
     *
     * @return
     */
    private int getAdminId(String email) {
        return adminController.getAdminId(email);
    }

    /**
     * Roles checker
     *
     * @param adminRole
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void SwitchRoles(String adminRole, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        switch (adminRole.toLowerCase()) {
            case "masteradmin":
                getSession(request);
                getGeneralStatic();
                startAdminControle();
                getAllCompanies();
                SetAttributes(request);
                gotoMasterAdminPage(request, response);
                break;
            case "nurses":
                getSession(request);
                SetAttributes(request);
                startNurseControle();
                gotoNursesAdmin(request, response);
                break;
            default:
                startAdminControle();
                GotoLoginPage(request, response);
                break;
        }
    }

    private void startNurseControle() {
        getNurseIdByEmail();
        getNurseFullDetails(adminId);
    }

    private void getNurseFullDetails(int adminId) {
        adminFullList = adminController.getAllAdminListById(adminId);
    }

    private void getNurseIdByEmail() {
        adminId = adminController.getAdminId(SessionEmail);
    }

    /**
     * All com.fertifa.app.Admin Actions
     */
    private void startAdminControle() {
        getAdminIdByEmail();
        getAdminFullDetails(adminId);
    }

    /**
     * Get admin Id by Session email
     */
    private void getAdminIdByEmail() {
        adminId = adminController.getAdminId(SessionEmail);
    }

    private void getAdminFullDetails(int adminId) {
        adminFullList = adminController.getAllAdminListById(adminId);
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
                myArrayDates.add(usersStaticList.get(i).getDateString());

            }
        } else {
            myArrayDates = new ArrayList<>();
            myArrayCount = new ArrayList<>();
        }

    }


    /**
     * com.fertifa.app.AdminSide.Employee page response
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void gotoNursesAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("DirectChatNurse");
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }

    /**
     * Master page response
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void gotoMasterAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("admin/admin-dashboard/admin-dashboard.jsp").forward(request, response);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);

    }

    /**
     * Setting attributes for page responses
     *
     * @param request
     */
    private void SetAttributes(HttpServletRequest request) {
        request.setAttribute("allUsers", AllUsers);
        request.setAttribute("pendingUsers", pendingUsers);
        request.setAttribute("activeUsers", ActiveUsers);
        request.setAttribute("inActiveUsers", inActiveUsers);

        request.setAttribute("allCompanies", AllCompanies);
        request.setAttribute("pendingCompanies", pendingCompanies);
        request.setAttribute("activeCompanies", ActiveCompanies);
        request.setAttribute("inActiveComapnies", inActiveComapnies);

        request.setAttribute("AdminFullList", adminFullList);
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
    }


    /**
     * Error case send back to login page
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void GotoLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "Your email or password is wrong, please try again");

        request.getRequestDispatcher("com/fertifa/app/adminSide/SignIn.jsp").forward(request, response);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }


    /**
     * Checking admin Inputs
     *
     * @param email
     * @param password
     * @return
     * @throws SQLException
     */
    private boolean CheckAdminInputs(String email, String password) throws SQLException {
        return adminController.validateAdminUsers(email, password);
    }

    /**
     * Getting parameters from page
     *
     * @param request
     */
    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("email") != null) {
            email = request.getParameter("email");
        }
        if (request.getParameter("password") != null) {
            password = request.getParameter("password");
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

    /**
     * Session starts for successful login
     *
     * @param request
     * @throws IOException
     */
    private void getSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("adminsemail", email);
        session.setMaxInactiveInterval(0);
        SessionEmail = (String) session.getAttribute("adminsemail");
        notificationSession(request);
    }

    private void notificationSession(HttpServletRequest request) {
        HttpSession sessionNotifications = request.getSession();
        sessionNotifications.setAttribute("notices", "");
        sessionNotifications.setAttribute("errorNotices", "");
        sessionNotifications.setAttribute("successNotices", "");

    }
}
