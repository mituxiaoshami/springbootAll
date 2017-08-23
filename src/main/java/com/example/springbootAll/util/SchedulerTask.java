package com.example.springbootAll.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: sea
 * @Description: 定时任务
 * @Date: 13:57 2017/8/16
 */
//@Component
public class SchedulerTask {

    Logger logger = LoggerFactory.getLogger(SchedulerTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private int count = 0;

    //@Scheduled 参数可以接受两种定时的设置，一种是我们常用的cron=”*/6 * * * * ?”,一种是 fixedRate = 6000，两种都表示每隔六秒打印一下内容。
    @Scheduled(cron = "*/6 * * * * ?")
    public void process() {
        logger.info(" scheduler task runing:"+(count++));
    }

    /**
     * @Scheduled(fixedRate = 6000) ：上一次开始执行时间点之后6秒再执行；
     * @Scheduled(fixedDelay = 6000) ：上一次执行完毕时间点之后6秒再执行；
     * @Scheduled(initialDelay=1000, fixedRate=6000) ：第一次延迟1秒后执行，之后按fixedRate的规则每6秒执行一次。
     */
    @Scheduled(fixedRate = 6000)
    public void reportCurrentTime() {
        logger.info("现在时间: "+dateFormat.format(new Date()));
    }

    //第一次延迟一秒执行，当执行完后3秒再执行
    @Scheduled(initialDelay = 1000,fixedDelay = 3000)
    public void timerInit() {
        logger.info("init: "+dateFormat.format(new Date()));
    }

    //每天23点27分50秒执行
    @Scheduled(cron = "50 27 23 * * ?")
    public void timerCron() {
        logger.info("current time :"+dateFormat.format(new Date()));
    }
}
