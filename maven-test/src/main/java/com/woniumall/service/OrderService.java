package com.woniumall.service;

import com.woniumall.dao.GoodsDao;
import com.woniumall.dao.OrderDao;
import com.woniumall.dao.OrderItemDao;
import com.woniumall.dao.UserDao;
import com.woniumall.entity.Goods;
import com.woniumall.entity.Order;
import com.woniumall.entity.OrderItem;
import com.woniumall.util.MallUtil;
import com.woniumall.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public void add(Order order, List<OrderItem> orderItems){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        GoodsDao goodsDao = sqlSession.getMapper(GoodsDao.class);
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        OrderItemDao orderItemDao = sqlSession.getMapper(OrderItemDao.class);

        //补全订单信息
        order.setNo(MallUtil.getNo());
        order.setOrderTime(MallUtil.getNow());
        //定义订单中的价格,初始值为0
        BigDecimal totalMoney = new BigDecimal("0");
        //循环添加订单项中的价格,并且补全订单项信息
        for (OrderItem orderItem : orderItems) {
            //根据商品id查询商品单价
            //设置商品价格
            orderItem.setPrice(goodsDao.queryById(orderItem.getGoodsId()).getSalePrice());
            BigDecimal salePrice = orderItem.getPrice();
            //获取商品数量
            BigDecimal num = new BigDecimal(String.valueOf(orderItem.getNum()));
            //计算当前商品总价
            BigDecimal multiply = salePrice.multiply(num);
            //计算总价
            totalMoney = totalMoney.add(multiply);
        }
        //设置总价
        order.setTotalMoney(totalMoney);
        //设置订单状态
        order.setStatus("0");
        //添加订单信息到表中,先不提交
        Integer row1 = orderDao.insert(order);
        //设置订单项的id
        for (OrderItem orderItem : orderItems){
            orderItem.setOrderId(order.getId());
            //添加订单项到表中
            orderItemDao.insert(orderItem);
            //更新库存
            Goods goods = goodsDao.queryById(orderItem.getGoodsId());
            Integer stock = goods.getStock() - orderItem.getNum();
            goodsDao.updateStockByid(goods.getId(),stock);
        }
        //增加用户积分,10块钱1积分
        Integer score = (totalMoney.intValue()) / 10;
        //设置用户积分
        userDao.updateScoreById(order.getUserid(),score);

    }

}
