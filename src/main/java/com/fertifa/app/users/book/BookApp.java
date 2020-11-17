package com.fertifa.app.users.book;

import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.ServoceController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Services;
import com.fertifa.app.models.Users;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/p")
@Controller
@RequiredArgsConstructor
public class BookApp extends com.fertifa.app.baseUrl.BaseUrl {
    private UsersController usersController = new UsersController();
    List<Services> servicesList = new ArrayList<>();
    List<Services> treatmentList = new ArrayList<>();
    ServoceController servoceController = new ServoceController();
    private Users users = new Users();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployeeId(request, response));
            bookApp(request, response);
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

    private void bookApp(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        getAllServices();
        getAllTreatments();
        setRequests(request);
        gotoPage(request, response);
    }

    private void getAllTreatments() throws SQLException {
        treatmentList = servoceController.getAll(2);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath() + "/BookAppointment.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("ServiceList", servicesList);
        request.setAttribute("TreatmentList", treatmentList);
        request.setAttribute("EmployeeObject", users);
    }

    private void getAllServices() throws SQLException {
        servicesList = servoceController.getAll(1);
    }
}
