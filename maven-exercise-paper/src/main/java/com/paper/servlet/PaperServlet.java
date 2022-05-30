package com.paper.servlet;

import com.paper.entity.Paper;
import com.paper.entity.PaperType;
import com.paper.service.PaperService;
import com.paper.service.PaperTypeService;
import com.paper.service.ServiceProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/paper")
public class PaperServlet extends HttpServlet {

    private static PaperService proxy = ServiceProxyFactory.getProxy(new PaperService());
    private static PaperTypeService typeProxy = ServiceProxyFactory.getProxy(new PaperTypeService());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String opr = request.getParameter("opr");
        if("gotoPaperList".equals(opr)){
            gotoPaperList(request,response);
        }else if("paperAdd".equals(opr)){
            paperAdd(request,response);
        }else if("addSuccess".equals(opr)){
            addSuccess(request,response);
        }else if("paperAdd".equals(opr)){

        }else if("paperAdd".equals(opr)){

        }else{
            System.out.println("opr not found......");
        }

    }

    /**准备进入论文列表*/
    private void gotoPaperList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String tid = request.getParameter("typeId");
        System.out.println(tid);
        Integer typeId = null;
        if(tid!=null && !"".equals(tid)){
            typeId = Integer.parseInt(tid);
        }
        System.out.println(typeId);
        //把查询条件封装成对象
        Paper paper = new Paper();
        paper.setTitle(title);
        paper.setTypeId(typeId);

        //
        List<Paper> paperList = proxy.queryPaperList(paper);
        List<PaperType> paperTypes = typeProxy.queryPaperType();


        request.getSession().setAttribute("paperList",paperList);
        request.getSession().setAttribute("paperTypes",paperTypes);
        //查询条件回填
        request.setAttribute("title",title);
        request.setAttribute("typeId",typeId);
//        for(Paper papers : paperList){
//            if(papers.getId().equals(typeId) && typeId!=null){
//                request.setAttribute("perName",papers.getPername());
//                break;
//            }
//        }
        //先跳转到指定页面
        request.getRequestDispatcher("/paperlist.jsp").forward(request,response);
    }

    /**跳转到增加页面*/
    private void paperAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/paperAdd.jsp").forward(request,response);
    }

    /**论文添加*/
    private void addSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String paperSummary = request.getParameter("papersummary");
        Integer typeId = Integer.parseInt(request.getParameter("typeId"));
        String file = request.getParameter("file");
        Paper paper = new Paper();
        paper.setTitle(title);
        paper.setPaperSummary(paperSummary);
        paper.setTypeId(typeId);
        paper.setPaperPath(file);
        proxy.addPaper(paper);
        request.getRequestDispatcher("/addSuccess.jsp").forward(request,response);
    }

}
