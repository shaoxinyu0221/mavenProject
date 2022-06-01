package com.exam.service;

import com.exam.dao.StudentDao;
import com.exam.entity.Student;
import com.exam.util.MyBatisUtil;

import java.util.List;

public class StudentService {
    public List<Student> queryStuByTid(Integer tid) {
        StudentDao studentDao = MyBatisUtil.getDao(StudentDao.class);
        List<Student> studentList = studentDao.selectStuByTid(tid);
        return studentList;
    }

    public List<Student> queryAllStudent() {
        StudentDao studentDao = MyBatisUtil.getDao(StudentDao.class);
        List<Student> studentInfo = studentDao.selectAllStudent();
        return studentInfo;
    }
}
