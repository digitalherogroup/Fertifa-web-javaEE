package com.fertifa.app.partners;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/partners/update")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB

public class UpdatePartner extends com.fertifa.app.baseUrl.BaseUrl {
    private AdminController adminController = new AdminController();
    private PartnerController partnerController = new PartnerController();
    private List<Partners> partnersList = new ArrayList<>();
    private String Title = "";
    private String UpdateDomain = "";
    private String Description = "";
    private int Id = 0;
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "upload";
 private Admins admins = new Admins();

    @SneakyThrows
    @Override
   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            updatePartner(request, response);
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

    private void updatePartner(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getParameters(request, response);
    }

    private void getParameters(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        if (request.getParameter("id") != null) {
            Id = Integer.parseInt(request.getParameter("id"));
        }
        if (request.getParameter("updateName") != null) {
            Title = request.getParameter("updateName");
        }
        if (request.getParameter("updateDomain") != null) {
            UpdateDomain = request.getParameter("updateDomain");
        }
        if (request.getParameter("Updatedescription") != null) {
            Description = request.getParameter("Updatedescription");
        }
        if (request.getPart("Image").getSize() > 0) {
            UploadImageToServer(request, response);
        } else {
            AddToDataWithoutImage(request, response);
        }
    }

    private void AddToDataWithoutImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        // getParamters(request,response);
        Partners partnersModel = new Partners(Title, Description, UpdateDomain,UpdateDomain);
        if (partnerController.Update(partnersModel, Id) > 0) {
            getAllPartners();
            setRequestToPage(request);
            String message = "Partner Updated Successfully";
            request.getSession().setAttribute("successNotices", message);
            gotopage(request, response);

        } else {
            getAllPartners();
            setRequestToPage(request);
            String message = "Partner Updated Successfully";
            request.getSession().setAttribute("successNotices", message);
            gotopage(request, response);
        }
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
                String fileName = extractFileName(part);
                // refines the fileName in case it is an absolute path
                fileName = new File(fileName).getName();

                String filepat = UPLOAD_DIRECTORY + "/" + fileName;
                part.write(savePath + File.separator + fileName);
                UpdateImage(request, response, filepat);
            }

        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    private void UpdateImage(HttpServletRequest request, HttpServletResponse response, String filePath) throws ServletException, IOException, SQLException {
        //getParamters(request,response);
        //String linkToSave = com.fertifa.app.Constances.WEBSITE + filePath;
        String linkToSave = filePath;
        Partners partnerModel = new Partners(Title, Description, linkToSave, UpdateDomain);
        if (partnerController.UpdateImageById(partnerModel, Id) > 0) {
            getAllPartners();
            setRequestToPage(request);
            String message = "Partner Updated Successfully";
            request.getSession().setAttribute("successNotices", message);
            gotopage(request, response);

        } else {
            getAllPartners();
            setRequestToPage(request);
            String message = "Something went wrong.";
            request.getSession().setAttribute("errorNotices", message);
            gotopage(request, response);
        }
    }

    private void gotopage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        String url = "/admin/partners";
        response.sendRedirect(url);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("PartnersFullList", partnersList);
    }

    private void getAllPartners() throws SQLException {
        partnersList = partnerController.showAll();
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
