package com.fertifa.app.users.invited.by.affiliate;

import com.fertifa.app.constants.AffiliateConstances;
import com.fertifa.app.controllers.AffiliateController;
import com.fertifa.app.controllers.AffiliateStaticController;
import com.fertifa.app.affiliate.builder.AffiliateStatics;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@WebServlet("/employee/affiliate/register")
public class InvitedByAffiliate extends HttpServlet {
    private String ip;
    private String link;
    private String idFromLink = "";
    private String emailFromLink = "";
    private AffiliateController affiliateController = new AffiliateController();
    private AffiliateStaticController affiliateStaticController = new AffiliateStaticController();
    private AffiliateStatics affiliateStatics = new AffiliateStatics(new AffiliateStatics.StaticBuilder());
    private List<String> ageList = new ArrayList<>();


    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        invitedByAffiliate(request, response);

    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        invitedByAffiliate(request, response);
    }

    private void invitedByAffiliate(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        getParameters(request,response);
    }

    private void AddAgeList() {

        ageList = new ArrayList<>();
        for (int i = 18; i <101 ; i++) {
            ageList.add(String.valueOf(i));
        }
    }
    private void getIp(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        ip = getIpAddr(request);
    }

    private boolean checkTheIp(String ip, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        return affiliateStaticController.checkingClicksByIpInAffiliateStatic(AffiliateConstances.AFFILIATE_STATICS_IP, ip, AffiliateConstances.AFFILIATE_STATICS_CLICK, 1);
    }

    private void saveStatics(HttpServletRequest request, HttpServletResponse response, AffiliateStatics affiliateStatics) throws IOException, SQLException, ServletException {

        if (affiliateStaticController.saveStatic(affiliateStatics) > 0) {
            String message = "";
            gotoRegisterPage(request, response, message);
        } else {
            String message = "The was a problem, please retry again";
            gotoRegisterPage(request, response, message);
        }

    }

    private AffiliateStatics createAffiliateStatic() {

        return new AffiliateStatics.StaticBuilder()
                .affiliateId(Integer.parseInt(idFromLink))
                .affiliateEmail(emailFromLink)
                .clickId(1)
                .registerId(0)
                .dateOfStatic(new Timestamp(new Date().getTime()))
                .ip(ip).build();
    }

    private void getParameters(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        getIp(request, response);
        link = request.getParameter("link");
        if (CheckAffiliateSecret(link)) {
            startDecoding(link,request,response);
        } else {
            String message = "You already have a account, please login.";
            gotoRegisterPage(request, response, message);
        }
    }

    private boolean CheckAffiliateSecret(String link) throws SQLException {

        return affiliateController.checkingAnythingInAffiliate(AffiliateConstances.AFFILIATE_SECRET_KEY, link);
    }

    private void gotoRegisterPage(HttpServletRequest request, HttpServletResponse response, String message) throws IOException, ServletException {

        String url = request.getServletPath() + "/";
        AddAgeList();
        request.setAttribute("message", message);
        request.setAttribute("AgeList", ageList);
        request.setAttribute("AffiliateStatics",affiliateStatics );
        request.getRequestDispatcher(url).include(request, response);
    }


    private void startDecoding(String link, HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {

        Base64.Decoder decoder = Base64.getDecoder();
        String[] str = new String(decoder.decode(link)).split("_");
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals("id")) {
                idFromLink = str[i + 1];

            } else if (str[i].equals("email")) {
                emailFromLink = str[i + 1];
            }
        }
        affiliateStatics = createAffiliateStatic();
        if(!checkTheIp(ip, request, response)) {
            saveStatics(request, response,affiliateStatics);
        }else{
            String message="";
            gotoRegisterPage(request, response, message);
        }

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
