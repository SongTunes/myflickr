package com.example.myflickr.controller;

import com.example.myflickr.common.Result;
import com.example.myflickr.entity.Photo;
import com.example.myflickr.entity.User;
import com.example.myflickr.service.PhotoService;
import com.example.myflickr.service.UserService;
import com.example.myflickr.utils.JwtTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    // spring的功能 注入infoMapper
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        // 后端User属性定义多没关系 保证前端传来的能接收有就行
        User u = userService.login(user);
        System.out.println(user);
        return Result.success(u);  // 这里返回了u则说明验证一定通过了

    }

    @GetMapping("/all-user")
    public Result selectAll(){
        List<User> userData = userService.selectAll();
        System.out.println(userData);
        return Result.success(userData);
    }

    @PostMapping("/signup")
    public Result insert(@RequestBody User user){
        // @RequestBody需要格式为JSON
        System.out.println(user);
        int res = userService.signup(user);
        return Result.success(res);
    }

    @PostMapping("/upload")
    public Result upload(Boolean isPrivate, Integer uid, Integer cid, @DateTimeFormat(pattern = "MM-dd-yy") Date date, MultipartFile photoFile, HttpServletRequest request) {
        Photo photo = userService.upload(isPrivate, uid, cid, date, photoFile, request);
        return Result.success("图片上传成功", photo);
    }


}
