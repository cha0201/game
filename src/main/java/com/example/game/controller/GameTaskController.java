package com.example.game.controller;

import com.example.game.request.DwTaskRequest;
import com.example.game.request.GameTaskRequest;
import com.example.game.service.GameTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/task")
@RestController
@CrossOrigin
public class GameTaskController {

    @Autowired
    private GameTaskService gameTaskService;


    @GetMapping("/list")
    public ResponseEntity getGameTaskList(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
        return ResponseEntity.ok(gameTaskService.getGameTaskList(currentPage, pageSize));
    }


    @PostMapping("/add")
    public ResponseEntity addGameTask(@RequestBody GameTaskRequest gameTaskRequest) {
        return ResponseEntity.ok(gameTaskService.addGameTask(gameTaskRequest));
    }

    @GetMapping("/dw-id-list")
    public ResponseEntity selectDwIdList(){
        return ResponseEntity.ok(gameTaskService.selectDwIdList());
    }


    @PostMapping("/add-dw-task")
    public ResponseEntity addDwTask(@RequestBody DwTaskRequest dwTaskRequest){
        return ResponseEntity.ok(gameTaskService.addDwTask(dwTaskRequest));
    }

}
