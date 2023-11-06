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
}
