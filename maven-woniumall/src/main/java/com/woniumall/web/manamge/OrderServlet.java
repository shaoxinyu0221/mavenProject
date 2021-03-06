package com.woniumall.web.manamge;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.woniumall.dao.OrderItemDao;
import com.woniumall.entity.Order;
import com.woniumall.entity.OrderItem;
import com.woniumall.entity.User;
import com.woniumall.service.OrderService;
import com.woniumall.service.ServiceProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/manage/order")
public class OrderServlet extends HttpServlet {

    private OrderService orderService = ServiceProxyFactory.getProxy(new OrderService());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opr = request.getParameter("opr");
        if ("gotoOrderList".equals(opr)){
            gotoOrderList(request,response);
        }else if ("changeStatus".equals(opr)){
            changeStatus(request,response);
        }else if ("gotoOrderItem".equals(opr)){
            gotoOrderItem(request,response);
        }
    }

    /**查看订单详情页*/
    private void gotoOrderItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        System.out.println("orderId:"+orderId);
        //通过订单id查询order_item表单,四表关联查询
        List<OrderItem> orderItemList = orderService.getOrderItemList(orderId);
        System.out.println("方法调用了么");
        //将拿到的数据,写成JSON格式字符串
        String formatJson = JSON.toJSONStringWithDateFormat(orderItemList, "yyyy-MM-dd HH:mm:ss");
        System.out.println(formatJson);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(formatJson);
        out.flush();
        out.close();

    }

    /**发货*/
    private void changeStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        orderService.updateOrderStatus(orderId,"2");
        request.setAttribute("success","已发货");
        gotoOrderList(request,response);
    }


    /**查询订单列表,并前往页面*/
    private void gotoOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取查询元素
        String userAccount = request.getParameter("userAccount");
        String orderStatus = request.getParameter("orderStatus");
        //封装成对象
        Order order = new Order();
        User user = new User();
        user.setAccount(userAccount);
        order.setUser(user);
        order.setStatus(orderStatus);
        //回显
        request.setAttribute("userAccount",userAccount);
        request.setAttribute("orderStatus",orderStatus);

        //设置分页,默认从第一页开始
        Integer pageNumDefault = 1;
        String pageNum = request.getParameter("pageNum");
        if (pageNum!=null && !"".equals(pageNum)){
            pageNumDefault = Integer.parseInt(pageNum);
        }
        //设置默认显示行,默认显示5行
        Integer pageSizeDefault = 5;
        String pageSize = request.getParameter("pageSize");
        if (pageSize!=null && !"".equals(pageSize)){
            pageSizeDefault = Integer.parseInt(pageSize);
            request.setAttribute("pageSize",pageSize);
        }

        //查询
        PageInfo<Order> orderInfo = orderService.getOrderList(orderStatus,pageNumDefault,pageSizeDefault,userAccount);
        request.setAttribute("orderInfo",orderInfo);
        //页面跳转
        request.getRequestDispatcher("/manage/order_list.jsp").forward(request,response);

    }
}
