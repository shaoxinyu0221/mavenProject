package com.woniumall.entity;

import java.io.Serializable;

//开启二级缓存第三步,实现实体类的序列化
public class Category implements Serializable {

    private Integer id;
    private String name;
    private String status;
    public static final String NORMAL = "1";
    public static final String UNABLE = "0";

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Category(Integer id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Category(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
