package com.example.game.controller;

import com.example.game.service.GameRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/record")
@RestController
public class GameRecordController {

    @Autowired
    private GameRecordService gameRecordService;

    @GetMapping("/account-list")
    public ResponseEntity getRecordAccountList() {
        return ResponseEntity.ok(gameRecordService.getRecordAccountList());
    }


    @GetMapping("/statistics/list")
    public ResponseEntity getRecordStatisticsResult(@RequestParam("time") String time, @RequestParam("account") String account, @RequestParam("operator") String operator) {
        return ResponseEntity.ok(gameRecordService.getRecordStatistics(time, account, operator));
    }
}
