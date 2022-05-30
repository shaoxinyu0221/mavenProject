package com.paper.service;

import com.paper.dao.PaperDao;
import com.paper.entity.Paper;
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
        paperDao.addPaper(paper);
    }

}
