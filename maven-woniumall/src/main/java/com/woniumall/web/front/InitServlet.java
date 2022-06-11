package com.woniumall.web.front;

import com.woniumall.entity.Category;
import com.woniumall.service.CategoryService;
import com.woniumall.service.ServiceProxyFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.List;

@WebServlet(name = "init", urlPatterns = { "/init" }, loadOnStartup = 1)
public class InitServlet extends HttpServlet {

	private CategoryService categoryService = ServiceProxyFactory.getProxy(new CategoryService());

	@Override
	public void init() throws ServletException {
		ServletContext context = getServletContext();
		context.setAttribute("base", context.getContextPath());
		context.setAttribute("site", "图书商城");
		//查询状态为正常的商品种类的前6个,为首页导航栏展示
		List<Category> categoryListForNav = categoryService.getCategoryListForNav();
		context.setAttribute("categoryListForNav", categoryListForNav);
		//查询所有的状态正常的商品种类
		List<Category> categoryListForAll = categoryService.getCategoryListForAll();
		context.setAttribute("categoryListForAll", categoryListForAll);
	}
}
