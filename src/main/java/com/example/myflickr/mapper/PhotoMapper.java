package com.example.myflickr.mapper;

import com.example.myflickr.entity.Photo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PhotoMapper {
    @Select("select * from sch1.t_photo")
    public List<Photo> selectAll();
}
