package com.woniumall.service;

import com.woniumall.dao.AdminDao;
import com.woniumall.dao.UserDao;
import com.woniumall.entity.Admin;
import com.woniumall.entity.User;
import com.woniumall.exception.UserNoActive;
import com.woniumall.exception.UserWasBanned;
import com.woniumall.exception.UsernameOrPasswordEorrException;
import com.woniumall.util.MyBatisUtil;

public class AdminService {
    /**
     * 登录方法
     * @param account 账号
     * @param password 密码
     * @return 如果没有异常,返回一个admin对象
     */
    public Admin login(String account, String password){
        AdminDao adminDao = MyBatisUtil.getDao(AdminDao.class);
        Admin admin = adminDao.queryAdminToLogin(account, password);
        if(admin == null){
            throw new UsernameOrPasswordEorrException("用户名或密码输入错误");
        }else if(admin.getStatus().equals(Admin.UNABLE)){
            throw new UserWasBanned("当前账号已经被ban");
        }else {
            return admin;
        }
    }
}
