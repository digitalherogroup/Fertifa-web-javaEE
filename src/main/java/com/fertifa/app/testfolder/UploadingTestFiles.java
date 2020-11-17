package com.fertifa.app.testfolder;

import com.fertifa.app.adminSide.news.AddNewNews;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet("/UploadingTestFiles")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class UploadingTestFiles extends HttpServlet {

    private final static Logger LOGGER =
            Logger.getLogger(AddNewNews.class.getCanonicalName());


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            uploadingTestFiles(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            uploadingTestFiles(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void uploadingTestFiles(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        handleIO(request);
    }

    /*private void UploadImageToServer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + UPLOAD_DIRECTORY;
        com.fertifa.app.Constances.FFFF = savePath;

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
            }
        }
    }
*/
    private static void handleIO(HttpServletRequest request) throws IOException {
        System.out.println("request.getServletContext().getRealPath() " + request.getServletContext().getRealPath(""));
        String cwd = System.getProperty("user.home");
        System.out.println("cwd " + System.getProperty("user.dir"));//cwd /usr/local/cpanel/base
        System.out.println("home " + System.getProperty("user.home"));//home /home/fertifabenefits
        System.out.println();
        File dirAboveCws = new File(new File(cwd).getAbsolutePath());
        if (!dirAboveCws.exists()) {
            dirAboveCws.mkdir();
        }
        System.out.println(System.getProperty(""));
        //File dirAboveCws = new File(cwd).getParentFile();
        System.out.println(" new File(cwd).getAbsolutePath() " + new File(cwd).getAbsolutePath());//home/fertifabenefits
        System.out.println("dirAboveCws " + dirAboveCws);  //dirAboveCws /home
        File[] imageFiles = dirAboveCws.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return !pathname.getName().contains("jpg");
            }
        });

        for (File imageFile : imageFiles) {
            System.out.println("image File " + imageFile);//image File /usr/local/cpanel/sys_cpanel
            System.out.println("images File " + imageFiles);
            FileInputStream fileInputStream = new FileInputStream(imageFile);

            Image image = ImageIO.read(fileInputStream);  // Here is your image
            System.out.println(image + " image ");
        }
    }

}
