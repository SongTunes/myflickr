package com.example.myflickr.mapper;

import com.example.myflickr.entity.City;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CityMapper {
    @Select("select * from sch1.t_city")
    public List<City> selectAll();

    @Insert("insert into sch1.t_city (name) values (#{name})")
    public int insert(City city);

    @Delete("delete from sch1.t_city where id = #{id}")
    public int deleteById(Integer id);
}
