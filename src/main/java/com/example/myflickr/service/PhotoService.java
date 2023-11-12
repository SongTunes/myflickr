package com.example.myflickr.service;

import com.example.myflickr.entity.Photo;
import com.example.myflickr.exception.ServiceException;
import com.example.myflickr.mapper.PhotoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {
    private static final Logger log = LoggerFactory.getLogger(PhotoService.class);

    @Autowired
    private PhotoMapper photoMapper;

    public PageInfo<Photo> getAllPhoto(Integer PageNum, Integer PageSize){
        // 开启分页查询
        PageHelper.startPage(PageNum, PageSize);
        List<Photo> photos = photoMapper.selectAll();
        return PageInfo.of(photos);
    }

    public int deletePhotoById(Integer id){
        return photoMapper.deleteById(id);
    }

//    public Photo insert(Photo photo, HttpServletRequest request){
//        int insertNum = photoMapper.insert(photo);
//        if(insertNum <= 0){
//            throw new ServiceException("图片上传失败");
//        }
//        String url = request.getScheme() + "://" +
//                request.getServerName() + ":" + request.getServerPort() +
//                "/myflickr/photo/" + photo.getPhotoName();
//        System.out.println("url: " + url);
//        photo.setUrl(url);
//        return photo;
//    }
}
