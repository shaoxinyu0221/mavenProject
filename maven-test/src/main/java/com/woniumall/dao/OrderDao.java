package com.woniumall.dao;

import com.woniumall.entity.Order;

import java.util.List;

public interface OrderDao {

    Integer insert(Order order);

    Order getById(Integer id);

    List<Order> getByOrderIdToUserStepOne(Integer id);


    Order getByIdStepOne(Integer id);

}
