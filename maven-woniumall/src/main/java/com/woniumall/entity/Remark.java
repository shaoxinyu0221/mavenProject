package com.woniumall.entity;

import java.math.BigDecimal;

public class Remark {

    private Integer id;
    private Goods goods;
    private User user;
    private BigDecimal score;
    private String content;
    private String remarkTime;
    private String status;
    public static final String NORMAL = "1";
    public static final String UNABLE = "0";


}
