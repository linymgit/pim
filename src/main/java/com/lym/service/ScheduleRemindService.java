package com.lym.service;

import com.alibaba.fastjson.JSON;
import com.lym.entity.Schedule;
import com.lym.task.RemindItem;
import com.lym.websocket.WebsocketMsgHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Date 2020/1/29
 * @auth linyimin
 * @Desc
 **/
@Service
public class ScheduleRemindService{
    private static final Logger log = LoggerFactory.getLogger(ScheduleRemindService.class);

    private DelayQueue<RemindItem> queue = new DelayQueue<>();

    private ExecutorService exec = Executors.newFixedThreadPool(1);

    @Autowired
    private WebsocketMsgHandler websocketMsgHandler;

    @Autowired
    private ScheduleService scheduleService;

    @PostConstruct
    private void execute(){

        exec.execute(() -> {
            for(;;){
                try {
                    Thread.sleep(100);
                    RemindItem take = queue.take();
                    if (log.isDebugEnabled()) {
                        log.info(JSON.toJSONString(take.getData()));
                    }
                    Schedule s = (Schedule) take.getData();
                    WebSocketSession webSocketSession = websocketMsgHandler.getId2SessionMap().get(s.getUserId());
                    if (Objects.isNull(webSocketSession)) {
                        log.warn("webSocketSession is null userId is "+s.getUserId());
                    }else{
                        TextMessage textMessage = new TextMessage(JSON.toJSONBytes(s));
                        webSocketSession.sendMessage(textMessage);
                    }

                    s.setRemindCount(s.getRemindCount()+1);
                    if (s.getRemindCount()<s.getRemindSum()) {
                        addRemindItem(new RemindItem(s.getRemindPeriod()*60*1000, s));
                    }
                    scheduleService.edit(s);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @PreDestroy
    public void close() {
        System.out.println("close");
        exec.shutdown();
    }

    public synchronized void addRemindItem(RemindItem remindItem){
        this.queue.offer(remindItem);
    }

    public synchronized boolean removeRemindItem(Long id){
        return queue.removeIf(remindItem -> {
            Schedule schedule = (Schedule) remindItem.getData();
            return schedule.getId().equals(id);
        });
    }

}
