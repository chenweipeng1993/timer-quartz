package com.peng.timer;

import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
    public String name;

    public MyTimerTask(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println("Current exec name is:"+name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
