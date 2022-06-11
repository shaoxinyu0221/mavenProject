package com.woniumall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniumall.dao.GoodsDao;
import com.woniumall.entity.Goods;
import com.woniumall.util.MallUtil;
import com.woniumall.util.MyBatisUtil;

import java.util.List;

public class GoodsService {

    public PageInfo<Goods> getGoodsInfo(Goods goods, Integer pageNumDefault, Integer pageSizeDefault) {
        //1.开启分页功能
        PageHelper.startPage(pageNumDefault, pageSizeDefault);
        //2.查询
        GoodsDao goodsDao = MyBatisUtil.getDao(GoodsDao.class);
        List<Goods> goodsList = goodsDao.queryGoodsByCondition(goods);
        //3.封装成pageInfo
        PageInfo<Goods> goodsInfo = new PageInfo(goodsList);

        return goodsInfo;
    }

    public void addGoods(Goods goods) {
        GoodsDao goodsDao = MyBatisUtil.getDao(GoodsDao.class);
        goods.setNo(MallUtil.getNo());
        goods.setUpTime(MallUtil.getNow());
        goods.setStatus("1");
        Integer add = goodsDao.add(goods);

    }

    public Goods queryGoodsById(Integer goodId) {
        GoodsDao goodsDao = MyBatisUtil.getDao(GoodsDao.class);
        return goodsDao.queryById(goodId);
    }

    public void updateGoodStatusById(String status, Integer goodId) {
        GoodsDao goodsDao = MyBatisUtil.getDao(GoodsDao.class);
        goodsDao.updateStatusById(status,goodId);
    }

    public void updateGoods(Goods goods) {
        GoodsDao goodsDao = MyBatisUtil.getDao(GoodsDao.class);
        goodsDao.update(goods);
    }

    public void downGoodsBatch(String[] splitIds, String status) {
        GoodsDao goodsDao = MyBatisUtil.getDao(GoodsDao.class);
        goodsDao.changeGoodStatusBatch(splitIds,status);
    }

    public List<Goods> queryGoodsByIds(Integer[] goodsIds) {
        GoodsDao goodsDao = MyBatisUtil.getDao(GoodsDao.class);
        return goodsDao.queryByIds(goodsIds);
    }
}
