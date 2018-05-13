package com.peng.quartz.cronScheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        //打印当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Start exec time is : "+sdf.format(date));

        //创建一个JobDetail实例，将该实例与HelloJob class绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("myJob", "group1").build();

        //每秒钟触发一次任务-使用cronTrigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("mytrigger", "group1")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ? *"))
                .build();
        //,或 -至 *每 /每隔 #第几 L最后 W工作日 15W距离15号最近的工作日
        // * * * * * ? * 每秒执行一次
        //LW最后一个工作日 周字段英文字母不区分大小写
        //1.2018年内每天10点15分触发一次 0 15 10 * * ? 2018
        //2.每天的14点整值14点59分55秒，以及18点整至18点59分55秒，每5秒触发一次 0/5 * 14,18 * * ? *
        //3.每月周一至周五的10点15分触发一次 0 15 10 * * 2-6 *
        //4.每月最后一天的10点15分触发一次 0 15 10 L * ? *
        //5.每月第三个周五的10点15分触发一次0 15 10 ? * 6#3 *

        //创建Schedule调度器实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        //启动scheduler
        scheduler.start();

        //scheduler调度器绑定jobdetail和trigger 触发器
        System.out.println("scheduled time is :"+sdf.format(scheduler.scheduleJob(jobDetail,trigger)));
        //scheduler执行两秒后挂起
        Thread.sleep(2000L);
        //挂起
        scheduler.standby();

        //scheduler挂起三秒后继续执行
        Thread.sleep(3000L);
        scheduler.start();

        Thread.sleep(2000L);
        //传入参数是true表是等待所有正在执行的job执行完毕之后，在关闭scheduler
        //传入false表是立即关闭
        scheduler.shutdown(true);
    }
}
