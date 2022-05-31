package com.paper.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.UUID;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("accunt");
        System.out.println(account);
        Part pic = request.getPart("pic");
        //获取存储路径
        String realPath = this.getServletContext().getRealPath("images");
        //判定路径是否存在,如果不存在,则创建
        File targetDir = new File(realPath);
        if (!targetDir.exists()){
            targetDir.mkdirs();
        }
        //获取上传的文件名
        String fileName = pic.getSubmittedFileName();
        //为了防止文件名重复,用uuid取代原文件名
        //1.获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //2.将uuid与文件后缀拼接
        String randName = UUID.randomUUID().toString().replaceAll("-", "")+suffix;
        //根据文件名创建文件
        File file = new File(targetDir,randName);
        //创建字节输入流管道
        InputStream input = pic.getInputStream();
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
