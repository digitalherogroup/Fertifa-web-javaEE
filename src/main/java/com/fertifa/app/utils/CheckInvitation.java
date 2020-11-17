package com.fertifa.app.utils;

import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.InvitationController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/CheckInvitation")
public class CheckInvitation extends HttpServlet {
    private String TokenId = "";
    private String Company = "";
    private String CompanyIdInput = "";
    private String AffiliateId = "";
    private InvitationController invitationController = new InvitationController();
    private String CompanyEmail = "";
    private int CompanyId = 0;
    private int Branch = 0;
    private Users users = new Users();
    private UsersController usersController = new UsersController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            checkInvitation(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            checkInvitation(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void checkInvitation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        getparamerts(request);
        checkIncomes(request, response);
    }

    private void checkIncomes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (ValidateInvitation(TokenId, Company)) {
            System.out.println("ValidateInvitation(TokenId,com.fertifa.app.Company) " + ValidateInvitation(TokenId, Company));
            getListOfTheCompany(TokenId);
            setRequests(request);
            gotoRegisterPage(request, response);
        } else {
            setRequests(request);
            gotoErroPage(request, response);
        }
    }

    private void getListOfTheCompany(String tokenId) {
        CompanyEmail = invitationController.GetCompanyByTokenId(tokenId);
        users = usersController.findById(Constances.USER_EMAIL_INDATA, CompanyEmail);
    }

    private void gotoErroPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }

    private void gotoRegisterPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("EmployerRegister.jsp").forward(request, response);
        //response.sendRedirect("EmployerRegister.jsp");
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("ID", CompanyIdInput);
        request.setAttribute("ID", users.getId());
        request.setAttribute("Company", Company);
        request.setAttribute("TokenId", TokenId);
        request.setAttribute("CompanyEmail", CompanyEmail);
        request.setAttribute("AffiliateId", AffiliateId);
        request.setAttribute("users", users);
    }

    private boolean ValidateInvitation(String tokenId, String company) throws SQLException {
        return invitationController.CheckingInvitation(tokenId, company);
    }

    private void getparamerts(HttpServletRequest request) {

        if (request.getParameter("branch_id") != null) {
            Branch = Integer.parseInt(request.getParameter("branch_id"));
            System.out.println("Branch " + Branch);
        }
        TokenId = request.getParameter("TokenidToCheck");
        System.out.println("TokenidToCheck " + TokenId);
        Company = request.getParameter("CompanyToCheck");
        System.out.println("company " + Company);
        AffiliateId = request.getParameter("AffiliateId");
        System.out.println("AffiliateId " + AffiliateId);
        CompanyIdInput = request.getParameter("CompanyId");
        System.out.println("AffiliateId " + CompanyId);
    }
}
