package com.woniumall.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class MallUtil {


    public static String getNow(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        return format;
    }

    /**
     * 获取订单no
     */
    public static String getNo(){
        String no = "BOOK" + (int)(Math.random()*1000);
        return no;
    }

}
