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

    }

    public List<User> selectAll(){
        return userMapper.selectAll();
    }
    public int insert(User user){
        // 重复性校验
        User user2Check = userMapper.selectByName(user.getName());
        if(user2Check != null){
            // 任何项目都要有: 全局异常处理
            throw new ServiceException("用户名已存在");
        }

        return userMapper.insert(user);
    }
}
