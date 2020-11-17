package com.fertifa.app.adminSide.affiliate.invite.send;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AffiliateConstances;
import com.fertifa.app.controllers.AffiliateController;
import com.fertifa.app.controllers.InvitationController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Affiliate;
import com.fertifa.app.models.Tokens;
import com.fertifa.app.utils.SendingEmailNewAfiliate;
import com.fertifa.app.utils.TokenMaker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

@WebServlet("/admin/affiliate/send")
public class AffiliateSendInvitation extends HttpServlet {

    private String AdminEmail = "";
    private int AdminId;
    private int AdminRole;

    //com.fertifa.app.Admin
    private Admins adminsObject = new Admins();

    //token and invitation
    private String token = "";
    private InvitationController invitationController = new InvitationController();

    //Affiliate
    private Affiliate newAffiliate = new Affiliate();
    private AffiliateController affiliateController = new AffiliateController();
    private int affiliateId = 0;

    //Encoding
    private String tokenEncoder="";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean isLogined = AdminCookiManager.isUserLogged(request, response);
            if (isLogined) {
                AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
                if (userCookie == null) {
                    // go to login page
                    gotoLoginPage(request, response);
                    return;
                }
                AdminEmail = userCookie.getUserEmail();
                AdminId = userCookie.getUserId();
                AdminRole = userCookie.getUserRole();
                affiliateSendInvitation(request, response);
            } else {
                gotoLoginPage(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                gotoLoginPage(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean isLogined = AdminCookiManager.isUserLogged(request, response);
            if (isLogined) {
                AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
                if (userCookie == null) {
                    // go to login page
                    gotoLoginPage(request, response);
                    return;
                }
                AdminEmail = userCookie.getUserEmail();
                AdminId = userCookie.getUserId();
                AdminRole = userCookie.getUserRole();
                affiliateSendInvitation(request, response);
            } else {
                gotoLoginPage(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                gotoLoginPage(request, response);
            }
        }
    }

    private void affiliateSendInvitation(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        //getAdminFullDetails(AdminId);
        getParameters(request);
        StartCheckingBeforeSendingEmail(newAffiliate, request, response);
    }


    private void StartCheckingBeforeSendingEmail(Affiliate affiliate, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (!CheckAffiliatEmailInData(affiliate.getEmail())) {
            //if done add com.fertifa.app.affiliate with 0 status
            if (AddingNewAffiliateToData(affiliate) > 0) {
                //AddToken
                affiliateId = getLastAffiliateId();
                token = TokenMaker.buildToken(12);
                tokenEncoder = DcncodeToken(affiliate,affiliateId,token);
                if (invitationController.AddNewTokenAndCompany(createTokenObject()) > 0) {
                    //sendEmail to com.fertifa.app.affiliate
                    if (SendInvitationToAffiliate(tokenEncoder, affiliate, affiliateId, request, response)) {
                        TriggerSuccessNotification(request, response);
                    }
                }
            }
        } else {
            TriggerErrorNotification(request, response);
        }

    }

    private String DcncodeToken(Affiliate affiliate, int affiliateId, String token) {
        Base64.Encoder encoder = Base64.getMimeEncoder();
        String str = encoder.encodeToString(("email_"+affiliate.getEmail() +"_firstname_"+ affiliate.getFirstName() + "_lastname_"+affiliate.getLastName() +"_id_"+ affiliateId +"_token_"+ this.token).getBytes());
        System.out.println(str);
        return str;
    }



    private int getLastAffiliateId() throws SQLException {
        return affiliateController.getTheAffiliateId();
    }

    private Tokens createTokenObject() {
        return new Tokens(affiliateId, token, newAffiliate.getFirstName(), newAffiliate.getLastName(), newAffiliate.getEmail());
    }

    private void TriggerSuccessNotification(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BuildDataToJSP(request, response);
        gotoPage(request, response);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message = "The invitation sent successfully";
        request.getSession().setAttribute("successNotices", message);
        String url = "/admin/affiliate/invite";
        response.sendRedirect(url);
    }

    private void TriggerErrorNotification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BuildDataToJSP(request, response);
        gotoWarningPage(request, response);
    }

    private int AddingNewAffiliateToData(Affiliate affiliate) throws SQLException {
        return affiliateController.saveAffiliate(affiliate);
    }

    private boolean SendInvitationToAffiliate(String buildToken, Affiliate affiliate, int theAffiliateId, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return SendingEmailNewAfiliate.sendAffiliate(buildToken, affiliate, theAffiliateId, request, response);
    }

    private void gotoWarningPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "The com.fertifa.app.affiliate or Employee already exists";
        request.getSession().setAttribute("errorNotices", message);
        String url = "/admin/affiliate/invite";
        response.sendRedirect(url);
    }

    private void BuildDataToJSP(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("AdminFullList", adminsObject);
    }

    private boolean CheckAffiliatEmailInData(String email) throws SQLException {
        return affiliateController.checkingAnythingInAffiliate(AffiliateConstances.AFFILIATE_EMAIL_INDATA, email);
    }

    private void getParameters(HttpServletRequest request) {
        String[] myParams = request.getParameterValues("formElements");
        newAffiliate = new Affiliate(myParams[0], myParams[1], myParams[2]);
    }

    private void gotoLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/login/";
        response.sendRedirect(url);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }

   /* private void getAdminFullDetails(int adminId) {
        adminsObject = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA,adminId);
    }*/
}
