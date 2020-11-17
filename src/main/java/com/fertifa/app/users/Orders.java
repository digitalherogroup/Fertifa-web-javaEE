package com.fertifa.app.users;

import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.OrderController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.converters.DateConverter;
import com.fertifa.app.models.FullDetailAppintment;
import com.fertifa.app.models.ServiceDateTime;
import com.fertifa.app.models.ShoppingCartFinal;
import com.fertifa.app.models.Users;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employee/orders")
public class Orders extends com.fertifa.app.baseUrl.BaseUrl {
    private UsersController usersController = new UsersController();
    List<FullDetailAppintment> ordersList = new ArrayList<>();
    OrderController orderController = new OrderController();
    List<ShoppingCartFinal> shoppingCardList = new ArrayList<>();
    List<Users> userListforId = new ArrayList<Users>();
    private Users users = new Users();
    private List<ServiceDateTime> serviceDateTimeArrayList = new ArrayList<>();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            orders(request, response);
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

    private void orders(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getAllOrders();
        setRequests(request);
        gotoPage(request, response);
    }

       private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/myOrders.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("OrdersList", ordersList);
        request.setAttribute("EmployeeObject", users);
        request.setAttribute("orders", "active");
    }

    private void getAllOrders() throws SQLException {
        String dates = "";
        shoppingCardList = new ArrayList<>();
        userListforId = new ArrayList<>();
        ordersList = new ArrayList<>();
        serviceDateTimeArrayList = new ArrayList<>();

        //shoppingCardList = orderController.UserOrderShoopingById(UserId);
        shoppingCardList = orderController.UserShoppingFinalMyOrderById(users.getId());
        // userListforId = usersController.getAllUsersListById(UserId);

        //serviceDateTimeArrayList = timeDateOrderController.getByUserId(UserId);
        if (shoppingCardList.size() > 0) {
            String DatesString = "";

            for (int i = 0; i < shoppingCardList.size(); i++) {
                dates = "";
                dates = shoppingCardList.get(i).getOrder_dates();
                System.out.println();

                if (dates.contains(" - ")) {

                    DatesString = ("<div><span  class=\"rounded-time\">" + dates + "</span></div> ");
                    FullDetailAppintment fullDetailAppintment = new FullDetailAppintment(
                            DatesString,
                            shoppingCardList.get(i).getServiceName(),
                            shoppingCardList.get(i).getPrice(),
                            shoppingCardList.get(i).getId(),
                            shoppingCardList.get(i).getSimpleDate());
                    ordersList.add(fullDetailAppintment);
                    DatesString = "";
                    System.out.println(DatesString);
                } else if (dates.contains(".")) {
                    DatesString = ("<div><span  class=\"rounded-time\">" + DateConverter.convertDateWithRegex(dates) + "</span></div> ");
                    FullDetailAppintment fullDetailAppintment = new FullDetailAppintment(
                            DatesString,
                            shoppingCardList.get(i).getServiceName(),
                            shoppingCardList.get(i).getPrice(),
                            shoppingCardList.get(i).getId(),
                            shoppingCardList.get(i).getSimpleDate());
                    ordersList.add(fullDetailAppintment);
                    DatesString = "";
                    System.out.println(DatesString);
                } else {
                    FullDetailAppintment fullDetailAppintment = new FullDetailAppintment(
                            DatesString,
                            shoppingCardList.get(i).getServiceName(),
                            shoppingCardList.get(i).getPrice(),
                            shoppingCardList.get(i).getId(),
                            shoppingCardList.get(i).getSimpleDate());
                    ordersList.add(fullDetailAppintment);
                    DatesString = "";
                    System.out.println(DatesString);
                }
            }
        } else {
            ordersList = new ArrayList<>();
        }
    }

    private String getDatesStringValues() {
        String dates = "";
        String DatesString = "";
        for (int i = 0; i < this.serviceDateTimeArrayList.size(); i++) {
            dates = "";
            dates = this.serviceDateTimeArrayList.get(i).getDate_time();
            if (dates != null && !dates.isEmpty()) {
                if (dates.contains("&")) {
                    String[] stringArray = dates.split("&");
                    for (int j = 0; j < stringArray.length; j++) {
                        DatesString += ("<div><span  class=\"rounded-time\">" + stringArray[j] + "</span></div> ");

                    }
                } else {
                    DatesString = ("<div><span  class=\"rounded-time\">" + dates + "</span></div> ");
                }
            }
        }
        return DatesString;
    }



}
