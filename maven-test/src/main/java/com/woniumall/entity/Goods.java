package com.woniumall.entity;

import java.math.BigDecimal;

public class Goods {

    private Integer id;
    private String name;
    private String no;
    private Integer categoryId;
    private String categoryName;
    private Category category;
    private Integer stock;
    private String author;
    private String publisher;
    private String publishTime;
    private BigDecimal marketPrice;
    private BigDecimal salePrice;
    private String isNew;
    public static final String IS_NEW = "1";
    public static final String  NO_NEW= "0";
    private String isHot;
    public static final String IS_HOT = "1";
    public static final String  NO_HOT= "0";
    private String img;
    private String description;
    private String upTime;
    private String downTime;
    private Integer saleNum;
    private Integer remarkNum;
    private BigDecimal remarkScore;
    private String status;
    public static final String NORMAL = "1";
    public static final String UNABLE = "0";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getDownTime() {
        return downTime;
    }

    public void setDownTime(String downTime) {
        this.downTime = downTime;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Integer getRemarkNum() {
        return remarkNum;
    }

    public void setRemarkNum(Integer remarkNum) {
        this.remarkNum = remarkNum;
    }

    public BigDecimal getRemarkScore() {
        return remarkScore;
    }

    public void setRemarkScore(BigDecimal remarkScore) {
        this.remarkScore = remarkScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", no='" + no + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", category=" + category +
                ", stock=" + stock +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", marketPrice=" + marketPrice +
                ", salePrice=" + salePrice +
                ", isNew='" + isNew + '\'' +
                ", isHot='" + isHot + '\'' +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                ", upTime='" + upTime + '\'' +
                ", downTime='" + downTime + '\'' +
                ", saleNum=" + saleNum +
                ", remarkNum=" + remarkNum +
                ", remarkScore=" + remarkScore +
                ", status='" + status + '\'' +
                '}';
    }
}
