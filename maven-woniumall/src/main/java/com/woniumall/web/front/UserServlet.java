package com.woniumall.web.front;

import com.alibaba.fastjson.JSON;
import com.woniumall.entity.ResponseEntity;
import com.woniumall.entity.User;
import com.woniumall.exception.MyServiceException;
import com.woniumall.service.CategoryService;
import com.woniumall.service.GoodsService;
import com.woniumall.service.ServiceProxyFactory;
import com.woniumall.service.UserService;
import com.woniumall.util.EmailUtil;
import com.woniumall.util.MD5Util;
import com.woniumall.util.MallUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Map;

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
        }else if ("checkEmail".equals(opr)){
            checkEmail(request, response);
        }else if("active".equals(opr)){
            active(request, response);
        }
    }

    /**用户点击邮件激活*/
    private void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String email = request.getParameter("email");
        userService.activeForEmail(email);
        //激活成功,激活后跳转到激活成功页,激活成功后点击连接跳转到首页或登录页
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /**验证邮箱是否存在*/
    private void checkEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ResponseEntity<Void> responseEntity = new ResponseEntity<>();
        String email = request.getParameter("email");
        User user = userService.checkEmail(email);
        if (user == null){
            responseEntity = new ResponseEntity<>(200, "当前邮箱未被使用");
        }else {
            responseEntity = new ResponseEntity<>(400, "当前邮箱已被使用");
        }
        MallUtil.responseAjax(response, responseEntity);
    }

    /**用Ajax进行注册操作*/
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ResponseEntity<Void> responseEntity = new ResponseEntity<>();

        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String phoneNum = request.getParameter("phoneNum");
        String email = request.getParameter("email");
        String captcha = request.getParameter("captcha");
        if (captcha.equalsIgnoreCase((String)request.getSession().getAttribute("captcha"))){
            User user = new User();
            user.setAccount(account);
            user.setPassword(MD5Util.md5(password));
            user.setEmail(email);
            userService.register(user);
            responseEntity = new ResponseEntity<>(200, "注册成功");
            //注册成功,发送邮件激活
            EmailUtil.sendMail(email);
        }else{
            responseEntity = new ResponseEntity<>(400, "验证码错误");
        }

        MallUtil.responseAjax(response,responseEntity);
    }

    /**前往注册页面*/
    private void gotoRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/register.jsp").forward(request, response);
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
        ResponseEntity<Void> responseEntity = new ResponseEntity<>();
        //获取元素
        String username = request.getParameter("account");
        //查询账号是否存在
        User user = userService.accountIsExist(username);
        if (user == null && username != "") {
            responseEntity = new ResponseEntity<>(200,"当前账号可用");
        }else{
            responseEntity = new ResponseEntity<>(400,"当前账号已被使用");
        }
        MallUtil.responseAjax(response,responseEntity);
    }



    /**用ajax实现登录操作*/
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //创建ajax响应实体类
        ResponseEntity<Void> responseEntity = null;
        //获取元素
        String username = request.getParameter("account");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");
        if (!captcha.equalsIgnoreCase((String)request.getSession().getAttribute("captcha"))){
            responseEntity = new ResponseEntity<Void>(400, "验证码错误");
        }else {
            try {
                User login = userService.login(username, MD5Util.md5(password));
                request.getSession().setAttribute("userCurrentLogin",login);
                //如果登录成功,设置响应实体类的值
                responseEntity = ResponseEntity.SUCCESS;
            } catch (Exception e) {
                if (e instanceof MyServiceException){
                    responseEntity = new ResponseEntity<Void>(400, e.getMessage());
                }else {
                    responseEntity = new ResponseEntity<Void>(500, "服务器维护中,请稍后重试");
                    e.printStackTrace();
                }
            }
        }

        MallUtil.responseAjax(response,responseEntity);

    }


    /**前往登录页面*/
    private void gotoLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
