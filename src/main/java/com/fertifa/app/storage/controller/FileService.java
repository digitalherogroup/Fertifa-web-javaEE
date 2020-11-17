package com.fertifa.app.storage.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileService {

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    public ResponseEntity<?> uploadFile(MultipartFile file) {
        Map<String,Object> body = new HashMap<>();
        try {
            String name = StringUtils.cleanPath(file.getOriginalFilename());

            String withoutLastCharacter = name.replace(" ", "-");
            Path copyLocation = Paths.get(uploadDir + File.separator   + "/jvm/apache-tomcat-9.0.27/domains/second.fertifabenefits.com/ROOT/upload/" + withoutLastCharacter);
            //Path copyLocation = Paths.get(uploadDir + File.separator   + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            String finalLocation = "/upload/"+file.getOriginalFilename();
            body.put("imageLink",finalLocation);
            return new ResponseEntity<>(body.get("imageLink"), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}