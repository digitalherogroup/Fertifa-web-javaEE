package com.fertifa.app.users.Cart;

import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.ShoppingCartController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.controllers.stripecontroller.NewStripeCardController;
import com.fertifa.app.controllers.stripecontroller.NewStripeUsersController;
import com.fertifa.app.models.StripeUsers;
import com.fertifa.app.models.Users;
import com.fertifa.app.models.stripe.NewStripeCardResponse;
import com.fertifa.app.models.stripe.NewStripeUsersResponse;
import com.fertifa.app.payments.ActionStripes;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employee/shoppingcart")
public class ShoppingCart extends com.fertifa.app.baseUrl.BaseUrl {
    private UsersController usersController = new UsersController();
    int orderId = 0;
    private int Total = 0;
    private ShoppingCartController shoppingCartController = new ShoppingCartController();
    private List<com.fertifa.app.models.ShoppingCart> shoppingCartList = new ArrayList<>();
    private String stripeId = "";
    private List<StripeUsers> stripeUserList = new ArrayList<>();
    private Users users = new Users();
    private List<com.fertifa.app.models.ShoppingCart> modelShop = new ArrayList<>();
    private NewStripeCardController newStripeCardController = new NewStripeCardController();
    private NewStripeUsersController newStripeUsersController = new NewStripeUsersController();
    private List<NewStripeCardResponse> cards = new ArrayList<>();
    private NewStripeUsersResponse newStripeUsersRequest = new NewStripeUsersResponse();
    private NewStripeCardResponse stripeDefaultUserCard = new NewStripeCardResponse();

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            shoppingCart(request, response);
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

    private void shoppingCart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getUserStripId();
        getAllOtherCardsDropDown();
        getDefaultCard();
        getAllCartByuser(users.getId());
        getOrderId();
        getUserStripePayment();
        lastStripeAction(stripeId);
        setRequests(request);
        gotoPage(request, response);
    }

    private void getDefaultCard() throws SQLException {
        stripeDefaultUserCard = newStripeCardController.getTheDefaultCard(newStripeUsersRequest.getCustomerId());
    }

    private void getAllOtherCardsDropDown() throws SQLException {
        cards = newStripeCardController.findAllByCustomerIdStatusNull(newStripeUsersRequest.getCustomerId());
    }

    private void getUserStripId() throws SQLException {
        newStripeUsersRequest = newStripeUsersController.getStripUserObjectByUserId(users.getId());
    }

    private void lastStripeAction(String stripeId) {
        if (stripeId != null) {
            ActionStripes actionStripes = new ActionStripes();
            stripeUserList = actionStripes.getStripeUsersByStripe(stripeId);
        }
    }

    private void getUserStripePayment() {
        ActionStripes actionStripes = new ActionStripes();
        stripeId = actionStripes.getStripeIdByUserId(users.getId());
    }

    private void getOrderId() {
        if (shoppingCartList.size() > 0) {
            orderId = shoppingCartList.get(0).getOrder_id();
        }
    }

    private void getAllCartByuser(int userId) throws SQLException {
        Total = 0;
        shoppingCartList = new ArrayList<>();
        shoppingCartList = shoppingCartController.getAllById(userId);

        modelShop = new ArrayList<>();
        for (int i = 0; i < shoppingCartList.size(); i++) {

            String datesShop = shoppingCartList.get(i).getOrder_date();
            String[] datestringarray = datesShop.split("&");
            if (datestringarray.length > 0) {
                for (int j = 0; j < datestringarray.length; j++) {
                    Total += shoppingCartList.get(i).getPrice();
                    modelShop.add(new com.fertifa.app.models.ShoppingCart(
                            shoppingCartList.get(i).getId(),
                            datestringarray[j],
                            shoppingCartList.get(i).getOrder_id(),
                            shoppingCartList.get(i).getServiceName(),
                            shoppingCartList.get(i).getPrice(),
                            shoppingCartList.get(i).getUser_id(),
                            shoppingCartList.get(i).getPriceId(),
                            shoppingCartList.get(i).getProductId(),
                            shoppingCartList.get(i).getStatus()));
                }
            } else {
                modelShop = shoppingCartList;
            }
            System.out.println();
        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("back",null);
        String url = request.getServletPath() + "/Cart.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("ShoppingCart", "active");
        request.setAttribute("OrderId", orderId);
        request.setAttribute("DefaultCard", stripeDefaultUserCard);
        request.setAttribute("EmployeeObject", users);
        //request.setAttribute("ShoppingCartList", shoppingCartList);
        request.setAttribute("ShoppingCartList", modelShop);
        request.setAttribute("count", shoppingCartList.size());
        request.setAttribute("StripeId", stripeId);
        request.setAttribute("StripeUserList", stripeUserList);
        request.setAttribute("Total", Total);
        request.setAttribute("Cards", cards);
        request.setAttribute("CardsSize", cards.size());
    }
}
