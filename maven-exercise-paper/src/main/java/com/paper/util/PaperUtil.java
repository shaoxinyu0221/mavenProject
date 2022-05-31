package com.paper.util;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 论文工具类
 * @author Administrator
 */
public class PaperUtil {

    /**
     * 上传文件功能
     */
    public static void uploadFile(Part partName,String realPath) throws ServletException, IOException {
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

    }
}
