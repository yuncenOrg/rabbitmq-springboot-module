package com.liuyuncen.receive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.receive
 * @author: Xiang想
 * @createTime: 2022-09-01  18:52
 * @description: TODO
 * @version: 1.0
 */
@Component
public class PluginDelayReceive {

    @RabbitHandler
    @RabbitListener(queues = "plugin-delay-queue")
    public void delayProcess(String message){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("【插件延迟队列】【" + sdf.format(new Date()) + "】收到消息：" + message);
    }
}
