package com.example.myflickr.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@TableName("photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String uid;
//    public String cid;
//    public String password;

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                '}';
    }
}
