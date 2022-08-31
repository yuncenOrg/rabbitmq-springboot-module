package com.liuyuncen.task;

import com.liuyuncen.controller.TTLConroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.task
 * @author: Xiang想
 * @createTime: 2022-08-31  16:34
 * @description: TODO
 * @version: 1.0
 */
@Component
public class AutoTask {
    @Autowired
    private TTLConroller ttlConroller;

    @Scheduled(cron = "*/3 * * * * ?")
    public void task(){
        String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ttlConroller.sendTtl();
        System.out.println("自动执行 每隔三秒执行1次" + nowTime);
    }
}
