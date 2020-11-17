package com.fertifa.app.sales;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.ServoceController;
import com.fertifa.app.controllers.SessionController;
import com.fertifa.app.controllers.ShoppingCartController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Services;
import com.fertifa.app.models.ShoppingCardSalesModel;
import com.fertifa.app.models.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/ShowMoreSalesInfoAjax")
public class ShowMoreSalesInfoAjax extends HttpServlet {
    private int UserId = 0;
    private UsersController usersController = new UsersController();
    private String SessionUserEmail = "";
    private SessionController sessionController = new SessionController();
    private int Id = 0;
    List<Users> usersList = new ArrayList<>();
    private int userId = 0;
    List<Services> servicesList = new ArrayList<>();
    ServoceController servoceController = new ServoceController();
    List<ShoppingCardSalesModel> shoppingCardSalesModelList = new ArrayList<>();
    ShoppingCartController shoppingCartController = new ShoppingCartController();
    List<Users> usersCompanyList = new ArrayList<>();
    String[] myArrayDates;
    Float[] myArrayCount;
    private String SessionEmail = null;
    private AdminController adminController = new AdminController();
    private List<Admins> adminFullList = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectMapper objectMappere = new ObjectMapper();
    String seven = "";
    String todays = "";
    String DateFrom = "";
    String DateTo = "";
    int ServiceId = 0;
    int CompanyId = 0;
    Map<String, Object> jsonMap = new LinkedHashMap<>();
    private String AdminEmail;
    private int AdminId;
    private int AdminRole;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean isLogined = AdminCookiManager.isUserLogged(request, response);
            if (isLogined) {
                AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
                if (userCookie == null) {
                    // go to login page
                    gotoLoginPage(request, response);
                    return;
                }
                AdminEmail = userCookie.getUserEmail();
                AdminId = userCookie.getUserId();
                AdminRole = userCookie.getUserRole();
                showMoreSalesInfoAjax(request, response);
            } else {
                gotoLoginPage(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                gotoLoginPage(request, response);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean isLogined = AdminCookiManager.isUserLogged(request, response);
            if (isLogined) {
                AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
                if (userCookie == null) {
                    // go to login page
                    gotoLoginPage(request, response);
                    return;
                }
                AdminEmail = userCookie.getUserEmail();
                AdminId = userCookie.getUserId();
                AdminRole = userCookie.getUserRole();
                showMoreSalesInfoAjax(request, response);
            } else {
                gotoLoginPage(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                gotoLoginPage(request, response);
            }
        }
    }

    private void showMoreSalesInfoAjax(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        try {
            UnicodeingSevlet(request);
            startAdminControle();
            getAllComapnies();
            getParameters(request);
            getAllServicess();
            DataLastSevenDays(response);
            setRequests(request);
            gotoPage(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("/adminSide/Sales.jsp").forward(request, response);
            request.getSession().setAttribute("successNotices", null);
            request.getSession().setAttribute("errorNotices", null);
        }


    }

    private void DataLastSevenDays(HttpServletResponse response) throws SQLException, IOException {

        shoppingCardSalesModelList = shoppingCartController.getSalesShoppingCard(DateFrom, DateTo, ServiceId, CompanyId);
        myArrayDates = new String[shoppingCardSalesModelList.size()];
        myArrayCount = new Float[shoppingCardSalesModelList.size()];
        jsonMap = new LinkedHashMap<>();
        String MyArrAyStatus = "success";
        if (shoppingCardSalesModelList.size() > 0) {
            jsonMap.put("status", MyArrAyStatus);
            for (int i = 0; i < shoppingCardSalesModelList.size(); i++) {
                myArrayDates[i] = shoppingCardSalesModelList.get(i).getDateString();
                myArrayCount[i] = shoppingCardSalesModelList.get(i).getDayTotal();
            }
            //usersListExport.add("'" + usersList.get(i).getDateString() + "'");
            jsonMap.put("dates", myArrayDates);
            jsonMap.put("count", myArrayCount);


            ObjectMapper mapper = new ObjectMapper();
            PrintWriter out = response.getWriter();
            out.println(mapper.writeValueAsString(jsonMap));
            out.flush();
            out.close();
        } else {
          /*  jsonMap.put("status", "NoInfo");
            Gson gson = new Gson();
            String json = "";
            response.setContentType("application/json");
            json = gson.toJson(jsonMap);
            response.getWriter().print(json);*/
            jsonMap.put("status", MyArrAyStatus);
            myArrayDates = new String[0];
            myArrayCount = new Float[0];
            jsonMap.put("dates", myArrayDates);
            jsonMap.put("count", myArrayCount);
            ObjectMapper mapper = new ObjectMapper();
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            out.println(mapper.writeValueAsString(jsonMap));
            out.flush();
            out.close();
        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/adminSide/Sales.jsp").forward(request, response);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }

    private void setRequests(HttpServletRequest request) throws JsonProcessingException {
        request.setAttribute("ShoppingCartFinalList", shoppingCardSalesModelList);
        request.setAttribute("UsersCompanyList", usersCompanyList);
        request.setAttribute("ID", AdminId);
        request.setAttribute("UsersList", usersList);
        request.setAttribute("MyArrayDates", objectMapper.writeValueAsString(myArrayDates));
        request.setAttribute("MyArrayCount", objectMapper.writeValueAsString(myArrayCount));
        request.setAttribute("AdminFullList", adminFullList);
        request.setAttribute("ServicesList", servicesList);
        request.setAttribute("Seven", DateTo);
        request.setAttribute("Todays", DateFrom);
        request.setAttribute("AdminId", AdminId);
    }

    private void getAllServicess() throws SQLException {

        servicesList = servoceController.getAll(1);
    }


    private void getParameters(HttpServletRequest request) {
        DateFrom = request.getParameter("dateFrom");
        DateTo = request.getParameter("dateTo");
        if (request.getParameter("category") != null && !request.getParameter("category").equals("")) {
            ServiceId = Integer.parseInt(request.getParameter("category"));
        }
        if (request.getParameter("company") != null && !request.getParameter("company").equals("")) {
            CompanyId = Integer.parseInt(request.getParameter("company"));
        }
    }

    private void getAllComapnies() throws SQLException {
        usersCompanyList = usersController.getAllCompaniesByRole("company");
    }

    private void startAdminControle() {
        getAdminFullDetails(AdminId);
    }

    /**
     * com.fertifa.app.Admin full details
     *
     * @param adminId
     */
    private void getAdminFullDetails(int adminId) {
        adminFullList = adminController.getAllAdminListById(adminId);
    }


    private void gotoLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
