package com.woniumall.test.dao;

import com.woniumall.dao.CategoryDao;
import com.woniumall.entity.Category;
import com.woniumall.util.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class CategoryDaoTest {
    SqlSession sqlSession = null;
    CategoryDao categoryDao = null;

    @Before
    public void getUp(){
        sqlSession = MyBatisUtil.getSqlSession();
        categoryDao = sqlSession.getMapper(CategoryDao.class);
    }

    @After
    public void tearDown(){
        sqlSession.commit();
        MyBatisUtil.close(sqlSession);
    }

    @Test
    public void testInsert(){
        Category category = new Category("少儿读物", Category.NORMAL);
        Integer rows = categoryDao.insert(category);
        if(rows == 1){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
    }


    @Test
    public void delete(){
        Integer rows = categoryDao.delete(5);
        if(rows == 1){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }


    @Test
    public void update(){
        Category category = new Category(1,"奇幻文学", "1");
        Integer rows = categoryDao.update(category);
        if(rows == 1){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
    }


    @Test
    public void queryById(){
        //mybatis默认开启一级缓存,不能关闭,只能清除
//        Category category = categoryDao.queryById(1);
//        System.out.println(category);
//        System.out.println("====================");
//        //清除一级缓存的条件
//        //1.手动清除
//        //sqlSession.clearCache();
//        //2.SQLSession关闭时,自动清除
//        //3.执行update,delete,insert,commit时,自动清除一级缓存
//        sqlSession.close();
////        Category category2 = categoryDao.queryById(1);
////        System.out.println(category2);
        /**
         * 实现二级缓存的步骤
         * 一,在全局的xml文件中,开启二级缓存
         * 二,在dao对应的xml文件中,添加<cache />标签
         * 三,给想要实现二级缓存的类实现Serializable序列化接口
         * 四,可不执行,给select语句添加useCache=true,因为默认是true,所以可以不写
         */
        SqlSessionFactory factory  = null;
        try {
            InputStream input = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            factory = builder.build(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSession sqlSession1 = factory.openSession();
        Category category1 = sqlSession1.getMapper(CategoryDao.class).queryById(1);
        SqlSession sqlSession2 = factory.openSession();
        Category category2 = sqlSession2.getMapper(CategoryDao.class).queryById(1);

        System.out.println(category1);
        System.out.println(category2);
    }


    @Test
    public void queryAll(){
        for (Category category : categoryDao.queryAll()) {
            System.out.println(category);
        }
    }



}
