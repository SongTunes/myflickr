package com.example.myflickr.controller;


import com.example.myflickr.common.Result;
import com.example.myflickr.entity.Photo;
import com.example.myflickr.mapper.PhotoMapper;
import com.example.myflickr.service.PhotoService;
import com.github.pagehelper.PageException;
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
    public Result getAllPhoto(Integer PageNum, Integer PageSize){
        PageInfo<Photo> photoData = photoService.getAllPhoto(PageNum, PageSize);
        System.out.println(photoData);
        return Result.success(photoData);
    }

    @GetMapping("/delete")
    public Result deletePhoto(Integer id){
        int n = photoService.deletePhotoById(id);
        return Result.success("图片删除成功", n);
    }

}
