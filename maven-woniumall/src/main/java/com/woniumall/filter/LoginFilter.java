package com.woniumall.filter;

import com.woniumall.entity.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/manage/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        //1.类型转换,将req装换为HttpServletRequest类型
        HttpServletRequest request = (HttpServletRequest) req;
        //2.获取URI,查看当前地址
        String uri = request.getRequestURI();
        //获取opr操作
        String opr = request.getParameter("opr");
        //3.判断,如果是直接请求登录页面,直接放行
        if (uri.endsWith("login.jsp") || ("login".equals(opr) && uri.endsWith("admin"))){
            chain.doFilter(req,resp);
            return;
        }
        //如果直接是admin或者manage,直接跳转到登录页
        if(uri.endsWith("manage")){
            request.getRequestDispatcher("/manage/login.jsp").forward(req,resp);
            return;
        }


        //4.如果不是,则判断是否已经登录过,如果已经登录过,放行,否则提示并跳转到登录页
        //判断会话作用域中是否有当前登录的用户
        Admin adminCurrentLogin = (Admin) request.getSession().getAttribute("adminCurrentLogin");
        //如果为空,说明为登录,跳转到登录页;不为空,说明当前已登录,放行
        if (adminCurrentLogin == null){
            request.setAttribute("error", "请先登录");
            request.getRequestDispatcher("/manage/login.jsp").forward(req,resp);
        }else {
            chain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
