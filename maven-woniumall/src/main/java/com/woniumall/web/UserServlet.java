package com.woniumall.web;

import com.github.pagehelper.PageInfo;
import com.woniumall.entity.User;
import com.woniumall.service.ServiceProxyFactory;
import com.woniumall.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manage/user")
public class UserServlet extends HttpServlet {

    private UserService userService = ServiceProxyFactory.getProxy(new UserService());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opr = request.getParameter("opr");
        if ("gotoUserList".equals(opr)){
            gotoUserList(request, response);
        }else if ("userBan".equals(opr)){
            userBan(request, response);
        }
    }


    /**修改用户状态*/
    private void userBan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        String userStatus = request.getParameter("updateStatus");
        if (userStatus.equals("1")){
            //根据id修改用户状态
            userService.changeUserStatus("0",userId);
        }else {
            userService.changeUserStatus("1",userId);
        }
        //跳转页面
        gotoUserList(request, response);
    }


    /**查询用户列表*/
    private void gotoUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //拿到页码和每页的行数
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        //拿到查询条件
        String userAccount = request.getParameter("userAccount");
        String userStatus = request.getParameter("userStatus");
        //回显
        request.setAttribute("userAccount",userAccount);
        request.setAttribute("userStatus",userStatus);
        //封装成对象
        User user = new User();
        user.setAccount(userAccount);
        user.setStatus(userStatus);
        //设置默认起始页和默认行数
        Integer pageNumDefault = 1;
        if (pageNum!=null && !"".equals(pageNum)){
            pageNumDefault = Integer.parseInt(pageNum);
        }

        Integer pageSizeDefault = 5;
        if (pageSize!=null && !"".equals(pageSize)){
            pageSizeDefault = Integer.parseInt(pageSize);
        }

        PageInfo<User> userInfo = userService.getUserList(user,pageNumDefault,pageSizeDefault);

        request.setAttribute("userInfo",userInfo);
        request.setAttribute("pageSize",pageSizeDefault);
        request.getRequestDispatcher("/manage/user_list.jsp").forward(request,response);
    }

}
