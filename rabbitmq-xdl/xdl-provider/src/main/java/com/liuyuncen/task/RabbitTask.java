package com.liuyuncen.task;

import com.liuyuncen.config.RabbitConfig;
import com.liuyuncen.service.RabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.task
 * @author: Xiang想
 * @createTime: 2022-08-31  17:46
 * @description: TODO
 * @version: 1.0
 */
@Component
public class RabbitTask {

    @Autowired
    RabbitService rabbitService;


    @Scheduled(cron = "*/3 * * * * ?")
    public void task(){
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String msg = time + " 我是 xdl 消息";
        System.out.println(msg);
        rabbitService.sendMessage(RabbitConfig.exchange_name,RabbitConfig.routing_key,msg);
    }
}
