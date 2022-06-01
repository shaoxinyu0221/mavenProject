package com.exam.dao;

import com.exam.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> selectStuByTid(Integer tid);

    List<Student> selectAllStudent();

    Student queryById(Integer studentId);
}
