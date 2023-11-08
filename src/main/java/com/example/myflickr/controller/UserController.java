package com.example.myflickr.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.myflickr.common.Result;
import com.example.myflickr.entity.Photo;
import com.example.myflickr.entity.User;
import com.example.myflickr.exception.ServiceException;
import com.example.myflickr.service.PhotoService;
import com.example.myflickr.service.UserService;
import com.example.myflickr.utils.JwtTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
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
        User u = userService.login(user.getName(), user.getPassword());
        u = userService.getUserInfo(u.getToken());
        return Result.success("登录成功", u);  // 这里返回了u则说明验证一定通过了

    }

    @PostMapping("/signup")
    public Result signup(@RequestBody User user){
        // @RequestBody需要格式为JSON
        User u = userService.signup(user.getName(), user.getPassword());
        return Result.success("注册成功", u);
    }


    @GetMapping("/info")
    public Result getUserInfo(String token){
        User u = userService.getUserInfo(token);
        return Result.success(u);
    }

    @GetMapping("/all-user")
    public Result selectAll(){
        List<User> userData = userService.getAllUser();
        System.out.println(userData);
        return Result.success(userData);
    }

    @PostMapping("/update")
    public Result updateUser(User user){
        if(userService.updateUser(user) > 0){
            return Result.success("用户更新成功");
        }
        return Result.error("用户更新失败");
    }

    @GetMapping("/delete")
    public Result deleteUserById(Integer id){
        if(userService.deleteUserById(id) > 0){
            return Result.success("用户删除成功");
        }
        return Result.error("用户删除失败");
    }

    @PostMapping("/upload")
    public Result upload(
            @RequestParam(defaultValue = "false") Boolean isPrivate,
            @RequestParam Integer uid,
            @RequestParam Integer cid,
            @RequestParam @DateTimeFormat(pattern = "MM-dd-yy") Date date,
            @RequestParam MultipartFile photoFile,
            HttpServletRequest request
    )
    {
        Photo photo = userService.upload(isPrivate, uid, cid, date, photoFile, request);
        return Result.success("图片上传成功", photo);
    }

    // @RequestParam(required=false)
    // 前端Get方法传token=
    @GetMapping("/export")
    public void exportUser(HttpServletResponse response) throws IOException {
        ExcelWriter writer = ExcelUtil.getWriter(true);
        List<User> list = new ArrayList<>();
        list = userService.getAllUser();
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset-utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="  + URLEncoder.encode("用户信息表", "UTF-8") + ".xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);  // 写完之后关闭流
        // 保险再开关一次
        writer.close();
        outputStream.flush();
        outputStream.close();
//        return Result.success("导出成功");
    }

    @PostMapping("/import")
    public Result importUser(@RequestParam MultipartFile userFile) throws IOException {

        ExcelReader reader = ExcelUtil.getReader(userFile.getInputStream());
        List<User> userList = reader.readAll(User.class);
        // write to db
        try{
            userService.add(userList);
        } catch (Exception e) {
            return Result.error("用户表导入失败");
        }

        return Result.success("用户表导入成功");
    }


}
