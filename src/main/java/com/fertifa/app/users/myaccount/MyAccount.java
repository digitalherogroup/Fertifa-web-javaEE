package com.fertifa.app.users.myaccount;


import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.OrderController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.controllers.stripecontroller.NewStripeCardController;
import com.fertifa.app.controllers.stripecontroller.NewStripeUsersController;
import com.fertifa.app.converters.DateConverter;
import com.fertifa.app.models.FullDetailAppintment;
import com.fertifa.app.models.ServiceDateTime;
import com.fertifa.app.models.ShoppingCartFinal;
import com.fertifa.app.models.Users;
import com.fertifa.app.models.stripe.NewStripeCardResponse;
import com.fertifa.app.models.stripe.NewStripeUsersResponse;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employee/myaccount")
public class MyAccount extends com.fertifa.app.baseUrl.BaseUrl {

    private Users users = new Users();
    private UsersController usersController = new UsersController();
    private NewStripeUsersResponse newStripeUsersRequest = new NewStripeUsersResponse();
    private NewStripeUsersController newStripeUsersController = new NewStripeUsersController();
    private NewStripeCardController newStripeCardController = new NewStripeCardController();
    private List<NewStripeCardResponse> cards = new ArrayList<>();
    private List<ShoppingCartFinal> shoppingCardList = new ArrayList<>();
    private List<Users> userListforId = new ArrayList<Users>();
    private OrderController orderController = new OrderController();
    private List<FullDetailAppintment> ordersList = new ArrayList<>();
    private List<ServiceDateTime> serviceDateTimeArrayList = new ArrayList<>();

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            myAccount(request, response);
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

    private void myAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        getUserStripId();
        getAllCardByCustomerId();
        //my orders
        getAllOrders();
        setAttributes(request);
        gotoPage(request, response);
    }

    private void setAttributes(HttpServletRequest request) {
        request.setAttribute("OrdersList", ordersList);
        request.setAttribute("Cards", cards);
        request.setAttribute("EmployeeObject", users);
        request.setAttribute("cardCount", cards.size());
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

    private void getAllCardByCustomerId() throws SQLException {
        cards = newStripeCardController.findAllByCustomerId(newStripeUsersRequest.getCustomerId());
    }

    private void getUserStripId() throws SQLException {
        newStripeUsersRequest = newStripeUsersController.getStripUserObjectByUserId(users.getId());
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath() + "/myaccount.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("session_success_message", null);
        request.getSession().setAttribute("session_error_message", null);
    }

}
