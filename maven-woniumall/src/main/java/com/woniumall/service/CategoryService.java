package com.woniumall.service;

import com.woniumall.dao.CategoryDao;
import com.woniumall.dao.GoodsDao;
import com.woniumall.entity.Category;
import com.woniumall.entity.Goods;
import com.woniumall.exception.GoodsInThisCategoryId;
import com.woniumall.exception.MyServiceException;
import com.woniumall.util.MyBatisUtil;

import java.util.List;

public class CategoryService {


    public List<Category> getCategoryList() {
        CategoryDao categoryDao = MyBatisUtil.getDao(CategoryDao.class);
        return categoryDao.queryAll();

    }

    public void deleteCategory(Integer categoryId) {
        CategoryDao categoryDao = MyBatisUtil.getDao(CategoryDao.class);
        //查询有没有该类别的商品
        GoodsDao goodsDao = MyBatisUtil.getDao(GoodsDao.class);
        List<Goods> goodsList = goodsDao.queryGoodsByCategoryId(categoryId);
        if(goodsList.size() != 0){
            throw new GoodsInThisCategoryId("当前类别下存在商品,无法删除");
        }else{
            //如果没有商品,执行删除操作
            categoryDao.delete(categoryId);
        }
    }

    public Category queryCategoryById(Integer id) {
        CategoryDao categoryDao = MyBatisUtil.getDao(CategoryDao.class);
        return categoryDao.queryById(id);
    }

    public void updateCategory(Category category) {
        CategoryDao categoryDao = MyBatisUtil.getDao(CategoryDao.class);
        categoryDao.update(category);
    }

    public void addCategory(Category category) {
        CategoryDao categoryDao = MyBatisUtil.getDao(CategoryDao.class);
        Category category1 = categoryDao.queryByName(category.getName());
        if (category1 != null){
            throw new MyServiceException("名字已经存在");
        }
        categoryDao.insert(category);
    }


    public List<Category> getCategoryListForNav() {
        CategoryDao categoryDao = MyBatisUtil.getDao(CategoryDao.class);
        return categoryDao.queryCategoryListForNav();
    }

    public List<Category> getCategoryListForAll() {
        CategoryDao categoryDao = MyBatisUtil.getDao(CategoryDao.class);
        return categoryDao.queryCategoryListForAll();
    }
}
