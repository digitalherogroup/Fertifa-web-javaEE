package com.fertifa.app.users.Cart;

import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Users;
import com.stripe.exception.StripeException;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employee/payment-info")
public class EmployePaymentInfo extends com.fertifa.app.baseUrl.BaseUrl {
    private UsersController usersController = new UsersController();
    private String Total = "";
    List<com.fertifa.app.models.ShoppingCart> shoppingCarts = new ArrayList<>();
    private int totalPayment = 0;
    private Users users = new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            employePaymentInfo(request, response);
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

    private void employePaymentInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException, StripeException {
        getParameters(request);
        setRequests(request);
        gotoToNextPage(request, response);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("ShoppingCart", "active");
        request.setAttribute("Total", Total);
        request.setAttribute("EmployeeObject",users);
    }

    private void getParameters(HttpServletRequest request) {
        Total = request.getParameter("total");
    }


    private void gotoToNextPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/PaymentInfo.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }


}
