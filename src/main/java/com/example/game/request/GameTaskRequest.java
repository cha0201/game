package com.example.game.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GameTaskRequest {

    private Integer taskId;

    private String gameName;

    private String remark;

    private List<Date> time;

    private String creator;


}
