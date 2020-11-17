package com.fertifa.app.converters;

import com.fertifa.app.constants.Constances;
import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.InvitationController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Tokens;
import com.fertifa.app.models.Users;
import com.fertifa.app.parser.AnotherMethod;
import com.fertifa.app.parser.EmployeeModel;
import com.fertifa.app.utils.SendingEmailNewUser;
import com.fertifa.app.utils.TokenMaker;
import com.fertifa.app.utils.Validation;
import lombok.SneakyThrows;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/employer/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class UploadingData extends com.fertifa.app.baseUrl.BaseUrl {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "tempFiles";
    private List<EmployeeModel> employeeModelList = new ArrayList<>();
    private List<Users> employeeModel = new ArrayList<>();
    private UsersController usersController = new UsersController();
    private String token = "";

    private InvitationController invitationController = new InvitationController();
    private List<Users> usersEmailList = new ArrayList<>();
    private Users users = new Users();

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookieManager(request, response)) {
            super.service(request, response);
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA,getEmployerId(request,response));
            uploadingData(request, response);
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

    private void uploadingData(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException, InvalidFormatException, MessagingException {
        UploadExcelToServer(request, response);
    }


    public boolean extractFilTypeSuffix(String fileName) throws IOException {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return extension.equals("csv");
    }

    private void UploadExcelToServer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException, InvalidFormatException, MessagingException {
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + UPLOAD_DIRECTORY;

        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        for (Part part : request.getParts()) {
            if (part.getName().equals("Image")) {
                String fileName = getFileName(part);
                // refines the fileName in case it is an absolute path
                fileName = new File(fileName).getName();
                if(!extractFilTypeSuffix(fileName)){
                    String message = "File not in CSV format";
                    sendWarning(request,response,message);
                }else {
                    String filepat = UPLOAD_DIRECTORY + "/" + fileName;
                    part.write(savePath + File.separator + fileName);
                    //startUplaoding(request,response,filepat);
                    extractFilType(request, response, savePath, fileName);
                }
            }
        }
    }

    private void extractFilType(HttpServletRequest request, HttpServletResponse response, String path, String fileName) throws IOException, ServletException, SQLException, MessagingException {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (extension.equals("xlsx")) {
            //startExtractingxlsx(path, fileName);
            String message = "File not in CSV format";
            sendWarning(request, response,message);
        } else if (extension.equals("xls")) {
            String message = "File not in CSV format";
            sendWarning(request, response,message);
            //startExtractingxls(path, fileName);
        } else if (extension.equals("csv")) {
            startExtractingCsv(request, response, path, fileName);
        } else {
            String message = "File not in CSV format";
            sendWarning(request, response,message);
        }
    }

    private void sendWarning(HttpServletRequest request, HttpServletResponse response,String message) throws ServletException, IOException {

        request.getSession().setAttribute("session_error_message", message);
        getRequests(request);
        gotoPage(request, response);
    }


    private void startExtractingCsv(HttpServletRequest request, HttpServletResponse response, String path, String fileName) throws IOException, ServletException, SQLException, MessagingException {
        employeeModelList = new ArrayList<>();
        employeeModelList = AnotherMethod.ReadingCSV(path, fileName);
        StartTempDatabase(request, response, employeeModelList);

    }

    private void StartTempDatabase(HttpServletRequest request, HttpServletResponse response, List<EmployeeModel> employeeModelList) throws IOException, SQLException, ServletException, MessagingException {

        // check if employee not exists in database
        List<EmployeeModel> validEmployeeList = validateEmployeeList(employeeModelList);

        // create employee with token
        usersEmailList = generateEmployeeWithToken(validEmployeeList);

        // validate emails
        validateEmails(usersEmailList);

        // save data to token and users database
        saveUsersToDatabase();

        // send multi invitation
        sendMultiInvitation(response, request, usersEmailList);

        getAllTempEmpByCompanyId(users.getId());
        getRequests(request);
        gotoPage(request, response);
    }

    private List<Users> validateEmails(List<Users> usersEmailList) {
        List<Users> usersList = new ArrayList<>();
        for (Users users: usersEmailList) {
            if (Validation.validate(users.getEmail())) {
                usersList.add(users);
            }
        }
        return this.usersEmailList = usersList;
    }

    private void saveUsersToDatabase() throws SQLException {
        for (Users user : usersEmailList) {
            invitationController.AddNewTokenAndUser(new Tokens(user.getCompanyId(), user.getToken(), user.getFirstName(), user.getLastName(), user.getEmail()));
            usersController.AddnewUsersInvitation(user);
        }
    }

    private List<Users> generateEmployeeWithToken(List<EmployeeModel> employeeModelList) {
        List<Users> usersList = new ArrayList<>();
        for (EmployeeModel employeeModel : employeeModelList) {

            token = TokenMaker.buildToken(12);

            Users forEmail = new Users(
                    employeeModel.getEmail(),
                    employeeModel.getFirsName(),
                    employeeModel.getLastName(),
                    Constances.PENDING,
                    Constances.USERS,
                    new Timestamp(new Date().getTime()),
                    users.getId(),
                    token);

            usersList.add(forEmail);
        }
        return usersList;
    }

    private List<EmployeeModel> validateEmployeeList(List<EmployeeModel> employeeModelList) throws SQLException {

        List<EmployeeModel> validEmployeeEmail = new ArrayList<>();

        for (EmployeeModel employee : employeeModelList) {
            boolean isExists = usersController.userIsExists(employee.getEmail());
            if (!isExists) {
                validEmployeeEmail.add(employee);
            }
        }
        return validEmployeeEmail;
    }

    private void sendMultiInvitation(HttpServletResponse response, HttpServletRequest request, List<Users> usersEmailList) throws ServletException, MessagingException, IOException {
        SendingEmailNewUser.sendMuliUser(usersEmailList, request, response);
    }


    private void getAllTempEmpByCompanyId(int id) throws SQLException {
        employeeModel = new ArrayList<>();
        employeeModel = usersController.getAllUsersEmailByCompanyId(id);
    }


    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/employer/enrolmentdata";
        response.sendRedirect(url);
       //request.getRequestDispatcher(url).forward(request, response);
    }

    private void getRequests(HttpServletRequest request) {
        request.setAttribute("EmployeeModelList", employeeModel);
    }


    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        //LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
