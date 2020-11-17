package com.fertifa.app.adminSide.affiliate;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.constants.AffiliateConstances;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.AffiliateController;
import com.fertifa.app.controllers.AffiliateStaticController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Affiliate;
import com.fertifa.app.models.Users;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/affiliate/statics")
public class AffiliateStatic extends com.fertifa.app.baseUrl.BaseUrl {
    private Admins admins = new Admins();
    private AdminController adminController = new AdminController();
    private Users users = new Users();
    private UsersController usersController = new UsersController();
    private Affiliate affiliate = new Affiliate();
    private AffiliateController affiliateController = new AffiliateController();
    private AffiliateStaticController affiliateStaticController = new AffiliateStaticController();
    private List<Integer> countsList = new ArrayList<>();

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        affiliateStatic(request,response);
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        affiliateStatic(request,response);
    }

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            affiliateStatic(request, response);
        }
    }


    private void affiliateStatic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        startAdminControle(request,response);
        getParameters(request,response);
        getAffiliateObject(users);
        getClicksAndRegistryCounts(affiliate.getId());
        setAttributes(request,response);
        gotoProfile(request,response);
    }

    private void setAttributes(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("EmployerObject",users);
        request.setAttribute("AffiliateObject",affiliate);
        request.setAttribute("CountClicks",countsList.get(0));
        request.setAttribute("CountRegisters",countsList.get(1));
        request.setAttribute("CountClicksArray",countsList);
        request.setAttribute("AdminsObject",admins);
    }

    private void getClicksAndRegistryCounts(int affiliateId) throws SQLException {
        countsList = new ArrayList<>();
        countsList.add(affiliateStaticController.CountClicksByAffiliateId(AffiliateConstances.AFFILIATE_REGISTER_CLICK,affiliateId));
        countsList.add(affiliateStaticController.CountClicksByAffiliateId(AffiliateConstances.AFFILIATE_STATICS_CLICK,affiliateId));
    }

    private void getAffiliateObject(Users users) throws SQLException {
        affiliate  = affiliateController.findAllById(String.valueOf(users.getAffiliateId()));
    }

    private void getParameters(HttpServletRequest request, HttpServletResponse response) {
        if(request.getParameter("id")!=null){
            users = usersController.findById(Constances.USER_ID_INDATA,request.getParameter("id"));
        }
    }

    private void gotoProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath()+"/Statics.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
        System.out.println();
    }
}
