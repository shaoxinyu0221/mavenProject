package com.woniumall.test.dao;

import com.woniumall.dao.UserDao;
import com.woniumall.entity.User;
import com.woniumall.util.MallUtil;
import com.woniumall.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class UserDaoTest {

    SqlSession sqlSession = null;
    UserDao userDao = null;

    @Before
    public void getUp(){
        sqlSession = MyBatisUtil.getSqlSession();
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After
    public void tearDown(){
        sqlSession.commit();
        MyBatisUtil.close(sqlSession);
    }

    @Test
    public void login(){
        String account = "admin";
        String password = "123456";
        User user = userDao.queryUserToLogin(account, password);
        if(user == null){
            System.out.println("登录失败");
        }else {
            System.out.println("登录成功");
        }
    }

    @Test
    public void add(){
        User user = new User();
        user.setAccount("admin");
        user.setRegTime(MallUtil.getNow());
        user.setScore(0);
        user.setPassword("123456");
        user.setAvatar("img/avatar_01");
        user.setEmail("111@.com");
        user.setMoney(new BigDecimal("0"));
        user.setStatus(User.NORMAL);
        Integer insert = userDao.insert(user);
        System.out.println(insert);
    }


    @Test
    public void queryById(){
        User user = userDao.queryById(1);
        System.out.println(user);
    }

}
