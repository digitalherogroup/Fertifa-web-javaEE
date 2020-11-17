package com.fertifa.app.affiliate.add;

import com.fertifa.app.constants.AffiliateConstances;
import com.fertifa.app.controllers.AffiliateController;
import com.fertifa.app.models.Affiliate;
import com.fertifa.app.utils.DecodingEncoding;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@WebServlet("/com/fertifa/app/affiliate/add")
public class AddAffiliate extends HttpServlet {
    private Affiliate affiliate = new Affiliate();
    private AffiliateController affiliateController = new AffiliateController();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addAffiliate(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addAffiliate(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addAffiliate(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        getParameters(request);
        saveAffiliate(request,response,affiliate);
    }

    private void saveAffiliate(HttpServletRequest request, HttpServletResponse response, Affiliate affiliate) throws SQLException, ServletException, IOException {
        if(affiliateController.updateAffiliate(affiliate) > 0){
            createCookies(request,response);
            setAttributes(request);
            gotoAffiliateHomePage(request,response);
        }else{
            setAttributes(request);
            gotoError(request,response);
        }
    }

    private void gotoError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = "error.jsp";
       response.sendRedirect(url);
    }

    private void createCookies(HttpServletRequest request, HttpServletResponse response) {

    }

    private void gotoAffiliateHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0);
        String url = "/com/fertifa/app/affiliate/dashboard/console.jsp?id=" +affiliate.getId();
        response.sendRedirect(url);
    }

    private void setAttributes(HttpServletRequest request) {
        request.setAttribute("",affiliate);
    }

    private void getParameters(HttpServletRequest request) {
        Map<String,String[]> params = request.getParameterMap();
        String [] theId= params.get("id");
        String[] theEmail = params.get("email");
        String Secret_Key = theId[0] +"_"+  theEmail[0]  +"_"+ new Timestamp(new Date().getTime());
        affiliate = new Affiliate(
                theId,
                params.get("fistName"),
                params.get("lastName"),
                theEmail,
                params.get("gender"),
                AffiliateConstances.AFFILIATE_ACTIVE,
                AffiliateConstances.AFFILIATE_ROLE_NAME,
                DecodingEncoding.EncodePassword(params.get("password")),
                DecodingEncoding.EncodePassword(Secret_Key));
        System.out.println();
    }


}
