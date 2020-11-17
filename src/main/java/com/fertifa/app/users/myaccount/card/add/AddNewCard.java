package com.fertifa.app.users.myaccount.card.add;


import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Users;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employee/myaccount/addnewcard")
public class AddNewCard extends com.fertifa.app.baseUrl.BaseUrl {

    private Users users = new Users();
    private UsersController usersController = new UsersController();

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            addNewCard(request, response);
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

    private void addNewCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAttributes(request);
        gotoPage(request,response);
    }

    private void setAttributes(HttpServletRequest request) {
        request.setAttribute("EmployeeObject",users);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url =  request.getServletPath() + "/add-new-card.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }



}
