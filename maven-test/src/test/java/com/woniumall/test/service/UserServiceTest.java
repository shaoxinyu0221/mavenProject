package com.woniumall.test.service;

import com.woniumall.entity.User;
import com.woniumall.exception.MyServiceException;
import com.woniumall.service.ServiceProxyFactory;
import com.woniumall.service.UserService;
import com.woniumall.util.MallUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserServiceTest {

    private UserService proxy = ServiceProxyFactory.getProxy(new UserService());

    @Test
    public void testLogin(){
        try {
            User user = proxy.login("admin", "12345");
        } catch (Exception e) {
            if(e instanceof MyServiceException){
                System.out.println(e.getMessage());
            }else {
                e.printStackTrace();
                System.out.println("登陆失败,系统维护中");
            }
        }
    }



    @Test
    public void testRegister(){
        User user = new User();
        user.setAccount("admin");
        user.setRegTime(MallUtil.getNow());
        user.setScore(0);
        user.setPassword("123456");
        user.setAvatar("img/avatar_01");
        user.setEmail("111@.com");
        user.setMoney(new BigDecimal("0"));
        user.setStatus(User.UNACTIVE);

        try {
            proxy.register(user);
        } catch (Exception e) {
            if (e instanceof MyServiceException){
                System.out.println(e.getMessage());
            }else {
                e.printStackTrace();
                System.out.println("系统繁忙,请稍后重试");
            }
        }
    }


}
