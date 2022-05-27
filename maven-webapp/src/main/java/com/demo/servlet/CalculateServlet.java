package com.demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculate")
public class CalculateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        int num1 = Integer.parseInt(req.getParameter("num1"));
//        int num2 = Integer.parseInt(req.getParameter("num2"));
//        System.out.println(num1);
//        System.out.println(num2);
//        int num = num1 * num2;
//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        out.println("<html>");
//        out.println("<head><title>test</title></head>");
//        out.println("<body>");
//        out.println("两数的乘积为:"+num);
//        out.println("</body>");
//        out.println("</html>");
//
//        out.flush();
//        out.close();

        /**
         * 页面跳转
         */
        //1.服务端跳转--请求转发,跳转后地址栏的网址不会改变
        //request.getRequestDispatcher("/show.jsp").forward(request,response);

        //2.客户端跳转--请求重定向,跳转后地址栏网址会发生改变
        response.sendRedirect("show.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
