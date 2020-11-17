package com.fertifa.app.adminSide.Bookings;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.TimeDateOrderController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.ChatSession;
import com.fertifa.app.models.FullDetailAppintment;
import com.fertifa.app.models.ServiceDateTime;
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

@WebServlet("/admin/booking")
public class Bookings extends com.fertifa.app.baseUrl.BaseUrl {

    private AdminController adminController = new AdminController();
    private List<Users> companyList = new ArrayList<>();
    List<ChatSession> sessionChatlistRequest = new ArrayList<>();
    List<ChatSession> sessionChatlistNotification = new ArrayList<>();
    List<ChatSession> sessionChatlistString = new ArrayList<>();

    private List<ServiceDateTime> serviceDateTimeArrayList = new ArrayList<>();
    private TimeDateOrderController timeDateOrderController = new TimeDateOrderController();
    List<FullDetailAppintment> fullDetails = new ArrayList<>();
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            bookings(request, response);
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

    private void bookings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        startAdminControle(request, response);
        getAllRequests();
        SetAttributes(request);
        gotopage(request, response);
    }

    private void getAllRequests() throws SQLException {
        serviceDateTimeArrayList = new ArrayList<>();

        String dates = "";
        int Orderid = 0;
        int userId = 0;
        int Status = 0;
        List<Users> usersList = new ArrayList<>();
        serviceDateTimeArrayList = timeDateOrderController.getAll();
        if (serviceDateTimeArrayList.size() > 0) {
            String DatesString = "";
            fullDetails = new ArrayList<>();
            System.out.println(serviceDateTimeArrayList.size());

            for (int i = 0; i < serviceDateTimeArrayList.size(); i++) {
                dates = "";
                dates = serviceDateTimeArrayList.get(i).getDate_time();

                if (dates != null && !dates.isEmpty()) {
                    if (dates.contains("&")) {
                        String[] stringArray = dates.split("&");
                        for (int j = 0; j < stringArray.length; j++) {
                            DatesString += ("<li><span  class=\"rounded-time\">" + stringArray[j] + "</span></li> ");

                        }
                    } else {
                        DatesString = ("<li><span  class=\"rounded-time\">" + dates + "</span></li> ");
                    }

                    FullDetailAppintment fullDetailAppintment = new FullDetailAppintment(DatesString,
                            serviceDateTimeArrayList.get(i).getServiceName(),
                            serviceDateTimeArrayList.get(i).getGetServicePrice(),
                            serviceDateTimeArrayList.get(i).getId(),
                            serviceDateTimeArrayList.get(i).getFirstName(),
                            serviceDateTimeArrayList.get(i).getLastName(),
                            serviceDateTimeArrayList.get(i).getCompanyName(),
                            serviceDateTimeArrayList.get(i).getStatus());
                    fullDetails.add(fullDetailAppintment);
                    DatesString = "";
                    System.out.println(DatesString);
                } else {
                    continue;
                }
            }
        } else {
            serviceDateTimeArrayList = new ArrayList<>();
        }

    }

    private void SetAttributes(HttpServletRequest request) {
        request.setAttribute("Messages", "active");
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("CompanyList", companyList);
        request.setAttribute("ChatSessionListRequest", sessionChatlistRequest);
        request.setAttribute("ChatSessionListNotification", sessionChatlistNotification);
        request.setAttribute("ChatSessionListString", sessionChatlistString);
        request.setAttribute("ServiceDateTimeArrayListFinal", fullDetails);

    }

    private void gotopage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath() + "/Bookings.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }


    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
