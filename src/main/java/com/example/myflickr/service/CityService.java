package com.example.myflickr.service;

import com.example.myflickr.entity.City;
import com.example.myflickr.mapper.CityMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private static final Logger log = LoggerFactory.getLogger(CityService.class);

    @Autowired
    private CityMapper cityMapper;

    public List<City> getAllCity(){
        return cityMapper.selectAll();
    }

    public City add(City city){
        if(cityMapper.insert(city) > 0){
            return city;
        }
        return null;
    }
}
