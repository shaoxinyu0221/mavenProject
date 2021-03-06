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
        }else if ("upGoodsBatch".equals(opr)){
            upGoodsBatch(request, response);
        }
    }

    /**??????????????????*/
    private void upGoodsBatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //??????????????????id??????
        String ids = request.getParameter("ids");
        String[] splitIds = ids.split(",");
        //??????service??????,????????????id,????????????????????????
        goodsService.downGoodsBatch(splitIds,"1");
        request.setAttribute("success", "??????????????????");
        //???????????????????????????
        queryGoodsList(request, response);
    }


    /**??????????????????*/
    private void deleteBatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //??????????????????id??????
        String ids = request.getParameter("ids");
        String[] splitIds = ids.split(",");
        //??????service??????,????????????id,????????????????????????
        goodsService.downGoodsBatch(splitIds,"0");
        request.setAttribute("success", "??????????????????");
        //???????????????????????????
        queryGoodsList(request, response);
    }

    /**??????????????????*/
    private void updateGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            //????????????????????????
            String id = request.getParameter("goodId");
            Goods oldGoods = goodsService.queryGoodsById(Integer.parseInt(id));
            //?????????????????????????????????
            String img = oldGoods.getImg();
            //??????????????????????????????
            Part goodPicture = request.getPart("goodPicture");
            if (goodPicture.getSize()!=0){
                String realPath = this.getServletContext().getRealPath("goodsImages");
                String fileName = MallUtil.uploadFile(goodPicture, realPath);
                img = "goodsImages"+fileName;
            }
            //??????????????????
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
            //???????????????
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
            //????????????
            goods.setNo(oldGoods.getNo());
            goods.setStatus(oldGoods.getStatus());
            goods.setUpTime(oldGoods.getUpTime());
            goods.setDownTime(oldGoods.getDownTime());
            goods.setSaleNum(oldGoods.getSaleNum());
            goods.setRemarkNum(oldGoods.getRemarkNum());
            goods.setRemarkScore(oldGoods.getRemarkScore());
            //??????????????????
            goodsService.updateGoods(goods);
            request.setAttribute("success","????????????");
            queryGoodsList(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error","??????????????????,????????????");
            gotoGoodsUpdate(request, response);
        }
    }

    /**???????????????????????????*/
    private void gotoGoodsUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer goodId = Integer.parseInt(request.getParameter("goodId"));
        Goods good = goodsService.queryGoodsById(goodId);
        request.setAttribute("good",good);
        List<Category> categoryList = categoryService.getCategoryList();
        request.setAttribute("categoryList",categoryList);
        request.getRequestDispatcher("/manage/goods_update.jsp").forward(request, response);
    }

    /**??????????????????,???????????????????????????,?????????????????????????????????*/
    private void changeGoodStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer goodId = Integer.parseInt(request.getParameter("goodId"));
        Goods good = goodsService.queryGoodsById(goodId);
        if (good.getStatus().equals("1")){
            //goodsService.updateGoodStatusById("0",goodId);
            good.setStatus("0");
            good.setDownTime(MallUtil.getNow());
            goodsService.updateGoods(good);
            request.setAttribute("success","????????????");
        }else if(good.getStatus().equals("0")){
            //goodsService.updateGoodStatusById("1",goodId);
            good.setStatus("1");
            good.setUpTime(MallUtil.getNow());
            goodsService.updateGoods(good);
            request.setAttribute("success","????????????");
        }
        queryGoodsList(request, response);
    }


    /**??????????????????*/
    private void addGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            String goodsName = request.getParameter("goodsName");
            Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
            //???????????????????????????
            Part goodPicture = request.getPart("goodPicture");
            //??????????????????
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
            //???????????????
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
            //????????????
            goodsService.addGoods(goods);
            request.setAttribute("success","????????????");
            queryGoodsList(request, response);
        } catch (Exception e) {
            if (e instanceof MyServiceException){
                request.setAttribute("error",e.getMessage());
            }else {
                e.printStackTrace();
                request.setAttribute("error","???????????????");
            }
            gotoAddGoods(request, response);
        }
    }


    /**???????????????????????????*/
    protected void gotoAddGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Category> categoryList = categoryService.getCategoryList();
        request.setAttribute("categoryList",categoryList);
        request.getRequestDispatcher("/manage/goods_add.jsp").forward(request, response);
    }

    /**??????????????????*/
    protected void queryGoodsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //???????????????????????????
        String goodName = request.getParameter("goodName");
        Integer categoryId = null;
        String goodTypeId = request.getParameter("goodTypeId");
        if (goodTypeId != null && !"".equals(goodTypeId)){
            categoryId = Integer.parseInt(goodTypeId);
        }
        //????????????
        request.setAttribute("goodName",goodName);
        request.setAttribute("goodTypeId",goodTypeId);
        //???????????????
        Goods goods = new Goods();
        goods.setName(goodName);
        goods.setCategoryId(categoryId);
        //????????????,????????????????????????
        Integer pageNumDefault = 1;
        String pageNum = request.getParameter("pageNum");
        if (pageNum!=null && !"".equals(pageNum)){
            pageNumDefault = Integer.parseInt(pageNum);
        }
        //?????????????????????,????????????5???
        Integer pageSizeDefault = 5;
        String pageSize = request.getParameter("pageSize");
        if (pageSize!=null && !"".equals(pageSize)){
            pageSizeDefault = Integer.parseInt(pageSize);
            request.setAttribute("pageSize",pageSize);
        }
        PageInfo<Goods> goodsInfo = goodsService.getGoodsInfo(goods, pageNumDefault, pageSizeDefault);
        request.setAttribute("goodsInfo",goodsInfo);
        //?????????????????????
        List<Category> categoryList = categoryService.getCategoryList();
        request.setAttribute("categoryList",categoryList);


        request.getRequestDispatcher("/manage/goods_list.jsp").forward(request,response);
    }

}
