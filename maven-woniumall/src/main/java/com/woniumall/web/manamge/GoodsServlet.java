package com.woniumall.web.manamge;

import com.github.pagehelper.PageInfo;
import com.woniumall.entity.Category;
import com.woniumall.entity.Goods;
import com.woniumall.exception.MyServiceException;
import com.woniumall.service.CategoryService;
import com.woniumall.service.GoodsService;
import com.woniumall.service.ServiceProxyFactory;
import com.woniumall.util.MallUtil;
import com.woniumall.util.MyBatisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/manage/goods")
@MultipartConfig
public class GoodsServlet extends HttpServlet {

    private GoodsService goodsService = ServiceProxyFactory.getProxy(new GoodsService());
    private CategoryService categoryService = ServiceProxyFactory.getProxy(new CategoryService());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opr = request.getParameter("opr");
        if ("queryGoodsList".equals(opr)){
            queryGoodsList(request, response);
        }else if ("gotoAddGoods".equals(opr)){
            gotoAddGoods(request, response);
        }else if ("addGoods".equals(opr)){
            addGoods(request, response);
        }else if ("changeGoodStatus".equals(opr)){
            changeGoodStatus(request, response);
        }else if ("gotoGoodsUpdate".equals(opr)){
            gotoGoodsUpdate(request, response);
        }else if ("updateGoods".equals(opr)){
            updateGoods(request, response);
        }else if("deleteBatch".equals(opr)){
            deleteBatch(request, response);
        }
    }

    private void deleteBatch(HttpServletRequest request, HttpServletResponse response) {
    }

    /**修改商品信息*/
    private void updateGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            //查询当前商品信息
            String id = request.getParameter("goodId");
            Goods oldGoods = goodsService.queryGoodsById(Integer.parseInt(id));
            //查看当前商品的文件路径
            String img = oldGoods.getImg();
            //查看文件路径是否修改
            Part goodPicture = request.getPart("goodPicture");
            if (goodPicture.getSize()!=0){
                String realPath = this.getServletContext().getRealPath("goodsImages");
                String fileName = MallUtil.uploadFile(goodPicture, realPath);
                img = "goodsImages"+fileName;
            }
            //获取表单信息
            String goodsName = request.getParameter("goodsName");
            Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
            Integer goodStock = Integer.parseInt(request.getParameter("goodStock"));
            BigDecimal marketPrice = new BigDecimal(request.getParameter("marketPrice"));
            BigDecimal salePrice = new BigDecimal(request.getParameter("salePrice"));
            String isNew = request.getParameter("isNew");
            String isHot = request.getParameter("isHot");
            String author = request.getParameter("author");
            String publisher = request.getParameter("publisher");
            String publishTime = request.getParameter("publishTime");
            String description = request.getParameter("description");
            //封装成对象
            Goods goods = new Goods();
            goods.setId(Integer.parseInt(id));
            goods.setName(goodsName);
            goods.setCategoryId(categoryId);
            goods.setImg(img);
            goods.setStock(goodStock);
            goods.setAuthor(author);
            goods.setDescription(description);
            goods.setPublisher(publisher);
            goods.setPublishTime(publishTime);
            goods.setMarketPrice(marketPrice);
            goods.setSalePrice(salePrice);
            goods.setIsNew(isNew);
            goods.setIsHot(isHot);
            //补全信息
            goods.setNo(oldGoods.getNo());
            goods.setStatus(oldGoods.getStatus());
            goods.setUpTime(oldGoods.getUpTime());
            goods.setDownTime(oldGoods.getDownTime());
            goods.setSaleNum(oldGoods.getSaleNum());
            goods.setRemarkNum(oldGoods.getRemarkNum());
            goods.setRemarkScore(oldGoods.getRemarkScore());
            //调用更新方法
            goodsService.updateGoods(goods);
            request.setAttribute("success","更新成功");
            queryGoodsList(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error","服务器维护中,更新失败");
            gotoGoodsUpdate(request, response);
        }
    }

    /**进入修改商品信息表*/
    private void gotoGoodsUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer goodId = Integer.parseInt(request.getParameter("goodId"));
        Goods good = goodsService.queryGoodsById(goodId);
        request.setAttribute("good",good);
        List<Category> categoryList = categoryService.getCategoryList();
        request.setAttribute("categoryList",categoryList);
        request.getRequestDispatcher("/manage/goods_update.jsp").forward(request, response);
    }

    /**改变商品状态,如果是下架改为上架,如果是上架改为下架状态*/
    private void changeGoodStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer goodId = Integer.parseInt(request.getParameter("goodId"));
        Goods good = goodsService.queryGoodsById(goodId);
        if (good.getStatus().equals("1")){
            //goodsService.updateGoodStatusById("0",goodId);
            good.setStatus("0");
            good.setDownTime(MallUtil.getNow());
            goodsService.updateGoods(good);
            request.setAttribute("success","下架成功");
        }else if(good.getStatus().equals("0")){
            //goodsService.updateGoodStatusById("1",goodId);
            good.setStatus("1");
            good.setUpTime(MallUtil.getNow());
            goodsService.updateGoods(good);
            request.setAttribute("success","上架成功");
        }
        queryGoodsList(request, response);
    }


    /**添加商品页面*/
    private void addGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            String goodsName = request.getParameter("goodsName");
            Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
            //添加上传的图片文件
            Part goodPicture = request.getPart("goodPicture");
            //获取存储路径
            String imgPath = request.getServletContext().getRealPath("goodsImages");
            String uploadFile = MallUtil.uploadFile(goodPicture, imgPath);
            String img = "goodsImages/"+uploadFile;
            Integer goodStock = Integer.parseInt(request.getParameter("goodStock"));
            BigDecimal marketPrice = new BigDecimal(request.getParameter("marketPrice"));
            BigDecimal salePrice = new BigDecimal(request.getParameter("salePrice"));
            String isNew = request.getParameter("isNew");
            String isHot = request.getParameter("isHot");
            String author = request.getParameter("author");
            String publisher = request.getParameter("publisher");
            String publishTime = request.getParameter("publishTime");
            String description = request.getParameter("description");
            //封装成对象
            Goods goods = new Goods();
            goods.setName(goodsName);
            goods.setCategoryId(categoryId);
            goods.setImg(img);
            goods.setStock(goodStock);
            goods.setAuthor(author);
            goods.setDescription(description);
            goods.setPublisher(publisher);
            goods.setPublishTime(publishTime);
            goods.setMarketPrice(marketPrice);
            goods.setSalePrice(salePrice);
            goods.setIsNew(isNew);
            goods.setIsHot(isHot);
            //调用方法
            goodsService.addGoods(goods);
            request.setAttribute("success","添加成功");
            queryGoodsList(request, response);
        } catch (Exception e) {
            if (e instanceof MyServiceException){
                request.setAttribute("error",e.getMessage());
            }else {
                e.printStackTrace();
                request.setAttribute("error","系统维护中");
            }
            gotoAddGoods(request, response);
        }
    }


    /**跳转到商品添加页面*/
    protected void gotoAddGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Category> categoryList = categoryService.getCategoryList();
        request.setAttribute("categoryList",categoryList);
        request.getRequestDispatcher("/manage/goods_add.jsp").forward(request, response);
    }

    /**商品信息管理*/
    protected void queryGoodsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取搜索框内的元素
        String goodName = request.getParameter("goodName");
        Integer categoryId = null;
        String goodTypeId = request.getParameter("goodTypeId");
        if (goodTypeId != null && !"".equals(goodTypeId)){
            categoryId = Integer.parseInt(goodTypeId);
        }
        //条件回显
        request.setAttribute("goodName",goodName);
        request.setAttribute("goodTypeId",goodTypeId);
        //封装成对象
        Goods goods = new Goods();
        goods.setName(goodName);
        goods.setCategoryId(categoryId);
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
        PageInfo<Goods> goodsInfo = goodsService.getGoodsInfo(goods, pageNumDefault, pageSizeDefault);
        request.setAttribute("goodsInfo",goodsInfo);
        //查询商品类型表
        List<Category> categoryList = categoryService.getCategoryList();
        request.setAttribute("categoryList",categoryList);


        request.getRequestDispatcher("/manage/goods_list.jsp").forward(request,response);
    }

}
