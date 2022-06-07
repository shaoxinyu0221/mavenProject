package com.woniumall.web;

import com.woniumall.dao.AreaCodeDao;
import com.woniumall.entity.AreaCode;
import com.woniumall.util.MyBatisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/code")
public class AreaCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //拿到查询条件
        String superior = request.getParameter("superior");
        //根据superior进行条件查询
        AreaCodeDao codeDao = MyBatisUtil.getDao(AreaCodeDao.class);
        List<AreaCode> codeList = codeDao.queryAreaCodeList(superior);
        //用StringBuilder拼接一个对象
        StringBuilder builder = new StringBuilder("[");
        for (AreaCode areaCode : codeList){
            builder.append("{"+"\"code\":"+"\""+areaCode.getCode()+"\""+","
                            +"\"content\":"+"\""+areaCode.getContent()+"\""+"}"+",");
        }
        //  [{"code":"..."},{"content":"..."},....]
        //如果长度不为0,去掉最后一个逗号
        if(codeList.size()>0){
            builder.deleteCharAt(builder.length()-1);
        }
        //拼接最后
        builder.append("]");
        System.out.println(builder.toString());

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(builder.toString());
        out.flush();
        out.close();
    }
}
