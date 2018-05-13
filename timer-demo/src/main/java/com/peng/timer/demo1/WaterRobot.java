package com.peng.timer.demo1;

import java.util.Timer;
import java.util.TimerTask;

public class WaterRobot extends TimerTask {
    //最大容量为5L
    private Integer bucketCapacity = 0;
    private Timer timer;
    public WaterRobot(Timer timer) {
        this.timer = timer;
    }

    public void run() {
        if (bucketCapacity < 5) {
            System.out.println("add water");
            bucketCapacity++;
        }else {
            //停止执行
            cancel();
            System.out.println("the waterRobot has been aborted");
            System.out.println(timer.purge());
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timer.cancel();
        }
    }
}
