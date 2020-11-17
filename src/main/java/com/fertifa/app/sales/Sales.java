package com.fertifa.app.sales;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.ServoceController;
import com.fertifa.app.controllers.ShoppingCartController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Services;
import com.fertifa.app.models.ShoppingCardSalesModel;
import com.fertifa.app.models.Users;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/admin/sales")
public class Sales extends com.fertifa.app.baseUrl.BaseUrl {
    private UsersController usersController = new UsersController();
    private List<Users> usersList = new ArrayList<>();
    private List<Services> servicesList = new ArrayList<>();
    private ServoceController servoceController = new ServoceController();
    private List<ShoppingCardSalesModel> shoppingCardSalesModelList = new ArrayList<>();
    private ShoppingCartController shoppingCartController = new ShoppingCartController();
    private List<Users> usersCompanyList = new ArrayList<>();
    private String[] myArrayDates;
    float[] myArrayCount;
    private AdminController adminController = new AdminController();
    private ObjectMapper objectMapper = new ObjectMapper();
    private String seven = "";
    private String todays = "";
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            sales(request, response);
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

    private void sales(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        startAdminControle(request, response);
        getAllComapnies();
        getAllServicess();
        DataLastSevenDays();
        setRequests(request);
        gotoPage(request, response);
    }


    private void getAllServicess() throws SQLException {

        servicesList = servoceController.getAll(1);
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }


    private void getAllComapnies() throws SQLException {
        usersCompanyList = usersController.getAllCompaniesByRole("company");
    }

    private void setRequests(HttpServletRequest request) throws JsonProcessingException {
        request.setAttribute("ShoppingCartFinalList", shoppingCardSalesModelList);
        request.setAttribute("UsersCompanyList", usersCompanyList);
        request.setAttribute("UsersList", usersList);
        request.setAttribute("MyArrayDates", objectMapper.writeValueAsString(myArrayDates));
        request.setAttribute("MyArrayCount", objectMapper.writeValueAsString(myArrayCount));
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("ServicesList", servicesList);
        request.setAttribute("Seven", seven);
        request.setAttribute("Todays", todays);
        request.setAttribute("CompanyId", 0);
        request.setAttribute("ServiceId", 0);
    }

    private void DataLastSevenDays() throws SQLException {
        Timestamp sevenDays = new Timestamp(new Date().getTime());
        Timestamp today = new Timestamp(new Date().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(sevenDays.getTime());
        cal.add(Calendar.WEDNESDAY, -7);
        sevenDays = new Timestamp(cal.getTime().getTime());


        seven = sevenDays.toString();
        todays = today.toString();
        shoppingCardSalesModelList = shoppingCartController.getSalesShoppingCard(seven, todays, 0, 0);
        myArrayDates = new String[shoppingCardSalesModelList.size()];
        myArrayCount = new float[shoppingCardSalesModelList.size()];
        if (shoppingCardSalesModelList.size() > 0) {
            for (int i = 0; i < shoppingCardSalesModelList.size(); i++) {
                String[] gg = new String[shoppingCardSalesModelList.size()];
                gg = shoppingCardSalesModelList.get(i).getDateString().split(" ");
                String date = gg[0];
                System.out.println(date);
                myArrayDates[i] = date;
                System.out.println(shoppingCardSalesModelList.get(i).getDayTotal());
                myArrayCount[i] = shoppingCardSalesModelList.get(i).getDayTotal();
            }
        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath() + "/Sales.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }

}
