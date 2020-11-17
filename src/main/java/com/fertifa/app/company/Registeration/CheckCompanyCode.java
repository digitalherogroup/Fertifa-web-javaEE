package Company.Registeration;


import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.helpser.ChookiManager;
import com.fertifa.app.models.Users;
import com.fertifa.app.notification.useractionnotification.actions.admin.RegisterNotification;
import com.fertifa.app.notification.useractionnotification.api.EmailNotificationManager;
import com.fertifa.app.notification.useractionnotification.api.EmailNotificationManagerForUser;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/CheckCompanyCode")
public class CheckCompanyCode extends HttpServlet {

    private String Email = "";
    private String Code = "";
    private String TokenCode = "";
    private UsersController usersController = new UsersController();
    private EmailNotificationManager emailNotificationManager ;
    private EmailNotificationManagerForUser emailNotificationManagerUser;
    private Users users = new Users();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkCompanyCode(request,response);
    }

    private void checkCompanyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        requestParameters(request);
        checkInputs(request, response, users);
    }

    private void checkInputs(HttpServletRequest request, HttpServletResponse response, Users users) throws ServletException, IOException, SQLException {
        assert users != null;
        if (CheckInput(TokenCode, Code)) {
            if(users.getBranchId()==2){

                changeCompanyStatus(String.valueOf(users.getId()));
                saveCompanyToCookie(response);
            }else {
                changeCompanyStatus(String.valueOf(users.getId()));
                saveCompanyToCookie(response);
            }
            setRequeests(request);
            gotoSuccessRegisteredPage(request, response);
        } else {
            setRequeests(request);
            gotoPageWithError(request, response,users);
        }
    }


    private int changeCompanyStatus(String UserId) throws SQLException {
        return usersController.ChangeCompanyStatusToActive(UserId);
    }

    private void saveCompanyToCookie(HttpServletResponse response) {
        // get user object
        if (users == null) return;
        ChookiManager.saveDataToCookie(String.valueOf(users.getId()), users.getEmail(), "2", 2,1, response);
    }

    private void setRequeests(HttpServletRequest request) {
        request.setAttribute("EmployerObject", users);
    }

    private void gotoPageWithError(HttpServletRequest request, HttpServletResponse response,Users users) throws ServletException, IOException {

        request.setAttribute("message", "You have entered the incorrect code. Please try again or contact <a href=\"mailto:info@fertifa.com\">info@fertifa.com</a>");
        request.setAttribute("ID", users.getId());
        request.setAttribute("company", users.getComapny());
        request.setAttribute("TokenCode", TokenCode);
        request.setAttribute("email", users.getEmail());
        request.setAttribute("Company", users.getComapny());
        request.setAttribute("CompanyEmail", Email);
        request.setAttribute("Token", TokenCode);
        request.getRequestDispatcher("EmployerRegisterCode.jsp").forward(request, response);
    }

    private void gotoSuccessRegisteredPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        emailNotificationManager = new EmailNotificationManager(new RegisterNotification(),users,2);
        String url = "employer/employer-dashboard";
        response.sendRedirect(url);
    }

    private boolean CheckInput(String codes, String code) {
        return code.equals(codes);
    }

    private void requestParameters(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            users = usersController.findById(Constances.USER_ID_INDATA, request.getParameter("id"));
        }

        if(request.getParameter("TokenCode")!= null) {
            TokenCode = request.getParameter("TokenCode");
        }else if(request.getParameter("Token")!= null){
            TokenCode = request.getParameter("Token");
        }
        if (request.getParameter("email") != null) {
            Email = request.getParameter("email");
        }

        if (request.getParameter("code") != null) {
            Code = request.getParameter("code").trim();
        }
    }
}