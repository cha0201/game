package com.example.game.service.impl;

import com.example.game.dao.GameUserDao;
import com.example.game.entity.GameUser;
import com.example.game.service.GameUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameUserServiceImpl implements GameUserService {


    @Autowired
    private GameUserDao gameUserDao;

    @Override
    public List<GameUser> getGameUserList() {
        return gameUserDao.selectGameUserListAll();
    }
}
