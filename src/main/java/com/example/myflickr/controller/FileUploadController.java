package com.example.myflickr.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public String uploadFile(String name, MultipartFile photo, HttpServletRequest request) throws IOException {
        String dir = request.getServletContext().getRealPath("/upload/");
        System.out.println(name);
        System.out.println(dir);
        saveFile(photo, dir);
        return "File upload ok.";
    }

    public void saveFile(MultipartFile photo, String dir) throws IOException {
        File fDir = new File(dir);
        if(!fDir.exists()){
            fDir.mkdir();
        }
        File file = new File(dir + photo.getOriginalFilename());
        photo.transferTo(file);
    }
}
