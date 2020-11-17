package com.fertifa.app.adminSide.news;


import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.NewsController;
import com.fertifa.app.models.Admins;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/news/delete")
public class DeleteNews extends com.fertifa.app.baseUrl.BaseUrl {
    private int NewsId =0;
    private AdminController adminController = new AdminController();
    private NewsController newsController = new NewsController();
    private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            deleteNews(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
            super.doGet(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
            super.doPost(request, response);
    }
    private void deleteNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {

        startAdminControle(request,response);
        getParameters(request);
        startDeletProgress(NewsId,request,response);
    }

    private void startDeletProgress(int newsId, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if(newsController.DeleteNews(NewsId) > 0){

            setRequests(request);
            String message = "News Deleted Successfully";
            request.getSession().setAttribute("successNotices",message);
            gotoPage(request,response);
        }else{

            setRequests(request);
            String message = "Something went wrong";
            request.getSession().setAttribute("errorNotices",message);
            gotoPage(request,response);
        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/news";
        response.sendRedirect(url);
       // request.getRequestDispatcher(url).forward(request,response);

    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
    }

    private void getParameters(HttpServletRequest request) {
        NewsId = Integer.parseInt(request.getParameter("id"));
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response)throws SQLException {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
