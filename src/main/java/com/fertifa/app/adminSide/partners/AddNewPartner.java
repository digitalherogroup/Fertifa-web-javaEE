package com.fertifa.app.adminSide.partners;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.PartnerController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.Partners;
import lombok.SneakyThrows;

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

@WebServlet("/admin/partners/add/partner")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AddNewPartner extends com.fertifa.app.baseUrl.BaseUrl {

    private AdminController adminController = new AdminController();
    private PartnerController partnerController = new PartnerController();

    private String Title = "";
    private String Description = "";
    private String Domain = "";

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "upload";
private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            addNewPartner(request, response);
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

    private void addNewPartner(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getParamters(request, response);
    }

    private void getParamters(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        if (request.getParameter("name") != null) {
            Title = request.getParameter("name");
        }
        if (request.getParameter("description") != null) {
            Description = request.getParameter("description");
        }
        if (request.getParameter("domain") != null) {
            Domain = request.getParameter("domain");
        }
        List<Partners> newsModelList = new ArrayList<>();
        newsModelList = partnerController.showAll();
        boolean isThere = false;
        if (newsModelList.size() > 0) {
            for (int i = 0; i < newsModelList.size(); i++) {
                if (
                        newsModelList.get(i).getPartnerName().contains(Title)) {
                    isThere = true;
                }
            }
        }
        if (isThere) {

            setRequestToPage(request);
            goToPage(request, response);
        } else {
            if (request.getPart("Image").getSize() > 0) {
                UploadImageToServer(request, response);
            } else {
                AddToDataWithoutImage(request, response);
            }
        }
    }

    private void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        String url = "/admin/partners/add";
        response.sendRedirect(url);
    }

    private void UploadImageToServer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
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

                String filepat = UPLOAD_DIRECTORY + "/" + fileName;
                part.write(savePath + File.separator + fileName);
                //startUplaoding(request,response,filepat);
                AddToData(request, response, filepat);
            }

        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private void AddToData(HttpServletRequest request, HttpServletResponse response, String filePath) throws ServletException, IOException, SQLException {
        //getParamters(request,response);
        //String linkToSave = com.fertifa.app.Constances.WEBSITE + filePath;
        String linkToSave = filePath;
        Partners newPartner = new Partners(Title, Description, new Timestamp(new Date().getTime()), linkToSave, Domain);
        if (partnerController.save(newPartner) > 0) {
            setRequestToPage(request);
            String message = "Partner Added Successfully";
            request.getSession().setAttribute("successNotices", message);
            goToPage(request, response);

        } else {
            setRequestToPage(request);
            String message = "Something went wrong";
            request.getSession().setAttribute("errorNotices", message);
            goToPage(request, response);
        }
    }

    private void AddToDataWithoutImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        // getParamters(request,response);
        Partners newPartnerWithoutImage = new Partners(Title, Description, new Timestamp(new Date().getTime()));
        if (partnerController.save(newPartnerWithoutImage) > 0) {
            setRequestToPage(request);
            String message = "Partner Added Successfully";
            request.getSession().setAttribute("successNotices", message);
            goToPage(request, response);

        } else {
            setRequestToPage(request);
            String message = "Something went wrong";
            request.getSession().setAttribute("errorNotices", message);
            goToPage(request, response);
        }
    }


    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);

    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
