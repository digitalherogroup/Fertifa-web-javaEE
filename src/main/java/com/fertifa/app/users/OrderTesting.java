package com.fertifa.app.users;

import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.TestingOrdersController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.TestingOrders;
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

@WebServlet("/o")
public class OrderTesting extends com.fertifa.app.baseUrl.BaseUrl {

    private UsersController usersController = new UsersController();
    List<TestingOrders> testingOrdersList = new ArrayList<>();
    TestingOrdersController testingOrdersController = new TestingOrdersController();
    private Users users = new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA,getEmployeeId(request,response));
            orderTesting(request, response);
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

    private void orderTesting(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getAllServices();
        setRequests(request);
        gotoPage(request, response);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/OrderTesting.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("TestingOrdersList", testingOrdersList);
        request.setAttribute("EmployeeObject", users);
    }

    private void getAllServices() throws SQLException {
        testingOrdersList = testingOrdersController.getAll();
    }


}
