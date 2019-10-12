package com.example.game.service.impl;

import com.example.game.entity.GameInfo;
import com.example.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    MongoTemplate mongotemplate;

    @Override
    public List<GameInfo> getGameList(String name, String time) {
        Query query = new Query();
        query.addCriteria(Criteria.where("game_union_name").regex(".*?\\" + name + ".*"));
        List<GameInfo> gameInfoList = mongotemplate.find(query, GameInfo.class);//查询mongo数据库
        return gameInfoList;
    }
}
