package com.woniumall.web;

import com.woniumall.entity.Category;
import com.woniumall.entity.User;
import com.woniumall.exception.MyServiceException;
import com.woniumall.service.CategoryService;
import com.woniumall.service.ServiceProxyFactory;
import com.woniumall.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/manage/category")
public class CategoryServlet extends HttpServlet {

    private UserService userService = ServiceProxyFactory.getProxy(new UserService());
    private CategoryService categoryService = ServiceProxyFactory.getProxy(new CategoryService());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opr = request.getParameter("opr");
        if ("queryCategoryList".equals(opr)){
            queryCategoryList(request, response);
        }else if("categoryDelete".equals(opr)){
            categoryDelete(request, response);
        }else if ("gotoCategoryUpdate".equals(opr)){
            gotoCategoryUpdate(request, response);
        }else if ("categoryUpdate".equals(opr)){
            categoryUpdate(request, response);
        }else if ("gotoCategoryAdd".equals(opr)){
            gotoCategoryAdd(request, response);
        }else if ("categoryAdd".equals(opr)){
            categoryAdd(request, response);
        }
    }

    /**新增商品类别*/
    protected void categoryAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String categoryName = request.getParameter("title");
        String categoryStatus = request.getParameter("categoryStatus");
        Category category = new Category();
        category.setName(categoryName);
        category.setStatus(categoryStatus);
        try {
            categoryService.addCategory(category);
            request.setAttribute("success","添加成功");
            queryCategoryList(request, response);
        } catch (Exception e) {
            if (e instanceof MyServiceException){
                request.setAttribute("error",e.getMessage());
            }else {
                request.setAttribute("error","服务器繁忙,请稍后重试");
                e.printStackTrace();
            }
            gotoCategoryAdd(request, response);
        }

    }


    /**前往新增页面*/
    protected void gotoCategoryAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/manage/category_add.jsp").forward(request,response);
    }


    /**类别修改*/
    protected void categoryUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer id = Integer.parseInt(request.getParameter("id"));
        String categoryName = request.getParameter("title");
        String categoryStatus = request.getParameter("categoryStatus");
        //封装成对象
        Category category = new Category();
        category.setId(id);
        category.setName(categoryName);
        category.setStatus(categoryStatus);

        try {
            categoryService.updateCategory(category);
            request.setAttribute("success","更新成功");
            queryCategoryList(request, response);
        } catch (Exception e) {
            if (e instanceof MyServiceException){
                request.setAttribute("error",e.getMessage());
            }else {
                request.setAttribute("error","服务器繁忙,请稍后重试");
                e.printStackTrace();
            }
        }

    }


    /**跳转到修改页面*/
    protected void gotoCategoryUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer id = Integer.parseInt(request.getParameter("categoryId"));
        Category category = categoryService.queryCategoryById(id);
        request.setAttribute("category",category);
        request.getRequestDispatcher("/manage/category_update.jsp").forward(request,response);
    }


    /**删除商品类别*/
    protected void categoryDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String categoryId = request.getParameter("categoryId");
        Integer id = Integer.parseInt(categoryId);
        try {
            categoryService.deleteCategory(id);
            request.setAttribute("success","删除成功");
        } catch (Exception e) {
            if (e instanceof MyServiceException){
                request.setAttribute("error",e.getMessage());
            }else {
                request.setAttribute("error","服务器繁忙,请稍后重试");
                e.printStackTrace();
            }
        }
        queryCategoryList(request, response);
    }

    /**查询商品类别列表*/
    protected void queryCategoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Category> categoryList = categoryService.getCategoryList();
        request.setAttribute("categoryList",categoryList);
        //跳转
        request.getRequestDispatcher("/manage/category_list.jsp").forward(request,response);
    }



}
