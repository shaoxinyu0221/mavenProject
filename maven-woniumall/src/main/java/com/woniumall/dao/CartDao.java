package com.woniumall.dao;

import com.woniumall.entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartDao {

    Integer insert(Cart cart);

    List<Cart> getByUserId(@Param("userid") Integer userid);

    List<Cart> getByUserIdToGoods(@Param("userid") Integer userid);

    Integer queryCartByUserIdAndGoodsId(@Param("userId") Integer userId, @Param("goodsId") Integer goodsId);

    void changeGoodsNum(@Param("buyNums") Integer buyNums, @Param("userId") Integer userId, @Param("goodsId") Integer goodsId);

    void deleteCartByGoodsIdAndUserId(@Param("userId") Integer userId, @Param("deleteGoodsId") Integer deleteGoodsId);

    void reduceNum(@Param("goodsId")Integer goodsId, @Param("userId")Integer userId);

    void addNum(@Param("goodsId")Integer goodsId, @Param("userId")Integer userId);

    void changeNum(@Param("buyNums")Integer buyNums, @Param("userId")Integer userId, @Param("goodsId")Integer goodsId);

    Integer getCartCount(Integer userId);

    void changeStatusIsSelected(@Param("goodsId") String goodsId,@Param("userId")Integer userId);

    void changeStatusNotSelected(@Param("goodsId")String goodsId, @Param("userId")Integer userId);

    Cart queryGoodsNum(@Param("userId")Integer userId, @Param("goodsId")String goodsId);
}
