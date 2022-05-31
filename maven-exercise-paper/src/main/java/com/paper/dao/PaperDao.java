package com.paper.dao;

import com.paper.entity.Paper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaperDao {

    List<Paper> selectPaperByCondition(Paper paper);

    Integer updatePaper(Paper paper);

    void addPaper(Paper paer);

    void deletePaper(Integer id);

    Paper selectPaparTtle(String title);

    Paper selectPaperById(Integer id);

}
