package com.lym.task;

import java.util.concurrent.DelayQueue;

/**
 * @Date 2020/1/29
 * @auth linyimin
 * @Desc
 **/
public class Consumer implements Runnable {
    // 延时队列 ,消费者从其中获取消息进行消费
    private DelayQueue<RemindItem> queue;

    public Consumer(DelayQueue<RemindItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for(;;){
            try {
                Thread.sleep(100);
                RemindItem take = queue.take();
                System.out.println("消费消息id：" + take.getData() + " 消息体：" + take.getData());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
