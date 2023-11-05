package com.example.myflickr.service;

import com.example.myflickr.entity.Photo;
import com.example.myflickr.mapper.PhotoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {
    @Autowired
    private PhotoMapper photoMapper;

    public PageInfo<Photo> selectAll(){
        // 开启分页查询
        PageHelper.startPage(1, 5);
        List<Photo> photos = photoMapper.selectAll();
        return PageInfo.of(photos);
    }
}
