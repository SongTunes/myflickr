package com.example.myflickr.mapper;

import com.example.myflickr.entity.Photo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PhotoMapper {
    @Select("select * from sch1.t_photo")
    public List<Photo> selectAll();

    @Insert("insert into sch1.t_photo (uid, cid, date, private, path) values (#{uid}, #{cid}, #{date}, #{isPrivate}, #{path})")
    public int insert(Photo photo);
}
