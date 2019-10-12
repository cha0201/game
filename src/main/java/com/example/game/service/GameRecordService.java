package com.example.game.service;

import com.example.game.entity.GameRecordStatistics;
import com.example.game.value.AccountVo;

import java.util.List;

public interface GameRecordService {

    List<AccountVo> getRecordAccountList();


    List<GameRecordStatistics> getRecordStatistics(String time,String account,String operator);

}
