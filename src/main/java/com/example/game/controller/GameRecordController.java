package com.example.game.controller;

import com.example.game.entity.GameRecord;
import com.example.game.request.GameRecordRequest;
import com.example.game.service.GameRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @GetMapping("/list")
    public ResponseEntity getRecordList(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize, @RequestParam("account") String account, @RequestParam("operator") String operator) {
        return ResponseEntity.ok(gameRecordService.getRecordList(currentPage, pageSize, account, operator));
    }

    @PostMapping("/update")
    public ResponseEntity updateRecord(@RequestBody GameRecordRequest gameRecordRequest){

        return ResponseEntity.ok(gameRecordService.updateRecord(gameRecordRequest));
    }


}
