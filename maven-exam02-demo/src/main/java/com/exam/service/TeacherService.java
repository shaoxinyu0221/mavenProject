package com.exam.service;

import com.exam.dao.TeacherDao;
import com.exam.entity.Teacher;
import com.exam.util.MyBatisUtil;

import java.util.List;

public class TeacherService {


    public Teacher queryTeacherById(Integer tid) {
        TeacherDao teacherDao = MyBatisUtil.getDao(TeacherDao.class);
        return teacherDao.selectTeacherById(tid);
    }

    public List<Teacher> queryAllTeacher() {
        TeacherDao teacherDao = MyBatisUtil.getDao(TeacherDao.class);
        List<Teacher> teacherInfo = teacherDao.selectAllTeacher();
        return teacherInfo;
    }
}
