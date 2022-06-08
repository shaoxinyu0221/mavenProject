package com.woniumall.web.front;

import com.woniumall.entity.User;
import com.woniumall.service.CategoryService;
import com.woniumall.service.GoodsService;
import com.woniumall.service.ServiceProxyFactory;
import com.woniumall.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/front/user")
public class UserServlet extends HttpServlet{

    private UserService userService = ServiceProxyFactory.getProxy(new UserService());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String opr = request.getParameter("opr");
        if ("gotoLogin".equals(opr)) {
            gotoLogin(request, response);
        }else if ("login".equals(opr)) {
            login(request, response);
        }else if ("accountIsExist".equals(opr)){
            accountIsExist(request, response);
        }else if ("logout".equals(opr)){
            logout(request, response);
        }else if ("gotoRegister".equals(opr)) {
            gotoRegister(request, response);
        }else if ("register".equals(opr)) {
            register(request, response);
        }
    }

    /**用Ajax进行注册操作*/
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }

    /**前往注册页面*/
    private void gotoRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/front/register.jsp").forward(request, response);
    }

    /**注销登录*/
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //清除作用域
        request.getSession().invalidate();
        //跳转到首页
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    /**判断账号是否存在*/
    private void accountIsExist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取元素
        String username = request.getParameter("account");
        //查询账号是否存在
        User user = userService.accountIsExist(username);
        if (user == null && username != "") {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("账号可以使用");
        }else{
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("账号不可用");
        }
    }



    /**用ajax实现登录操作*/
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取元素
        String username = request.getParameter("account");
        String password = request.getParameter("password");
        try {
            User login = userService.login(username, password);
            request.getSession().setAttribute("userCurrentLogin",login);
//          response.setContentType("text/html;charset=utf-8");
//          response.getWriter().println("success");
        } catch (Exception e) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(e.getMessage());
            e.printStackTrace();
        }

    }


    /**前往登录页面*/
    private void gotoLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
