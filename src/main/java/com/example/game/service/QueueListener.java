package com.example.game.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * 队列监听器，初始化启动所有监听任务
 *
 * @author Logen
 *
 */
@Component
public class QueueListener {

    @Autowired
    private GameQueryRunnable gameQueryRunnable;

    /**
     * 初始化时启动监听请求队列
     */
    @PostConstruct
    public void init() {
        gameQueryRunnable.start();
    }

    /**
     * 销毁容器时停止监听任务
     */
    @PreDestroy
    public void destroy() {
        gameQueryRunnable.setRunning(false);
    }

}