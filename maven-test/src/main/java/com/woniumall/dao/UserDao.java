package com.woniumall.dao;

import com.woniumall.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    User queryUserToLogin(@Param("account") String account, @Param("password") String password);

    User queryUserByAccount(@Param("account") String account);

    Integer insert(User user);

    User queryById(Integer id);

    Integer updateScoreById(@Param("id") Integer id, @Param("score") Integer score);

    Integer getByOrderIdToUserStepTwo(Integer id);

    User getByIdSteptwo(Integer userId);

}
