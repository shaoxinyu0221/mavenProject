package com.woniumall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniumall.dao.UserDao;
import com.woniumall.entity.Address;
import com.woniumall.entity.User;
import com.woniumall.exception.AccountIsExist;
import com.woniumall.exception.UserNoActive;
import com.woniumall.exception.UserWasBanned;
import com.woniumall.exception.UsernameOrPasswordEorrException;
import com.woniumall.util.MyBatisUtil;

import java.util.List;
import java.util.Scanner;

public class UserService {


    public static Integer userId = null;

    /**
     * 登录方法
     * @param account 账号
     * @param password 密码
     * @return 如果没有异常,返回一个用户对象
     */
    public User login(String account, String password){
        UserDao userDao = MyBatisUtil.getDao(UserDao.class);
        User user = userDao.queryUserToLogin(account, password);
        if(user == null){
            throw new UsernameOrPasswordEorrException("用户名或密码输入错误");
        }else if(user.getStatus().equals(User.UNABLE)){
            throw new UserWasBanned("当前账号已经被ban");
        }else if (user.getStatus().equals(User.UNACTIVE)){
            throw new UserNoActive("当前用户未激活,请前往邮箱进行激活");
        }else {
            userId = user.getId();
            return user;
        }
    }


    /**
     * 查找账号是否存在,为注册做铺垫
     * @param user
     * @return
     */
    public void register(User user){
        UserDao userDao = MyBatisUtil.getDao(UserDao.class);
        User user1 = userDao.queryUserByAccount(user.getAccount());
        if (user1 != null){
            throw new AccountIsExist("当前账号已存在");
        }else {
            Integer insert = userDao.insert(user);
        }

    }


    public PageInfo<User> getUserList(User user, Integer pageNumDefault, Integer pageSizeDefault) {
        //开启分页
        PageHelper.startPage(pageNumDefault, pageSizeDefault);
        UserDao userDao = MyBatisUtil.getDao(UserDao.class);
        List<User> userList = userDao.queryUserByCondition(user);

        return new PageInfo<User>(userList);
    }



    public void changeUserStatus(String status, Integer userId) {
        UserDao userDao = MyBatisUtil.getDao(UserDao.class);
        userDao.updateStatusById(status,userId);
    }

    public User accountIsExist(String username) {
        UserDao userDao = MyBatisUtil.getDao(UserDao.class);
        User user = userDao.queryUserByAccount(username);
        return user;
    }

    public List<Address> getUserAddress(Integer userCurrentLoginId) {
        UserDao userDao = MyBatisUtil.getDao(UserDao.class);
        return userDao.selectUserAddressList(userCurrentLoginId);

    }

    public User checkEmail(String email) {
        UserDao userDao = MyBatisUtil.getDao(UserDao.class);
        return userDao.selectEmail(email);
    }

    public void activeForEmail(String email) {
        UserDao userDao = MyBatisUtil.getDao(UserDao.class);
        userDao.updateStatusByEmail(email);
    }
}
