package com.woniumall.dao;

import com.woniumall.entity.Order;
import com.woniumall.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {

    Integer insert(Order order);

    Order getById(Integer id);

    List<Order> getByOrderIdToUserStepOne(Integer id);

    Order getByIdStepOne(Integer id);

    List<Order> selectOrderByCondition(@Param("order") Order order);
}
