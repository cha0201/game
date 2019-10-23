package com.example.game.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class GameRecordRequest {

    private Integer id;

    private Date recordDate;

    private String recordAccount;

    private Integer recordCount;

    private BigDecimal recordAmount;

    private BigDecimal recordValidAmount;

    private BigDecimal recordGain;

    private Integer gameUserId;
}
