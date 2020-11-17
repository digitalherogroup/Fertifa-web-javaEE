package com.fertifa.app;

import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.SessionController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Users;
import com.fertifa.app.statics.GeneralSatatics;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/HomeAdmin")
public class HomeAdmin extends HttpServlet {

    private String SessionEmail = "";
    private int adminId = 0;
    private AdminController adminController = new AdminController();
    private SessionController sessionController = new SessionController();
    private List<Admins> adminFullList = new ArrayList<>();
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            homeAdmin(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            homeAdmin(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void homeAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        UnicodeingSevlet(request);
        getGeneralStatic();
        startAdminControle();
        getAllCompanies();
        SetAttributes(request);
        gotoMasterAdminPage(request,response);
    }

    private void SetAttributes(HttpServletRequest request) {
       /* request.setAttribute("allUsers", AllUsers);
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
*/

        request.setAttribute("myArrayDates", myArrayDates);
        request.setAttribute("myArrayCount", myArrayCount);
        request.setAttribute("myArrayCountCom", myArrayCountCom);

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
    private void gotoMasterAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("com.fertifa.app.StartAdminMaster");
        request.getSession().setAttribute("successNotices",null);
        request.getSession().setAttribute("errorNotices",null);
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
     * All com.fertifa.app.Admin Actions
     */
    private void startAdminControle() {
        getAdminIdByEmail();
        getAdminFullDetails(adminId);
    }

    private void getAdminFullDetails(int adminId) {
        adminFullList = adminController.getAllAdminListById(adminId);
    }

    private void getAdminIdByEmail() {
        adminId = adminController.getAdminId(SessionEmail);
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
        SessionEmail = null;
        sessionController.DistroySession(request);
        request.getRequestDispatcher("com/fertifa/app/adminSide/SignIn.jsp").forward(request, response);
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
