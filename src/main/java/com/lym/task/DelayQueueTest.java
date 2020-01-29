package com.lym.task;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Date 2020/1/29
 * @auth linyimin
 * @Desc
 **/
public class DelayQueueTest {
    public static void main(String[] args) {
        // 创建延时队列
        DelayQueue<RemindItem> queue = new DelayQueue<RemindItem>();
        // 添加延时消息,m1 延时3s
        RemindItem m1 = new RemindItem(10000, "hello");
        // 添加延时消息,m2 延时10s
        RemindItem m2 = new RemindItem(50000, "world");
        //将延时消息放到延时队列中
        queue.offer(m2);
        queue.offer(m1);
        // 启动消费线程 消费添加到延时队列中的消息，前提是任务到了延期时间
        ExecutorService exec = Executors.newFixedThreadPool(1);
        exec.execute(new Consumer(queue));
        exec.shutdown();
    }
}
