package com.example.game.service;


import com.example.game.entity.GameTask;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * 存放所有异步处理接口请求队列的对象,一个接口对应一个队列
 *
 * @author Logen
 */
@Component
public class RequestQueue {

    /**
     * 处理下订单接口的队列，设置缓冲容量为50
     */
    private BlockingQueue<GameTask> taskQueue = new LinkedBlockingQueue<>(60);

    public BlockingQueue<GameTask> getTaskQueue() {
        return taskQueue;
    }

}