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

    /**????????????????????????*/
    private void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String email = request.getParameter("email");
        userService.activeForEmail(email);
        //????????????,?????????????????????????????????,??????????????????????????????????????????????????????
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /**????????????????????????*/
    private void checkEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ResponseEntity<Void> responseEntity = new ResponseEntity<>();
        String email = request.getParameter("email");
        User user = userService.checkEmail(email);
        if (user == null){
            responseEntity = new ResponseEntity<>(200, "????????????????????????");
        }else {
            responseEntity = new ResponseEntity<>(400, "????????????????????????");
        }
        MallUtil.responseAjax(response, responseEntity);
    }

    /**???Ajax??????????????????*/
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
            responseEntity = new ResponseEntity<>(200, "????????????");
            //????????????,??????????????????
            EmailUtil.sendMail(email);
        }else{
            responseEntity = new ResponseEntity<>(400, "???????????????");
        }

        MallUtil.responseAjax(response,responseEntity);
    }

    /**??????????????????*/
    private void gotoRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    /**????????????*/
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //???????????????
        request.getSession().invalidate();
        //???????????????
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    /**????????????????????????*/
    private void accountIsExist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ResponseEntity<Void> responseEntity = new ResponseEntity<>();
        //????????????
        String username = request.getParameter("account");
        //????????????????????????
        User user = userService.accountIsExist(username);
        if (user == null && username != "") {
            responseEntity = new ResponseEntity<>(200,"??????????????????");
        }else{
            responseEntity = new ResponseEntity<>(400,"????????????????????????");
        }
        MallUtil.responseAjax(response,responseEntity);
    }



    /**???ajax??????????????????*/
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //??????ajax???????????????
        ResponseEntity<Void> responseEntity = null;
        //????????????
        String username = request.getParameter("account");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");
        if (!captcha.equalsIgnoreCase((String)request.getSession().getAttribute("captcha"))){
            responseEntity = new ResponseEntity<Void>(400, "???????????????");
        }else {
            try {
                User login = userService.login(username, MD5Util.md5(password));
                request.getSession().setAttribute("userCurrentLogin",login);
                //??????????????????,???????????????????????????
                responseEntity = ResponseEntity.SUCCESS;
            } catch (Exception e) {
                if (e instanceof MyServiceException){
                    responseEntity = new ResponseEntity<Void>(400, e.getMessage());
                }else {
                    responseEntity = new ResponseEntity<Void>(500, "??????????????????,???????????????");
                    e.printStackTrace();
                }
            }
        }

        MallUtil.responseAjax(response,responseEntity);

    }


    /**??????????????????*/
    private void gotoLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
