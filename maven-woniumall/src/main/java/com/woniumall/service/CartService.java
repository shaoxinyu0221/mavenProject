package com.woniumall.service;

import com.woniumall.dao.CartDao;
import com.woniumall.dao.GoodsDao;
import com.woniumall.entity.Cart;
import com.woniumall.entity.Goods;
import com.woniumall.util.MallUtil;
import com.woniumall.util.MyBatisUtil;

import java.util.List;

public class CartService {

    public void addCart(Integer userId, Integer goodsId, Integer buyNums) {
        CartDao cartDao = MyBatisUtil.getDao(CartDao.class);
        //根据商品id和用户id查询购物车中是否有重复数据
        Integer count = cartDao.queryCartByUserIdAndGoodsId(userId,goodsId);
        if (count != 0){
            //如果行数等于1,说明购物车中有重复数据,修改购买数量
            cartDao.changeGoodsNum(buyNums,userId,goodsId);
            return;
        }
        //根据id查询当前商品价格
        GoodsDao goodsDao = MyBatisUtil.getDao(GoodsDao.class);
        Goods goods = goodsDao.queryById(goodsId);
        //补全购物车信息
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setGoodsId(goodsId);
        cart.setNum(buyNums);
        cart.setAddPrice(goods.getSalePrice());
        cart.setAddTime(MallUtil.getNow());
        cart.setIsChecked("1");
        cartDao.insert(cart);
    }


    public List<Cart> getCartListByUserId(Integer userId) {
        CartDao cartDao = MyBatisUtil.getDao(CartDao.class);
        return cartDao.getByUserIdToGoods(userId);
    }


    public void deleteCartByGoodsIdAndUserId(Integer userId, Integer deleteGoodsId) {
        CartDao cartDao = MyBatisUtil.getDao(CartDao.class);
        cartDao.deleteCartByGoodsIdAndUserId(userId,deleteGoodsId);
    }


    public void changeNum(Integer goodsId, Integer userId, String i,Integer buyNums) {
        CartDao cartDao = MyBatisUtil.getDao(CartDao.class);
        if (i.equals("0")){
            //如果0,说明应该减少数量
            cartDao.reduceNum(goodsId,userId);
        }else if (i.equals("1")){
            //如果i是0,说明说明应该增加
            cartDao.addNum(goodsId,userId);
        }else{
            //如果是其他,就是直接改变数量
            cartDao.changeNum(buyNums,userId,goodsId);
        }

    }

    public Integer getCartCount(Integer userId) {
        CartDao cartDao = MyBatisUtil.getDao(CartDao.class);
        return cartDao.getCartCount(userId);
    }

    public void changeStatusIsSelected(String goodsId, Integer userId) {
        CartDao cartDao = MyBatisUtil.getDao(CartDao.class);
        cartDao.changeStatusIsSelected(goodsId,userId);
    }

    public void changeStatusNotSelected(String goodsId, Integer userId) {
        CartDao cartDao = MyBatisUtil.getDao(CartDao.class);
        cartDao.changeStatusNotSelected(goodsId,userId);
    }

    public Cart queryGoodsNum(Integer userId, String goodsId) {
        CartDao cartDao = MyBatisUtil.getDao(CartDao.class);
        return cartDao.queryGoodsNum(userId,goodsId);
    }
}
