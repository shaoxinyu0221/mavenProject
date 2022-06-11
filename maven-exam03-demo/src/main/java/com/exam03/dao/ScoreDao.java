package com.exam03.dao;

import com.exam03.entity.Score;
import com.exam03.entity.Student;

import java.util.List;

public interface ScoreDao {
    List<Score> queryStudentScore(Integer classId);

    Integer queryMinChinese(Integer classId);

    Integer queryMinMath(Integer classId);

    Integer queryMinEnglish(Integer classId);

    Score selectMinChinese(Integer classId);

    Score selectMaxChinese(Integer classId);

    Double queryAvgChinese(Integer classId);
}
