package com.demo.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(value = "/ServletCycle",loadOnStartup = -1)
public class ServletCycle extends HttpServlet {

    public ServletCycle() {
        System.out.println("1.构造器执行了......");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("2.初始化方法执行init....");
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("3.service服务执行了");
    }


    @Override
    public void destroy() {
        System.out.println("4.destory方法执行了");
    }



}
