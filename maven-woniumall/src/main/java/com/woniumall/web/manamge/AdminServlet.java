package com.woniumall.web.manamge;

import com.woniumall.entity.Admin;
import com.woniumall.entity.User;
import com.woniumall.exception.MyServiceException;
import com.woniumall.service.AdminService;
import com.woniumall.service.ServiceProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manage/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opr = request.getParameter("opr");
        if ("login".equals(opr)){
            login(request, response);
        }else if("logout".equals(opr)){
            logout(request, response);
        }
    }

    /**清空会话*/
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("退出方法执行了");
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/manage/login.jsp");
    }

    /**登录方法*/
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        AdminService adminService = ServiceProxyFactory.getProxy(new AdminService());
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        try {
            Admin login = adminService.login(account, password);

            //如果登录成功,添加cookie
            Cookie cookieAccount = new Cookie("account", account);
            Cookie cookiePassword = new Cookie("password", password);
            //判断用户是否勾选记住我,如果选择记住,则添加cookie
            String rememberMe = request.getParameter("rememberMe");
            if (rememberMe!=null && "on".equals(rememberMe)){
                //设置cookie时效
                cookieAccount.setMaxAge(500);
                cookiePassword.setMaxAge(500);
            }else {
                cookieAccount.setMaxAge(0);
                cookiePassword.setMaxAge(0);
            }
            //向浏览器发送cookie
            response.addCookie(cookieAccount);
            response.addCookie(cookiePassword);

            //将当前登录用户存入会话作用域
            request.getSession().setAttribute("adminCurrentLogin",login);
            //登录成功后调方法跳转
            request.getRequestDispatcher("/manage/home.jsp").forward(request,response);
        } catch (Exception e) {
            if (e instanceof MyServiceException){
                request.setAttribute("error",e.getMessage());
            }else {
                request.setAttribute("error","服务器繁忙,请稍后重试");
                e.printStackTrace();
            }
            request.getRequestDispatcher("/manage/home.jsp").forward(request,response);
        }

    }

}
