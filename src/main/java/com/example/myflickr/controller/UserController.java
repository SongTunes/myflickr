package com.example.myflickr.controller;

import com.example.myflickr.common.Result;
import com.example.myflickr.entity.User;
import com.example.myflickr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    // spring的功能 注入infoMapper
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        // 后端User属性定义多没关系 保证前端传来的能接收有就行
        System.out.println(user);
        return Result.success();
    }

    @GetMapping("/all-user")
    public Result selectAll(){
        List<User> userData = userService.selectAll();
        System.out.println(userData);
        return Result.success(userData);
    }


    @PostMapping("/add")
    public Result insert(@RequestBody User user){
        // @RequestBody需要格式为JSON
        System.out.println(user);
        int res = userService.insert(user);
        if(res > 0) return Result.success(res);
        return Result.error("insert error");
    }
}
