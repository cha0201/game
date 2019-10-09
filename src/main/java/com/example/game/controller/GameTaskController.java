package com.example.game.controller;

import com.example.game.request.GameTaskRequest;
import com.example.game.service.GameTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/task")
public class GameTaskController {

    @Autowired
    private GameTaskService gameTaskService;


    @GetMapping("/list")
    public ResponseEntity getGameTaskList(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
        return ResponseEntity.ok(gameTaskService.getGameTaskList(currentPage, pageSize));
    }


    @PostMapping("/add")
    public ResponseEntity addGameTask(GameTaskRequest gameTaskRequest) {
        return ResponseEntity.ok(gameTaskService.addGameTask(gameTaskRequest));
    }

}
