package com.example.game.service;

import com.example.game.request.DwTaskRequest;
import com.example.game.request.GameTaskRequest;
import com.example.game.response.QueryGameTaskResponse;

import java.util.List;

public interface GameTaskService {

    QueryGameTaskResponse getGameTaskList(Integer currentPage,Integer pageSize);

    int addGameTask(GameTaskRequest gameTaskRequest);

    int addDwTask(DwTaskRequest dwTaskRequest);

    List<String> selectDwIdList();
}
