package com.woniumall.web.front;

import com.github.pagehelper.PageInfo;
import com.woniumall.entity.Goods;
import com.woniumall.service.GoodsService;
import com.woniumall.service.ServiceProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goods/goodsView")
public class GoodsServlet extends HttpServlet {

    private GoodsService goodsService = ServiceProxyFactory.getProxy(new GoodsService());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String opr = request.getParameter("opr");
        if ("gotoGoodsView".equals(opr)) {
            gotoGoodsView(request, response);
        }
    }

    private void gotoGoodsView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取商品id
        Integer goodsId = Integer.parseInt(request.getParameter("goodsId"));
        Goods goods = goodsService.queryGoodsById(goodsId);
        request.setAttribute("goods", goods);
        //查询热卖商品作为推荐商品
        Goods good = new Goods();
        goods.setIsHot("Y");
        goods.setStatus("1");
        PageInfo<Goods> goodsInfoIsHot = goodsService.getGoodsInfo(good, 1, 5);
        request.setAttribute("goodsInfoIsHot",goodsInfoIsHot);

        //跳转到商品详情页
        request.getRequestDispatcher("/goods_view.jsp").forward(request, response);
    }
}
