package com.woniumall.entity;

import java.math.BigDecimal;

public class Cart {

    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private User user;
    private Goods goods;
    private Integer num;
    private BigDecimal addPrice;
    private String addTime;
    private String isChecked;
    public static final String IS_CHECKED = "1";
    public static final String NO_CHECKED = "0";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(BigDecimal addPrice) {
        this.addPrice = addPrice;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", user=" + user +
                ", goods=" + goods +
                ", num=" + num +
                ", addPrice=" + addPrice +
                ", addTime='" + addTime + '\'' +
                ", isChecked='" + isChecked + '\'' +
                '}';
    }
}
