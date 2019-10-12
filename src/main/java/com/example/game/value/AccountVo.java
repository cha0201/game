package com.example.game.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class AccountVo implements Serializable {
    private String key;

    private String value;
}
