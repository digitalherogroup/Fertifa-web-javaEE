package com.fertifa.app.affiliate.register;


import com.fertifa.app.controllers.AffiliateController;
import com.fertifa.app.models.Affiliate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/com/fertifa/app/affiliate/check/register")
public class RegisterAffiliate extends HttpServlet {
    private String id = "";
    private AffiliateController affiliateController = new AffiliateController();
    private Affiliate affiliates = new Affiliate();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            registerAffiliate(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            requestAttributes(request);
            findAffiliateById(id);
            setAttributes(request);
            gotoToDashBoard(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void gotoToDashBoard(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0);
        String url = "/com/fertifa/app/affiliate/dashboard/console.jsp?id=" +affiliates.getId();
        response.sendRedirect(url);
    }

    private void registerAffiliate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        requestAttributes(request);
        findAffiliateById(id);
        setAttributes(request);
        gotoRegisterPage(request, response);
    }

    private void setAttributes(HttpServletRequest request) {
        request.setAttribute("affiliateDetails", affiliates);
    }

    private void findAffiliateById(String id) throws SQLException {
        affiliates = affiliateController.findAllById(id);
    }

    private void gotoRegisterPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = request.getServletPath() + "/registerAffiliate.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void requestAttributes(HttpServletRequest request) {
        id = request.getParameter("id");
    }
}
