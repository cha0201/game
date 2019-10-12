package com.example.game.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
public class GameRecordStatistics implements Serializable {

    private String date;

    private String account;

    private Integer pourNum;

    private BigDecimal pourAmount;

    private BigDecimal effectAmount;

    private BigDecimal profitAmount;

    private String operator;

}
