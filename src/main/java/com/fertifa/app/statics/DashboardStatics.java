package com.fertifa.app.statics;

import com.fertifa.app.models.Admins;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/admin/admin-dashboard/ajax")
public class DashboardStatics extends com.fertifa.app.baseUrl.BaseUrl {

    private Map<String, String> userMapAll = new LinkedHashMap<>();
    private String userMap = "";
    private String companyId = "";
    private String dateFrom = "";
    private String dateTo = "";
    private GeneralSatatics generalSatatics = new GeneralSatatics();
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            dashboardStatics(request, response);
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


    private void dashboardStatics(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        getParameters(request);
        getGeneralStatic(response);
        gotoPage(request, response);

    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("company") != null) {
            companyId = request.getParameter("company");
        }
        if (request.getParameter("dateFrom") != null) {
            dateFrom = request.getParameter("dateFrom");
        }
        if (request.getParameter("dateTo") != null) {
            dateTo = request.getParameter("dateTo");
        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/admin-dashboard";
        response.sendRedirect(url);
    }

    private void getGeneralStatic(HttpServletResponse response) throws IOException, SQLException {
        if (companyId.isEmpty()) {
            companyId = "0";
        }
        userMapAll = generalSatatics.getGeneralStaticWithCompanyId(Integer.parseInt(companyId), dateFrom, dateTo);
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.println(mapper.writeValueAsString(userMapAll));
        out.flush();
        out.close();
    }
}
