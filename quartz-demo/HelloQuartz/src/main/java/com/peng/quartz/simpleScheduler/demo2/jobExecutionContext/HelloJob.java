package com.peng.quartz.simpleScheduler.demo2.jobExecutionContext;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //打印时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current exec time is : "+sdf.format(date));
        //执行具体的业务逻辑
        System.out.println("Hello World Quartz!");

        //从JobExecutionContext中获取jobDetail，就能获取他的相关信息
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        System.out.println("JobDetail 信息:"+jobDetail.getKey().getName() + jobDetail.getKey().getGroup() + jobDetail.getKey().getClass());
        //从jobDetail中获取DataMap数据
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        System.out.println("JobDetail jobDataMap:"+jobDataMap.getString("message") + jobDataMap.getFloat("floatValue"));

        //从JobExecutionContext中获取Trigger，就能获取他的相关信息
        Trigger trigger = jobExecutionContext.getTrigger();
        System.out.println("Trigger 信息:"+trigger.getKey().getName() + trigger.getKey().getGroup() + trigger.getKey().getClass());
        //从Trigger中获取DataMap数据
        JobDataMap triggerJobDataMap = trigger.getJobDataMap();
        System.out.println("Trigger jobDataMap:"+triggerJobDataMap.getString("message") + triggerJobDataMap.getDouble("doubuleValue"));

        //上面是单独获取dataMap的数据，还可以一起获取，如果key值相同，以trigger中的为主
        //获取合并的JobDataMap
        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        System.out.println("合并的jobDataMap:"+mergedJobDataMap.getString("message") + mergedJobDataMap.getString("floatValue") + mergedJobDataMap.getString("doubuleValue"));

    }
}
