package com.ken.other.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通过在配置类注解@EnableSchedule开启对定时任务的支持，然后在要执行计划任务的方法上注解@Schedule，声明这个一个定时任务。
 * <p>
 * Spring通过@Schedule支持多种类型的计划任务，包含 cron、fixDelay、fixRate等
 */
@Service
public class ScheduleTaskService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /*
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
       System.out.println("没隔五秒执行一次 " + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 31 11 ? * *")
    public void fixTimeExecution() {
      System.out.println("在指定时间 " + dateFormat.format(new Date()) + " 执行");
    }
    */
}
