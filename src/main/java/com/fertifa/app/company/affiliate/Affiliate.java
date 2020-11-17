package com.fertifa.app.company.affiliate;

import com.fertifa.app.constants.AffiliateConstances;
import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.AffiliateController;
import com.fertifa.app.controllers.AffiliateStaticController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.controllers.WhiteController;
import com.fertifa.app.models.Users;
import com.fertifa.app.models.WhiteModel;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employer/affiliate")
public class Affiliate extends com.fertifa.app.baseUrl.BaseUrl {

    private List<Integer> countsList = new ArrayList<>();
    private int affiliateId;
    private int countsClick;
    private int registerClick;
    private int countPageClick;
    private int countLost;
    private UsersController usersController = new UsersController();
    private AffiliateStaticController affiliateStaticController = new AffiliateStaticController();
    private AffiliateController affiliateController = new AffiliateController();
    private Users users = new Users();
    private com.fertifa.app.models.Affiliate affiliate = new com.fertifa.app.models.Affiliate();
    private WhiteController whiteController = new WhiteController();
    private List<WhiteModel> whiteModelList = new ArrayList<>();
    private List<Integer> percentages = new ArrayList<>();
    private float registersPercentageFloat = 0;
    private float clickedPercentageFloat = 0;
    private float lostPercentageFloat = 0;

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, getEmployerId(request, response));
            affiliate(request, response);
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

    private void affiliate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        getUsersAffiliateId(users.getId());
        getClicksAndRegistryCounts(affiliateId);
        getAffiliateObject(affiliateId);
        getWhiteDomains();
        setAttributes(request, response);
        gotoPage(request, response);
    }

    private void getWhiteDomains() throws SQLException {
        whiteModelList = whiteController.findAll(users.getId());
    }

    private void setAttributes(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("EmployerObject", users);
        request.setAttribute("AffiliateObject", affiliate);
        request.setAttribute("SecretKey", affiliate.getSecretKey());
        request.setAttribute("CountClicks", countsClick);
        request.setAttribute("CountPageClick", countPageClick);
        request.setAttribute("CountRegisters", registerClick);
        request.setAttribute("CountClicksArray", countsList);
        request.setAttribute("WhiteModelList", whiteModelList);
        request.setAttribute("CountLost", countLost);
        request.setAttribute("CountClicksArray_Percentage", percentages);
    }

    private void getAffiliateObject(int affiliateId) throws SQLException {
        affiliate = affiliateController.findAllById(String.valueOf(affiliateId));
    }

    private void getUsersAffiliateId(int id) {
        affiliateId = usersController.getUserAffiliateId(id);
    }

    private void getClicksAndRegistryCounts(int affiliateId) throws SQLException {
        countsList = new ArrayList<>();
        countsList.add(affiliateStaticController.CountClicksByAffiliateId(AffiliateConstances.AFFILIATE_STATICS_CLICK, affiliateId));
        countsList.add(affiliateStaticController.CountClicksByAffiliateId(AffiliateConstances.AFFILIATE_FIVE_SECOND_CLICK, affiliateId));
        countsList.add(affiliateStaticController.CountClicksByAffiliateId(AffiliateConstances.AFFILIATE_REGISTER_CLICK, affiliateId));

        countsClick = affiliateStaticController.CountClicksByAffiliateId(AffiliateConstances.AFFILIATE_STATICS_CLICK, affiliateId);
        registerClick = affiliateStaticController.CountClicksByAffiliateId(AffiliateConstances.AFFILIATE_REGISTER_CLICK, affiliateId);
        countPageClick = affiliateStaticController.CountClicksByAffiliateId(AffiliateConstances.AFFILIATE_FIVE_SECOND_CLICK, affiliateId);
        countLost = affiliateStaticController.CountLostClicks(AffiliateConstances.AFFILIATE_LESS_FIVE_SECOND_CLICK, affiliateId);
        countLostPercentage();
    }

    private void countLostPercentage() {
        float lost = 0;
        lost = registerClick - countsClick;
        if (lost < 0) {
            lost *= -1;
        }
        if (registerClick == countsClick) {
            registersPercentageFloat = 50;
            clickedPercentageFloat = 50;
            lostPercentageFloat = 0;
        }
        else if(registerClick == 0){
            registersPercentageFloat = 0;
            clickedPercentageFloat = 0;
            lostPercentageFloat = 100;
        }

        else if (registerClick + countsClick + lost == 0) {
            registersPercentageFloat = 0;
            clickedPercentageFloat = 0;
            lostPercentageFloat = 0;
        } else {
            float percentage = 100 / (registerClick + countsClick + lost);
            registersPercentageFloat = registerClick * percentage;
            clickedPercentageFloat = countsClick * percentage;
            lostPercentageFloat = lost * percentage;
        }
        percentages = new ArrayList<>();
        percentages.add((int) registersPercentageFloat);
        percentages.add((int) clickedPercentageFloat);
        percentages.add((int) lostPercentageFloat);

        System.out.println(registersPercentageFloat + clickedPercentageFloat + lostPercentageFloat);
        System.out.println();
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = request.getServletPath() + "/affiliate.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("session_success_message", null);
    }
}
