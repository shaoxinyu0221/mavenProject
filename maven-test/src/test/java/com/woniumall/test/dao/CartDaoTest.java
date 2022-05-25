package com.woniumall.test.dao;

import com.woniumall.dao.CartDao;
import com.woniumall.dao.UserDao;
import com.woniumall.entity.Cart;
import com.woniumall.entity.User;
import com.woniumall.util.MallUtil;
import com.woniumall.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class CartDaoTest {

    SqlSession sqlSession = null;
    CartDao cartDao = null;

    @Before
    public void getUp(){
        sqlSession = MyBatisUtil.getSqlSession();
        cartDao = sqlSession.getMapper(CartDao.class);
    }

    @After
    public void tearDown(){
        sqlSession.commit();
        MyBatisUtil.close(sqlSession);
    }

    @Test
    public void insert(){
        Cart cart = new Cart();
        cart.setUserId(1);
        cart.setGoodsId(2);
        cart.setNum(1);
        cart.setAddPrice(new BigDecimal("108.99"));
        cart.setAddTime(MallUtil.getNow());
        cart.setIsChecked(User.NORMAL);

        Integer insert = cartDao.insert(cart);
        System.out.println(insert);
    }

    @Test
    public void getByUserId(){
        List<Cart> byUserId = cartDao.getByUserId(1);
        System.out.println(byUserId);
    }


    @Test
    public void getByUserIdToGoods(){
        List<Cart> byUserIdToGoods = cartDao.getByUserIdToGoods(1);
        System.out.println(byUserIdToGoods);
    }

}
