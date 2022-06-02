package com.paper.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.paper.dao.PaperDao;
import com.paper.entity.Paper;
import com.paper.exception.MyException;
import com.paper.util.MyBatisUtil;

import java.util.List;

public class PaperService {


    public List<Paper> queryPaperList(Paper paper){
        PaperDao paperDao = MyBatisUtil.getDao(PaperDao.class);
        List<Paper> paperList = paperDao.selectPaperByCondition(paper);
        return paperList;
    }

    public void addPaper(Paper paper){
        PaperDao paperDao = MyBatisUtil.getDao(PaperDao.class);
        String title = paper.getTitle();
        Paper p = paperDao.selectPaparTtle(title);
        if(p!=null){
            throw new MyException("当前论文名已存在");
        }
        paperDao.addPaper(paper);
    }


    public Paper queryPaperById(Integer id){
        PaperDao paperDao = MyBatisUtil.getDao(PaperDao.class);
        Paper paper = paperDao.selectPaperById(id);
        return paper;
    }


    public void paperModify(Paper paper){
        PaperDao paperDao = MyBatisUtil.getDao(PaperDao.class);
        Integer integer = paperDao.updatePaper(paper);
        if (integer == 0){
            throw new MyException("修改失败");
        }
    }

    public void deleteById(Integer id){
        PaperDao paperDao = MyBatisUtil.getDao(PaperDao.class);
        paperDao.deletePaper(id);
    }

    public void deleteBatch(String ids) {
        PaperDao paperDao = MyBatisUtil.getDao(PaperDao.class);
        String[] strings = ids.split(",");
        paperDao.deleteBatch(strings);

    }

    public PageInfo<Paper> getPaperInfo(Paper paper,Integer pageNum,Integer pageSize) {
        //1.开启分页,提供想要展示的页码和想要展示的条数,只有紧跟着这条的才会启用拦截器
        PageHelper.startPage(pageNum,pageSize);
        //2.进行查询
        PaperDao paperDao = MyBatisUtil.getDao(PaperDao.class);
        List<Paper> paperList = paperDao.selectPaperByCondition(paper);
        //3.进行封装,将list封装进pageInfo中,必须用构造器
        //新建pageInfo对象,必须用构造器,不能用set方法
        PageInfo<Paper> pageInfo = new PageInfo<>(paperList);

        return pageInfo;
    }
}
