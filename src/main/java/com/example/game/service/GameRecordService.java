package com.example.game.service;

import com.example.game.entity.GameRecord;
import com.example.game.entity.GameRecordStatistics;
import com.example.game.request.GameRecordRequest;
import com.example.game.response.QueryGameRecordResponse;
import com.example.game.value.AccountVo;

import java.util.List;

public interface GameRecordService {

    List<AccountVo> getRecordAccountList();


    List<GameRecordStatistics> getRecordStatistics(String time,String account,String operator);

    QueryGameRecordResponse getRecordList(Integer currentPage,Integer pageSize,String account, String operator);

    int updateRecord(GameRecordRequest gameRecordRequest);
}
