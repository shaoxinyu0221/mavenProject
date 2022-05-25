package com.demo.service;

import com.demo.dao.UserDao;
import com.demo.entity.User;
import com.demo.exception.IntegerException;
import com.demo.exception.UserNullException;
import com.demo.util.MyBatisUtil;

public class UserService {
    UserDao userDao = MyBatisUtil.getDao(UserDao.class);

    public Integer addUser(User user){
        Integer insert = userDao.insert(user);
        if (insert == 0){
            throw new IntegerException("插入失败");
        }
        return insert;
    }


    public Integer deleteUser(Integer id){
        Integer integer = userDao.deleteById(id);
        if (integer == 0){
            throw new IntegerException("删除失败");
        }
        return integer;
    }

    public Integer updateUser(User user){
        Integer integer = userDao.updateById(user);
        if (integer == 0){
            throw new IntegerException("更新失败");
        }
        return integer;
    }


    public User queryUser(Integer id){
        User user = userDao.queryById(id);
        if (user == null){
            throw new UserNullException("该用户不存在");
        }
        return user;
    }
}
