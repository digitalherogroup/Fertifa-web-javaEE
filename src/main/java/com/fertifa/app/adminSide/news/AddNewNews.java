package com.fertifa.app.adminSide.news;


import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.adminSide.news.NewsRequest.NewsRequestBuilder;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.NewsController;
import com.fertifa.app.models.Admins;
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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/admin/news/add/addnews"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AddNewNews extends com.fertifa.app.baseUrl.BaseUrl {
    //Admin
    private static final  String SUCCESS = "successNotices";
    private static final  String ERROR = "errorNotices";

    private AdminController adminController = new AdminController();
    private Admins admins = new Admins();
    private NewsController newsController = new NewsController();
    private static final long serialVersionUID = 1L;
    private NewsRequest newsRequest = new NewsRequest(new NewsRequestBuilder());
    //directory
    private static final String UPLOAD_DIRECTORY = "upload";
    //logger
    private final static Logger LOGGER =
        Logger.getLogger(AddNewNews.class.getCanonicalName());

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        if (checkCookie(request, response)) {
            super.service(request, response);
            addNewNews(request, response);
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

    private void addNewNews(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request, response);
        getParamters(request, response);
    }

    private void getParamters(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        newsRequest = new NewsRequest.NewsRequestBuilder()
            .title(correctText(request.getParameter("title")))
            .Description(correctText(request.getParameter("description")))
            .categoryActive(Integer.parseInt(request.getParameter("activeCategory")))
            .categoryId(Arrays.asList(request.getParameterMap().get("category")))
            .Content(correctText(request.getParameter("content")))
            .build();

        if (CheckDuplicatedContent()) {
            String message = "Duplicated content";
            setRequestToPage(request);
            goToPage(request, response, message, ERROR);
        } else {
            if (request.getPart("Image").getSize() > 0) {
                UploadImageToServer(request, response);
            } else {
                AddToDataWithoutImage(request, response);
            }
        }

    }
    private String correctText(String text) {
     /*   String filteredText = text.replaceAll("[^\\x00-\\x7f,£]", "");
        return filteredText;*/
     return text;
    }

    private boolean CheckDuplicatedContent() throws SQLException {
        return newsController.checkboxDuplicated(newsRequest.getTitle());
    }

    //uploading images
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
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private void AddToData(HttpServletRequest request, HttpServletResponse response, String filePath) throws IOException, SQLException {
        String linkToSave = filePath;
        newsRequest = new NewsRequest.NewsRequestBuilder()
            .title(request.getParameter("title"))
            .Description(request.getParameter("description"))
            .categoryActive(Integer.parseInt(request.getParameter("activeCategory")))
            .categoryId(Arrays.asList(request.getParameterMap().get("category")))
            .Content(correctText(request.getParameter("content")))
            .imageLink(linkToSave)
            .build();
        getDataSavingResponse(request, response);
    }

    private void getDataSavingResponse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        if (newsController.save(newsRequest) > 0) {
            String message = "News Added Successfully";
            setRequestToPage(request);
            goToPage(request, response, message, SUCCESS);

        } else {
            String message = "Something went wrong";
            setRequestToPage(request);
            goToPage(request, response, message, ERROR);
        }
    }

    private void AddToDataWithoutImage(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        getDataSavingResponse(request, response);
    }

    private void goToPage(HttpServletRequest request, HttpServletResponse response, String message, String errorNotices) throws IOException {
        request.getSession().setAttribute(errorNotices, message);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        String url = "/admin/news/add";
        response.sendRedirect(url);

    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response)throws SQLException {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
