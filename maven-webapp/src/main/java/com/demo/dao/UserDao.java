package com.demo.dao;

import com.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {


    User queryUserToLogin(@Param("username") String username, @Param("password") String password);

    List<User> queryUserList();

}
