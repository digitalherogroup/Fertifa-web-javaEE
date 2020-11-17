package com.fertifa.app.company;


import com.fertifa.app.constants.Constances;
import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.AffiliateController;
import com.fertifa.app.controllers.InvitationController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.converters.DateConverter;
import com.fertifa.app.helpser.ChookiManager;
import com.fertifa.app.models.Affiliate;
import com.fertifa.app.models.Users;
import com.fertifa.app.utils.SendEmailToAdmin;
import com.fertifa.app.utils.SendingEmailNewCompany;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

@WebServlet("/RegisterCompany")
public class RegisterCompany extends HttpServlet {
    private String Company = "";
    private String CompanyAddress = "";
    private String CompanyDomain = "";
    private String CompanyPassword = "";
    private String CompanyEmail = "";
    private String ConfirmPassword = "";
    private String Email = "";
    private String CompanyId = "";
    private UsersController usersController = new UsersController();
    private AffiliateController affiliateController = new AffiliateController();
    private List<Users> usersList = new ArrayList<>();
    private String companyName = "";
    private String tokenCode = "";
    private String AffiliateId = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            registerCompany(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            registerCompany(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void registerCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        requestParameters(request);

        checkInputs(request, response);
    }

    private void checkInputs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (CompanyAddress.equals("") || !CompanyPassword.equals(ConfirmPassword)) {
            setRequeests(request);
            gotoPageWithError(request, response);
        } else {
            startAddingNewCompanyProcess(request, response);
        }
    }

    private void startAddingNewCompanyProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (usersController.UpdatenewCompanyRegistrationByID(CreateObjectCompany(), Integer.parseInt(CompanyId)) > 0) {
            System.out.println("usersController.UpdatenewCompanyRegistrationByID(CreateObjectCompany(), Integer.parseInt(CompanyId)) " + usersController.UpdatenewCompanyRegistrationByID(CreateObjectCompany(), Integer.parseInt(CompanyId)));
            Affiliate affiliate = affiliateController.findAllById(AffiliateId);
            Users newUsers = usersController.findById(Constances.USER_ID_INDATA, CompanyId);
            if (newUsers != null) {
                System.out.println("companyName " + newUsers.getComapny() + " CompanyEmail " + newUsers.getEmail());
                NotifyFertifaByEmail(newUsers.getComapny(), response);
                changeTokenValue(newUsers.getId());
                addAffiliateSecretKey(affiliate);
                sendVerificationCode(request, response, newUsers);
                setSuccessRequests(request);
                gotoSuccessRegisteredPage(request, response);
            } else {
                String message = "Something went wrong";
                request.getSession().setAttribute("errorNotices", message);
                setRequeests(request);
                gotoPageWithError(request, response);
            }

        } else {
            String message = "Something went wrong";
            request.getSession().setAttribute("errorNotices", message);
            setRequeests(request);
            gotoPageWithError(request, response);
        }
    }

    private void addAffiliateSecretKey(Affiliate affiliate) throws SQLException {

        affiliate.setSecretKey(Decoding(affiliate));
        affiliate.setRegisteredDate(DateConverter.convertDateWithRegex(new Timestamp(new Date().getTime())));
        affiliateController.updateAffiliate(affiliate);
    }

    private String Decoding(Affiliate affiliate) {
        Base64.Encoder encoder = Base64.getMimeEncoder();
        String str =  "id_" + affiliate.getId()
                + "_email_" + affiliate.getEmail();
        String strencoded = encoder.encodeToString(str.getBytes());
        System.out.println(str);
        return strencoded;
    }

    private void changeTokenValue(int CompanyId) throws SQLException {
        InvitationController invitationController = new InvitationController();
        String token = "KLJSJHDFKSFNJLKJJHKHLG" + CompanyId;
        if (invitationController.UpdateTokenByUserId(token, CompanyId) > 0) {
            System.out.println("Updated Token");
        } else {
            System.out.println("Updated Token failed");
        }
    }

    private void sendVerificationCode(HttpServletRequest request, HttpServletResponse response, Users users) throws IOException {
        Random random = new Random();
        HttpSession session = request.getSession();
        tokenCode = String.valueOf(random.nextInt(9999) + 1000);
        SendingEmailNewCompany.sendCodeCompanies(tokenCode, users.getComapny(), "", users.getEmail(), request, response);
        session.setAttribute("TokenCode", tokenCode);
    }

    private void NotifyFertifaByEmail(String companyName, HttpServletResponse response) throws IOException {
        SendEmailToAdmin.sendNewCompany(companyName, response);
    }

    private int changeCompanyStatus(String companyId) throws SQLException {
        return usersController.ChangeCompanyStatusToActive(companyId);
    }


    private void gotoSuccessRegisteredPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int companyStatus = usersController.getStatusByEmail(Email);
        ChookiManager.saveDataToCookie(CompanyId, Email, "2", 2, 2, response);
        request.getSession().setAttribute("userRole", Integer.valueOf(2));
        request.getRequestDispatcher("EmployerRegisterCode.jsp").forward(request, response);
    }

    private void setSuccessRequests(HttpServletRequest request) {
        request.setAttribute("Company", Company);
        request.setAttribute("CompanyEmail", Email);
        request.setAttribute("Token", tokenCode);
        request.setAttribute("ID", CompanyId);
        request.setAttribute("UserListByID", usersList);
    }

    private Users CreateObjectCompany() {
        return new Users(CompanyPassword, CompanyAddress, CompanyDomain);
    }

    private void gotoPageWithError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "please fix  the errors");
        request.setAttribute("Tracker", "active");
        request.setAttribute("ID", CompanyId);
        request.setAttribute("TokenCode", tokenCode);
        request.getRequestDispatcher("EmployerRegister.jsp").forward(request, response);

    }

    private void setRequeests(HttpServletRequest request) {
        request.setAttribute("message", "Please check your e-mail for a verification code and enter it below. E-mail us at info@fertifa.com if you have any issues.");
        request.setAttribute("Company", Company);
        request.setAttribute("CompanyEmail", Email);
        request.setAttribute("TokenCode", tokenCode);
        request.setAttribute("ID", CompanyId);
    }

    private void requestParameters(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            CompanyId = request.getParameter("id");
            System.out.println("CompanyId " + CompanyId);
        }
        if (request.getParameter("company") != null) {
            Company = request.getParameter("company");
            System.out.println("Company " + Company);
        }
        if (request.getParameter("email") != null) {
            Email = request.getParameter("email");
            System.out.println("Email " + Email);
        }
        if (request.getParameter("address") != null) {
            CompanyAddress = request.getParameter("address");
            System.out.println("address " + CompanyAddress);
        }
        if (request.getParameter("domain") != null) {
            CompanyDomain = request.getParameter("domain");
            System.out.println("CompanyDomain " + CompanyDomain);
        }
        if (request.getParameter("password") != null) {
            CompanyPassword = request.getParameter("password");
            System.out.println("CompanyPassword " + CompanyPassword);
        }

        if (request.getParameter("confirm_password") != null) {
            ConfirmPassword = request.getParameter("confirm_password");
            System.out.println("ConfirmPassword " + ConfirmPassword);
        }
        if (request.getParameter("AffiliateId") != null) {
            AffiliateId = request.getParameter("AffiliateId");
            System.out.println("AffiliateId " + AffiliateId);
            if(AffiliateId.isEmpty()  || AffiliateId.equals("")){
                Users userForAffiliate = new Users();
                userForAffiliate = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA,CompanyId);
                if(userForAffiliate.getAffiliateId() != 0){
                    AffiliateId = String.valueOf(userForAffiliate.getAffiliateId());
                }
            }
        }
    }
}