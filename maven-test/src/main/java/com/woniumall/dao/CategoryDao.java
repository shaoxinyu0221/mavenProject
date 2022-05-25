package com.woniumall.dao;

import com.woniumall.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDao {

    //增删改查
    Integer insert(Category category);


    Integer delete(@Param("id") Integer id);


    Integer update(Category category);


    Category queryById(@Param("id") Integer id);


    List<Category> queryAll();
}
