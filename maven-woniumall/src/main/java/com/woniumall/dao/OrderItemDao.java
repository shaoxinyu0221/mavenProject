package com.woniumall.dao;

import com.woniumall.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {

    Integer insert(OrderItem orderItem);

    //已知订单id，查询订单明细
    List<OrderItem> getByOrderId(Integer orderId);

    List<OrderItem> getByIdStepThree(Integer orderId);
}
