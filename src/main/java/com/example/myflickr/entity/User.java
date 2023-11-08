package com.example.myflickr.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.util.List;

@TableName("t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Alias("编号")
    private Integer id;
    @Alias("用户名")
    private String name;
    @Alias("密码")
    private String password;
    @Alias("性别")
    private String gender;
    @Alias("出生日期")
    private String birth;
    private String role;

    @Transient
    private String token;

    @Transient
    private List<City> activeCity;

    public User(){}
    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    public List<City> getActiveCity() {
        return activeCity;
    }

    public void setActiveCity(List<City> activeCity) {
        this.activeCity = activeCity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", birth='" + birth + '\'' +
                ", role='" + role + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
