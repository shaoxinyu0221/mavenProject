package com.woniumall.filter;

import com.woniumall.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/shopping")
public class FrontLogin implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        User userCurrentLogin = (User)req.getSession().getAttribute("userCurrentLogin");
        //如果作用域为空,说明没有登录,跳转到登录页面
        if (userCurrentLogin == null){
            request.setAttribute("error","还未登录,请先登陆");
            request.getRequestDispatcher("").forward(request, response);
            return;
        }
        chain.doFilter(req,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
