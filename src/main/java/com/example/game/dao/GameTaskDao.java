package com.example.game.dao;

import com.example.game.entity.GameTask;
import com.github.pagehelper.Page;

import java.util.List;

public interface GameTaskDao {
    int deleteByPrimaryKey(Integer taskId);

    int insert(GameTask record);

    int insertSelective(GameTask record);

    GameTask selectByPrimaryKey(Integer taskId);

    int updateByPrimaryKeySelective(GameTask record);

    int updateByPrimaryKeyWithBLOBs(GameTask record);

    int updateByPrimaryKey(GameTask record);

    Page<GameTask> selectGameTaskList();
}