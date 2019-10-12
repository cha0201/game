package com.example.game.dao;

import com.example.game.entity.GameUser;

import java.util.List;

public interface GameUserDao {
    int deleteByPrimaryKey(Integer userId);

    int insert(GameUser record);

    int insertSelective(GameUser record);

    GameUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(GameUser record);

    int updateByPrimaryKey(GameUser record);

    List<GameUser> selectGameUserListAll();
}