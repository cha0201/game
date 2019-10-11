package com.example.game.controller;

import com.example.game.dao.GameTaskDao;
import com.example.game.entity.GameTask;
import com.example.game.response.QueryGameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.game.value.Constants.GAME_TASK_KEY;

@RequestMapping("/game")
@RestController
@CrossOrigin
public class GameController {

    @Autowired
    MongoTemplate mongotemplate;

    @Autowired
    private GameTaskDao gameTaskDao;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/list")
    public ResponseEntity getGameInfoList(@RequestParam("taskId") Integer taskId) {
        GameTask gameTask = gameTaskDao.selectByPrimaryKey(taskId);
        QueryGameResponse queryGameResponse = new QueryGameResponse();
        String result = (String) redisTemplate.opsForValue().get(GAME_TASK_KEY + taskId);
        if (gameTask != null) {
            queryGameResponse.setTaskId(taskId);
            queryGameResponse.setResult(result);
            queryGameResponse.setStatus(gameTask.getStatus());
        }
        return ResponseEntity.ok(queryGameResponse);
    }


}
