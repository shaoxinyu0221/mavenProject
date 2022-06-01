package com.exercise.servlet;

import com.exercise.entity.Inform;
import com.exercise.entity.Manager;
import com.exercise.exception.MyException;
import com.exercise.service.InformService;
import com.exercise.service.ManagerService;
import com.exercise.service.ServiceProxyFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import static sun.security.jgss.GSSUtil.login;

@WebServlet("/book")
public class InformServlet extends HttpServlet {

    private InformService proxy = ServiceProxyFactory.getProxy(new InformService());
    private ManagerService managerProxy = ServiceProxyFactory.getProxy(new ManagerService());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String opr = request.getParameter("opr");
        if("check".equals(opr)){
            //调用登记方法
            check(request,response);
        }else if("gotoLogin".equals(opr)){
            gotoLogin(request,response);
        }else if("login".equals(opr)){
            login(request,response);
        }
    }

    /**预约列表*/
    protected void gotoInformList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Inform> informList = proxy.queryInformList();
        request.setAttribute("informList",informList);
        request.getRequestDispatcher("/informList.jsp").forward(request, response);
    }

    /**登录方法*/
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //封装成对象
        Manager manager = new Manager();
        manager.setUsername(username);
        manager.setPassword(password);
        try {
            //如果登录成功,跳转到新的页面
            managerProxy.login(manager);
            gotoInformList(request,response);
        } catch (Exception e) {
            if (e instanceof MyException){
                request.setAttribute("error", e.getMessage());
            }else {
                e.printStackTrace();
                request.setAttribute("error", "服务器繁忙");
            }

        }
    }

    /**跳转到管理员登录页面*/
    protected void gotoLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/managerLogin.jsp").forward(request,response);
    }

    /**预约登记方法*/
    protected void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String area = request.getParameter("area");
        String car = request.getParameter("car");
        String movingdate = request.getParameter("movingdate");
        String contact = request.getParameter("contact");
        String[] parameterValues = request.getParameterValues("telephone");
        String telephone = null;
        if (parameterValues[0] == null){
            telephone = parameterValues[1];
        }else {
            telephone = parameterValues[0]+"-"+parameterValues[1];
        }

        Inform inform = new Inform();
        inform.setArea(area);
        inform.setCar(car);
        inform.setMovingdate(movingdate);
        inform.setContact(contact);
        inform.setTelephone(telephone);
        inform.setStatus("未处理");


        try {
            proxy.checkMoving(inform);
            request.setAttribute("checkSuccess","预约登记成功");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            if (e instanceof MyException){
                request.setAttribute("error", e.getMessage());
            }else {
                e.printStackTrace();
                request.setAttribute("error", "系统繁忙");
            }

        }
    }

}
