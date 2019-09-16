package com.example.game.controller;

import com.example.game.entity.GameInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/game")
@RestController
public class GameController {

    @Autowired
    MongoTemplate mongotemplate;

    @GetMapping("/list")
    public ResponseEntity getGameInfoList() {
        Query query = new Query();
        query.addCriteria(Criteria.where("game_union_name").is("日皇杯"));
        List<GameInfo> gameInfoList = mongotemplate.find(query, GameInfo.class);
        return ResponseEntity.ok(gameInfoList);
    }


}
