package com.woniumall.test.dao;

import com.woniumall.dao.CategoryDao;
import com.woniumall.dao.GoodsDao;
import com.woniumall.entity.Category;
import com.woniumall.entity.Goods;
import com.woniumall.util.MallUtil;
import com.woniumall.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class GoodsDaoTest {
    SqlSession sqlSession = null;
    GoodsDao goodsDao = null;

    @Before
    public void getUp(){
        sqlSession = MyBatisUtil.getSqlSession();
        goodsDao = sqlSession.getMapper(GoodsDao.class);
    }

    @After
    public void tearDown(){
        sqlSession.commit();
        MyBatisUtil.close(sqlSession);
    }

    @Test
    public void testInsert(){
        Goods goods = new Goods();
        goods.setName("沙丘");
        goods.setNo("BOOK001");
        goods.setCategoryId(1);
        goods.setStock(100);
        goods.setAuthor("ss");
        goods.setPublisher("星空出版社");
        goods.setPublishTime("2008-06-24");
        goods.setMarketPrice(new BigDecimal("58.99"));
        goods.setSalePrice(new BigDecimal("38.99"));
        goods.setIsNew("Y");
        goods.setIsHot("Y");
        goods.setImg("img/book_01");
        goods.setDescription("一本好书");
        goods.setUpTime(MallUtil.getNow());
        goods.setStatus(Goods.NORMAL);
        Integer rows = goodsDao.add(goods);
        if(rows == 1){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
    }



    @Test
    public void queryById(){
        Goods goods = goodsDao.queryById(1);
        System.out.println(goods);
    }


    @Test
    public void query(){
        List<Goods> goods = goodsDao.queryAll();
        System.out.println(goods);
    }

    @Test
    public void update(){
        Goods goods = new Goods();
        goods.setId(2);
        goods.setName("话说北京");
        goods.setNo("BOOK0012");
        goods.setCategoryId(6);
        goods.setStock(100);
        goods.setAuthor("gg");
        goods.setPublisher("人民出版社");
        goods.setPublishTime("2008-06-24");
        goods.setMarketPrice(new BigDecimal("108.99"));
        goods.setSalePrice(new BigDecimal("18.99"));
        goods.setIsNew("Y");
        goods.setIsHot("Y");
        goods.setImg("img/book_02");
        goods.setDescription("一本好书");
        goods.setUpTime(MallUtil.getNow());
        goods.setStatus(Goods.NORMAL);

        Integer update = goodsDao.update(goods);
        System.out.println(update);
    }

}
