package com.example.myflickr.controller;

import com.example.myflickr.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
// 默认转换为JSON格式
public class HelloController {

//    @GetMapping("/hello")
//    public String hello(){
//        return "Hello World!";
//    }
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(String name, String age){
        return "Hello, " + name + age;
    }
    // 前端和后端参数名称不同 用@RequestParam
    // http://localhost:8080/hello?name=tom&age=12

    // JSON
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postJson(@RequestBody User user){
        System.out.println(user);
        return "post ok";
    }
}
