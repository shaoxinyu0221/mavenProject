package com.woniumall.web.front;

import com.woniumall.entity.*;
import com.woniumall.exception.MyServiceException;
import com.woniumall.service.*;
import com.woniumall.util.MallUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/shopping")
public class ShoppingServlet extends HttpServlet {

    private UserService userService = ServiceProxyFactory.getProxy(new UserService());
    private GoodsService goodsService = ServiceProxyFactory.getProxy(new GoodsService());
    private CartService cartService = ServiceProxyFactory.getProxy(new CartService());
    private OrderItemService OrderItemService = ServiceProxyFactory.getProxy(new OrderItemService());
    private OrderService orderService = ServiceProxyFactory.getProxy(new OrderService());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opr = request.getParameter("opr");
        if ("gotoOrderAdd".equals(opr)){
            gotoOrderAdd(request, response);
        }else if("joinCart".equals(opr)){
            joinCart(request, response);
        }else if("gotoCart".equals(opr)){
            gotoCart(request, response);
        }else if("deleteCart".equals(opr)){
            deleteCart(request, response);
        }else if("gotoSubmit".equals(opr)){
            gotoSubmit(request, response);
        }else if("reduceGoodsNum".equals(opr)){
            reduceGoodsNum(request, response);
        }else if ("addGoodsNum".equals(opr)){
            addGoodsNum(request, response);
        }else if ("changeNumFromCart".equals(opr)){
            changeNumFromCart(request, response);
        }else if("viewCartCount".equals(opr)){
            viewCartCount(request, response);
        }else if("viewCartList".equals(opr)){
            viewCartList(request, response);
        }
    }

    /**查看当前用户购物车列表*/
    private void viewCartList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        User userCurrentLogin = (User)request.getSession().getAttribute("userCurrentLogin");
        Integer userId = userCurrentLogin.getId();
        List<Cart> cartListByUserId = cartService.getCartListByUserId(userId);
        Integer allNum = 0;
        BigDecimal allPrice = new BigDecimal("0");
        for (Cart cart : cartListByUserId) {
            allNum += cart.getNum();
            allPrice = allPrice.add(cart.getAddPrice().multiply(new BigDecimal(cart.getNum())));
        }
        //发送给前段
        ResponseEntity<String> responseEntity = new ResponseEntity(200,"查询成功",allNum.toString()+","+allPrice.toString());
        MallUtil.responseAjax(response,responseEntity);
    }

    /**查看购物车商品数量*/
    private void viewCartCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        User userCurrentLogin = (User)request.getSession().getAttribute("userCurrentLogin");
        Integer userId = userCurrentLogin.getId();
        //根据用户id查询购物车数量
        Integer count = cartService.getCartCount(userId);
        ResponseEntity<Void> responseEntity = new ResponseEntity(200,count.toString());
        MallUtil.responseAjax(response,responseEntity);
    }

    /**直接修改商品数量*/
    private void changeNumFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer goodNum = Integer.parseInt(request.getParameter("goodNum"));
        ResponseEntity<Void> responseEntity = new ResponseEntity();
        try {
            //获取商品id
            Integer goodsId = Integer.parseInt(request.getParameter("goodsId"));
            //获取当前用户id
            User userCurrentLogin = (User)request.getSession().getAttribute("userCurrentLogin");
            Integer userId = userCurrentLogin.getId();
            //减少商品数量
            cartService.changeNum(goodsId,userId,"2",goodNum);
            responseEntity = new ResponseEntity<>(200, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(500, "服务器繁忙");
        }
        MallUtil.responseAjax(response,responseEntity);
    }

    /**在购物车中直接增加商品数量*/
    private void addGoodsNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ResponseEntity<Void> responseEntity = new ResponseEntity();
        try {
            //获取商品id
            Integer goodsId = Integer.parseInt(request.getParameter("goodsId"));
            //获取当前用户id
            User userCurrentLogin = (User)request.getSession().getAttribute("userCurrentLogin");
            Integer userId = userCurrentLogin.getId();
            //减少商品数量
            cartService.changeNum(goodsId,userId,"1",0);
            responseEntity = new ResponseEntity<>(200, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(500, "服务器繁忙");
        }
        MallUtil.responseAjax(response,responseEntity);
    }

    /**在购物车中直接减少商品数量*/
    private void reduceGoodsNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String goodNum = request.getParameter("goodNum");
        ResponseEntity<Void> responseEntity = new ResponseEntity();
        try {
            //获取商品id
            Integer goodsId = Integer.parseInt(request.getParameter("goodsId"));
            //获取当前用户id
            User userCurrentLogin = (User)request.getSession().getAttribute("userCurrentLogin");
            Integer userId = userCurrentLogin.getId();
            //减少商品数量
            cartService.changeNum(goodsId,userId,"0",0);
            responseEntity = new ResponseEntity<>(200, "减少成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(500, "服务器繁忙");
        }
        MallUtil.responseAjax(response,responseEntity);

    }


    /**下订单方法*/
    private void gotoSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            //获取用户的收货地址
            String orderAddress = request.getParameter("orderAddress");
            String[] split = orderAddress.split("-");
            //获取用户支付方式
            String paytype = request.getParameter("paytype");
            //获取当前登录用户信息
            User userCurrentLogin = (User)request.getSession().getAttribute("userCurrentLogin");
            Integer userId = userCurrentLogin.getId();
            //获取商品id
            String[] orderGoodsIds = request.getParameterValues("OrderGoodsId");
            //获取商品数量
            String[] buyNums = request.getParameterValues("OrderGoodsNum");
            //封装成orderItem集合
            List<OrderItem> orderItemList = OrderItemService.getOrderItemList(orderGoodsIds, buyNums);
            //创建订单对象
            Order order = new Order();
            order.setUserid(userId);
            order.setPayType(paytype);
            order.setAccept(split[0]);
            order.setTelephone(split[1]);
            order.setAddress(split[2]);
            orderService.add(order,orderItemList);
            //如果订单添加成功.设置作用域
            request.setAttribute("order",order);
            //跳转到订单提价页面
            request.getRequestDispatcher("/order_submit.jsp").forward(request, response);
        } catch (Exception e) {
            if (e instanceof MyServiceException){
                request.setAttribute("error",e.getMessage());
            }else {
                e.printStackTrace();
                request.setAttribute("error","服务器繁忙,请稍后重试");
            }
            //跳转到首页
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }


    /**删除购物车方法*/
    private void deleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取商品id
        Integer deleteGoodsId = Integer.parseInt(request.getParameter("deleteGoodsId"));
        //获取当前登录用户的id
        User userCurrentLogin = (User)request.getSession().getAttribute("userCurrentLogin");
        Integer userId = userCurrentLogin.getId();
        //根据用户id和商品id删除商品
        cartService.deleteCartByGoodsIdAndUserId(userId,deleteGoodsId);
        ResponseEntity<Void> responseEntity = new ResponseEntity(200,"删除成功");
        MallUtil.responseAjax(response,responseEntity);
    }


    /**前往购物车页面*/
    private void gotoCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //拿到UserId
        User userCurrentLogin = (User)request.getSession().getAttribute("userCurrentLogin");
        Integer userId = userCurrentLogin.getId();
        //根据userid查询购物车
        List<Cart> cartList = cartService.getCartListByUserId(userId);
        request.setAttribute("cartList",cartList);
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }


    /**添加购物车方法*/
    private void joinCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseEntity<Void> responseEntity = new ResponseEntity<>();
        //购买数量
        Integer buyNums = Integer.parseInt(request.getParameter("buyNums"));
        //获取当前登录的用户的id
        User user = (User)request.getSession().getAttribute("userCurrentLogin");
        Integer userId = user.getId();
        //获取商品id
        Integer goodsId = Integer.parseInt(request.getParameter("goodsId"));
        //向购物车添加信息
        cartService.addCart(userId,goodsId,buyNums);
        responseEntity = new ResponseEntity<>(200,"添加成功");
        MallUtil.responseAjax(response,responseEntity);
    }


    /**前往核对订单信息页面*/
    private void gotoOrderAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //查询当前登录用户的信息
        User userCurrentLogin = (User)request.getSession().getAttribute("userCurrentLogin");
        Integer userCurrentLoginId = userCurrentLogin.getId();
        //通过当前登录的用户id查询用户的默认收获地址
        List<Address> address = userService.getUserAddress(userCurrentLoginId);
        //将地址信息放入作用域
        request.setAttribute("address", address);
        //查询用户购买的商品信息
        String[] goodsIds = request.getParameterValues("goodsId");
        //获取购买数量
        String[] buyNums = request.getParameterValues("buyNums");
        //封装成功一个orderItem数组
        List<OrderItem> orderItemList = OrderItemService.getOrderItemList(goodsIds,buyNums);
        //将集合添加到作用域
        request.setAttribute("orderItemList",orderItemList);

        request.getRequestDispatcher("/order_add.jsp").forward(request, response);
    }
}
