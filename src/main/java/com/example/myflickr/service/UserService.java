package com.example.myflickr.service;


import com.example.myflickr.entity.User;
import com.example.myflickr.exception.ServiceException;
import com.example.myflickr.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User login(User user){
        if(user.getName() == null || "".equals(user.getName())){
            throw new ServiceException("用户名不可为空");
        }
        if(user.getPassword() == null || "".equals(user.getPassword())){
            throw new ServiceException("密码不可为空");
        }
        User u = userMapper.selectByNameAndPassword(user.getName(), user.getPassword());
        if (u == null){
            throw new ServiceException("用户名或密码错误");
        }
        return u;
    }



    public List<User> selectAll(){
        return userMapper.selectAll();
    }
    public int signup(User user){
        if(user.getName() == null || "".equals(user.getName())){
            throw new ServiceException("用户名不可为空");
        }
        if(user.getPassword() == null || "".equals(user.getPassword())){
            user.setPassword("123");
        }
        // 重复性校验
        User u = userMapper.selectByName(user.getName());
        if(u != null){
            // 任何项目都要有: 全局异常处理
            throw new ServiceException("用户名已存在");
        }

        return userMapper.insert(user);
    }
}
