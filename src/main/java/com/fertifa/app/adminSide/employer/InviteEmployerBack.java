package com.fertifa.app.adminSide.employer;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.adminSide.Response.AffiliateUserResponse;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.constants.AffiliateConstances;
import com.fertifa.app.constants.PackageConstance;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.AffiliateController;
import com.fertifa.app.controllers.InvitationController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Affiliate;
import com.fertifa.app.models.Tokens;
import com.fertifa.app.models.Users;
import com.fertifa.app.utils.SendingEmailNewCompanyAffiliate;
import com.fertifa.app.utils.TokenMaker;
import lombok.SneakyThrows;

import javax.mail.internet.ParseException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

@WebServlet("/admin/employer/inviter")
public class InviteEmployerBack extends com.fertifa.app.baseUrl.BaseUrl {
    private UsersController usersController = new UsersController();
    private AdminController adminController = new AdminController();
    private InvitationController invitationController = new InvitationController();

    private String PackageName = "";
    private String Token = "";
    private int CompanyId = 0;
    private Users users = new Users();
    private Admins admins = new Admins();
    private AffiliateController affiliateController = new AffiliateController();

    private String packageing = null;
    private String email = null;
    private String name = null;
    private String type = null;

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            inviteEmployerBack(request, response);
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

    private void inviteEmployerBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException, ParseException {
        getAdminFullDetails(request, response);
        getParameters(request, response);
        //AddingNewEmployee(request, response, users);
    }

    private void getAdminFullDetails(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

    private boolean checkUserEmail(String emailCompanyEmail) throws SQLException {
        return usersController.CheckEmail(emailCompanyEmail);
    }


    private void AddingNewEmployee(HttpServletRequest request, HttpServletResponse response, Users users, int affiliateId) throws SQLException, ServletException, IOException {
        if (checkIfParametrsGotValues(users)) {
            checkEmail(request, response, users, affiliateId);

        } else {

            setRequests(request);
            gotoPage(request, response);
        }
    }

    private void checkEmail(HttpServletRequest request, HttpServletResponse response, Users users, int affiliateId) throws SQLException, ServletException, IOException {
        if (!checkUserEmail(users.getEmail())) {
            AddNewCompany(request, response, users, affiliateId);
        } else {
            String message = "Employer Already exists ";
            request.getSession().setAttribute("successNotices", message);
            setRequests(request);
            gotoPage(request, response);

        }
    }

    private void AddNewCompany(HttpServletRequest request, HttpServletResponse response, Users users, int affiliateId) throws SQLException, ServletException, IOException {
        if (usersController.AddnewCompanyInvitation(users) > 0) {
            String message = "Employer added successfully";
            request.getSession().setAttribute("successNotices", message);
            users.setId(getTheNewCompanyId(users));
            CreateToken(request, response, users, affiliateId);
        } else {
            String message = "Employer Something went work, please try again later.";
            request.getSession().setAttribute("errorNotices", message);
            setRequests(request);
            gotoPage(request, response);

        }
    }

    private void CreateToken(HttpServletRequest request, HttpServletResponse response, Users users, int affiliateId) throws SQLException, ServletException, IOException {
        Token = TokenMaker.buildToken(12);
        if (invitationController.AddNewTokenAndCompany(CreateTokenObject(users)) > 0) {

            String message = "Token created successfully";
            request.getSession().setAttribute("successNotices", message);
            SendInvitation(request, response, users, affiliateId);
        } else {
            String message = "Token Something went work, please try again later.";
            request.getSession().setAttribute("errorNotices", message);
            setRequests(request);
            gotoPage(request, response);

        }
    }

    private void SendInvitation(HttpServletRequest request, HttpServletResponse response, Users users, int affiliateId) throws ServletException, IOException {
        SendingEmailNewCompanyAffiliate.send(Token, users, affiliateId, request);
        String messageIn = "Invitation sent successfully";
        request.getSession().setAttribute("successNotices", messageIn);
        setRequests(request);
        gotoPage(request, response);
    }


    private int getTheNewCompanyId(Users users) throws SQLException {
        return usersController.getUserIdByEmail(users.getEmail());
    }

    private Tokens CreateTokenObject(Users users) {
        return new Tokens(Token, users.getId(), users.getEmail(), users.getComapny());
    }


    //Checking Parameters values to start building new company details after invitation
    private boolean checkIfParametrsGotValues(Users users) {
        return users.getEmail() != null && users.getComapny() != null;
    }


    private void getParameters(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ParseException {
        AffiliateUserResponse affiliateUserResponse = new AffiliateUserResponse();
        Enumeration<String> arrayString = request.getParameterNames();

        if (request.getParameter("email") != null) {
            email = request.getParameter("email");
        }
        if (request.getParameter("company") != null) {
            name = request.getParameter("company");
        }
        if (request.getParameter("typename") != null) {
            type = request.getParameter("typename");
            if ("1".equals(type)) {
                packageing = "3";
                PackageName = PackageConstance.PACAGE_BRONZE;
                affiliateUserResponse = new AffiliateUserResponse(email,name,PackageName,packageing,type);
                StartAffiliateEmployee(request, response, affiliateUserResponse);
            } else {
                if (request.getParameter("packagename") != null) {
                    packageing = request.getParameter("packagename");
                    if (packageing.contains("1")) {
                        PackageName = PackageConstance.PACAGE_SILVER;
                    } else if (packageing.contains("2")) {
                        PackageName = PackageConstance.PACAGE_GOLD;
                    }
                    affiliateUserResponse = new AffiliateUserResponse(email, name, PackageName, packageing, type);
                    StartAffiliateEmployee(request, response, affiliateUserResponse);
                } else {
                    String message = "com.fertifa.app.Company Something went work, please try again later.";
                    request.getSession().setAttribute("errorNotices", message);
                    setRequests(request);
                    gotoPage(request, response);
                }
            }
        }


    }



    private void StartAffiliateEmployee(HttpServletRequest request, HttpServletResponse response, AffiliateUserResponse affiliateUserResponse) throws SQLException, ServletException, IOException {
        Affiliate affiliate = new Affiliate(affiliateUserResponse.getEmail(), affiliateUserResponse.getType());
        if (!CheckAffiliatEmailInData(affiliate.getEmail())) {
            //if done add com.fertifa.app.affiliate with 0 status
            if (AddingNewAffiliateToData(affiliate) > 0) {
                //AddToken
                int affiliateId = getLastAffiliateId();
                users = getUsersObject(affiliateUserResponse, affiliateId);
                AddingNewEmployee(request, response, users, affiliateId);

            }
        } else {
            String message = "Employer or Affiliate already exists.";
            request.getSession().setAttribute("errorNotices", message);
            setRequests(request);
            gotoPage(request, response);
        }
    }

    private Users getUsersObject(AffiliateUserResponse affiliateUserResponse, int affiliateId) {
        return new Users(affiliateUserResponse.getEmail(),
                affiliateUserResponse.getCompany(),
                Integer.parseInt(affiliateUserResponse.getPackid()),
                affiliateId,
                affiliateUserResponse.getPackName());
    }


    private int getLastAffiliateId() throws SQLException {
        return affiliateController.getTheAffiliateId();
    }

    private int AddingNewAffiliateToData(Affiliate affiliate) throws SQLException {
        return affiliateController.saveAffiliate(affiliate);
    }

    private boolean CheckAffiliatEmailInData(String email) throws SQLException {
        return affiliateController.checkingAnythingInAffiliate(AffiliateConstances.AFFILIATE_EMAIL_INDATA, email);
    }


    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/employer/invite";
        response.sendRedirect(url);

    }

    private void setRequests(HttpServletRequest request) {
        //request.setAttribute("UsersList",adminsList);
        request.setAttribute("AdminsObject", admins);
    }
}
