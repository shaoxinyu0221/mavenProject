package com.exam.dao;

import com.exam.entity.Teacher;

import java.util.List;

public interface TeacherDao {


    Teacher selectTeacherById(Integer tid);

    List<Teacher> selectAllTeacher();
}
