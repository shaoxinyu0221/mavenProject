package com.woniumall.web.front;

import com.woniumall.entity.Cart;
import com.woniumall.entity.ResponseEntity;
import com.woniumall.entity.User;
import com.woniumall.service.CartService;
import com.woniumall.service.ServiceProxyFactory;
import com.woniumall.util.MallUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private CartService cartService = ServiceProxyFactory.getProxy(new CartService());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String opr = request.getParameter("opr");
        if ("changeCartSelected".equals(opr)) {
            changeCartSelected(request, response);
        }else if ("changeCartNotSelected".equals(opr)){
            changeCartNotSelected(request, response);
        }else if("queryGoodsNum".equals(opr)){
            queryGoodsNum(request, response);
        }
    }

    private void queryGoodsNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取当前用户id
        User userCurrentLogin = (User)request.getSession().getAttribute("userCurrentLogin");
        Integer userId = userCurrentLogin.getId();
        //获取商品id
        String goodsId = request.getParameter("goodsId");
        Cart cart = cartService.queryGoodsNum(userId,goodsId);
        Integer goodNum = cart.getNum();
        ResponseEntity<Integer> responseEntity = new ResponseEntity(200,"ok",goodNum);
        MallUtil.responseAjax(response,responseEntity);
    }

    /**修改购物车中商品为被选中*/
    private void changeCartSelected(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String goodsId = request.getParameter("goodsId");
        //获取当前用户id
        User userCurrentLogin = (User)request.getSession().getAttribute("userCurrentLogin");
        Integer userId = userCurrentLogin.getId();
        //根据id修改商品的选中状态为1
        cartService.changeStatusIsSelected(goodsId,userId);
        ResponseEntity<Void> responseEntity = new ResponseEntity(200,"ok");
        MallUtil.responseAjax(response,responseEntity);
    }


    /**修改购物车中商品为被选中*/
    private void changeCartNotSelected(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String goodsId = request.getParameter("goodsId");
        //获取当前用户id
        User userCurrentLogin = (User)request.getSession().getAttribute("userCurrentLogin");
        Integer userId = userCurrentLogin.getId();
        //根据id修改商品的选中状态为1
        cartService.changeStatusNotSelected(goodsId,userId);
        ResponseEntity<Void> responseEntity = new ResponseEntity(200,"ok");
        MallUtil.responseAjax(response,responseEntity);
    }


}
