package com.liuyuncen.task;

import com.liuyuncen.controller.DelayController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.task
 * @author: Xiang想
 * @createTime: 2022-09-01  14:44
 * @description: TODO
 * @version: 1.0
 */
@Component
public class DelayTask {

    @Autowired
    DelayController delayController;

    @Scheduled(cron = "*/1 * * * * ?")
    public void task(){
        Random random = new Random();
        int i = random.nextInt(10)+1;
        String millTimes = String.valueOf(i * 1000);
        delayController.delayByMessage(millTimes);
    }

}
