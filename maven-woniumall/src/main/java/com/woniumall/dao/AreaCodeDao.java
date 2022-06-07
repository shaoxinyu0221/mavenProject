package com.woniumall.dao;

import com.woniumall.entity.AreaCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AreaCodeDao {


    List<AreaCode> queryAreaCodeList(@Param("superior") String superior);
}
