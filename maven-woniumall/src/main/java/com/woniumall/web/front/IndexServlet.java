package com.woniumall.web.front;

import com.github.pagehelper.PageInfo;
import com.woniumall.entity.Category;
import com.woniumall.entity.Goods;
import com.woniumall.service.CategoryService;
import com.woniumall.service.GoodsService;
import com.woniumall.service.ServiceProxyFactory;
import com.woniumall.util.MyBatisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private CategoryService categoryService = ServiceProxyFactory.getProxy(new CategoryService());
    private GoodsService goodsService = ServiceProxyFactory.getProxy(new GoodsService());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String opr = request.getParameter("opr");
        if ("gotoIndex".equals(opr)) {
            gotoIndex(request, response);
        }
    }


    protected void gotoIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //查询12条商品作为最新商品展示
        Goods goods = new Goods();
        goods.setIsNew("Y");
        goods.setStatus("1");
        PageInfo<Goods> goodsInfoIsNew = goodsService.getGoodsInfo(goods, 1, 12);
        request.setAttribute("goodsInfoIsNew",goodsInfoIsNew);
        //查询10条商品信息作为热卖商品
        goods = new Goods();
        goods.setIsHot("Y");
        goods.setStatus("1");
        PageInfo<Goods> goodsInfoIsHot = goodsService.getGoodsInfo(goods, 1, 10);
        request.setAttribute("goodsInfoIsHot",goodsInfoIsHot);

        //跳转到首页
        request.getRequestDispatcher("/gotoindex.jsp").forward(request, response);

    }

}

