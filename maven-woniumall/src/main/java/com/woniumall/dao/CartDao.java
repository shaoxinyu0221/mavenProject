package com.woniumall.dao;

import com.woniumall.entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartDao {

    Integer insert(Cart cart);

    List<Cart> getByUserId(@Param("userid") Integer userid);

    List<Cart> getByUserIdToGoods(@Param("userid") Integer userid);

}
