package com.example.myflickr.mapper;

import com.example.myflickr.entity.City;
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

    @Insert("insert into sch1.t_user (name, password) values (#{name}, #{password})")
    public int insert(User user);

    @Select("select * from sch1.t_user where name = #{name} and password = #{password}")
    public User selectByNameAndPassword(String name, String password);

    @Select("select getUserActiveCity(#{id})")
    public List<City> selectUserActiveCity(Integer id);

    @Delete("delete from sch1.t_user where id = #{id}")
    public int deleteById(Integer id);

    @Update("update sch1.t_user set name = #{name}, gender = #{gender}, birth = #{birth} where id = #{id}")
    public int updateById(User user);


    // @Params注解干啥的?
}
