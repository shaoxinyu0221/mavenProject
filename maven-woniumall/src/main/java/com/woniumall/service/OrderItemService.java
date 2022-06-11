package com.woniumall.service;

import com.woniumall.dao.GoodsDao;
import com.woniumall.entity.Goods;
import com.woniumall.entity.OrderItem;
import com.woniumall.util.MyBatisUtil;

import java.util.ArrayList;
import java.util.List;

public class OrderItemService {
    public List<OrderItem> getOrderItemList(String[] goodsIds, String[] buyNums) {
        GoodsDao goodsDao = MyBatisUtil.getDao(GoodsDao.class);
        //封装成order_item集合
        List<OrderItem> orderItemList = new ArrayList<>();
        for (int i=0;i<goodsIds.length;i++){
            OrderItem orderItem = new OrderItem();
            //利用商品id查询商品信息
            Goods goods = goodsDao.queryById(Integer.parseInt(goodsIds[i]));
            orderItem.setGoods(goods);
            orderItem.setGoodsId(goods.getId());
            orderItem.setNum(Integer.parseInt(buyNums[i]));
            orderItem.setPrice(goods.getSalePrice());
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }
}
