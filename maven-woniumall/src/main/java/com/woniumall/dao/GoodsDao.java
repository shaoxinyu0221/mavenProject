package com.woniumall.dao;

import com.woniumall.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsDao {

    Integer add(Goods goods);


    Goods queryById(@Param("id") Integer id);


    List<Goods> queryAll();

    Integer update(Goods goods);


    Integer updateStockByid(@Param("id") Integer id, @Param("stock") Integer stock);


    Goods getByIdStepFour(Integer goodsId);

    List<Goods> queryGoodsByCategoryId(Integer categoryId);

    List<Goods> queryGoodsByCondition(Goods goods);

    void updateStatusById(@Param("status") String status, @Param("goodId") Integer goodId);

    void changeGoodStatusBatch(@Param("array") String[] splitIds, @Param("status") String status);

    List<Goods> queryByIds(Integer[] goodsIds);
}
