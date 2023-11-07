package com.example.myflickr.controller;


import com.example.myflickr.common.Result;
import com.example.myflickr.entity.Photo;
import com.example.myflickr.mapper.PhotoMapper;
import com.example.myflickr.service.PhotoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @GetMapping("/all-photo")
    public Result selectAll(){
        PageInfo<Photo> photoData = photoService.selectAll();
        System.out.println(photoData);
        return Result.success(photoData);
    }

}
