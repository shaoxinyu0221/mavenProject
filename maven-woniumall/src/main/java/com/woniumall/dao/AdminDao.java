package com.woniumall.dao;

import com.woniumall.entity.Admin;
import com.woniumall.entity.User;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {

    Admin queryAdminToLogin(@Param("account") String account, @Param("password") String password);

}
