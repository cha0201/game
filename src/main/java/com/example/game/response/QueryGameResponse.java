package com.example.game.response;

import com.example.game.entity.GameInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QueryGameResponse {

    private Integer taskId;

    private Integer status;

    private String result;

}
