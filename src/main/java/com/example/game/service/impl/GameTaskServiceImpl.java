package com.example.game.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.game.dao.GameTaskDao;
import com.example.game.entity.GameTask;
import com.example.game.request.GameTaskRequest;
import com.example.game.response.QueryGameTaskResponse;
import com.example.game.service.GameTaskService;
import com.example.game.service.RequestQueue;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GameTaskServiceImpl implements GameTaskService {

    @Autowired
    private GameTaskDao gameTaskDao;

    @Autowired
    private RequestQueue requestQueue;

    @Override
    public QueryGameTaskResponse getGameTaskList(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        Page page = gameTaskDao.selectGameTaskList();
        return QueryGameTaskResponse.builder()
                .total(page.getTotal())
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .pages(page.getPages())
                .data(page.getResult())
                .build();
    }

    @Override
    public int addGameTask(GameTaskRequest gameTaskRequest) {

        GameTask gameTask=new GameTask();
        JSONObject obj=new JSONObject();
        obj.put("gameName",gameTaskRequest.getGameName());
        obj.put("time",gameTaskRequest.getTime());
        gameTask.setCondition(obj.toJSONString());
        gameTask.setCreateTime(new Date());
        gameTask.setName("1");
        int count=gameTaskDao.insert(gameTask);
        if(count>0){
            requestQueue.getTaskQueue().add(gameTask);
        }
        return 0;
    }
}
