package com.demo.servlet;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet({"/print","/hello"})
public class NewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println("一个POST方式的请求到达NewServlet...");
        //接收客户端消息
        String info = req.getParameter("info");
        String name = req.getParameter("name");
        //当name相同时,从客户端获取信息
        String[] hobbies = req.getParameterValues("hobby");
        System.out.println(name);
        System.out.println(Arrays.toString(hobbies));
        //向浏览器输出消息
        resp.setContentType("text/html;charset=utf-8");
        //获取指向客户端的输出流
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><title>test</title></head>");
        out.println("<body>");
        out.println("你好"+info+"!");
        out.println("</body>");
        out.println("</html>");
        //释放自己资源
        out.flush();
        out.close();

    }
}
