package com.fertifa.app.adminSide.feedbacks;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.FeedBackController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Feedback;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/feedbacks/update")
public class UpdateFeedBack extends com.fertifa.app.baseUrl.BaseUrl {

    private AdminController adminController = new AdminController();
    private FeedBackController feedBackController = new FeedBackController();
    private List<Feedback> feedBacksList = new ArrayList<>();
    private int FeedBackId = 0;
    private String ConfirmFeedBack = "";
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            updateFeedBack(request, response);
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

    private void updateFeedBack(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getParameters(request,response);
        CheckAction(request,response);
    }

    private void CheckAction(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if(ConfirmFeedBack.equals("confirm")){
            ChangeFeedToApprove(request,response,FeedBackId);
        }else{
            ChangeFeedToReject(request,response,FeedBackId);
        }
    }

    private void ChangeFeedToReject(HttpServletRequest request, HttpServletResponse response, int id) throws SQLException, ServletException, IOException {
        if(feedBackController.UpdateFeedBackStatusToRejectQ(id)>0){
            String message = "Feedback Rejected successfully";
            request.getSession().setAttribute("successNotices",message);
            getAllFeedBacks();
            setRequestToPage(request,response);
            gotoPage(request,response);
        }else{
            String message = "Something went wrong";
            request.getSession().setAttribute("errorNotices",message);
            getAllFeedBacks();
            setRequestToPage(request,response);
            gotoPage(request,response);
        }
    }

    private void ChangeFeedToApprove(HttpServletRequest request, HttpServletResponse response,int id) throws SQLException, ServletException, IOException {
        if(feedBackController.UpdateFeedBackStatusToApproveQ(id)>0){
            String message = "Feddback Approved successfully";
            request.getSession().setAttribute("successNotices",message);
            getAllFeedBacks();
            setRequestToPage(request,response);
            gotoPage(request,response);
        }else{
            String message = "Something went wrong";
            request.getSession().setAttribute("errorNotices",message);
            getAllFeedBacks();
            setRequestToPage(request,response);
            gotoPage(request,response);
        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/feedbacks";
        response.sendRedirect(url);

    }

    private void setRequestToPage(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("FeedBacksList", feedBacksList);
    }

    private void getAllFeedBacks() throws SQLException {
        feedBacksList = feedBackController.AllFeedbacks();
    }

    private void getParameters(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("id") != null) {
            FeedBackId = Integer.parseInt(request.getParameter("id"));
        }
        if (request.getParameter("action") != null) {
            ConfirmFeedBack = request.getParameter("action");
        }
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
