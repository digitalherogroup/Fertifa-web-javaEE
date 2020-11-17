package com.fertifa.app.payments;

import com.fertifa.app.constants.Constances;
import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.OrderController;
import com.fertifa.app.controllers.SessionController;
import com.fertifa.app.controllers.ShoppingCartController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.ShoppingCart;
import com.fertifa.app.models.Users;
import com.fertifa.app.utils.SendingEmailNewUser;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employee/stripe-detail")
public class AddingUserStripeDetail extends com.fertifa.app.baseUrl.BaseUrl {

    private int UserId = 0;
    private UsersController usersController = new UsersController();
    private String SessionUserEmail = "";
    private SessionController sessionController = new SessionController();
    private SendingEmailNewUser sendEmailController = new SendingEmailNewUser();
    private int Id = 0;
    List<Users> usersList = new ArrayList<>();
    private String CardNumber = "";
    private String ExpiresMonths = "";
    private String ExpireYears = "";
    private String CVV = "";
    private String firstName = "";
    private String lastName = "";
    private String Phone = "";
    private String Email = "";
    private String Address = "";
    private String City = "";
    private String State = "";
    private String postCode = "";
    private String PhoneNumber = "";
    private String Country = "";
    private int Total = 0;
    private Users users = new Users();


    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            addingUserStripeDetail(request,response);
        }
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    private void addingUserStripeDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, StripeException, SQLException {
        getParameters(request);
        try {
            AddOrderPayment(request, response);
        } catch (Exception e) {
            request.getSession().setAttribute("message","Something else happened, completely unrelated to Stripe");
            gotoStatusPage(request, response);
        }
    }

    private void AddOrderPayment(HttpServletRequest request, HttpServletResponse response) throws StripeException, SQLException, ServletException, IOException {
        PaymentServiceImpl paymentService = new PaymentServiceImpl();
        StripeModel stripeModel = new StripeModel();
        Order order =new Order();
        User user;
        String stripeId = "";
        String orderId = "";
        List<ShoppingCart> shoppingCarts = new ArrayList<>();


        user = new User(firstName, lastName, Email, Address, City, State, postCode, PhoneNumber, Country);
        stripeId = paymentService.createCustomer(user, users.getId());

        if (stripeId != null) {
            user.setFertifaUser_id(users.getId());
            user.setStripeCustomerId(stripeId);
            order = new Order(user, Total);
            Card card = new Card(users.getId(), CardNumber, ExpiresMonths, ExpireYears, CVV);
            stripeModel = paymentService.addCustomerCard(stripeId, card, firstName, lastName, order.getChargeAmountDollars());
            orderId = paymentService.chargeCreditCard(order, stripeModel, users.getEmail());
        }

        if (!isValidCard(stripeModel,request)) {
            request.getSession().setAttribute("message",stripeModel.getMessage());
            gotoStatusPage(request, response);
            return;
        }

        if (stripeModel.getToken().getCard() != null) {
            OrderController orderController = new OrderController();
            int serviceId = orderController.getServiceIdByUserid(users.getId());
            int usersCompanyId = usersController.getUserCompanyIdByEmail(users.getEmail());
            int finalShoppingCardId = 0;
            ShoppingCartController shoppingCartController = new ShoppingCartController();
            shoppingCarts = shoppingCartController.getAllById(users.getId());
            for (int i = 0; i < shoppingCarts.size(); i++) {
                finalShoppingCardId=0;
                ShoppingCart shoppingCart = new ShoppingCart(shoppingCarts.get(i).getOrder_id(),
                        shoppingCarts.get(i).getUser_id(),
                        shoppingCarts.get(i).getServiceName(),
                        shoppingCarts.get(i).getPrice(),
                        shoppingCarts.get(i).getOrder_id());
                /*finalShoppingCardId = shoppingCartController.getLastId();*/


                if (shoppingCartController.AddToFinal(usersCompanyId,serviceId,users.getId(), user, orderId, shoppingCart, Total, stripeId) > 0) {

                    int orderIdFroShop = shoppingCarts.get(i).getOrder_id();
                    shoppingCartController.UpdateStatusById(orderIdFroShop, users.getId());

                }
                // order successfully

            }
            sendInvoice(stripeModel, request, response);
        } else {
            request.getSession().setAttribute("message","Something else happened, completely unrelated to Stripe");
            gotoStatusPage(request, response);
        }
    }




    private boolean isValidCard(StripeModel stripeModel,HttpServletRequest request) {

        if (stripeModel == null) {
            stripeModel = new StripeModel();
            request.getSession().setAttribute("message","Something else happened, completely unrelated to Stripe");
            return false;
        }

        return stripeModel.isSuccess();
    }

    private void sendInvoice(StripeModel stripeModel, HttpServletRequest request, HttpServletResponse response) throws StripeException, ServletException, IOException {
        Invoice invoice = Invoice.retrieve(stripeModel.getInvoice().getId());
        String invoiceUrl = stripeModel.getCharge().getReceiptUrl();
        // send invoice via email to customer and fertifa
        sendEmailController.sendInvoice(invoiceUrl, Constances.FERTIFA_OWNER, stripeModel.getCustomer().getEmail());
        request.getSession().setAttribute("message","Thanks for your purchase – please check your e-mails for a copy of your receipt. If you have any questions, please get in touch at info@fertifa.com");
        gotoStatusPage(request, response);
    }


    private void gotoStatusPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("back",null);
        String url = request.getServletPath()+"/";
        //String url = "/payment/success";
        response.sendRedirect(url);
    }

    private void getParameters(HttpServletRequest request) {
        CardNumber = request.getParameter("card_number");
        ExpireYears = request.getParameter("expireYears");
        ExpiresMonths = request.getParameter("expireMonths");
        ExpiresMonths = request.getParameter("expireMonths");
        CVV = request.getParameter("cvv");
        firstName = request.getParameter("first_name");
        lastName = request.getParameter("last_name");
        Phone = request.getParameter("phone");
        Email = request.getParameter("email");
        Address = request.getParameter("address");
        City = request.getParameter("city");
        PhoneNumber = request.getParameter("phone");
        State = request.getParameter("state");
        postCode = request.getParameter("post_code");
        Total = Integer.parseInt(request.getParameter("total"));
        Country = "GB";
    }



}
