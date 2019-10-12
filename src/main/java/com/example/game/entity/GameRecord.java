package com.example.game.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class GameRecord {
    private Integer id;

    private String recordUuid;

    private Date recordDate;

    private String recordAccount;

    private Integer recordCount;

    private BigDecimal recordAmount;

    private BigDecimal recordValidAmount;

    private BigDecimal recordGain;

    private Integer gameUserId;

    private Date createTime;

    private Date updateTime;


}