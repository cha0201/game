package com.example.game.service;

import com.alibaba.fastjson.JSONObject;
import com.example.game.dao.GameTaskDao;
import com.example.game.entity.GameInfo;
import com.example.game.entity.GameTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.game.value.Constants.GAME_TASK_KEY;

@Component
public class GameQueryRunnable extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(GameQueryRunnable.class);


    @Autowired
    private RequestQueue queue;

    @Autowired
    private GameService gameService;

    @Autowired
    private GameTaskDao gameTaskDao;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    private boolean running = true;

    @Override
    public void run() {
        while (running) {
            try {
                GameTask gameTask = queue.getTaskQueue().take();
                logger.info("[ Task ]开始处理任务:{}", gameTask);
                Thread.sleep(1000);
                gameTask.setStatus(2);
                gameTaskDao.updateByPrimaryKeySelective(gameTask);
                String condition = gameTask.getCondition();
                JSONObject jsonObject = JSONObject.parseObject(condition);
                String name = jsonObject.getString("gameName");
                String time = jsonObject.getString("time");
                List<GameInfo> gameInfoList = gameService.getGameList(name, time);
                String result = JSONObject.toJSONString(gameInfoList);
                redisTemplate.opsForValue().set(GAME_TASK_KEY + gameTask.getTaskId(), result);
//            gameTask.setResult(JSONObject.toJSONString(gameInfoList));
                gameTask.setStatus(3);
                gameTaskDao.updateByPrimaryKeySelective(gameTask);
                logger.info("[ Task ]开始处理结束:{}", gameTask);
            } catch (Exception e) {
                logger.error("任务处理失败 error: {}", e);
                running = false;
            }
        }
    }


    public void setRunning(boolean running) {
        this.running = running;
    }

}
