package com.example.game.dao;

import com.example.game.entity.TaskTest;

import java.util.List;

public interface TaskTestDao {
    int deleteByPrimaryKey(Integer taskId);

    int insert(TaskTest record);

    int insertSelective(TaskTest record);

    TaskTest selectByPrimaryKey(Integer taskId);

    List<String> selectDwIdList();

    int updateByPrimaryKeySelective(TaskTest record);

    int updateByPrimaryKey(TaskTest record);
}