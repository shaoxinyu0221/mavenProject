package com.demo.dao;

import com.demo.entity.User;

public interface UserDao {

    Integer insert(User user);

    Integer deleteById(Integer id);

    Integer updateById(User user);

    User queryById(Integer id);
}
