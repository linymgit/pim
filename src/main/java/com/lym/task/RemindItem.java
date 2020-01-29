package com.lym.task;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Date 2020/1/29
 * @auth linyimin
 * @Desc
 **/
public class RemindItem implements Delayed {

    private  long delayTime; // 延迟时间
    private  long expire; // 到期时间
    private Object data; // 数据

    public RemindItem(){
        delayTime=0;
        expire=0;
    }
    public RemindItem(long delay, Object data) {
        delayTime = delay;
        this.data = data;
        expire = System.currentTimeMillis() + delay;
    }

    /**
     * 优先队列里面优先级规则  TimeUnit .MILLISECONDS 获取单位 为毫秒的时间戳
     */
    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    /**
     * 剩余时间=到期时间-当前时间  convert: 将给定单元的时间段转换到此单元。
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public Object getData() {
        return data;
    }
}
