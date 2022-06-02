package com.paper.servlet;

import com.github.pagehelper.PageInfo;
import com.paper.entity.Paper;
import com.paper.entity.PaperType;
import com.paper.exception.MyException;
import com.paper.service.PaperService;
import com.paper.service.PaperTypeService;
import com.paper.service.ServiceProxyFactory;
import com.paper.util.PaperUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@WebServlet("/paper")
@MultipartConfig
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
        }else if("paperUpdate".equals(opr)){
            paperUpdate(request,response);
        }else if("paperModify".equals(opr)){
            paperModify(request,response);
        }else if("paperDelete".equals(opr)){
            paperDelete(request,response);
        }else if("download".equals(opr)){
            download(request,response);
        }else if("deleteBatch".equals(opr)){
            deleteBatch(request,response);
        }else{
            System.out.println("opr not found......");
        }

    }


    /**批量删除*/
    private void deleteBatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String ids = request.getParameter("ids");
        try {
            proxy.deleteBatch(ids);
            request.setAttribute("success","批量删除成功");
        } catch (Exception e) {
            request.setAttribute("error","批量删除失败");
            e.printStackTrace();
        }
        gotoPaperList(request, response);
    }


    /**文件下载功能*/
    private void download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer id = Integer.parseInt(request.getParameter("downId"));
        Paper paper = proxy.queryPaperById(id);
        String filePath = request.getServletContext().getRealPath(paper.getPaperPath());
        String fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
        //创建字节输入流
        FileInputStream input = new FileInputStream(filePath);
        response.setCharacterEncoding("UTF-8");
        //设置响应头
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        //创建字节输出流
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] byt = new byte[1024];
        int len = 0;
        while((len=input.read(byt)) != -1){
            outputStream.write(byt,0,len);
            outputStream.flush();
        }
        outputStream.close();
        input.close();
    }



    /**执行论文删除操作*/
    private void paperDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer id = Integer.parseInt(request.getParameter("deleteId"));
        try {
            proxy.deleteById(id);
            request.setAttribute("deleteSuccess", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("deleteError", "删除失败");
        }
        gotoPaperList(request, response);
    }


    /**执行论文修改操作*/
    private void paperModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            //获取数据
            Integer id = Integer.parseInt(request.getParameter("updateId"));
            String title = request.getParameter("updateTitle");
            String summary = request.getParameter("updateSummary");
            Integer typeId = Integer.parseInt(request.getParameter("updateTypeId"));
            String status = request.getParameter("status");
            Part file = request.getPart("file");
            String paperPath = null;
            Paper oldPaper = proxy.queryPaperById(id);
            if (file.getSize()==0){
                paperPath = oldPaper.getPaperPath();
            }else {
                String realPath = this.getServletContext().getRealPath("paper");
                String deletePath = this.getServletContext().getRealPath(oldPaper.getPaperPath());
                File file1 = new File(deletePath);
                if(file1.exists()){
                    file1.delete();
                }
                String fileName = PaperUtil.uploadFile(file, realPath);
                paperPath = "paper/"+fileName;
            }
            //封装数据
            Paper paper = new Paper();
            paper.setId(id);
            paper.setTitle(title);
            paper.setPaperSummary(summary);
            paper.setTypeId(typeId);
            paper.setPaperPath(paperPath);
            paper.setStatus(status);
            //调修改方法
            proxy.paperModify(paper);
            request.setAttribute("modifySuccess", "数据修改成功");
            //如果成功,跳转到列表
            gotoPaperList(request,response);
        } catch (Exception e) {
            if (e instanceof MyException){
                request.setAttribute("error", e.getMessage());
            }else {
                request.setAttribute("error", "系统维护中");
                e.printStackTrace();
            }
            //如果失败,留在当前页面
            paperUpdate(request,response);
        }

    }


    /**准备进入论文修改页面*/
    private void paperUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取当前行paper对象的id
        Integer id = Integer.parseInt(request.getParameter("id"));
        //根据id查出对应的paper对象
        Paper paper = proxy.queryPaperById(id);
        //存入作用于
        request.setAttribute("paper", paper);
        List<PaperType> typeList = typeProxy.queryPaperType();
        request.setAttribute("typeList",typeList);
        request.getRequestDispatcher("/paperUpdate.jsp").forward(request,response);
    }



    /**准备进入论文列表*/
    private void gotoPaperList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String tid = request.getParameter("typeId");
        Integer typeId = null;
        if(tid!=null && !"".equals(tid)){
            typeId = Integer.parseInt(tid);
        }
        //把查询条件封装成对象
        Paper paper = new Paper();
        paper.setTitle(title);
        paper.setTypeId(typeId);
        //设置pageNum的默认值
        Integer pageNum = 1;
        String pNum = request.getParameter("pNum");
        if(!"".equals(pNum) && pNum!=null){
            pageNum = Integer.parseInt(pNum);
        }
        Integer pageSize = 5;
        String size = request.getParameter("size");
        if(!"".equals(size) && size!=null){
            pageSize = Integer.parseInt(size);
        }
        request.setAttribute("pageSize",pageSize);
        //
        //分页查询语句
        PageInfo<Paper> paperInfo = proxy.getPaperInfo(paper,pageNum,pageSize);

        List<PaperType> paperTypes = typeProxy.queryPaperType();


        request.setAttribute("paperInfo",paperInfo);
        request.getSession().setAttribute("paperTypes",paperTypes);
        //查询条件回填
        request.setAttribute("title",title);
        request.setAttribute("typeId",typeId);

        request.getRequestDispatcher("/paperlist.jsp").forward(request,response);
    }

    /**跳转到增加页面*/
    private void paperAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PaperType> paperTypes = typeProxy.queryPaperType();
        request.setAttribute("paperTypes",paperTypes);
        request.getRequestDispatcher("/paperAdd.jsp").forward(request,response);
    }

    /**论文添加*/
    private void addSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取数据
            String title = request.getParameter("addtitle");
            String paperSummary = request.getParameter("addpapersummary");
            Integer typeId = Integer.parseInt(request.getParameter("addtypeId"));
            //获取封装好的文件对象
            Part addfile = request.getPart("addfile");
            //获取存放文件的路径名
            String realPath = this.getServletContext().getRealPath("paper");
            //调用文件转换方法
            String fileName = PaperUtil.uploadFile(addfile, realPath);
            //封装成对象
            Paper paper = new Paper();
            paper.setTitle(title);
            paper.setPaperSummary(paperSummary);
            paper.setTypeId(typeId);
            paper.setPaperPath("paper/"+fileName);
            proxy.addPaper(paper);
            //如果添加成功,跳转到列表页
            request.setAttribute("success", "论文添加成功");
            gotoPaperList(request,response);
        } catch (Exception e) {
            if(e instanceof MyException){
                request.setAttribute("error", e.getMessage());
            }else {
                e.printStackTrace();
                request.setAttribute("error", e.getMessage());
            }
            //如果添加失败,继续跳转到添加页面
            paperAdd(request,response);
        }

    }



}
