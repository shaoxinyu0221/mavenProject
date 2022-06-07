package com.woniumall.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class OnlineCountListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //获取全局对象
        ServletContext servletContext = se.getSession().getServletContext();
        Integer count = (Integer) servletContext.getAttribute("count");
        if (count == null){
            count = 1;
        }else {
            count++;
        }
        servletContext.setAttribute("count",count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext servletContext = se.getSession().getServletContext();
        Integer count = (Integer) servletContext.getAttribute("count");
        count--;
        if (count<0){
            count=0;
        }
        servletContext.setAttribute("count",count);
    }
}
