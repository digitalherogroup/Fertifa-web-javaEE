package com.fertifa.app.users.invited.by.affiliate;

import com.fertifa.app.constants.Constances;
import com.fertifa.app.constants.AffiliateConstances;
import com.fertifa.app.controllers.AffiliateController;
import com.fertifa.app.controllers.AffiliateStaticController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Affiliate;
import com.fertifa.app.models.Users;
import com.fertifa.app.affiliate.builder.AffiliateStatics;
import com.fertifa.app.helpser.ChookiManager;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@WebServlet("/employee/affiliate/add")
public class RegisterByAffiliate extends HttpServlet {
    private Users users = new Users();
    private String[] affiliateEmail;
    private String[] affiliateId;
    private String ip;
    private UsersController usersController = new UsersController();
    private AffiliateController  affiliateController = new AffiliateController();
    private AffiliateStatics affiliateStatics = new AffiliateStatics(new AffiliateStatics.StaticBuilder());
    private AffiliateStaticController affiliateStaticController = new AffiliateStaticController();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        registerByAffiliate(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        registerByAffiliate(request, response);
    }

    private void registerByAffiliate(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        getParameters(request, response);

        startChecks(request, response, affiliateStatics);
    }

    private void startChecks(HttpServletRequest request, HttpServletResponse response, AffiliateStatics affiliateStatics) throws SQLException, IOException, ServletException {
        if (!CheckIfAffiliateSameEmail(users)) {
            if (!CheckIfUserAlreadyExsists(users)) {
                addAffiliateStatic(request, response);
            }
            else {
                String message = "You already got account, please login";
                setRequests(request, response, message);
                gotoErrorRegister(request, response);
            }
        } else {
            String message = "You already got account, please login";
            setRequests(request, response, message);
            gotoErrorRegister(request, response);
        }
    }

    private boolean CheckIfAffiliateSameEmail(Users users) throws SQLException {
        return affiliateController.checkingAnythingInAffiliate(AffiliateConstances.AFFILIATE_EMAIL_INDATA,users.getEmail());
    }

    private void addAffiliateStatic(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        addStatic();
        saveUser(request, response, users);

    }

    private void saveUser(HttpServletRequest request, HttpServletResponse response, Users users) throws SQLException, IOException, ServletException {
        if (addUser(users) > 0) {
            String message = "";
            setRequests(request, response, message);
            gotoDashboard(request, response);
        } else {
            String message = "Something went wrong, please contact your Affiliate";
            setRequests(request, response, message);
            gotoErrorRegister(request, response);
        }
    }

    private void gotoDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChookiManager.removeAll(request,response,3);
        String url = "/employee/employee-dashboard";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void gotoErrorRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = "/employee/affiliate/register";
        response.sendRedirect(url);
    }

    private int addUser(Users users) throws SQLException {
        return usersController.AddnewUsersInvitationAffiliate(users);
    }

    private int addStatic() throws SQLException {
        affiliateStatics = createAffiliateStatic();
        return affiliateStaticController.saveStatic(affiliateStatics);
    }

    private AffiliateStatics createAffiliateStatic() {
        return new AffiliateStatics.StaticBuilder()
                .affiliateId(Integer.parseInt(affiliateId[0]))
                .affiliateEmail(affiliateEmail[0])
                .clickId(0)
                .registerId(1)
                .dateOfStatic(new Timestamp(new Date().getTime()))
                .ip(ip).build();
    }

    private void setRequests(HttpServletRequest request, HttpServletResponse response, String message) {
        request.setAttribute("message", message);
    }

    private boolean CheckIfUserAlreadyExsists(Users users) throws SQLException {
        return usersController.checkIfUserExsistInData(users.getEmail(), Constances.USERS);
    }

    private void getParameters(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        getIp(request, response);
        Map<String, String[]> paraMap = request.getParameterMap();
        affiliateEmail = paraMap.get("AffiliateEmail");
        affiliateId = paraMap.get("AffiliateId");
        String id = request.getParameter("AffiliateId");
        Affiliate affiliate = affiliateController.findAllById(id);
        Users commpany = usersController.findById(Constances.USER_AFFILITATID_INDATA, String.valueOf(affiliate.getId()));
        users = new Users(paraMap.get("firstName"), paraMap.get("lastName"), paraMap.get("email"), paraMap.get("gender"), paraMap.get("password"),commpany.getComapny(),paraMap.get("age"), commpany.getId(),true);
        createAffiliateStatic();
    }

    private void getIp(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        ip = getIpAddr(request);
    }

    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
