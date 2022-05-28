package com.demo.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/scope")
public class ScopeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //请求作用域
        req.setAttribute("req_name", "我是一个请求作用域");
        //会话作用域
        //1.获取当前会话对象
        /**
         * 会话作用域在什么时候开启,什么时候结束
         * servlet被请求时,会话作用域开启,关闭浏览器,会话作用域结束
         * 会话作用域中的数据,在当前会话中可被随意访问
         */
        HttpSession session = req.getSession();
        //2.为会话作用域设置内容
        session.setAttribute("ses_name", "我是一个会话作用域");


        //全局作用域
        /**
         * 全局作用域,服务器关闭时全局作用域结束
         * 在服务器关闭前,全局作用域中的数据可随意访问
         */
        //1.获取全局作用域
        ServletContext context = req.getServletContext();
        //2.给全局作用域设置值
        context.setAttribute("con_name", "这是一个全局作用域");


        //设置访问跳转,用请求转发模式
        req.getRequestDispatcher("/ScopeTest.jsp").forward(req, resp);

    }
}
