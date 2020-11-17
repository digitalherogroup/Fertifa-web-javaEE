package com.fertifa.app.affiliate.check;

import com.fertifa.app.constants.AffiliateConstances;
import com.fertifa.app.controllers.AffiliateController;
import com.fertifa.app.controllers.InvitationController;
import com.fertifa.app.models.Affiliate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

@WebServlet("/com/fertifa/app/affiliate/check/check-confirmation")
public class CheckConfirmation extends HttpServlet {
    private Affiliate affiliateInput = new Affiliate();
    private String affiliateToken = "";
    private AffiliateController  affiliateController = new AffiliateController();
    private InvitationController invitationController = new InvitationController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            checkConfirmation(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            checkConfirmation(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkConfirmation(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        RequestParameters(request);
        StartConfirmation(request,response,affiliateInput.getEmail());
    }

    private void StartConfirmation(HttpServletRequest request, HttpServletResponse response, String email) throws SQLException, IOException, ServletException {
        if(CheckEmail(request,email)){
            if(CheckToken(email,affiliateToken)) {
                ChangeToken(affiliateToken, affiliateInput.getId());
                setParameters(request,response,affiliateInput);
                gotoRegisterPage(request,response);
            }else{
                setParameters(request,response,affiliateInput);
                response.sendRedirect("error.jsp");
            }
        }else{
            setParameters(request,response,affiliateInput);
            response.sendRedirect("error.jsp");
        }
    }

    private void gotoRegisterPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath() + "/redirect.jsp?id="+affiliateInput.getId();
        response.sendRedirect(url);
    }

    private void setParameters(HttpServletRequest request, HttpServletResponse response, Affiliate affiliateInput) {
        request.setAttribute("affiliateDetails",affiliateInput);
    }

    private boolean CheckToken(String email, String affiliateToken) throws SQLException {
        return invitationController.CheckingInvitationAffiliate(email, affiliateToken);
    }

    private void ChangeToken(String affiliateToken, int id) throws SQLException {
        String token = "KLJSJHDFKSFNJLKJJHKHLG" + id;
        invitationController.UpdateTokenByUserId(token,id);
    }

    private boolean CheckEmail(HttpServletRequest request, String email) throws SQLException {
        return affiliateController.checkingAnythingInAffiliate(AffiliateConstances.AFFILIATE_EMAIL_INDATA,email);
    }

    private void RequestParameters(HttpServletRequest request) throws IOException, ServletException {
        String parameters =request.getParameter("token");
        String [] decodedParamters = Decoding(parameters);
        affiliateInput = new Affiliate(decodedParamters[1],decodedParamters[3], decodedParamters[5], decodedParamters[7]);
        affiliateToken = decodedParamters[9];

        System.out.println();

    }
    private String[] Decoding(String str) {
        Base64.Decoder decoder = Base64.getMimeDecoder();
        String strd = new String(decoder.decode(str));
        return strd.split("_");
    }
}
