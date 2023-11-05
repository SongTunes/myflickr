package com.example.myflickr.mapper;

import com.example.myflickr.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 表明+Mapper
@Mapper
public interface UserMapper {
    @Select("select * from user")
    public List<User> selectAll();

    @Select("select * from user where name = #{name}")
    public User selectByName(String name);  // 如果返回多条会抛出异常 会终止运行吗?

    @Insert("insert into user (name) values (#{name})")
    public int insert(User user);



//    @Insert("insert into aaa (id, name) values (#{id}, #{name})")
//    public int insert(Info info);
    // @Params注解干啥的?
}
