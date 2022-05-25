package com.woniumall.entity;

import java.math.BigDecimal;
import java.util.List;

public class User {

    private Integer id;
    private String account;
    private String password;
    private String regTime;
    private Integer score;
    private String avatar;
    private String email;
    private BigDecimal money;
    private String status;

    private List<Cart> cartList;

    public static final String NORMAL = "0";
    public static final String UNABLE = "1";
    public static final String UNACTIVE = "2";

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User() {
    }

    public User(Integer id, String account, String password, String regTime, Integer score, String avatar, String email, BigDecimal money, String status) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.regTime = regTime;
        this.score = score;
        this.avatar = avatar;
        this.email = email;
        this.money = money;
        this.status = status;
    }

    public User(String account, String password, String regTime, Integer score, String avatar, String email, BigDecimal money, String status) {
        this.account = account;
        this.password = password;
        this.regTime = regTime;
        this.score = score;
        this.avatar = avatar;
        this.email = email;
        this.money = money;
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", regTime='" + regTime + '\'' +
                ", score=" + score +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", money=" + money +
                ", status='" + status + '\'' +
                '}';
    }
}
