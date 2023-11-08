package com.example.myflickr.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.myflickr.entity.Photo;
import com.example.myflickr.entity.User;
import com.example.myflickr.exception.ServiceException;
import com.example.myflickr.mapper.PhotoMapper;
import com.example.myflickr.mapper.UserMapper;
import com.example.myflickr.utils.JwtTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;
import java.io.File;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PhotoMapper photoMapper;

    public User login(String name, String password){
        User user = new User(name, password);
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
        // 生成token 和user一起返回前端
        String token = JwtTokenUtils.genToken(u.getId().toString(), u.getPassword());
        u.setToken(token);
        return u;
    }

    public User signup(String name, String password){
        User user = new User(name, password);
        checkUser(user);
        if(userMapper.insert(user) > 0){
            return user;
        }
        return null;
    }

    public User getUserInfo(String token){
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException("401", "token验证失败 请登录");
        }
        // 根据token中的userid查询数据库
        User user = userMapper.selectById(Integer.valueOf(userId));
        user.setToken(token);
        user.setActiveCity(userMapper.selectUserActiveCity(Integer.valueOf(userId)));

        return user;
    }

    public int updateUser(User user){
        int n = 0;
        try{
            n = userMapper.updateById(user);
        } catch (Exception e){
            log.error("用户更新失败");
            throw new ServiceException("用户更新失败");
        }
        return n;
    }
    public int deleteUserById(Integer id){
        int n = 0;
        try{
            n = userMapper.deleteById(id);
        } catch (Exception e){
            log.error("用户删除失败");
            throw new ServiceException("用户删除失败");
        }
        return n;
    }

    public int add(User user){
        int n = 0;
        checkUser(user);
        try{
            n = userMapper.insert(user);
        } catch (Exception e){
            log.error("用户添加失败");
            throw new ServiceException("用户添加失败");
        }
        return n;
    }

    public int add(List<User> userList){
        int insertNum = 0;
        try {
            for (User u : userList) {
                insertNum += userMapper.insert(u);
            }
        } catch (Exception e){
            log.error("用户添加失败");
            throw new ServiceException("用户添加失败");
        }
        return insertNum;
    }

    public Boolean checkUser(User user){
        if(user.getName() == null || "".equals(user.getName())){
            throw new ServiceException("用户名不可为空");
        }
        if(user.getPassword() == null || "".equals(user.getPassword())){
            user.setPassword("123");
        }
        if(user.getRole() == null || "".equals(user.getRole())){
            user.setRole("ROLE_USER");
        }
        // 重复性校验
        User u = userMapper.selectByName(user.getName());
        if(u != null){
            // 任何项目都要有: 全局异常处理
            throw new ServiceException("用户名已存在");
        }
        return true;
    }

    // TODO: 文件上传重构到Controller层? Service层应该处理逻辑 一些基本的操作应该在Controller层
    public Photo upload(Boolean isPrivate, Integer uid, Integer cid, Date date, MultipartFile photoFile, HttpServletRequest request) {
//        String dir = request.getServletContext().getRealPath("/upload/");
        Photo photo = new Photo(isPrivate, uid, cid, date);

        // 1. Save file on server.
        String dir = "/home/myflickr/photo/";  // 需要路径存在
        saveFile(photoFile, dir, photo);

        String url = request.getScheme() + "://" +
                request.getServerName() + ":" + request.getServerPort() +
                "/myflickr/photo/" + photo.getPhotoName();
        log.info("url: " + url);
        photo.setUrl(url);

        // 2. Write to db.
        int insertNum = photoMapper.insert(photo);
        if(insertNum <= 0){
            throw new ServiceException("图片上传失败");
        }

        // 3. TODO: rollout if save file ok BUT write db fail.

        return photo;
    }

    private void saveFile(MultipartFile photoFile, String dir, Photo photo) {
        File fDir = new File(dir);
        if(!fDir.exists()){
            fDir.mkdir();
        }
        System.out.println(dir + photoFile.getOriginalFilename());
        try {
            File file = new File(dir + photoFile.getOriginalFilename());
            photoFile.transferTo(file);
        } catch (Exception e) {
            log.error("文件保存失败", e);
            throw new ServiceException("文件保存失败");
        }
        photo.setPhotoName(photoFile.getOriginalFilename());
        photo.setPath(dir + photoFile.getOriginalFilename());

//        return photo;

    }


    public List<User> getAllUser(){
        return userMapper.selectAll();
    }
    public User getUserById(Integer id){
        return userMapper.selectById(id);
    }

}
