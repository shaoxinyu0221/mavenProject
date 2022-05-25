package com.woniumall.test.dao;

import com.woniumall.dao.OrderDao;
import com.woniumall.dao.OrderItemDao;
import com.woniumall.entity.Order;
import com.woniumall.entity.OrderItem;
import com.woniumall.util.MallUtil;
import com.woniumall.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderItemDaoTest {

    SqlSession sqlSession = null;
    OrderItemDao orderItemDao = null;

    @Before
    public void getUp(){
        sqlSession = MyBatisUtil.getSqlSession();
        orderItemDao = sqlSession.getMapper(OrderItemDao.class);
    }

    @After
    public void tearDown(){
        sqlSession.commit();
        MyBatisUtil.close(sqlSession);
    }

    @Test
    public void insert(){
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(1);
        orderItem.setGoodsId(2);
        orderItem.setNum(1);
        orderItem.setPrice(new BigDecimal("18.45"));

        Integer insert = orderItemDao.insert(orderItem);
        System.out.println(insert);
    }

    @Test
    public void queryById(){
        for (OrderItem orderItem : orderItemDao.getByOrderId(1)) {
            System.out.println(orderItem);
        }
    }

}
