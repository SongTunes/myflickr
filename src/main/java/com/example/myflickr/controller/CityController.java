package com.example.myflickr.controller;


import com.example.myflickr.common.Result;
import com.example.myflickr.entity.City;
import com.example.myflickr.entity.Photo;
import com.example.myflickr.service.CityService;
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
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/all-city")
    public Result getAllCity(){
        List<City> cityData = cityService.getAllCity();
        return Result.success(cityData);
    }

    @RequestMapping("/add")
    public Result add(City city){
        City c = cityService.add(city);
        return Result.success(c);
    }
}
