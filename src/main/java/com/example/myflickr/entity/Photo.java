package com.example.myflickr.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@TableName("t_photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer uid;
    private Integer cid;
    @Column(name = "private")
    private Boolean isPrivate;
    @DateTimeFormat(pattern = "MM-dd-yy")
    private Date date;
    private String path;

    @Transient
    private String photoName;

    @Transient
    private String url;

    public Photo(){

    }
    public Photo(Boolean isPrivate, Integer uid, Integer cid, Date date){
        this.isPrivate = isPrivate;
        this.uid = uid;
        this.cid = cid;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", uid=" + uid +
                ", cid=" + cid +
                ", isPrivate='" + isPrivate + '\'' +
                ", path='" + path + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
