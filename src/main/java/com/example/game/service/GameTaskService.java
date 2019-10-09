package com.example.game.service;

import com.example.game.request.GameTaskRequest;
import com.example.game.response.QueryGameTaskResponse;

public interface GameTaskService {

    QueryGameTaskResponse getGameTaskList(Integer currentPage,Integer pageSize);

    int addGameTask(GameTaskRequest gameTaskRequest);
}
