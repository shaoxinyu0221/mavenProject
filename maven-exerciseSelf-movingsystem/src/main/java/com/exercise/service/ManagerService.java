package com.exercise.service;

import com.exercise.dao.ManagerDao;
import com.exercise.entity.Manager;
import com.exercise.exception.MyException;
import com.exercise.util.MyBatisUtil;

public class ManagerService {

    public void login(Manager manager){
        ManagerDao managerDao = MyBatisUtil.getDao(ManagerDao.class);
        Manager managerLogin = managerDao.selectToLogin(manager);
        if(managerLogin == null){
            throw new MyException("用户名密码错误");
        }
    }
}
