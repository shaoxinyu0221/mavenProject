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


    /**????????????*/
    private void deleteBatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String ids = request.getParameter("ids");
        try {
            proxy.deleteBatch(ids);
            request.setAttribute("success","??????????????????");
        } catch (Exception e) {
            request.setAttribute("error","??????????????????");
            e.printStackTrace();
        }
        gotoPaperList(request, response);
    }


    /**??????????????????*/
    private void download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer id = Integer.parseInt(request.getParameter("downId"));
        Paper paper = proxy.queryPaperById(id);
        String filePath = request.getServletContext().getRealPath(paper.getPaperPath());
        String fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
        //?????????????????????
        FileInputStream input = new FileInputStream(filePath);
        response.setCharacterEncoding("UTF-8");
        //???????????????
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        //?????????????????????
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



    /**????????????????????????*/
    private void paperDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer id = Integer.parseInt(request.getParameter("deleteId"));
        try {
            proxy.deleteById(id);
            request.setAttribute("deleteSuccess", "????????????");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("deleteError", "????????????");
        }
        gotoPaperList(request, response);
    }


    /**????????????????????????*/
    private void paperModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            //????????????
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
            //????????????
            Paper paper = new Paper();
            paper.setId(id);
            paper.setTitle(title);
            paper.setPaperSummary(summary);
            paper.setTypeId(typeId);
            paper.setPaperPath(paperPath);
            paper.setStatus(status);
            //???????????????
            proxy.paperModify(paper);
            request.setAttribute("modifySuccess", "??????????????????");
            //????????????,???????????????
            gotoPaperList(request,response);
        } catch (Exception e) {
            if (e instanceof MyException){
                request.setAttribute("error", e.getMessage());
            }else {
                request.setAttribute("error", "???????????????");
                e.printStackTrace();
            }
            //????????????,??????????????????
            paperUpdate(request,response);
        }

    }


    /**??????????????????????????????*/
    private void paperUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //???????????????paper?????????id
        Integer id = Integer.parseInt(request.getParameter("id"));
        //??????id???????????????paper??????
        Paper paper = proxy.queryPaperById(id);
        //???????????????
        request.setAttribute("paper", paper);
        List<PaperType> typeList = typeProxy.queryPaperType();
        request.setAttribute("typeList",typeList);
        request.getRequestDispatcher("/paperUpdate.jsp").forward(request,response);
    }



    /**????????????????????????*/
    private void gotoPaperList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String tid = request.getParameter("typeId");
        Integer typeId = null;
        if(tid!=null && !"".equals(tid)){
            typeId = Integer.parseInt(tid);
        }
        //??????????????????????????????
        Paper paper = new Paper();
        paper.setTitle(title);
        paper.setTypeId(typeId);
        //??????pageNum????????????
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
        //??????????????????
        PageInfo<Paper> paperInfo = proxy.getPaperInfo(paper,pageNum,pageSize);

        List<PaperType> paperTypes = typeProxy.queryPaperType();


        request.setAttribute("paperInfo",paperInfo);
        request.getSession().setAttribute("paperTypes",paperTypes);
        //??????????????????
        request.setAttribute("title",title);
        request.setAttribute("typeId",typeId);

        request.getRequestDispatcher("/paperlist.jsp").forward(request,response);
    }

    /**?????????????????????*/
    private void paperAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PaperType> paperTypes = typeProxy.queryPaperType();
        request.setAttribute("paperTypes",paperTypes);
        request.getRequestDispatcher("/paperAdd.jsp").forward(request,response);
    }

    /**????????????*/
    private void addSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //????????????
            String title = request.getParameter("addtitle");
            String paperSummary = request.getParameter("addpapersummary");
            Integer typeId = Integer.parseInt(request.getParameter("addtypeId"));
            //??????????????????????????????
            Part addfile = request.getPart("addfile");
            //??????????????????????????????
            String realPath = this.getServletContext().getRealPath("paper");
            //????????????????????????
            String fileName = PaperUtil.uploadFile(addfile, realPath);
            //???????????????
            Paper paper = new Paper();
            paper.setTitle(title);
            paper.setPaperSummary(paperSummary);
            paper.setTypeId(typeId);
            paper.setPaperPath("paper/"+fileName);
            proxy.addPaper(paper);
            //??????????????????,??????????????????
            request.setAttribute("success", "??????????????????");
            gotoPaperList(request,response);
        } catch (Exception e) {
            if(e instanceof MyException){
                request.setAttribute("error", e.getMessage());
            }else {
                e.printStackTrace();
                request.setAttribute("error", e.getMessage());
            }
            //??????????????????,???????????????????????????
            paperAdd(request,response);
        }

    }



}
