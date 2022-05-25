package com.demo.test.service;

import com.demo.entity.User;
import com.demo.service.ServiceProxyFactory;
import com.demo.service.UserService;
import org.junit.Test;

public class UserServceTest {

    private static UserService porxy = ServiceProxyFactory.getPorxy(new UserService());

    @Test
    public void testInsert(){
        try {
            User user = new User();
            user.setUsername("海绵宝宝");
            user.setPassword("123");
            porxy.addUser(user);
            System.out.println("插入成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testQuery(){
        try {
            User user = porxy.queryUser(4);
            System.out.println(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate(){
        try {
            User user = new User();
            user.setId(4);
            user.setUsername("章鱼哥");
            user.setPassword("123");
            porxy.updateUser(user);
            System.out.println("更新成功");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete(){
        try {
            porxy.deleteUser(4);
            System.out.println("删除成功");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
