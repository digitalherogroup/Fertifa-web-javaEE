package com.fertifa.app.adminSide.employer;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.PackageController;
import com.fertifa.app.controllers.SessionController;

import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Users;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/admin/customer/employer/update")
public class UpdateCompany extends com.fertifa.app.baseUrl.BaseUrl {
    private String CompanyName = null;
    private String Domain = null;
    private String Status = null;
    private String PhoneNumber = null;
    private String Address = null;
    private String PackageName = null;
    private int BranchId = 0;

    private String SessionEmail = null;
    private int PackageId = 0;
    private AdminController adminController = new AdminController();
    private SessionController sessionController = new SessionController();
    private UsersController usersController = new UsersController();
    private List<Admins> adminFullList = new ArrayList<>();
    private List<Users> CompanyList = new ArrayList<>();
    private int CompanyId = 0;
    private PackageController packageController = new PackageController();
    private Admins admins = new Admins();
    private List<Users> usersList = new ArrayList<>();


    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            updateCompany(request, response);
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

    private void updateCompany(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getParameters(request);
        getPackageIdByName(PackageName);
        StartUpdatingCompany(CompanyId,request,response);

    }

    private void StartUpdatingCompany(int companyId, HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
        if(usersController.UpdateCompany(CreateObjectOfCompany(),companyId) > 0){
            startUpdatingEmployees(CompanyName,companyId);
            getAllComapnies();
            setRequests(request);
            gotoPageWithSuccess(request,response);
        }else{
            getAllComapnies();
            setRequests(request);
            gotoPageWithError(request,response);
        }
    }

    private void startUpdatingEmployees(String companyName, int companyId) throws SQLException {
        usersController.updateEmployeeCompanyName(companyName,companyId);
    }

    private void getPackageIdByName(String packageName) throws SQLException {
        PackageId = packageController.getPackagId(packageName);
    }

    private Users CreateObjectOfCompany() {
        Date date = new Date();
        return new Users(CompanyName,Integer.parseInt(Status),Domain,Address, new Timestamp(date.getTime()),PhoneNumber,PackageName,PackageId);
    }


    private void gotoPageWithSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "Employer updated successfully";
        request.getSession().setAttribute("successNotices",message);

        String url ="/admin/customer/employer";
        response.sendRedirect(url);
        //request.getRequestDispatcher(url).forward(request,response);
        request.getSession().setAttribute("successNotices",null);
        request.getSession().setAttribute("errorNotices",null);
    }

    private void gotoPageWithError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "Something went wrong";
        request.getSession().setAttribute("successNotices",message);
        String url ="/admin/customer/employer/CompanyData.jsp";
        request.getRequestDispatcher(url).forward(request,response);
        request.getSession().setAttribute("successNotices",null);
        request.getSession().setAttribute("errorNotices",null);
    }

    private void setRequests(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("UsersList", usersList);
    }


    private void getAllComapnies() throws SQLException {
        usersList = usersController.getAllCompaniesByRole("company");
    }


    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

    private void getParameters(HttpServletRequest request) throws SQLException {
      //TODO:Changing this section to String[] or map<String,String[]>
        if(CheckNullPoints(request.getParameter("id"))){
            CompanyId = Integer.parseInt(request.getParameter("id"));
        }
        if(CheckNullPoints(request.getParameter("company"))){
            CompanyName = request.getParameter("company");
        }

        if(CheckNullPoints(request.getParameter("status"))){
            Status = request.getParameter("status");
        }

        if(CheckNullPoints(request.getParameter("domain"))){
            Domain = request.getParameter("domain");
        }

        if(CheckNullPoints(request.getParameter("address"))){
            Address = request.getParameter("address");
        }

        if(CheckNullPoints(request.getParameter("phone"))){
            PhoneNumber = request.getParameter("phone");
        }
        if(CheckNullPoints(request.getParameter("packagename"))){
            PackageName = request.getParameter("packagename");
        }
    }


    private boolean CheckNullPoints(String name) {
        return name != null;
    }

}
