package com.exam03.dao;

import com.exam03.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    List<Student> selectByClassId(Integer classIdInt);

    void addScore(@Param("subjectId") Integer subjectId, @Param("studentId") Integer studentId, @Param("score") Integer score);
}
