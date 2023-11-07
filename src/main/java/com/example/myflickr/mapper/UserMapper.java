package com.example.myflickr.mapper;

import com.example.myflickr.entity.City;
import com.example.myflickr.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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


    // @Params注解干啥的?
}
