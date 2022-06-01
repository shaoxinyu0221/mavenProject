package com.exam.service;

import com.exam.dao.StudentDao;
import com.exam.dao.TopicDao;
import com.exam.entity.Student;
import com.exam.entity.Topic;
import com.exam.exception.MyException;
import com.exam.util.MyBatisUtil;

import java.util.List;

public class TopicService {

    public List<Topic> queryAllTopic(){
        TopicDao topicDao = MyBatisUtil.getDao(TopicDao.class);
        List<Topic> topicList = topicDao.selectAllTopic();
        return topicList;
    }

    public void addTopic(Topic topic,Integer studentId) {
        TopicDao topicDao = MyBatisUtil.getDao(TopicDao.class);
        StudentDao studentDao = MyBatisUtil.getDao(StudentDao.class);
        Student student = studentDao.queryById(studentId);
        if (student!=null){
            if(student.getTopicName() != null){
                throw new MyException("该学生已有课题");
            }
        }
        topicDao.isertTopic(topic);
    }

    public void deleteByid(Integer id) {
        TopicDao topicDao = MyBatisUtil.getDao(TopicDao.class);
        topicDao.deleteByid(id);
    }
}
