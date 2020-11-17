package com.fertifa.app.adminSide.news;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.controllers.NewsController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.models.NewsModel;
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
import java.util.Arrays;
import java.util.List;

@WebServlet("/admin/news/edit/update")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class UpdateNewNews extends com.fertifa.app.baseUrl.BaseUrl {
    private AdminController adminController = new AdminController();
    private NewsController newsController = new NewsController();
    private List<NewsModel> newsList = new ArrayList<>();

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "upload";
    private NewsRequest newsRequest = new NewsRequest(new NewsRequest.NewsRequestBuilder());
    private Admins admins = new Admins();
    private static final  String SUCCESS = "successNotices";
    private static final  String ERROR = "errorNotices";
    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkCookie(request, response)) {
            super.service(request, response);
            updateNewNews(request, response);
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

    private void updateNewNews(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        startAdminControle(request,response);
        getParameters(request,response);
    }

    private void getParameters(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        newsRequest = new NewsRequest.NewsRequestBuilder()
            .id(Integer.parseInt(request.getParameter("id")))
            .title(correctText(request.getParameter("updatetitle")))
            .Description(correctText(request.getParameter("Updatedescription")))
            .categoryActive(Integer.parseInt(request.getParameter("activeCategory")))
            .categoryId(Arrays.asList(request.getParameterMap().get("category")))
            .Content(correctText(request.getParameter("content")))
            .imageLink(request.getParameter("imageEdit"))
            .build();

        if (request.getPart("Image").getSize()>0) {
            UploadImageToServer(request, response);
        } else {
            AddToDataWithoutImage(request, response);
        }

    }


    private String correctText(String text) {
     /*   String filteredText = text.replaceAll("[^\\x00-\\x7f,£]", "");
        return filteredText;*/
        return text;
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
            if(part.getName().equals("Image")) {
                String fileName = extractFileName(part);
                // refines the fileName in case it is an absolute path
                fileName = new File(fileName).getName();

                String filepat = UPLOAD_DIRECTORY + "/" + fileName;
                part.write(savePath + File.separator + fileName);
                UpdateImage(request, response, filepat);
            }

        }
    }

    /**
     * Extracts file name from HTTP header content-disposition
     */
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
        newsRequest = new NewsRequest.NewsRequestBuilder()
            .id(Integer.parseInt(request.getParameter("id")))
            .title(correctText(request.getParameter("updatetitle")))
            .Description(correctText(request.getParameter("Updatedescription")))
            .categoryActive(Integer.parseInt(request.getParameter("activeCategory")))
            .categoryId(Arrays.asList(request.getParameterMap().get("category")))
            .Content(correctText(request.getParameter("content")))
            .imageLink(filePath)
            .build();
        getDataSavingResponse(request, response);
    }

    private void getDataSavingResponse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        if (newsController.updateObject(newsRequest) > 0) {
            String message = "News Updated Successfully";
            setRequestToPage(request);
            goToPage(request, response, message, SUCCESS);

        } else {
            String message = "Something went wrong";
            setRequestToPage(request);
            goToPage(request, response, message, ERROR);
        }
    }

    private void goToPage(HttpServletRequest request, HttpServletResponse response, String message, String errorNotices) throws IOException {
        request.getSession().setAttribute(errorNotices, message);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        String url = "/admin/news";
        response.sendRedirect(url);

    }


    private void AddToDataWithoutImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        List<NewsModel> list =  new ArrayList<>();
        list  =  newsController.getById(Integer.parseInt(request.getParameter("id")));

        if(list.get(0).getThumbnailUrl() != null){
            newsRequest = new NewsRequest.NewsRequestBuilder()
                .id(Integer.parseInt(request.getParameter("id")))
                .title(correctText(request.getParameter("updatetitle")))
                .Description(correctText(request.getParameter("Updatedescription")))
                .categoryActive(Integer.parseInt(request.getParameter("activeCategory")))
                .categoryId(Arrays.asList(request.getParameterMap().get("category")))
                .Content(correctText(request.getParameter("content")))
                .imageLink(request.getParameter("imageEdit"))
                .build();
            getDataSavingResponse(request,response);
        }else {
            getDataSavingResponse(request, response);
        }
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("AdminsObject", admins);
        request.setAttribute("NewsList", newsList);

    }

    private void startAdminControle(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

}
