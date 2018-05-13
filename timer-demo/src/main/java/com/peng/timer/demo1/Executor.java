package com.peng.timer.demo1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

public class Executor {
    public static void main(String[] args) {
        Timer timer = new Timer();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("current time is :"+sdf.format(calendar.getTime()));

        DancingRobot dr = new DancingRobot();
        WaterRobot wr = new WaterRobot(timer);

        timer.schedule(dr,calendar.getTime(),2000L);
        timer.scheduleAtFixedRate(wr,calendar.getTime(),1000L);
    }
}
