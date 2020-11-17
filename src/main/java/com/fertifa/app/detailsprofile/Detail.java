package com.fertifa.app.detailsprofile;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.constants.AffiliateConstances;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.AffiliateController;
import com.fertifa.app.controllers.AffiliateStaticController;
import com.fertifa.app.controllers.HealthHistroyController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Affiliate;
import com.fertifa.app.models.HealthHistory;
import com.fertifa.app.models.Users;
import com.fertifa.app.users.Modal.HelthHistoryModel;
import com.google.gson.Gson;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/profile/detail")
public class Detail extends com.fertifa.app.baseUrl.BaseUrl {

    private AdminController adminController = new AdminController();
    private UsersController usersController = new UsersController();
    private int Id = 0;
    private int countsClick;
    private int registerClick;
    private int countPageClick;
    private List<HealthHistory> healthHistoriesList = new ArrayList<>();
    private HealthHistroyController healthHistroyController = new HealthHistroyController();
    private List<HelthHistoryModel> helthHistoryModel = new ArrayList<>();
    private Admins admins = new Admins();
    private Users users = new Users();
    private Affiliate affiliate = new Affiliate();
    private AffiliateStaticController affiliateStaticController = new AffiliateStaticController();
    private List<Integer> countsList = new ArrayList<>();


    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            detail(request, response);
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

    private void detail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request, response);
        getParameters(request);
        getUserById(Id);
        getClicksAndRegistryCounts();
        getUserHealthHistory(Id);
        setRequests(request);
        gotoPage(request, response);

    }

    private void getClicksAndRegistryCounts() throws SQLException {
        countsList = new ArrayList<>();
        if (affiliate.getId() != 0) {
            countsList.add(affiliateStaticController.CountClicksByAffiliateId(AffiliateConstances.AFFILIATE_STATICS_CLICK, affiliate.getId()));
            countsList.add(affiliateStaticController.CountClicksByAffiliateId(AffiliateConstances.AFFILIATE_FIVE_SECOND_CLICK, affiliate.getId()));
            countsList.add(affiliateStaticController.CountClicksByAffiliateId(AffiliateConstances.AFFILIATE_REGISTER_CLICK, affiliate.getId()));
            countsClick = affiliateStaticController.CountClicksByAffiliateId(AffiliateConstances.AFFILIATE_STATICS_CLICK, affiliate.getId());
            registerClick = affiliateStaticController.CountClicksByAffiliateId(AffiliateConstances.AFFILIATE_REGISTER_CLICK, affiliate.getId());
            countPageClick = affiliateStaticController.CountClicksByAffiliateId(AffiliateConstances.AFFILIATE_FIVE_SECOND_CLICK, affiliate.getId());
            System.out.println();
        } else {
            int number = 3;
            while (number > 0) {
                countsList.add(0);
                number--;
            }
            countsClick = registerClick = countsClick = 0;
        }
    }

    private void getUserHealthHistory(int id) throws SQLException {
        healthHistoriesList = new ArrayList<>();
        healthHistoriesList = healthHistroyController.getHistoryById(id);

        if (healthHistoriesList == null || healthHistoriesList.size() == 0) {
            // error case
            helthHistoryModel = null;
        } else {
            // success case
            String contentJson = healthHistoriesList.get(0).getContent();
            helthHistoryModel = new ArrayList<>();
            helthHistoryModel.add(new Gson().fromJson(contentJson, HelthHistoryModel.class));
            if (helthHistoryModel == null) {
                // can not deserialize json to model
                System.out.println();
            } else {
                // Parsing success
                System.out.println();
            }
        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath() + "/Profile.jsp";
        request.getRequestDispatcher(url).forward(request, response);
        request.getSession().setAttribute("successNotices", null);
        request.getSession().setAttribute("errorNotices", null);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("UsersObject", users);
        request.setAttribute("Affiliate", affiliate);
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("HealthHistryIdList", helthHistoryModel);
        request.setAttribute("CountClicks", countsList.get(0));
        request.setAttribute("CountRegisters", countsList.get(1));
        request.setAttribute("CountPageClick", countsList.get(2));
        request.setAttribute("CountClicksArray", countsList);
    }

    private void getUserById(int id) throws SQLException {
        users = usersController.findById(Constances.USER_ID_INDATA, String.valueOf(id));
        AffiliateController affiliateController = new AffiliateController();
        affiliate = affiliateController.findAllById(String.valueOf(users.getAffiliateId()));

    }

    private void getParameters(HttpServletRequest request) {
        Id = Integer.parseInt(request.getParameter("id"));
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
