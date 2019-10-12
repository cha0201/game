package com.example.game.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class GameRecordConditionVo {

    private Date startDateTime;

    private Date endDateTime;

    private String account;

    private String gameUserId;

    private String columnCondition;

    private String condition;
}
