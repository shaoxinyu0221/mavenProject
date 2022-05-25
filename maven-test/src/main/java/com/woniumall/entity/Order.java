package com.woniumall.entity;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private Integer id;
    private String no;
    private Integer userid;
    private User user;
    private String orderTime;
    private String payType;
    public static final String ZFB="0";
    public static final String WX="1";
    public static final String CARD="2";
    private String payTime;
    private String receiveTime;
    private String accept;
    private String telephone;
    private String address;
    private BigDecimal totalMoney;
    private String status;
    public static final String NOPAY="0";
    public static final String UNDELIVERY="1";
    public static final String DELIVERING="2";
    public static final String DELIVERED="3";
    private List<OrderItem> orderItemList;

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", userid=" + userid +
                ", user=" + user +
                ", orderTime='" + orderTime + '\'' +
                ", payType='" + payType + '\'' +
                ", payTime='" + payTime + '\'' +
                ", receiveTime='" + receiveTime + '\'' +
                ", accept='" + accept + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", totalMoney=" + totalMoney +
                ", status='" + status + '\'' +
                ", orderItemList=" + orderItemList +
                '}';
    }
}
