package com.woniumall.test.dao;

import com.woniumall.dao.OrderDao;
import com.woniumall.entity.Goods;
import com.woniumall.entity.Order;
import com.woniumall.entity.OrderItem;
import com.woniumall.entity.User;
import com.woniumall.util.MallUtil;
import com.woniumall.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class OrderDaoTest {

    SqlSession sqlSession = null;
    OrderDao orderDao = null;

    @Before
    public void getUp(){
        sqlSession = MyBatisUtil.getSqlSession();
        orderDao = sqlSession.getMapper(OrderDao.class);
    }

    @After
    public void tearDown(){
        sqlSession.commit();
        MyBatisUtil.close(sqlSession);
    }

    @Test
    public void insert(){
        Order order = new Order();
        order.setNo("order0001");
        order.setUserid(1);
        order.setOrderTime(MallUtil.getNow());
        order.setPayType("0");
        order.setAccept("海绵宝宝");
        order.setTelephone("111222333444");
        order.setAddress("比奇堡");
        order.setTotalMoney(new BigDecimal("123.4"));
        order.setStatus("1");

        Integer insert = orderDao.insert(order);
        System.out.println(insert);
    }


    @Test
    public void queryListById(){
        Order order = orderDao.getById(2);
        for (OrderItem orderItem : order.getOrderItemList()) {
            User user = order.getUser();
            Goods goods = orderItem.getGoods();
            System.out.println(order.getId()+"\t"+order.getOrderTime()+"\t"+
                    order.getAccept()+"\t"+order.getAddress()+"\t"+orderItem.getNum()+"\t"+
                    orderItem.getPrice()+"\t"+goods.getName()+"\t"+goods.getImg()+"\t"+user.getAccount());
        }

    }


    @Test
    public void queryByStep(){
        for (Order order : orderDao.getByOrderIdToUserStepOne(1)) {
            System.out.println(order);
        }

    }


    @Test
    public void getByIdStep(){
        Order order = orderDao.getByIdStepOne(2);
        System.out.println(order.getOrderTime());
    }
}
