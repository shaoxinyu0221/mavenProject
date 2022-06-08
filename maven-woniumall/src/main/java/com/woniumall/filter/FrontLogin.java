package com.woniumall.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebFilter("/shopping/*")
public class FrontLogin implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServlet req = (HttpServlet) request;

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
