package com.fertifa.app.affiliate.recover.recoveringpassword;

import com.fertifa.app.constants.AffiliateConstances;
import com.fertifa.app.controllers.AffiliateController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/affiliate/recover/recovering")
public class PasswordRecover extends HttpServlet {
    //Affiliate
    private String theForgottenEmail="";
    private AffiliateController  affiliateController =  new AffiliateController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            passwordRecover(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            passwordRecover(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void passwordRecover(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        getParameters(request);
        sendingEmail(request, response);
    }

    private void sendingEmail(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if(affiliateController.checkingAnythingInAffiliate(AffiliateConstances.AFFILIATE_EMAIL_INDATA,theForgottenEmail)){

        }
    }

    private void getParameters(HttpServletRequest request) {
        if (request.getParameter("email") != null) {
            theForgottenEmail = request.getParameter("email");
        }
    }
}
