package com.example.game.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameQueryRunnable extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(GameQueryRunnable.class);


    @Autowired
    private RequestQueue queue;

    private boolean running = true;

    @Override
    public void run(){

    }


    public void setRunning(boolean running) {
        this.running = running;
    }

}
