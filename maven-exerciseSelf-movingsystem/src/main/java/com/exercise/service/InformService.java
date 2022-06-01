package com.exercise.service;

import com.exercise.dao.InformDao;
import com.exercise.entity.Inform;
import com.exercise.exception.MyException;
import com.exercise.util.MyBatisUtil;

import java.util.List;

public class InformService {


    public void checkMoving(Inform inform) {
        InformDao informDao = MyBatisUtil.getDao(InformDao.class);
        Integer integer = informDao.insertInform(inform);
        if (integer == 0){
            throw new MyException("预约失败");
        }
    }

    public List<Inform> queryInformList(){
        InformDao informDao = MyBatisUtil.getDao(InformDao.class);
        List<Inform> informList = informDao.selectAllInform();
        return informList;
    }

}
