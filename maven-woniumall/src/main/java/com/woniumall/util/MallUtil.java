package com.woniumall.util;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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

    
    public static String uploadFile(Part partName, String realPath) throws ServletException, IOException {
        //判定路径是否存在,如果不存在,则创建
        File targetDir = new File(realPath);
        if (!targetDir.exists()){
            targetDir.mkdirs();
        }
        //获取上传的文件名
        String fileName = partName.getSubmittedFileName();
        //为了防止文件名重复,用uuid取代原文件名
        //1.获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //2.将uuid与文件后缀拼接
        String randName = UUID.randomUUID().toString().replaceAll("-", "")+suffix;
        //根据文件名创建文件
        File file = new File(targetDir,randName);
        //创建字节输入流管道
        InputStream input = partName.getInputStream();
        //创建字节输出流管道
        FileOutputStream out = new FileOutputStream(file);
        //创建字节数组
        byte[] bty = new byte[8192];

        int len = 0;
        if((len=input.read(bty)) != -1){
            out.write(bty,0,len);
            out.flush();
        }
        out.close();
        input.close();
        return randName;
    }


}
