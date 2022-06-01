package com.exercise.dao;

import com.exercise.entity.Inform;

import java.util.List;

public interface InformDao {

    Integer insertInform(Inform inform);

    List<Inform> selectAllInform();

}
