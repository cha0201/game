package com.example.game.service;

import com.example.game.entity.GameInfo;

import java.util.List;

public interface GameService {
    List<GameInfo> getGameList(String name, String time);
}
