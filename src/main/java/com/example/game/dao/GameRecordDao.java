package com.example.game.dao;

import com.example.game.dao.params.GameRecordParams;
import com.example.game.entity.GameRecord;
import com.example.game.entity.GameRecordStatistics;
import com.example.game.value.GameRecordConditionVo;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface GameRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(GameRecord record);

    int insertSelective(GameRecord record);

    GameRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GameRecord record);

    int updateByPrimaryKey(GameRecord record);

    List<Map<String,Object>> getRecordAccountList();

    Page<GameRecord> getGameRecordList(GameRecordParams gameRecordParams);

    List<GameRecordStatistics> selectGameRecordStatisticsResult(GameRecordConditionVo gameRecordConditionVo);
}