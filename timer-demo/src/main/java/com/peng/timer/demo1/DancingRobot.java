package com.peng.timer.demo1;

import java.text.SimpleDateFormat;
import java.util.TimerTask;

public class DancingRobot extends TimerTask {
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //scheduledExecutionTime()获取执行时间的方法
        System.out.println("Scheduled exec time is:"+sdf.format(scheduledExecutionTime()));
        System.out.println("Dancing happily!");
    }
}
