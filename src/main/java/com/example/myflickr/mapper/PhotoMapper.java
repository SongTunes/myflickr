package com.example.myflickr.mapper;

import com.example.myflickr.entity.Photo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PhotoMapper {
    @Select("select * from sch1.t_photo")
    public List<Photo> selectAll();

    @Select("select * from sch1.t_photo where private = 'false'")
    public List<Photo> selectPublic();

    @Select("select * from sch1.t_photo where uid = #{uid}")
    public List<Photo> selectByUid(Integer uid);

    @Select("select * from sch1.t_photo where cid = #{cid}")
    public List<Photo> selectByCid(Integer cid);

    @Select("select * from sch1.t_photo where uid = #{uid} and isprivate = 'false'")
    public List<Photo> selectPublicByUid(Integer uid);

    @Select("select * from sch1.t_photo where cid = #{cid} and isprivate = 'false'")
    public List<Photo> selectPublicByCid(Integer uid);

    @Insert("insert into sch1.t_photo (uid, cid, date, isprivate, path, url) values (#{uid}, #{cid}, #{date}, #{isprivate}, #{path}, #{url})")
    public int insert(Photo photo);

    @Delete("delete from sch1.t_photo where id = #{id}")
    public int deleteById(Integer id);
}
