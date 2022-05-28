package com.demo.servlet;

import com.demo.dao.UserDao;
import com.demo.entity.User;
import com.demo.service.UserService;
import com.demo.service.UserServiceProxyFactory;
import com.demo.util.MyBatisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserService proxy = UserServiceProxyFactory.getProxy(new UserService());
        UserDao userDao = null;
        try {
            User loginUser = proxy.login(username, password);
            userDao = MyBatisUtil.getDao(UserDao.class);
            List<User> userList = userDao.queryUserList();
            req.setAttribute("userlist", userList);
            req.getRequestDispatcher("/LoginSuccess.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error",e.getMessage());
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            e.printStackTrace();
        }finally{
            MyBatisUtil.getSqlSession().close();
        }

    }
}
