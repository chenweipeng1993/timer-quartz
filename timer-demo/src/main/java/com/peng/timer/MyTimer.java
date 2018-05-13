package com.peng.timer;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

public class MyTimer {
    public static void main(String[] args) {
        //创建一个Timer实例
        Timer timer = new Timer();
        //创建一个TimerTask实例
        MyTimerTask myTimerTask = new MyTimerTask("NO.1");
        //timer通过定时定频率调用myTimerTask的业务逻辑
        //开始两秒后执行，每隔三秒执行一次
        timer.schedule(myTimerTask,2000L,3000L);
        /**
         * 获取当前时间，并设置成距离当前时间三秒后的时间，执行
         */
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(calendar.getTime()));
        //加三秒
        calendar.add(Calendar.SECOND,3);
        //----schedule用法
        //1.在时间等于或者超过time的时候执行且执行一次task
        timer.schedule(myTimerTask,calendar.getTime());

        //2.时间等于或者超过time是首次执行，之后每隔2000毫秒重复执行 按照 上一次 实际执行完成的时间点 进行计算
        timer.schedule(myTimerTask,calendar.getTime(),2000L);

        //3.等待delay毫秒后首次执行，一次
        timer.schedule(myTimerTask,2000L);

        //4.等待delay好买哦首次执行，之后每隔period毫秒重复执行task 按 照 上一次 实际执行完成的时间点 进行计算
        timer.schedule(myTimerTask,2000L,3000L);

        //----scheduleAtFixedRate 按照上一次开始的时间点 进行计算 需要考虑同步
        //只有两种用法
        timer.scheduleAtFixedRate(myTimerTask,calendar.getTime(),2000L);
        timer.scheduleAtFixedRate(myTimerTask,2000L,3000L);
        //有停止方法的cancle()
    }


}
