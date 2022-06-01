package com.exam.dao;

import com.exam.entity.Topic;

import java.util.List;

public interface TopicDao {

    List<Topic> selectAllTopic();

    void isertTopic(Topic topic);

    void deleteByid(Integer id);
}
