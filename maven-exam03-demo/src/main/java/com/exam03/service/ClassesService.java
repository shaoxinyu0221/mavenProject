package com.exam03.service;

import com.exam03.dao.ClassesDao;
import com.exam03.dao.ScoreDao;
import com.exam03.dao.StudentDao;
import com.exam03.dao.SubjectDao;
import com.exam03.entity.Classes;
import com.exam03.entity.Score;
import com.exam03.entity.Student;
import com.exam03.entity.Subject;
import com.exam03.util.MyBatisUtil;

import java.util.List;

public class ClassesService {
    public List<Classes> getClaList() {
        ClassesDao classesDao = MyBatisUtil.getDao(ClassesDao.class);
        return classesDao.selectAll();
    }

    public List<Subject> getSubjectList() {
        SubjectDao subjectDao = MyBatisUtil.getDao(SubjectDao.class);
        return subjectDao.selectAll();

    }

    public List<Student> getStudentList(Integer classIdInt) {
        StudentDao studentDao = MyBatisUtil.getDao(StudentDao.class);
        return studentDao.selectByClassId(classIdInt);
    }

    public void addScore(Integer subjectId, Integer studentId, Integer score) {
        StudentDao studentDao = MyBatisUtil.getDao(StudentDao.class);
        studentDao.addScore(subjectId, studentId, score);
    }

    public List<Score> queryStudentScore(Integer classId) {
        ScoreDao scoreDao = MyBatisUtil.getDao(ScoreDao.class);
        return scoreDao.queryStudentScore(classId);
    }

    public Integer queryMinChinese(Integer classId) {
        ScoreDao scoreDao = MyBatisUtil.getDao(ScoreDao.class);
        return scoreDao.queryMinChinese(classId);
    }

    public Integer queryMinMath(Integer classId) {
        ScoreDao scoreDao = MyBatisUtil.getDao(ScoreDao.class);
        return scoreDao.queryMinMath(classId);
    }

    public Integer queryMinEnglish(Integer classId) {
        ScoreDao scoreDao = MyBatisUtil.getDao(ScoreDao.class);
        return scoreDao.queryMinEnglish(classId);
    }

    public Score selectMinChinese(Integer classId) {
        ScoreDao scoreDao = MyBatisUtil.getDao(ScoreDao.class);
        return scoreDao.selectMinChinese(classId);
    }

    public Score selectMaxChinese(Integer classId) {
        ScoreDao scoreDao = MyBatisUtil.getDao(ScoreDao.class);
        return scoreDao.selectMaxChinese(classId);
    }

    public Double queryAvgChinese(Integer classId) {
        ScoreDao scoreDao = MyBatisUtil.getDao(ScoreDao.class);
        return scoreDao.queryAvgChinese(classId);
    }
}
