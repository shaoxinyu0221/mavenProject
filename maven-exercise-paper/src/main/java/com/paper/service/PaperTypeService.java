package com.paper.service;

import com.paper.dao.PaperTypeDao;
import com.paper.entity.PaperType;
import com.paper.util.MyBatisUtil;

import java.util.List;

public class PaperTypeService {


    public List<PaperType> queryPaperType(){
        PaperTypeDao paperTypeDao = MyBatisUtil.getDao(PaperTypeDao.class);
        List<PaperType> paperTypes = paperTypeDao.selectPaperType();
        return paperTypes;
    }


}
