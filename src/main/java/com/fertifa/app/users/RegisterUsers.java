package Users;


import com.fertifa.app.constants.Constances;
import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.InvitationController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.helpser.ChookiManager;
import com.fertifa.app.models.Users;
import com.fertifa.app.notification.Notifications;
import com.fertifa.app.utils.SendEmailToAdmin;
import com.fertifa.app.utils.SendingEmailNewCompany;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet("/RegisterUsers")
public class RegisterUsers extends HttpServlet {

    private String userPassword = "";
    private String userEmail = "";
    private String ConfirmPassword = "";
    private String userFirstName = "";
    private String userLastName = "";
    private String userGender = "";
    private String userId = "";
    private String tokenCode = "";
    private int GenderId = 0;
    private int age = 0;
    private Users company = new Users();
    private UsersController usersController = new UsersController();
    private List<Users> usersList = new ArrayList<>();
    private int status = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            registerUsers(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            registerUsers(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void registerUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        requestParameters(request);
        checkInputs(request, response);
    }

    private void checkInputs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (userPassword.equals("") || !userPassword.equals(ConfirmPassword)) {
            setRequeests(request);
            gotoPageWithError(request, response);
        } else {
            startAddingNewCompanyProcess(request, response);
        }
    }

    private void startAddingNewCompanyProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        if (usersController.UpdatenewUserRegistrationByID(CreateObjectCompany(), Integer.parseInt(userId), Constances.USERS) > 0) {
            Users users = new Users();
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, userId);
            if (users != null) {
                NotifyFertifaByEmail(users.getFirstName(), users.getLastName(), String.valueOf(users.getId()));
                changeTokenValue(users.getId());
                sendVerificationCode(request, response);
                setSuccessRequests(request);
                gotoSuccessRegisteredPage(request, response);
            } else {
                String message = "Something went wrong";
                Notifications.ErrorNotify(request, message);
                setRequeests(request);
                gotoPageWithError(request, response);
            }
        } else {
            String message = "Something went wrong";
            Notifications.ErrorNotify(request, message);
            setRequeests(request);
            gotoPageWithError(request, response);
        }
    }

    private void changeTokenValue(int userCompanyId) throws SQLException {
        InvitationController invitationController = new InvitationController();
        String token = "KLJSJHDFKSFNJLKJJHKHLG" + userCompanyId;
        if (invitationController.UpdateTokenByUserId(token, userCompanyId) > 0) {
            System.out.println("Updated Token");
        } else {
            System.out.println("Updated Token failed");
        }
    }

    private void gotoSuccessRegisteredPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChookiManager.saveDataToCookie(userId, userEmail, "3", 3,4, response);
        request.getSession().setAttribute("userRole", 3);
        request.getRequestDispatcher("EmployersRegisterCode.jsp").forward(request, response);
    }

    private void setSuccessRequests(HttpServletRequest request) {
        request.setAttribute("message", "Please check your e-mail for a verification code and enter it below. E-mail us at info@fertifa.com if you have any issues.");
        request.setAttribute("userEmail", userEmail);
        request.setAttribute("TokenCode", tokenCode);
        request.setAttribute("Token", tokenCode);
        request.setAttribute("UserId", userId);
        request.setAttribute("UsersList", usersList);
    }

    private void sendVerificationCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Random random = new Random();
        HttpSession session = request.getSession();
        tokenCode = String.valueOf(random.nextInt(9999) + 1000);
        SendingEmailNewCompany.sendCodeUsers(tokenCode, userFirstName, userLastName, userEmail, request, response);
        session.setAttribute("TokenCode", tokenCode);
        session.setAttribute("Token", tokenCode);
    }

    private void NotifyFertifaByEmail(String userFirstName, String userLastName, String companyName) {
        SendEmailToAdmin.sendNewUser(userFirstName, userLastName, companyName);
    }

    private void getCompanyNameById(int userCompanyId) throws SQLException {
        Users company = usersController.findById(Constances.USER_ID_INDATA, String.valueOf(userCompanyId));
    }

    private Users CreateObjectCompany() {
        return new Users(userFirstName, userLastName, userEmail, Constances.PENDING, GenderId, userPassword, age);
    }

    private void gotoPageWithError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setAttribute("message","please check your email for verification code");
        request.setAttribute("message", "please fix  the errors");
        request.setAttribute("Tracker", "active");
        request.setAttribute("UserId", userId);
        request.setAttribute("TokenCode", tokenCode);
        request.setAttribute("Token", tokenCode);
        request.getRequestDispatcher("EmployersRegister.jsp").forward(request, response);
    }

    private void setRequeests(HttpServletRequest request) {
        request.setAttribute("message", "Please check your e-mail for a verification code and enter it below. E-mail us at info@fertifa.com if you have any issues.");
        request.setAttribute("UserFirstName", userFirstName);
        request.setAttribute("UserLastName", userLastName);
        request.setAttribute("UsersEmail", userEmail);
        request.setAttribute("TokenCode", tokenCode);
        request.setAttribute("Token", tokenCode);
        request.setAttribute("UserId", userId);
    }

    private void requestParameters(HttpServletRequest request) {

        if (request.getParameter("fistName") != null) {
            userFirstName = request.getParameter("fistName");
        }
        if (request.getParameter("lastName") != null) {
            userLastName = request.getParameter("lastName");
        }

        if (request.getParameter("age") != null) {
            age = Integer.parseInt(request.getParameter("age"));
        }
        if (request.getParameter("email") != null) {
            userEmail = request.getParameter("email");
        }
        if (request.getParameter("gender") != null) {
            userGender = request.getParameter("gender");
            getGenderId(userGender);
        }
        if (request.getParameter("password") != null) {
            userPassword = request.getParameter("password");
        }

        if (request.getParameter("confirm_password") != null) {
            ConfirmPassword = request.getParameter("confirm_password");
        }

        if(request.getParameter("Token")!= null){
            tokenCode = request.getParameter("TokenId");
        }
       /* if (request.getParameter("id") != null || !request.getParameter("id").isEmpty()) {
            userId = request.getParameter("id");
        }*/
        userId = String.valueOf(usersController.getUserIdByEmail(userEmail));

    }

    private void getGenderId(String userGender) {
        if (userGender.toLowerCase().equals("male")) {
            GenderId = Constances.MALE;
        } else {
            GenderId = Constances.FEMALMALE;
        }
    }
}