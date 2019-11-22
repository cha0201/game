package com.example.game.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DwTaskRequest {
    private String dwId;

    private Double dc;

    private Double jc;

    private String taskTime;

    private Integer zt;
}
