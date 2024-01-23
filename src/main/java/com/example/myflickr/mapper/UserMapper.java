package com.example.myflickr.mapper;

import com.example.myflickr.entity.City;
import com.example.myflickr.entity.Photo;
import com.example.myflickr.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

// 表明+Mapper
@Mapper
public interface UserMapper {
    @Select("select * from sch1.t_user")
    public List<User> selectAll();

    @Select("select * from sch1.t_user where id = #{id}")
    public User selectById(Integer id);

    @Select("select * from sch1.t_user where name = #{name}")
    public User selectByName(String name);  // 如果返回多条会抛出异常 会终止运行吗?

    @Insert("insert into sch1.t_user (name, password, gender, birth, role) values (#{name}, #{password}, #{gender}, #{birth}, #{role})")
    public int insert(User user);

    @Select("select * from sch1.t_user where name = #{name} and password = #{password}")
    public User selectByNameAndPassword(String name, String password);

    @Select("call sch1.enum_user_city(#{id});")
    public List<String> selectUserActiveCity(Integer id);

    // TODO: move to photoMapper
//    @Select("select * from sch1.t_photo where uid = #{uid}")
//    public List<Photo> selectPhotoByUid(Integer uid);

    @Select("select sch1.t_photo.id, uid, cid, isprivate, date, path, url, sch1.t_user.name as userName, sch1.t_city.name as cityName from ((sch1.t_photo inner join sch1.t_user on sch1.t_photo.uid = sch1.t_user.id) inner join sch1.t_city on sch1.t_photo.cid = sch1.t_city.id) where uid = #{uid}")
    public List<Photo> selectPhotoByUid(Integer uid);

    @Delete("delete from sch1.t_user where id = #{id}")
    public int deleteById(Integer id);

    @Update("update sch1.t_user set name = #{name}, gender = #{gender}, birth = #{birth} where id = #{id}")
    public int updateById(User user);


    // @Params注解干啥的?
}
