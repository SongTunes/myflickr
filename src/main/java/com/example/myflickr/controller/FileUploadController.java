/**
 * TODO: 文件返回前端
 */
package com.example.myflickr.controller;

import com.example.myflickr.common.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@CrossOrigin
@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result uploadFile(String name, MultipartFile photo, HttpServletRequest request) throws IOException {
//        String dir = request.getServletContext().getRealPath("/upload/");
        String dir = "/home/myflickr/photo/";
        System.out.println(name);
        System.out.println(dir);
        saveFile(photo, dir);
        return Result.success("文件上传成功");
    }

    public void saveFile(MultipartFile photo, String dir) throws IOException {
        File fDir = new File(dir);
        if(!fDir.exists()){
            fDir.mkdir();
        }
        System.out.println(dir + photo.getOriginalFilename());
        File file = new File(dir + photo.getOriginalFilename());
        photo.transferTo(file);
    }
}
